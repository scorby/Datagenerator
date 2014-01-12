package schemas;

/*
 * Copyright 2013, Martin Kleehaus
 * Technical University of Munich (TUM)
 * Professorship for Database Systems and Applications
 */

import datagenerator.DataGenerator;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.FmtDate;
import org.supercsv.cellprocessor.FmtNumber;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.io.ICsvMapWriter;
import org.supercsv.prefs.CsvPreference;

/**
 *
 * @author Martin Kleehaus
 */
public abstract class Schemas {
    
    private static final String path = "./csvSources/schema";
    private Map<String, Object> tempMap = new HashMap<>();
    public ICsvMapWriter mapWriter = null;
    public File fHeader;
    private String fileName;
    private Integer primaryKey = 1;
    private Integer foreignKey;
    private final Map<String, Schemas> subSchemas = new HashMap<>();
    private final Map<String, Schemas> parentSchemas = new HashMap<>();
    private Integer rowCount = 1;
    private Integer currentRow = 0;
    private Integer countFiles = 1;
    public boolean headerWritten = false;
    private boolean bottomTop = false;
    private final List<String> headerName = new ArrayList<>();
    private final List<String> headerType = new ArrayList<>();
    private final List<String> headerFormat = new ArrayList<>();
    private final List<String> headerOptional = new ArrayList<>();
    
//    public List<SchemaInterface> getSchemas() {
//        
//        ArrayList<SchemaInterface> schemaList = new ArrayList<>();
//
//        schemaList.add(new SchemaVAITM());
//        schemaList.add(new SchemaVAHDR());
//        return schemaList;
//    }
    
    public Schemas() {}
    
    public <T> Object getLastMapValue(T item, String key, int probability) {
        DataGenerator dg = DataGenerator.getInstance();
       
        if (!this.tempMap.isEmpty()) {
            return dg.getItem(this.tempMap.put(key,item), probability,item);
        }
        
        return item;
    }
    
    public void setMap (Map<String, Object> aMap) {
        if (this.tempMap.size() > 0) {
            this.tempMap.clear();
        }
        this.tempMap = aMap;
    }
    
    public <T> Object getMapItem(String key) {
        if(!this.tempMap.isEmpty() && this.tempMap.containsKey(key)) {
            return this.tempMap.get(key);
        } else
            return null;
    }
    
    public Integer getForeignKey() {
        return this.foreignKey;
    }
    
    public void setForeignKey(Integer key) {
        this.foreignKey = key;
    }
    
    public Integer getPrimaryKey () {
        if (this.primaryKey == null) {
            this.setPrimaryKey(1);
            return this.getPrimaryKey();
        }
        return this.primaryKey;
    }
    
    public Integer getNextPrimaryKey() {
        if (this.primaryKey == null) {
            this.setPrimaryKey(1);
            return this.getPrimaryKey();
        }
        return this.primaryKey++;
    }
    
    public void setPrimaryKey (Integer key) {
        this.primaryKey = key;
    }
    
    public void setParentSchema (String key, Schemas parent) {
        this.parentSchemas.put(key, parent);
    }
    
    public void setSubschema (String key, Schemas subSchema) {
        this.subSchemas.put(key, subSchema);
    }
    
    public Map<String, Schemas> getParentSchemas () {
        return this.parentSchemas;
    }
    
    public Schemas getParentSchema (String key) {
        return this.parentSchemas.get(key);
    }
    
    public Map<String, Schemas> getSubschemas () {
        return this.subSchemas;
    }
    
    public Schemas getSubschema (String key) {
        return this.subSchemas.get(key);
    }
    
    public Integer getRandomRowCount() {
        DataGenerator dg = DataGenerator.getInstance();
        this.rowCount = dg.getNumberBetween(0, 10);
        return this.rowCount;
    }
    
    public Integer getRowCount () {
        return this.rowCount;
    }
    
    public void setRowCount (Integer rowCount) {
        this.rowCount = rowCount;
    }
    
    public boolean getBottomTop () {
        return this.bottomTop;
    }
    
    public void setBottomTop (boolean bottomTop) {
        this.bottomTop = bottomTop;
    }
    
    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public void setCurrentRow() {
        this.currentRow++;
    }
    
    public void setCurrentRow(int i) {
        this.currentRow = i;
    }
    
    public Integer getCurrentRow() {
        return this.currentRow;
    }
    
    public Integer getNextFileNumber() {
        return countFiles++;
    }
    
    public void setNextFileNumber(Integer i) {
        this.countFiles = i;
    }
    
    public void readCSVFile(String filename) throws Exception{

        ICsvListReader listReader = null;
        //List<String> csvContent = new ArrayList<>();
        String[] str;

        try {
            listReader = new CsvListReader (new FileReader(path + "/" + filename + ".csv"),CsvPreference.STANDARD_PREFERENCE);

            listReader.getHeader(true);

            while( (listReader.read()) != null ) {
                str = listReader.get(1).split(";");
                this.headerName.add(str[0]);
                if(str.length > 1)
                    this.headerType.add(str[1]);
                else
                    this.headerType.add(null);
                if(str.length > 2)
                    this.headerOptional.add(str[2]);
                else
                    this.headerOptional.add(null);
                if(str.length > 3)
                    this.headerFormat.add(str[3]);
                else
                    this.headerFormat.add(null);

                //csvContent.add(listReader.getUntokenizedRow()); 
            }

        }
        finally {
            if( listReader != null ) {
                listReader.close();
            }
        }

    }
    
      
    public String[] getHeader() throws Exception{
        if(this.headerName.isEmpty()) {
            this.readCSVFile(this.getName());
        }
        return this.headerName.toArray(new String[this.headerName.size()]);
    }
    
    public CellProcessor[] getProcessors() throws Exception {
        List<Object> processorList = new ArrayList<>();
        int i = 0;
        CellProcessorAdaptor format = null;
        
        if(this.headerType.isEmpty()) {
            this.readCSVFile(this.getName());
        }

        for(String type : this.headerType) {
            switch(type.toLowerCase().trim()){
                case "date":
                    format = new FmtDate(this.headerFormat.get(i).toString());
                    break;
                case "time":
                    format = new FmtDate(this.headerFormat.get(i).toString());
                    break;
                case "number":
                    format = new FmtNumber(this.headerFormat.get(i).toString());
                    break;
                case "primary":
                    format = new UniqueHashCode();
                    break;
                default:
                    format = new Optional();
                    break;
            }
            if(this.headerOptional.get(i).toString().toLowerCase().equals("y") || 
               this.headerOptional.get(i).toString().toLowerCase().equals("yes") ||
               this.headerOptional.get(i).toString().equals("1") || this.headerOptional.isEmpty()) {
                format = new Optional(format);

            }
            processorList.add(format);
            i++;
        }
        
        final CellProcessor[] processor = processorList.toArray(new CellProcessor[processorList.size()]);
        
        return processor;
    }
    
    public abstract void setUniqueNumber();
    public abstract Map<String, Object> getData() throws Exception;
    public abstract String getName();
    public abstract Integer getDefaultPrimaryKey();

}

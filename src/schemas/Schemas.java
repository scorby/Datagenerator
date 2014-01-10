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
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.io.ICsvListWriter;
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
    private List<Schemas> subSchemas = new ArrayList<>();
    private List<Schemas> parentSchemas = new ArrayList<>();
    private Integer rowCount = 1;
    private Integer currentRow = 0;
    private Integer countFiles = 1;
    public boolean headerWritten = false;
    private boolean bottomTop = false;
    private List<String> headerName = new ArrayList<String>();
    private List<String> headerType = new ArrayList<String>();
    private List<String> headerFormat = new ArrayList<String>();
    
    
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
        if(!this.tempMap.isEmpty()) {
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
    
    public void setParentSchema (Schemas parent) {
        this.parentSchemas.add(parent);
    }
    
    public void setSubschema (Schemas subSchema) {
        this.subSchemas.add(subSchema);
    }
    
    public List<Schemas> getParentSchemas () {
        return this.parentSchemas;
    }
    
    public Schemas getParentSchema (int index) {
        return this.parentSchemas.get(index);
    }
    
    public List<Schemas> getSubschemas () {
        return this.subSchemas;
    }
    
    public Schemas getSubschema (int index) {
        return this.subSchemas.get(index);
    }
    
    public Integer getRowCount () {
        return this.rowCount;
    }
    
    public Integer getRowCount (int propability) {
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
                this.headerType.add(str[1]);
                this.headerFormat.add(str[2]);
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
    
    public abstract void setUniqueNumber();
    public abstract Map<String, Object> getData() throws Exception;
    public abstract CellProcessor[] getProcessors();
    public abstract String getName();
    public abstract Integer getDefaultPrimaryKey();

}

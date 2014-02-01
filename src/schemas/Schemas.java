package schemas;

/*
 * Copyright 2013, Martin Kleehaus
 * Technical University of Munich (TUM)
 * Professorship for Database Systems and Applications
 */

import datagenerator.DataGenerator;
import java.io.File;
import java.io.FileReader;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
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
    private boolean nextForeignKeyCheck = false;
    private final Map<String, List<Object>> MasterData = new HashMap<>();
    private final Map<String, List<Object>> MetaValues = new HashMap<>();
    private Double netvalue = 0d;
    private Double grossweight = 0d;
    private Date baseDate = new Date();
    
//    public List<SchemaInterface> getSchemas() {
//        
//        ArrayList<SchemaInterface> schemaList = new ArrayList<>();
//
//        schemaList.add(new SchemaVAITM());
//        schemaList.add(new SchemaVAHDR());
//        return schemaList;
//    }
    
    public Schemas() {}
    
    public <T> Object getLastMapValue(T item, String key, double probability) {
        DataGenerator dg = DataGenerator.getInstance();
       
        if (!this.tempMap.isEmpty()) {
            return dg.getItem(this.tempMap.put(key,item), probability,item);
        }
        
        return item;
    }
    
    public Object getLastMapValue(String key) {
        if(this.tempMap.containsKey(key)) {
            return this.tempMap.get(key);
        }
        return null;
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
        this.setCurrentRow(0);
        return this.rowCount;
    }
    
    /**
     * Get Number of rows which are associated to this schema
     * 
     * @return number of rows
     */
    public Integer getRowCount () {
        return this.rowCount;
    }
    
    public void setRowCount (Integer rowCount) {
        this.rowCount = rowCount;
    }

     /**
     * If this function is true, the schema is build by its child
     * 
     * @return true or false
     */
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
    
     /**
     * Get the current number of rows in this schema
     * 
     * @return number of rows
     */
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
            listReader = new CsvListReader (new FileReader(path + "/" + filename + ".csv"),CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);

            String[] header = listReader.getHeader(true);
            for (String key : header){
                this.MetaValues.put(key, new ArrayList<>());
            }
            
            List<String> list = new ArrayList<>();
            int n;
            while ((list = listReader.read()) != null){
                n = 1;
                for (String key : header){
                    this.MetaValues.get(key).add(listReader.get(n++));
                }
            }

        }
        finally {
            if( listReader != null ) {
                listReader.close();
            }
        }

    }
    
    public CellProcessor[] getProcessors() throws Exception {
        List<Object> processorList = new ArrayList<>();
        int i = 0;
        CellProcessorAdaptor format = null;
        
        if(this.MetaValues.get("type").isEmpty()) {
            this.readCSVFile(this.getName());
        }
        List<Object> typeList = this.MetaValues.get("type");
        List<Object> formatList = this.MetaValues.get("format");
        List<Object> optionalList = this.MetaValues.get("optional");
        
        for(Object type : typeList) {
            format = new Optional();
            if(type != null) {
                switch(type.toString().toLowerCase().trim()){
                    case "date":
                        format = new FmtDate(formatList.get(i).toString());
                        break;
                    case "time":
                        format = new FmtDate(formatList.get(i).toString());
                        break;
                    case "number":
                        format = new FmtNumber(formatList.get(i).toString());
                        break;
                    case "primary":
                        format = new UniqueHashCode();
                        break;
                    default:
                        format = new Optional();
                        break;
                }
            }
            if(optionalList.get(i).toString().toLowerCase().equals("y") || 
               optionalList.get(i).toString().toLowerCase().equals("yes") ||
               optionalList.get(i).toString().equals("1") || optionalList.isEmpty()) {
                format = new Optional(format);

            }
            processorList.add(format);
            i++;
        }
        
        
        final CellProcessor[] processor = processorList.toArray(new CellProcessor[processorList.size()]);
        
        return processor;
 
    }
    
    public String[] getMetaValues(String key) throws Exception{
        if(!this.MetaValues.containsKey(key)) {
            this.readCSVFile(this.getName());
        }
        
        return this.MetaValues.get(key).toArray(new String[this.MetaValues.get(key).size()]);
    }
        
    public Object getMasterData(String key, int pos, int row, Object md) throws Exception{
        DataGenerator dg = DataGenerator.getInstance();
        if(row > 0) {
            Double sfactor = this.parseDouble(this.MetaValues.get("sfactor").get(pos).toString());
            Integer rowCount = 0;
            if(this.getMasterData(key) != null) {
                rowCount = this.getMasterData(key).size();
            } 

            if (rowCount / row < sfactor) {
                this.setMasterData(key, md);
            }
        } else {
            this.setMasterData(key, md);
        }

        return this.getLastMapValue(this.MasterData.get(key).get(dg.getNumberUpTo(this.MasterData.get(key).size())), key, this.parseDouble(this.MetaValues.get("change").get(pos).toString()));

    }
    
    public List<Object> getMasterData(String key) {
        if(this.MasterData.containsKey(key)) {
            return this.MasterData.get(key);
        }
        return null;
    }
    
    public void setMasterData(String key, Object value) {
        if(!this.MasterData.containsKey(key)) {
            List<Object> list = new ArrayList<>();
            list.add(value);
            this.MasterData.put(key, list);
        } else {
            this.MasterData.get(key).add(value);
        }
    }
    
    public void dropMasterData(String key) {
        if(this.MasterData.containsKey(key)) {
            this.MasterData.remove(key);
        }
    }
    
    public double parseDouble(String input) throws NullPointerException, ParseException{
        if(input == null){
          throw new NullPointerException();
        }

        input = input.trim();

        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.FRANCE);
        ParsePosition parsePosition = new ParsePosition(0);
        Number number = numberFormat.parse(input, parsePosition);

        if(parsePosition.getIndex() != input.length()){
          throw new ParseException("Invalid input", parsePosition.getIndex());
        }

        return number.doubleValue();
   }
    
   public String fillString (String value, String fillChar, int count) {
        String s = new String();
        for(int i = 1;i<=(count-value.length());i++) {
            s = s + fillChar;
        }
        return s + value;
   }
   
   public Double getNetvalue() {
       return this.netvalue;
   }
   
   public void setNetvalue(Double value, boolean preserve) {
       if(preserve) {
           this.netvalue = value;
       } else {
           this.netvalue = this.netvalue + value;
       }
   }
   
   public Double getGrossWeight() {
       return this.grossweight;
   }
   
   public void setGrossWeight(Double value, boolean preserve) {
       if(preserve) {
           this.grossweight = value;
       } else {
           this.grossweight = this.grossweight + value;
       }
   }

    public Date getBaseDate() {
        return baseDate;
    }

    public void setBaseDate(Date baseDate) {
        this.baseDate = baseDate;
    }

    public boolean isNextForeignKeyCheck() {
        return nextForeignKeyCheck;
    }

    public void setNextForeignKeyCheck(boolean nextForeignKeyCheck) {
        this.nextForeignKeyCheck = nextForeignKeyCheck;
    }
            
    
    public abstract void setUniqueNumber();
    public abstract Map<String, Object> getData() throws Exception;
    public abstract String getName();
    public abstract Integer getDefaultPrimaryKey();

}

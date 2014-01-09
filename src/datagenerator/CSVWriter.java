package datagenerator;

/*
 * Copyright 2013, Martin Kleehaus
 * Technical University of Munich (TUM)
 * Professorship for Database Systems and Applications
 */

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.supercsv.io.CsvListWriter;
import org.supercsv.prefs.CsvPreference;
import java.util.Random;
import schemas.Schemas;

/**
 *
 * @author mkleehaus
 */
public class CSVWriter {
    
    private final String path;
    private final Integer maxSize;
    private final Integer maxRows;
    private final Integer maxFileSize;
    private final Integer splitFileAtSize;
    private List<Schemas> schemas = new ArrayList<>();
    private Map<String, List<Long>> schemaMap = new HashMap<>();
    private Integer currentRows = 0;
    private Long tempSize = 0l;
    private Long overallSize = 0l;
    private static final Random random = new Random(93285);
    private List<Object> csvList = new ArrayList<>();
    private boolean isDone = false;

    public CSVWriter(Schemas schema, String path, Integer maxSize, Integer maxFileSize, Integer splitFileAtSize, Integer maxRows) {
        this.path = path;
        this.maxSize = maxSize;
        this.maxRows = maxRows;
        this.maxFileSize = maxFileSize;
        this.splitFileAtSize = splitFileAtSize;
       
    }
    
    public void createSchemas(Schemas schema) throws Exception {      
        try {
            do
            {
                this.writeSchema(schema);
                if(!this.checkConstraints(schema)) {
                    break;
                }
            }while(true);
        }finally {
            this.close();
            this.isDone = true;
        }
    }
    
    private void writeSchema(Schemas schema) throws Exception {
        this.createFile(schema);
        this.addSchema(schema);
        this.addSchemaMap(schema);
        this.writeData(schema);
    }
    
    private void createFile (Schemas schema) throws Exception {
        String pathName;
        String fileName;
        pathName = this.path + "/" + schema.getName() + ".csv";
        
        if(schema.fHeader != null) {
            if(this.splitFileAtSize > 0 && schema.fHeader.length() >= this.splitFileAtSize*1000000) {
                fileName = schema.getName() + "_" + schema.getNextFileNumber() + ".csv";
                pathName = this.path + "/" + fileName;
                this.tempSize = this.tempSize + schema.fHeader.length();
                this.close(schema);
                schema.listWriter = new CsvListWriter(new FileWriter(pathName),CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);
                schema.fHeader = new File(pathName);
                schema.headerWritten = false;
                schema.setCurrentRow(0);
                schema.setFileName(fileName);
                this.writeHeader(schema);
            }  
        } else {
            schema.listWriter = new CsvListWriter(new FileWriter(pathName),CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);
            schema.fHeader = new File(pathName);
            schema.headerWritten = false;
            schema.setFileName(schema.getName() + ".csv");
            this.writeHeader(schema);
        }
    }
    
    private void writeHeader (Schemas schema) throws Exception {

        //write Header
        if(!schema.headerWritten) {
            schema.listWriter.writeHeader(schema.getHeader());
            schema.headerWritten = true;
        }
        
    }
    
    private void writeData(Schemas schema) throws Exception {

        // write the data
        Integer rowCount = schema.getRowCount();
        for(int i = 1;i<=rowCount;i++) 
        {
            if(!this.checkConstraints(schema)) {
                return;
            }
            this.csvList = schema.getData();
            if(this.csvList != null) { 
                schema.listWriter.write(this.csvList, schema.getProcessors());
                schema.setCurrentRow();
                this.setOverallSize();
                this.currentRows++;
            }
            this.addSchemaMap(schema);
            if(schema.getSubschemas().size() > 0 && !schema.getBottomTop()) {
                for (Schemas s:schema.getSubschemas()) {
                    this.writeSchema(s);
                }

            }

        }
        
        if(schema.getParentSchemas().size() > 0 && rowCount > 0) {
            for(Schemas s:schema.getParentSchemas()) {
                if(s.getBottomTop()) {
                    this.writeSchema(s);
                }
                
            }
        }
        
    }
    
    private void close(Schemas schema) throws Exception {
        if(schema.listWriter != null) {
            schema.listWriter.close();
        }
    }
    
    private void close() throws Exception {
        for(Schemas s:this.schemas) {
            if(s.listWriter != null) {
                s.listWriter.close();
            }
        }
    }
    
    
    private boolean checkConstraints(Schemas schema) throws Exception{
        
        if(this.maxRows > 0 && this.currentRows >= this.maxRows) {
            return false;
        }
        if(this.maxFileSize > 0 && (this.maxFileSize*1000000) <= schema.fHeader.length()) {
            return false;
        }
        
        if(this.maxSize > 0) { 
            if(this.maxSize*1000000 <= this.overallSize) {
                return false;
            }  
        }
        
        if(this.splitFileAtSize > 0 && ((this.splitFileAtSize*1000000) <= schema.fHeader.length())) {
            this.createFile(schema);
        }
        
        return true;
    }
    
    
    
    public String getPath() {
        return path;
    }

    public Integer getMaxSize() {
        return maxSize;
    }

    public Integer getMaxRows() {
        return maxRows;
    }

    private void addSchema(Schemas schema) throws Exception {
        if(!this.schemas.contains(schema)) {
            this.schemas.add(schema);
        }
    }
    
    public int getCurrentRows() {
        return this.currentRows;
    }
    
    public long getOverallSize() {
        return this.overallSize;
    }
    
    public List<Schemas> getSchemas() {
        return this.schemas;
    }
    public boolean isDone() {
        return this.isDone;
    }
    
    private void setOverallSize() {
        Long currentSize = 0l;
        for(Schemas s : this.schemas) {
            currentSize = currentSize + s.fHeader.length();
        }
        this.overallSize = currentSize + this.tempSize;
    }

    private void addSchemaMap(Schemas schema) {
        List<Long> list = new ArrayList<>();
        list.add((long)schema.getCurrentRow());
        list.add(schema.fHeader.length());
        this.schemaMap.put(schema.getFileName(), list); 
    }
    
    public Map getSchemaMap() {
        return this.schemaMap;
    }
}
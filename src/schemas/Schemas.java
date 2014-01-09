package schemas;

/*
 * Copyright 2013, Martin Kleehaus
 * Technical University of Munich (TUM)
 * Professorship for Database Systems and Applications
 */

import datagenerator.DataGenerator;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.ICsvListWriter;

/**
 *
 * @author Martin Kleehaus
 */
public abstract class Schemas {
    
    private List<Object> tempList = new ArrayList<>();
    public ICsvListWriter listWriter = null;
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
    
    
//    public List<SchemaInterface> getSchemas() {
//        
//        ArrayList<SchemaInterface> schemaList = new ArrayList<>();
//
//        schemaList.add(new SchemaVAITM());
//        schemaList.add(new SchemaVAHDR());
//        return schemaList;
//    }
    
    public Schemas() {
        
    }

    
    public <T> Object getLastListValue(T item, int pos, int probability) {
        DataGenerator dg = DataGenerator.getInstance();
       
        if (!this.tempList.isEmpty()) {
            return dg.getItem(this.tempList.get(pos), probability,item);
        }
        return item;
    }
    
    public void setList (List<Object> list) {
        if (this.tempList.size() > 0) {
            this.tempList = null;
        }
        this.tempList = list;
    }
    
    public List<Object> getList() {
        return this.tempList;
    }
    
    public <T> Object getListItem(int pos) {
        if(this.tempList.size() > pos) {
            return this.tempList.get(pos);
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
    
    public abstract void setUniqueNumber();
    public abstract String[] getHeader();
    public abstract List<Object> getData() throws Exception;
    public abstract CellProcessor[] getProcessors();
    public abstract String getName();
    public abstract Integer getDefaultPrimaryKey();

}

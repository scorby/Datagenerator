package schemas;

/*
 * Copyright 2013, Martin Kleehaus
 * Technical University of Munich (TUM)
 * Professorship for Database Systems and Applications
 */

import datagenerator.DataGenerator;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Martin Kleehaus
 */
public class SchemaVCHDR extends Schemas  {
    
    private Integer primaryKey = 1405000;
    
    @Override
    public void setUniqueNumber() {
        DataGenerator dg = DataGenerator.getInstance();
        dg.setUniqueNumber(this.primaryKey);
    }
    
    @Override
    public Map<String, Object> getData() throws Exception{
        if(this.getMapItem("DELIV_NUMB") == this.getSubschema("VCITM").getMapItem("DELIV_NUMB")) {
            return null;
        }
        DataGenerator dg = DataGenerator.getInstance();
        
        this.setPrimaryKey(Integer.parseInt(this.getSubschema("VCITM").getMapItem("DELIV_NUMB").toString()));
        
        int cnt = 0;
        
        
        Map<String, Object> columns = new HashMap<>();  
        columns.put(this.getHeader()[cnt++], this.getSubschema("VCITM").getMapItem("DELIV_NUMB"));
        columns.put(this.getHeader()[cnt++], this.getSubschema("VCITM").getMapItem("ACT_GI_DTE"));
        columns.put(this.getHeader()[cnt++], this.getSubschema("VCITM").getMapItem("BILLTOPRTY"));
        columns.put(this.getHeader()[cnt++], this.getSubschema("VCITM").getMapItem("BILL_BLOCK"));
        columns.put(this.getHeader()[cnt++], this.getSubschema("VCITM").getMapItem("COMP_CODE"));
        columns.put(this.getHeader()[cnt++], this.getLastMapValue(dg.getDateBetween(dg.getDate(2013, 1, 1), dg.getDate(2013,12,31)),"CREATEDON",80));
        columns.put(this.getHeader()[cnt++], this.getSubschema("VCITM").getMapItem("CREDITOR"));
        columns.put(this.getHeader()[cnt++], this.getSubschema("VCITM").getMapItem("CUST_GROUP"));
        columns.put(this.getHeader()[cnt++], this.getSubschema("VCITM").getMapItem("DEL_BLOCK"));
        columns.put(this.getHeader()[cnt++], dg.getCurrency(1, 2000));
        columns.put(this.getHeader()[cnt++], this.getSubschema("VCITM").getRowCount());
        columns.put(this.getHeader()[cnt++], this.getSubschema("VCITM").getMapItem("DEL_TYPE"));
        columns.put(this.getHeader()[cnt++], this.getSubschema("VCITM").getMapItem("DEL_WA_DH"));
        columns.put(this.getHeader()[cnt++], this.getSubschema("VCITM").getMapItem("DOC_CATEG"));
        columns.put(this.getHeader()[cnt++], "K4");
        columns.put(this.getHeader()[cnt++], this.getSubschema("VCITM").getMapItem("FORWAGENT"));
        columns.put(this.getHeader()[cnt++], this.getSubschema("VCITM").getMapItem("GI_DATE"));
        columns.put(this.getHeader()[cnt++], this.getSubschema("VCITM").getMapItem("GN_R3_SSY"));
        columns.put(this.getHeader()[cnt++], this.getSubschema("VCITM").getMapItem("INCOTERMS"));
        columns.put(this.getHeader()[cnt++], this.getSubschema("VCITM").getMapItem("INCOTERMS2"));
        columns.put(this.getHeader()[cnt++], this.getSubschema("VCITM").getMapItem("LOAD_PT"));
        columns.put(this.getHeader()[cnt++], this.getSubschema("VCITM").getMapItem("NET_WGT_DL"));
        columns.put(this.getHeader()[cnt++], dg.getNumberBetween(1, 100));
        columns.put(this.getHeader()[cnt++], this.getSubschema("VCITM").getMapItem("PAYER"));
        columns.put(this.getHeader()[cnt++], this.getSubschema("VCITM").getMapItem("RECORDMODE"));
        columns.put(this.getHeader()[cnt++], this.getSubschema("VCITM").getMapItem("ROUTE"));
        columns.put(this.getHeader()[cnt++], this.getSubschema("VCITM").getMapItem("SALESEMPLY"));
        columns.put(this.getHeader()[cnt++], this.getSubschema("VCITM").getMapItem("SALESORG"));
        columns.put(this.getHeader()[cnt++], this.getSubschema("VCITM").getMapItem("SALES_DIST"));
        columns.put(this.getHeader()[cnt++], this.getSubschema("VCITM").getMapItem("SHIP_TO"));
        columns.put(this.getHeader()[cnt++], this.getSubschema("VCITM").getMapItem("SOLD_TO"));
        columns.put(this.getHeader()[cnt++], this.getSubschema("VCITM").getMapItem("UNIT_OF_WT"));
        columns.put(this.getHeader()[cnt++], this.getSubschema("VCITM").getMapItem("UNLOAD_PT"));
        columns.put(this.getHeader()[cnt++], this.getSubschema("VCITM").getMapItem("VOLUMEUNIT"));
        columns.put(this.getHeader()[cnt++], this.getSubschema("VCITM").getMapItem("VOLUME_DL"));
        
        this.setMap(columns);
        
        return columns;
    }
    
    @Override
    public String getName() {
        return this.getClass().getSimpleName().replace("Schema", "");
    }
   
    @Override
    public Integer getPrimaryKey () {
        if (this.primaryKey == null) {
            this.setPrimaryKey(1);
            return this.getPrimaryKey();
        }
        return this.primaryKey;
    }
    
    @Override
    public Integer getNextPrimaryKey() {
        if (this.primaryKey == null) {
            this.setPrimaryKey(1);
            return this.getPrimaryKey();
        }
        return this.primaryKey++;
    }
    
    @Override
    public void setPrimaryKey (Integer key) {
        this.primaryKey = key;
    }
    
    @Override
    public Integer getDefaultPrimaryKey() {
        return 1;
    }
    
}

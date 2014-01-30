package schemas;

/*
 * Copyright 2013, Martin Kleehaus
 * Technical University of Munich (TUM)
 * Professorship for Database Systems and Applications
 */

import datagenerator.DataGenerator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Martin Kleehaus
 */
public class SchemaVDHDR extends Schemas  {
    
    private Integer primaryKey = 1405000;
    
    @Override
    public void setUniqueNumber() {
        DataGenerator dg = DataGenerator.getInstance();
        dg.setUniqueNumber(this.primaryKey);
    }
    
    @Override
    public Map<String, Object> getData() throws Exception{
        if(this.getMapItem("Delivery") == this.getSubschema("VDITM").getMapItem("Delivery")) {
            return null;
        }
        DataGenerator dg = DataGenerator.getInstance();
        String[] rType = {"KZ","M"};
        String[] cGrp = {"102","103","104","106"};
        String[] cGrp2 = {"1","2","3"};
        String[] dCateg = {"B", "C", "G", "H", "L"};
        
        final List<Object> csvList;
        this.setPrimaryKey(Integer.parseInt(this.getSubschema("VDITM").getMapItem("BILL_NUM").toString()));
        
        int cnt = 0;
        
        Map<String, Object> columns = new HashMap<>();   
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VDITM").getMapItem("BILL_NUM"));
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VDITM").getMapItem("BILL_TYPE"));
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VDITM").getMapItem("BILL_CAT"));
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VDITM").getMapItem("BILL_DATE"));
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VDITM").getMapItem("COMP_CODE"));
        columns.put(this.getMetaValues("header")[cnt++],this.getLastMapValue(dg.getDateBetween(dg.getDate(2013, 1, 1), dg.getDate(2013,12,31)),"CREATEDON",80));
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VDITM").getRowCount());
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VDITM").getMapItem("GN_CUSTOM"));
        columns.put(this.getMetaValues("header")[cnt++],"C" + dg.getNumberBetween(1, 9).toString());
        columns.put(this.getMetaValues("header")[cnt++],dg.getItem(1, 90, 9));
        columns.put(this.getMetaValues("header")[cnt++],this.getLastMapValue(dg.getItem(dCateg),"DOC_CATEG",80));
        columns.put(this.getMetaValues("header")[cnt++],this.getLastMapValue(dg.getLocCurrency(),"DOC_CURRCY",80));
        columns.put(this.getMetaValues("header")[cnt++],null);
        columns.put(this.getMetaValues("header")[cnt++],"K4");
        columns.put(this.getMetaValues("header")[cnt++],null);
        columns.put(this.getMetaValues("header")[cnt++],this.getLastMapValue(dg.getLocCurrency(),"DOC_CURRCY",100));
        columns.put(this.getMetaValues("header")[cnt++],this.getLastMapValue(dg.getNumberBetween(10000000,30000000),"PAYER",75));
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VDITM").getMapItem("RATE_TYPE"));
        columns.put(this.getMetaValues("header")[cnt++],null);
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VDITM").getMapItem("SALESEMPLY"));
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VDITM").getMapItem("SALESORG"));
        columns.put(this.getMetaValues("header")[cnt++],"1");
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VDITM").getMapItem("SOLD_TO"));
        columns.put(this.getMetaValues("header")[cnt++],this.getLastMapValue(dg.getLocCurrency(),"DOC_CURRCY",80));
            
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
    
    @Override
    public Integer getRandomRowCount() {
        return 1;
    }
    
}

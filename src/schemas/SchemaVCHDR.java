package schemas;

/*
 * Copyright 2013, Martin Kleehaus
 * Technical University of Munich (TUM)
 * Professorship for Database Systems and Applications
 */

import datagenerator.DataGenerator;
import java.util.Date;
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
        if(!this.getSubschema("VDITM").isNextForeignKeyCheck()) {
            return null;
        }
        
        DataGenerator dg = DataGenerator.getInstance();
        TableData tData = TableData.getInstance();
        
        this.setCurrentRow();
        int cnt = 0;
        
        Map<String, Object> columns = new HashMap<>();   
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VCITM").getMapItem("BillDoc"));
        if(this.getSubschema("VCITM").getMapItem("Ret") == "X") {
            columns.put(this.getMetaValues("header")[cnt++],"YI06"); //BillT
            columns.put(this.getMetaValues("header")[cnt++],"L"); //BlCat
            columns.put(this.getMetaValues("header")[cnt++],"M"); //DocCa
        } else {
            columns.put(this.getMetaValues("header")[cnt++],"YD03"); //BillT
            columns.put(this.getMetaValues("header")[cnt++],"A"); //BlCat
            columns.put(this.getMetaValues("header")[cnt++],"P"); //DocCa
        }
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VCITM").getParentSchema("VAITM").getMapItem("Curr")); //Curr
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VCITM").getParentSchema("VAITM").getParentSchema("VAHDR").getMapItem("SOrg")); //SOrg
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VCITM").getParentSchema("VAITM").getParentSchema("VAHDR").getMapItem("DChl")); //DChl
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VCITM").getParentSchema("VAITM").getParentSchema("VAHDR").getMapItem("PriPr")); //PriPr
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VCITM").getParentSchema("VAITM").getParentSchema("VAHDR").getMapItem("SC")); //SC
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VCITM").getBaseDate()); //Billing Date
        columns.put(this.getMetaValues("header")[cnt++],dg.getItem("B6",0.21,"BC")); //PG
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VCITM").getParentSchema("VAITM").getSubschema("VDITM").getParentSchema("VDHDR").getMapItem("SDst")); //SDst
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VCITM").getParentSchema("VAITM").getSubschema("VDITM").getParentSchema("VDHDR").getMapItem("IncoT")); //IncoT
        columns.put(this.getMetaValues("header")[cnt++],this.getMasterData("PayT", cnt-1, this.getCurrentRow(), "F" + this.fillString(dg.getNumberUpTo(999).toString(), "0", 3))); //PayT
        columns.put(this.getMetaValues("header")[cnt++],this.getMasterData("AAG", cnt-1, this.getCurrentRow(), "0" + dg.getNumberUpTo(9).toString())); //AAG
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VCITM").getMapItem("Ctry")); //DstC
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VCITM").getMapItem("Rg")); //Rg        
        Double sum = 0d;
        for(Object d : this.getSubschema("VCITM").getMasterData("Net value for Header").toArray()) {
            sum = sum + Double.parseDouble(d.toString()); //Net value
        }
        columns.put(this.getMetaValues("header")[cnt++],sum); //Net value
        columns.put(this.getMetaValues("header")[cnt++],tData.getUser(this.parseDouble(this.getMetaValues("sfactor")[cnt-1]), this.parseDouble(this.getMetaValues("change")[cnt-1]), this.getCurrentRow(),this.getLastMapValue(this.getMetaValues("header")[cnt - 1]),Integer.MAX_VALUE)); //created by
        columns.put(this.getMetaValues("header")[cnt++],dg.getTime(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]))); //Time
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VCITM").getBaseDate()); //Created on
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VCITM").getParentSchema("VAITM").getParentSchema("VAHDR").getMapItem("Sold-to pt")); //Payer
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VCITM").getParentSchema("VAITM").getParentSchema("VAHDR").getMapItem("Sold-to pt")); //Sold-to
        columns.put(this.getMetaValues("header")[cnt++],this.getLastMapValue(dg.getItem("GB" + this.fillString(dg.getNumberUpTo(217915751).toString(),"0",9),0.17,null), this.getMetaValues("header")[cnt-1], this.parseDouble(this.getMetaValues("change")[cnt-1]))); //Vat Registration
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VCITM").getMapItem("Dv")); //Dv
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VCITM").getParentSchema("VAITM").getParentSchema("VAHDR").getMapItem("CCAr")); //CCAr
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VCITM").getParentSchema("VAITM").getParentSchema("VAHDR").getMapItem("Cred acct")); //Cred acct
        columns.put(this.getMetaValues("header")[cnt++],Double.parseDouble(columns.get("Net value").toString()) * 0.2); //Tax amount
        columns.put(this.getMetaValues("header")[cnt++],dg.getDate((Date)columns.get("Created on"), this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]))); //TranslDate
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VCITM").getMapItem("Product hierarchy").toString().substring(0, 5)); //Product hierarchy
        
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

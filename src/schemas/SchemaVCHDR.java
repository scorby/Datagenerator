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
        if(!this.getSubschema("VCITM").isNextForeignKeyCheck()) {
            return null;
        }
        
        DataGenerator dg = DataGenerator.getInstance();
        TableData tData = TableData.getInstance();
        
        this.setColumnNumber(0);
        
        Map<String, Object> columns = new HashMap<>();   
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getSubschema("VCITM").getMapItem("BILL_DOC"));
        if(this.getSubschema("VCITM").getMapItem("Ret") == "X") {
            columns.put(this.getMetaValues("header")[this.nextColumn()],"YI06"); //BillT
            columns.put(this.getMetaValues("header")[this.nextColumn()],"L"); //BlCat
            columns.put(this.getMetaValues("header")[this.nextColumn()],"M"); //DocCa
        } else {
            columns.put(this.getMetaValues("header")[this.nextColumn()],"YD03"); //BillT
            columns.put(this.getMetaValues("header")[this.nextColumn()],"A"); //BlCat
            columns.put(this.getMetaValues("header")[this.nextColumn()],"P"); //DocCa
        }
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getSubschema("VCITM").getParentSchema("VAITM").getMapItem("CURR")); //Curr
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getSubschema("VCITM").getParentSchema("VAITM").getParentSchema("VAHDR").getMapItem("SORG")); //SOrg
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getSubschema("VCITM").getParentSchema("VAITM").getParentSchema("VAHDR").getMapItem("DCHL")); //DChl
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getSubschema("VCITM").getParentSchema("VAITM").getParentSchema("VAHDR").getMapItem("PRIPR")); //PriPr
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getSubschema("VCITM").getParentSchema("VAITM").getParentSchema("VAHDR").getMapItem("SC")); //SC
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getSubschema("VCITM").getBaseDate()); //Billing Date
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getItem("B6",0.21,"BC")); //PG
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getSubschema("VCITM").getParentSchema("VAITM").getSubschema("VDITM").getParentSchema("VDHDR").getMapItem("SDST")); //SDst
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getSubschema("VCITM").getParentSchema("VAITM").getSubschema("VDITM").getParentSchema("VDHDR").getMapItem("INCOT")); //IncoT
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getMasterData("PAYT", this.getColumnNumber()-1, this.getCurrentRow(), "F" + this.fillString(dg.getNumberUpTo(999).toString(), "0", 3))); //PayT
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getMasterData("AAG", this.getColumnNumber()-1, this.getCurrentRow(), "0" + dg.getNumberUpTo(9).toString())); //AAG
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getSubschema("VCITM").getMapItem("CTRY")); //DstC
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getSubschema("VCITM").getMapItem("RG")); //Rg        
        Double sum = 0d;
        for(Object d : this.getSubschema("VCITM").getMasterData("Net value for Header").toArray()) {
            sum = sum + Double.parseDouble(d.toString()); //Net value
        }
        columns.put(this.getMetaValues("header")[this.nextColumn()],sum); //Net value
        this.getSubschema("VCITM").dropMasterData("Net value for Header");
                    
        columns.put(this.getMetaValues("header")[this.nextColumn()],tData.getUser(this.parseDouble(this.getMetaValues("sfactor")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("change")[this.getColumnNumber()-1]), this.getCurrentRow(),this.getLastMapValue(this.getMetaValues("header")[this.getColumnNumber()-1]),Integer.MAX_VALUE)); //created by
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getTime(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]))); //Time
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getSubschema("VCITM").getBaseDate()); //Created on
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getSubschema("VCITM").getParentSchema("VAITM").getParentSchema("VAHDR").getMapItem("SOLD_TO_PT")); //Payer
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getSubschema("VCITM").getParentSchema("VAITM").getParentSchema("VAHDR").getMapItem("SOLD_TO_PT")); //Sold-to
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getLastMapValue(dg.getItem("GB" + this.fillString(dg.getNumberUpTo(217915751).toString(),"0",9),0.17,null), this.getMetaValues("header")[this.getColumnNumber()-1], this.parseDouble(this.getMetaValues("change")[this.getColumnNumber()-1]))); //Vat Registration
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getSubschema("VCITM").getMapItem("DV")); //Dv
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getSubschema("VCITM").getParentSchema("VAITM").getParentSchema("VAHDR").getMapItem("CCAR")); //CCAr
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getSubschema("VCITM").getParentSchema("VAITM").getParentSchema("VAHDR").getMapItem("CRED_ACCT")); //Cred acct
        columns.put(this.getMetaValues("header")[this.nextColumn()],Double.parseDouble(columns.get("NET_VALUE").toString()) * 0.2); //Tax amount
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getDate((Date)columns.get("CREATED_ON"), this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]))); //TranslDate
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getSubschema("VCITM").getMapItem("PRODUCT_HIERARCHY").toString().substring(0, 5)); //Product hierarchy
        
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
    public Integer getRandomRowCount() {
        return 1;
    }
    
    @Override
    public Integer getDefaultPrimaryKey() {
        return 1;
    }
    
}

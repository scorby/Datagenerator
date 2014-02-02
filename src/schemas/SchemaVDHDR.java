package schemas;

/*
 * Copyright 2013, Martin Kleehaus
 * Technical University of Munich (TUM)
 * Professorship for Database Systems and Applications
 */

import datagenerator.DataGenerator;
import java.util.Date;
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
//        if(this.getMapItem("Delivery") == this.getSubschema("VDITM").getMapItem("Delivery") || Integer.parseInt(this.getSubschema("VDITM").getMapItem("Delivery").toString()) == 1000) {
//            return null;
//        }
//        if(this.getSubschema("VDITM").getCurrentRow() != this.getSubschema("VDITM").getRowCount()) {
//            return null;
//        }
        if(!this.getSubschema("VDITM").isNextForeignKeyCheck()) {
            return null;
        }
        DataGenerator dg = DataGenerator.getInstance();
        TableData tData = TableData.getInstance();
        
        String[] incoT = {"CFR","EXW"};
        
        Map<String, Double> wbnumber = new HashMap<>();
        wbnumber.put("0", 0.612);
        wbnumber.put("1", 0.67);
        wbnumber.put("2", 0.68);
        wbnumber.put("3", 0.7);
        wbnumber.put("11", 1.0);
        
        int cnt = 0;
        
        Map<String, Object> columns = new HashMap<>();   
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VDITM").getMapItem("Delivery"));
        columns.put(this.getMetaValues("header")[cnt++],tData.getUser(this.parseDouble(this.getMetaValues("sfactor")[cnt-1]), this.parseDouble(this.getMetaValues("change")[cnt-1]), this.getCurrentRow(),this.getLastMapValue(this.getMetaValues("header")[cnt - 1]),Integer.MAX_VALUE)); //created by
        columns.put(this.getMetaValues("header")[cnt++],dg.getTime(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]))); //Time
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VDITM").getBaseDate());
        columns.put(this.getMetaValues("header")[cnt++],this.getMasterData("SDst", cnt-1, this.getCurrentRow(),"GB" + dg.getRandomChars(1).toUpperCase() + this.fillString(dg.getNumberBetween(0, 999).toString(),"0",3))); //SDst
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VDITM").getMapItem("ShPt"));
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VDITM").getMapItem("SOrg"));
        columns.put(this.getMetaValues("header")[cnt++],this.getLastMapValue("YD" + this.fillString(dg.getNumberBetween(1, 10).toString(),"0",2),this.getMetaValues("header")[cnt-1],0.8)); //DlvTy
        columns.put(this.getMetaValues("header")[cnt++],columns.get("Created on")); //Pl.GI date
        columns.put(this.getMetaValues("header")[cnt++],columns.get("Created on")); //Loadg Date
        columns.put(this.getMetaValues("header")[cnt++],columns.get("Created on")); //TrpPlanDt
        columns.put(this.getMetaValues("header")[cnt++],dg.getDate((Date)columns.get("Created on"), 0.009708738,0.09829231)); //Deliv.Date
        columns.put(this.getMetaValues("header")[cnt++],columns.get("Created on")); //Pick Date
        columns.put(this.getMetaValues("header")[cnt++],dg.getItem(incoT,this.parseDouble(this.getMetaValues("change")[cnt-1])));
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VDITM").getParentSchema("VAITM").getMapItem("Route")); //Route
        columns.put(this.getMetaValues("header")[cnt++],"J"); //DocCa
        columns.put(this.getMetaValues("header")[cnt++],"GB"); //FacCal
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VDITM").getParentSchema("VAITM").getMapItem("DPrio")); //DPrio
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VDITM").getParentSchema("VAITM").getParentSchema("VAHDR").getMapItem("SC")); //SC
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VDITM").getParentSchema("VAITM").getParentSchema("VAHDR").getMapItem("Sold-to pt")); //Sold-to pt
        columns.put(this.getMetaValues("header")[cnt++],this.getMasterData("Ship-to", cnt-1, this.getCurrentRow(), this.fillString(dg.getNumberUpTo(2119689295).toString(),"0",10))); //Ship-to
        Double sum = 0d;
        for(Object d : this.getSubschema("VDITM").getMasterData("Gross weight for Header").toArray()) {
            sum = sum + Double.parseDouble(d.toString());
        }
        columns.put(this.getMetaValues("header")[cnt++],sum); //Total weight
        columns.put(this.getMetaValues("header")[cnt++],sum); //Net weight
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VDITM").getMapItem("WUn")); //WUn
        columns.put(this.getMetaValues("header")[cnt++],51); //NoPk
        columns.put(this.getMetaValues("header")[cnt++],dg.getTime(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]))); //At
        columns.put(this.getMetaValues("header")[cnt++],columns.get("Created on")); //Billing Date
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VDITM").getParentSchema("VAITM").getMapItem("Route")); //Route
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VDITM").getParentSchema("VAITM").getMapItem("Curr")); //Curr
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VDITM").getMapItem("SOff")); //SOff
        columns.put(this.getMetaValues("header")[cnt++],this.getMasterData("Combination criteria for delivery", cnt-1, this.getCurrentRow(),"0" + this.fillString(dg.getNumberBetween(0, 99).toString(),"0",2) + "0")); //Combination criteria for delivery
        columns.put(this.getMetaValues("header")[cnt++],tData.getUser(this.parseDouble(this.getMetaValues("sfactor")[cnt-1]), this.parseDouble(this.getMetaValues("change")[cnt-1]), this.getCurrentRow(),this.getLastMapValue(this.getMetaValues("header")[cnt - 1]),Integer.MAX_VALUE)); //changed on
        columns.put(this.getMetaValues("header")[cnt++],dg.getDate((Date)columns.get("Created on"), this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]))); //changed on
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VDITM").getParentSchema("VAITM").getParentSchema("VAHDR").getMapItem("CCAr")); //CCAr
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VDITM").getParentSchema("VAITM").getParentSchema("VAHDR").getMapItem("Cred acct")); //cred acct.
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VDITM").getParentSchema("VAITM").getParentSchema("VAHDR").getMapItem("Cred")); //Cred
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VDITM").getParentSchema("VAITM").getParentSchema("VAHDR").getMapItem("Credrepgrp")); //Credrepgrp
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VDITM").getParentSchema("VAITM").getParentSchema("VAHDR").getMapItem("Risk category")); //Risk category
        columns.put(this.getMetaValues("header")[cnt++],columns.get("Doc Date")); //Doc Date
        columns.put(this.getMetaValues("header")[cnt++],columns.get("AcGI date")); //AcGI date
        sum = 0d;
        for(Object d : this.getSubschema("VDITM").getMasterData("Net value for Header").toArray()) {
            sum = sum + Double.parseDouble(d.toString()); //Net value
        }
        columns.put(this.getMetaValues("header")[cnt++],sum); //Net value
        columns.put(this.getMetaValues("header")[cnt++],dg.getTime(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]))); //Pick Time
        columns.put(this.getMetaValues("header")[cnt++],dg.getTime(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]))); //TP Time
        columns.put(this.getMetaValues("header")[cnt++],dg.getTime(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]))); //Loadg Time
        columns.put(this.getMetaValues("header")[cnt++],dg.getTime(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]))); //GI Time
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VDITM").getMapItem("Distance")); //Distance
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VDITM").getMapItem("Dis")); //Dis
        columns.put(this.getMetaValues("header")[cnt++],dg.getRandomChars(8).toString()); //Veh Plate N
        columns.put(this.getMetaValues("header")[cnt++],dg.getDecimal(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]),3)); //Gross weight GVM
        columns.put(this.getMetaValues("header")[cnt++],dg.getDecimal(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]),3)); //Tare weight
        columns.put(this.getMetaValues("header")[cnt++],dg.getItem("X",this.parseDouble(this.getMetaValues("change")[cnt-1]),null)); //Tare Compliment
        columns.put(this.getMetaValues("header")[cnt++],dg.getItem("X",this.parseDouble(this.getMetaValues("change")[cnt-1]),null)); //WeightCompliment
        columns.put(this.getMetaValues("header")[cnt++],dg.getTime(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]))); //Time Ticket
        columns.put(this.getMetaValues("header")[cnt++],tData.getUser(this.parseDouble(this.getMetaValues("sfactor")[cnt-1]), this.parseDouble(this.getMetaValues("change")[cnt-1]), this.getCurrentRow(),this.getLastMapValue(this.getMetaValues("header")[cnt - 1]),Integer.MAX_VALUE)); //User
        columns.put(this.getMetaValues("header")[cnt++],dg.getRange(wbnumber)); //Weigh Bridge Number
        columns.put(this.getMetaValues("header")[cnt++],dg.getItem("0A4B" + this.fillString(dg.getNumberUpTo(9999).toString(),"0",4),0.5,null)); //Terminal
        columns.put(this.getMetaValues("header")[cnt++],tData.getUser("user"));
        columns.put(this.getMetaValues("header")[cnt++],dg.getItem(this.fillString(dg.getNumberUpTo(999999).toString(),"0",6),0.2136,null)); //NAWI Serial Number
        columns.put(this.getMetaValues("header")[cnt++],dg.getItem(this.fillString(dg.getNumberUpTo(9999999).toString(),"0",7),0.598,0)); //Confirm
        
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

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
public class SchemaVAITM extends Schemas {
    
    private Integer primaryKey = 0;
    private Integer rowCount = 1;
    
    
    @Override
    public void setUniqueNumber() {
        DataGenerator dg = DataGenerator.getInstance();
        dg.setUniqueNumber(1);
    }
       
    
    @Override
    public Map<String, Object> getData() throws Exception{
        
        DataGenerator dg = DataGenerator.getInstance();
        TableData tData = TableData.getInstance();
        int foreignKey = 100;
        Date baseDate = new Date();
        
        //Definition of MasterData
        String[] bil = {"A","C",null};
        String[] uom = {"M3","MIN","TO","KG"};
        String[] curr = {"EUR","GBP","US"};
        String[] order = {"01","02","03"};
        
        Map<String, Double> convert = new HashMap<>();
        convert.put("M3TO",2.3);
        convert.put("M3KG",0.001);
        convert.put("MINTO",0.000001);
        convert.put("MINKG",0.001);
        convert.put("TOTO",1.0);
        convert.put("TOKG",1000.0);
        convert.put("KGTO",0.001);
        convert.put("KGKG",1.0);
        
        Map<String, Double> prio = new HashMap<>();
        prio.put("0", 0.705);
        prio.put("1", 0.805);
        prio.put("2", 0.940);
        prio.put("3", 0.996);
        prio.put("11", 1.0);
        
        this.setCurrentRow();
        if(this.getCurrentRow() == 1) {
            this.setForeignKey(foreignKey++);
            this.setPrimaryKey(this.getDefaultPrimaryKey());
            baseDate = dg.getDateBetween(dg.getDate(2013, 1, 1), dg.getDate(2015,12,31)); //Created on
        }
  
        int cnt = 0;
        
        Map<String, Object> columns = new HashMap<>();   
        columns.put(this.getMetaValues("header")[cnt++],this.getForeignKey()); //Sales Doc
        columns.put(this.getMetaValues("header")[cnt++],this.getNextPrimaryKey().toString() + "0"); //Item
        columns.put(this.getMetaValues("header")[cnt++],tData.getMaterial(this.parseDouble(this.getMetaValues("sfactor")[cnt-1]), this.parseDouble(this.getMetaValues("change")[cnt-1]), this.getCurrentRow(), this.getLastMapValue(this.getMetaValues("header")[cnt - 1]),Integer.MAX_VALUE)); //Material
        columns.put(this.getMetaValues("header")[cnt++],tData.getMaterial("Material entered", columns.get("Material").toString())); //Material entered
        columns.put(this.getMetaValues("header")[cnt++],tData.getMaterial("Matl Group", columns.get("Material").toString())); //Material group
        columns.put(this.getMetaValues("header")[cnt++],tData.getMaterial("Description", columns.get("Material").toString())); //Description
        columns.put(this.getMetaValues("header")[cnt++],tData.getMaterial("ItCa", columns.get("Material").toString())); //ItCa
        columns.put(this.getMetaValues("header")[cnt++],dg.getItem("X",0.15,null));
        columns.put(this.getMetaValues("header")[cnt++],this.getLastMapValue(dg.getItem(bil),this.getMetaValues("header")[cnt - 1], this.parseDouble(this.getMetaValues("change")[cnt - 1]))); //BilRl
        columns.put(this.getMetaValues("header")[cnt++],this.getLastMapValue(dg.getItem(dg.getRandomChars(2),0.05,null),this.getMetaValues("header")[cnt - 1], this.parseDouble(this.getMetaValues("change")[cnt - 1]))); //Rj
        columns.put(this.getMetaValues("header")[cnt++],this.getLastMapValue(dg.getNumberText(12),this.getMetaValues("header")[cnt - 1], this.parseDouble(this.getMetaValues("change")[cnt - 1]))); //Product Hierarchy
        columns.put(this.getMetaValues("header")[cnt++],this.getLastMapValue(dg.getItem(uom),this.getMetaValues("header")[cnt - 1], this.parseDouble(this.getMetaValues("change")[cnt - 1]))); //Uom
        columns.put(this.getMetaValues("header")[cnt++],1); //onversion factor
        columns.put(this.getMetaValues("header")[cnt++],columns.get("UoM")); //Bun
        String poItem = new String();
        for(int i = 1; i<=(6-columns.get("Item").toString().length() );i++) {
            poItem = poItem + "0";
        }
        columns.put(this.getMetaValues("header")[cnt++],poItem+columns.get("Item").toString()); //POItem
        columns.put(this.getMetaValues("header")[cnt++],tData.getMaterial("Customer Material Number", columns.get("Material").toString())); //Customer Material Number
        columns.put(this.getMetaValues("header")[cnt++],dg.getItem(this.getMasterData("Usage", cnt-1, this.getCurrentRow(), "B" + dg.getNumberBetween(10, 99).toString()),0.014,"B0B")); //Usage
        columns.put(this.getMetaValues("header")[cnt++],dg.getNumber(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]))); //Overdel Tol
        columns.put(this.getMetaValues("header")[cnt++],columns.get("Overdel. Tol.")); //Underdel Tol
        columns.put(this.getMetaValues("header")[cnt++],this.getLastMapValue(dg.getNumberUpTo(9)*10,this.getMetaValues("header")[cnt - 1], this.parseDouble(this.getMetaValues("change")[cnt - 1]))); //Dv
        columns.put(this.getMetaValues("header")[cnt++],this.getLastMapValue(dg.getNumberBetween(1000, 9999),this.getMetaValues("header")[cnt - 1], this.parseDouble(this.getMetaValues("change")[cnt - 1]))); //BusA
        if(this.getCurrentRow() == 1) {
            columns.put(this.getMetaValues("header")[cnt++],dg.getItem(dg.getNumberUpTo(9)*10,0.34,100)); //Prb
        } else {
            columns.put(this.getMetaValues("header")[cnt++],this.getLastMapValue("Prb"));
        }
        
        columns.put(this.getMetaValues("header")[cnt++],dg.getItem(dg.getCurrency(6149.3250, 39806.9660),0.05,dg.getCurrency(-182.1036, 146.5462))); //Net value
        this.setMasterData("Net value for Header", columns.get("Net value")); 
        
        columns.put(this.getMetaValues("header")[cnt++],this.getLastMapValue(dg.getItem(curr), this.getMetaValues("header")[cnt - 1], 0.05)); //Curr
        columns.put(this.getMetaValues("header")[cnt++],dg.getDecimal(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]),1)); //Order Quantity
        columns.put(this.getMetaValues("header")[cnt++],columns.get("Order Quantity")); //Required deliv. qty
        columns.put(this.getMetaValues("header")[cnt++],columns.get("Order Quantity")); //Cumul.confirmed qty
        columns.put(this.getMetaValues("header")[cnt++],columns.get("UoM")); //Su
        String WUn = dg.getItem("TO",0.66,"KG");
        columns.put(this.getMetaValues("header")[cnt++],Double.parseDouble(columns.get("Order Quantity").toString()) * convert.get(columns.get("SU") + WUn)); //Gross Weight
        columns.put(this.getMetaValues("header")[cnt++],columns.get("Gross weight")); //Net Weight
        columns.put(this.getMetaValues("header")[cnt++],WUn); //WUn
        columns.put(this.getMetaValues("header")[cnt++],dg.getItem("X",0.33,null)); //U
        columns.put(this.getMetaValues("header")[cnt++],dg.getItem("B",0.29,null)); //ComRI
        columns.put(this.getMetaValues("header")[cnt++],dg.getRange(prio)); //Prio
        columns.put(this.getMetaValues("header")[cnt++],columns.get("BusA")); //PInt
        columns.put(this.getMetaValues("header")[cnt++],columns.get("BusA")); //ShpT
        columns.put(this.getMetaValues("header")[cnt++],this.getMasterData("Route", cnt-1, this.getCurrentRow(), "GB" + this.fillString(dg.getNumberUpTo(9999).toString(), "0", 4))); //Route
        if(this.getCurrentRow() == 1) {
            baseDate = dg.getDateBetween(dg.getDate(2013, 1, 1), dg.getDate(2015,12,31)); 
            columns.put(this.getMetaValues("header")[cnt++],baseDate); //Created on
        } else {
            columns.put(this.getMetaValues("header")[cnt++],dg.getDate(baseDate, this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]))); //Created on            
        }
        columns.put(this.getMetaValues("header")[cnt++],tData.getUser(this.parseDouble(this.getMetaValues("sfactor")[cnt-1]), this.parseDouble(this.getMetaValues("change")[cnt-1]), this.getCurrentRow(),this.getLastMapValue(this.getMetaValues("header")[cnt - 1]),Integer.MAX_VALUE)); //created by
        columns.put(this.getMetaValues("header")[cnt++],dg.getTime(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]))); //Time
        if(columns.get("ComRl") != null && columns.get("ComRl") != "B") {
            columns.put(this.getMetaValues("header")[cnt++],dg.getDate((Date)columns.get("Created on"), this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]))); //BOM key dt.
        } else {
            columns.put(this.getMetaValues("header")[cnt++],null); //BOM key dt.
        }
        columns.put(this.getMetaValues("header")[cnt++],dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]))); //net Price
        if("MIN".equals(columns.get("UoM").toString())) {
            columns.put(this.getMetaValues("header")[cnt++],60); //per
        } else {
            columns.put(this.getMetaValues("header")[cnt++],1); //per
        }
        columns.put(this.getMetaValues("header")[cnt++], columns.get("UoM")); //UoM
        if(Double.parseDouble(columns.get("Net value").toString()) < 0) {
            columns.put(this.getMetaValues("header")[cnt++],"X"); //Ret
        } else {
            columns.put(this.getMetaValues("header")[cnt++],null); //Ret
        }
        columns.put(this.getMetaValues("header")[cnt++],this.getMasterData("AAG", cnt-1, this.getCurrentRow(), dg.getNumberBetween(10, 99).toString())); //AAG
        if(columns.get("Ret") != null && "X".equals(columns.get("Ret").toString())) {
            columns.put(this.getMetaValues("header")[cnt++],"A"); //OK
        } else {
            columns.put(this.getMetaValues("header")[cnt++],null); //OK
        }
        columns.put(this.getMetaValues("header")[cnt++],dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]),Double.parseDouble(columns.get("Net value").toString()))); //Cost
        columns.put(this.getMetaValues("header")[cnt++],dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]),Double.parseDouble(columns.get("Net value").toString()))); //Value of inv.gross
        columns.put(this.getMetaValues("header")[cnt++],dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]),Double.parseDouble(columns.get("Net value").toString()))); //Value w/o freight
        columns.put(this.getMetaValues("header")[cnt++],dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]))); //Value of all bills
        columns.put(this.getMetaValues("header")[cnt++],columns.get("Net value")); //Freight cost and r
        columns.put(this.getMetaValues("header")[cnt++],dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]))); //Subtotal 5
        columns.put(this.getMetaValues("header")[cnt++],dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]))); //Bonus paid
        columns.put(this.getMetaValues("header")[cnt++],dg.getDate((Date)columns.get("Created on"), this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]))); //changed on
        columns.put(this.getMetaValues("header")[cnt++],this.getMasterData("Profit Ctr", cnt-1, this.getCurrentRow(), this.fillString(dg.getNumberUpTo(1000000000).toString(), "0", 10))); //Profit Center
        columns.put(this.getMetaValues("header")[cnt++],this.getMasterData("MG 2", cnt-1, this.getCurrentRow(), dg.getItem("B" + this.fillString(dg.getNumberUpTo(99).toString(), "0", 2),0.33,null))); //MG 2
        columns.put(this.getMetaValues("header")[cnt++],this.getMasterData("MG 3", cnt-1, this.getCurrentRow(), dg.getItem("B" + this.fillString(dg.getNumberUpTo(99).toString(), "0", 2),0.20,null))); //MG 3
        columns.put(this.getMetaValues("header")[cnt++],this.getMasterData("MG 4", cnt-1, this.getCurrentRow(), dg.getItem("B" + this.fillString(dg.getNumberUpTo(99).toString(), "0", 2),0.08,null))); //MG 4
        columns.put(this.getMetaValues("header")[cnt++],this.getMasterData("MG 5", cnt-1, this.getCurrentRow(), "B" + this.fillString(dg.getNumberUpTo(99).toString(), "0", 2))); //MG 5
        columns.put(this.getMetaValues("header")[cnt++],dg.getItem("ZGB1",0.061,null)); //Reasn
        columns.put(this.getMetaValues("header")[cnt++],dg.getItem("E",0.053,null)); //S
        if(columns.get("Ret") != null && "X".equals(columns.get("Ret").toString())) {
            columns.put(this.getMetaValues("header")[cnt++],dg.getCurrency(-13.9147, 17.6974)); //Credit Price
            columns.put(this.getMetaValues("header")[cnt++],null); //Credit active
        } else {
            columns.put(this.getMetaValues("header")[cnt++],dg.getCurrency(86.2176, 65.1477)); //Credit Price
            columns.put(this.getMetaValues("header")[cnt++],"X"); //Credit active
        }
        columns.put(this.getMetaValues("header")[cnt++],1); //Exchange Rate
        columns.put(this.getMetaValues("header")[cnt++],dg.getItem("A",0.568,null)); //MPr
        columns.put(this.getMetaValues("header")[cnt++],this.getMasterData("PrCat", cnt-1, this.getCurrentRow(), dg.getItem(dg.getRandomChars(1).toUpperCase(),0.6294,null))); //PrCat
        columns.put(this.getMetaValues("header")[cnt++],dg.getItem(dg.getNumberBetween(100000000, 122213206),0.5036,0)); //Cost EastNo
        if(!"0".equals(columns.get("Cost Est.No.").toString())) {
            columns.put(this.getMetaValues("header")[cnt++],"PPC4"); //Costing Variant
        } else {
            columns.put(this.getMetaValues("header")[cnt++],null); //Costing Variant
        }
        columns.put(this.getMetaValues("header")[cnt++],Double.parseDouble(columns.get("Net value").toString())*0.20); //Tax Amount
        columns.put(this.getMetaValues("header")[cnt++],dg.getDecimal(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]),3)); //Distance
        columns.put(this.getMetaValues("header")[cnt++],dg.getItem("MI",0.6267,"KM")); //Dis
        if(columns.get("Credit active") != null && "X".equals(columns.get("Credit active").toString())) {
            columns.put(this.getMetaValues("header")[cnt++],dg.getItem(order));
            columns.put(this.getMetaValues("header")[cnt++],columns.get("Pint"));
        } else {
            columns.put(this.getMetaValues("header")[cnt++],null);
            columns.put(this.getMetaValues("header")[cnt++],null);            
        }
        columns.put(this.getMetaValues("header")[cnt++],dg.getDecimal(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]),3)); //Minimum
        columns.put(this.getMetaValues("header")[cnt++],dg.getDecimal(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]),3)); //Maximum
        columns.put(this.getMetaValues("header")[cnt++],dg.getDecimal(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]),3)); //Max Load
        columns.put(this.getMetaValues("header")[cnt++],dg.getDecimal(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]),3)); //Min Load
        if(columns.get("Credit active") != null && "X".equals(columns.get("Credit active").toString())) {
            columns.put(this.getMetaValues("header")[cnt++],dg.getDate((Date)columns.get("Created on"), this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]))); //First Load
        } else {
            columns.put(this.getMetaValues("header")[cnt++],null);
        }
        this.setMap(columns);
        
        //Check if Child has new Parent
        if(dg.getItem(1,0.3,0) == 1){
            this.setForeignKey(foreignKey++);
            this.setNextForeignKeyCheck(true);
            this.dropMasterData("Net value for Header");
        } else {
            this.setNextForeignKeyCheck(false);
        }

        if(this.getRowCount() == this.getCurrentRow()) {
            this.dropMasterData("Net value");
        }
        return columns;
    }
    
    @Override
    public String getName() {
        return this.getClass().getSimpleName().replace("Schema", "");
    }

    @Override
    public Integer getRandomRowCount() {
        DataGenerator dg = DataGenerator.getInstance();
        this.rowCount = dg.getNumber(4.1298, 6.1897);
        return this.rowCount;
    }
    
    @Override
    public Integer getDefaultPrimaryKey() {
        return 1;
    }
    
    
}

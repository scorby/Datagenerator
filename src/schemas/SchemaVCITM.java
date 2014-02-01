package schemas;

/*
 * Copyright 2013, Martin Kleehaus
 * Technical University of Munich (TUM)
 * Professorship for Database Systems and Applications
 */

import datagenerator.DataGenerator;
import static java.lang.Math.abs;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Martin Kleehaus
 */
public class SchemaVCITM extends Schemas {
    
    private Integer primaryKey = 0;
    private Integer foreignKey = 140500;
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
        int foreignKey = 300;
        Date baseDate = new Date();
        Map<String, Double> convert = new HashMap<>();
        convert.put("M3TO",2.3);
        convert.put("M3KG",0.001);
        convert.put("MINTO",0.000001);
        convert.put("MINKG",0.001);
        convert.put("TOTO",1.0);
        convert.put("TOKG",1000.0);
        convert.put("KGTO",0.001);
        convert.put("KGKG",1.0);
        
        this.setCurrentRow();
        if(this.getCurrentRow() == 1) {
            this.setPrimaryKey(this.getDefaultPrimaryKey());
            //this.setGrossWeight(0d, false);
            this.setForeignKey(foreignKey++);
        } 
        
        int cnt = 0;
        
        Map<String, Object> columns = new HashMap<>();   
        columns.put(this.getMetaValues("header")[cnt++],this.getForeignKey()); //Bill Doc
        columns.put(this.getMetaValues("header")[cnt++],this.getNextPrimaryKey().toString() + "0"); //Item
        if(this.getRowCount() > 1) {
            Double val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]));
            Double parentVal = Double.parseDouble(this.getParentSchema("VAITM").getMapItem("Order Quantity").toString());
            Double sum = 0d;
            for(Object d : this.getMasterData("Billed Quantity").toArray()) {
                sum = sum + Double.parseDouble(d.toString());
            }
            if(sum+val > parentVal) {
                columns.put(this.getMetaValues("header")[cnt++],parentVal-sum); //Billed quantity
            } else {
                columns.put(this.getMetaValues("header")[cnt++],val); //Billed quantity
            }
            this.setMasterData("Billed Quantity", columns.get("Billed Quantity"));
        } else {
            columns.put(this.getMetaValues("header")[cnt++],dg.getItem(Double.parseDouble(this.getParentSchema("VAITM").getMapItem("Order Quantity").toString())-dg.getCurrency(45.6803, 58.263),0.9164,this.getParentSchema("VAITM").getMapItem("Order Quantity")));
        }
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("SU")); //SU
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("BUn")); //BUn
        columns.put(this.getMetaValues("header")[cnt++],columns.get("Billed Quantity")); //Billqty in SKU
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("Order Quantity")); //Required Quantity
        columns.put(this.getMetaValues("header")[cnt++],Double.parseDouble(columns.get("Billed Quantity").toString()) * convert.get(columns.get("SU") + this.getParentSchema("VAITM").getMapItem("WUn").toString())); //Net Weight
        columns.put(this.getMetaValues("header")[cnt++],columns.get("Net weight")); //Gross weight
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("WUn").toString()); //WUn
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("BusA").toString()); //BusA
        columns.put(this.getMetaValues("header")[cnt++],tData.getUser(this.parseDouble(this.getMetaValues("sfactor")[cnt-1]), this.parseDouble(this.getMetaValues("change")[cnt-1]), this.getCurrentRow(),this.getLastMapValue(this.getMetaValues("header")[cnt - 1]),Integer.MAX_VALUE)); //created by
        if(this.getCurrentRow() == 1) {
            baseDate = dg.getDate((Date)this.getParentSchema("VAITM").getMapItem("Created on"),22.65806452,15.82757692);
            this.setBaseDate(baseDate);
            columns.put(this.getMetaValues("header")[cnt++],baseDate); //Created on
        } else {
            columns.put(this.getMetaValues("header")[cnt++],this.getLastMapValue("Created on")); //Created on            
        }
        columns.put(this.getMetaValues("header")[cnt++],dg.getTime(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]))); //Time
        columns.put(this.getMetaValues("header")[cnt++],dg.getDate(baseDate,this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]))); //Pricing dt
        columns.put(this.getMetaValues("header")[cnt++],columns.get("Pricing dt")); //Servrend dt
        if(this.getRowCount() > 1) {
            Double val = 0d;
            if(this.getParentSchema("VAITM").getMapItem("Ret") == "X") {
                val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]))*(-1);
            } else {
                val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]));
            }
            Double parentVal = Double.parseDouble(this.getParentSchema("VAITM").getMapItem("Net value").toString());
            Double sum = 0d;
            for(Object d : this.getMasterData("Net value").toArray()) {
                sum = sum + Double.parseDouble(d.toString());
            }
            if(abs(sum+val) > abs(parentVal)) {
                columns.put(this.getMetaValues("header")[cnt++],parentVal-sum); //Net value
            } else {
                columns.put(this.getMetaValues("header")[cnt++],val); //Net value
            }
            this.setMasterData("Net value", columns.get("Net value")); 
        } else {
            double m = dg.getNumberBetween(10, 90)/100;
            columns.put(this.getMetaValues("header")[cnt++],dg.getItem(Double.parseDouble(this.getParentSchema("VAITM").getMapItem("Net value").toString())*m,0.247,this.getParentSchema("VAITM").getMapItem("Net value")));
        }
        this.setMasterData("Net value for Header", columns.get("Net value")); 
        if(this.getParentSchema("VAITM").getMapItem("PrCat") == "M") {
            columns.put(this.getMetaValues("header")[cnt++],dg.getItem("K",0.012,"L")); //PrCat
        } else {
            columns.put(this.getMetaValues("header")[cnt++],"J"); //PrCat
        }
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("Sales Doc").toString()); //Sales Doc
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("Item").toString()); //Item
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("Material").toString()); //Material
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("Description").toString()); //Description
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("Matl Group").toString()); //Matl Group
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("ItCa").toString()); //ItCa
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("Product hierarchy").toString()); //Product hierarchy
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("ShPt").toString()); //ShPt
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("Dv").toString()); //Dv
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("Plnt").toString()); //Plnt
        if(this.getCurrentRow() == 1) {
            columns.put(this.getMetaValues("header")[cnt++],dg.getCountryCode()); //Ctry
            columns.put(this.getMetaValues("header")[cnt++],dg.getRandomChars(2).toUpperCase()); //Reg
        } else {
            columns.put(this.getMetaValues("header")[cnt++],getLastMapValue("Ctry")); //Ctry
            columns.put(this.getMetaValues("header")[cnt++],getLastMapValue("Reg")); //Reg
        }
        columns.put(this.getMetaValues("header")[cnt++],this.getLastMapValue(dg.getItem("X",0.8045,"B"),this.getMetaValues("header")[cnt-1],this.parseDouble(this.getMetaValues("change")[cnt-1]))); //Prcg
        columns.put(this.getMetaValues("header")[cnt++],dg.getItem(this.getParentSchema("VAITM").getMapItem("AAG"),0.7313,this.getMasterData("AAG", cnt-1, this.getCurrentRow(), dg.getNumberBetween(10, 99).toString()))); //AAG
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getParentSchema("VAHDR").getMapItem("SGrp")); //SGrp
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("SOff")); //SOff
        columns.put(this.getMetaValues("header")[cnt++],this.getLastMapValue(dg.getNumberUpTo(9)*10,this.getMetaValues("header")[cnt-1],this.parseDouble(this.getMetaValues("change")[cnt-1]))); //Di
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("Ret")); //Ret
        if(this.getRowCount() > 1) {
            Double val = 0d;
            if(this.getParentSchema("VAITM").getMapItem("Ret") == "X") {
                val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]))*(-1);
            } else {
                val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]));
            }
            Double parentVal = Double.parseDouble(this.getParentSchema("VAITM").getMapItem("Cost").toString());
            Double sum = 0d;
            for(Object d : this.getMasterData("Cost").toArray()) {
                sum = sum + Double.parseDouble(d.toString());
            }
            if(abs(sum+val) > abs(parentVal)) {
                columns.put(this.getMetaValues("header")[cnt++],parentVal-sum); //Cost
            } else {
                columns.put(this.getMetaValues("header")[cnt++],val); //Cost
            }
            this.setMasterData("Cost", columns.get("Cost")); 
        } else {
            double m = dg.getNumberBetween(10, 90)/100;
            columns.put(this.getMetaValues("header")[cnt++],dg.getItem(Double.parseDouble(this.getParentSchema("VAITM").getMapItem("Cost").toString())*m,0.247,this.getParentSchema("VAITM").getMapItem("Cost")));
        }
        if(this.getRowCount() > 1) {
            Double val = 0d;
            if(this.getParentSchema("VAITM").getMapItem("Ret") == "X") {
                val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]))*(-1);
            } else {
                val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]));
            }
            Double parentVal = Double.parseDouble(this.getParentSchema("VAITM").getMapItem("Value of invgross").toString());
            Double sum = 0d;
            for(Object d : this.getMasterData("Value of invgross").toArray()) {
                sum = sum + Double.parseDouble(d.toString());
            }
            if(abs(sum+val) > abs(parentVal)) {
                columns.put(this.getMetaValues("header")[cnt++],parentVal-sum); //Net value
            } else {
                columns.put(this.getMetaValues("header")[cnt++],val); //Net value
            }
            this.setMasterData("Value of invgross", columns.get("Value of invgross")); 
        } else {
            double m = dg.getNumberBetween(10, 90)/100;
            columns.put(this.getMetaValues("header")[cnt++],dg.getItem(Double.parseDouble(this.getParentSchema("VAITM").getMapItem("Value of invgross").toString())*m,0.247,this.getParentSchema("VAITM").getMapItem("Value of invgross")));
        }
        if(this.getRowCount() > 1) {
            Double val = 0d;
            if(this.getParentSchema("VAITM").getMapItem("Ret") == "X") {
                val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]))*(-1);
            } else {
                val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]));
            }
            Double parentVal = Double.parseDouble(this.getParentSchema("VAITM").getMapItem("Value w/o freight").toString());
            Double sum = 0d;
            for(Object d : this.getMasterData("Value w/o freight").toArray()) {
                sum = sum + Double.parseDouble(d.toString());
            }
            if(abs(sum+val) > abs(parentVal)) {
                columns.put(this.getMetaValues("header")[cnt++],parentVal-sum); //Value w/o freight
            } else {
                columns.put(this.getMetaValues("header")[cnt++],val); //Value w/o freight
            }
            this.setMasterData("Value w/o freight", columns.get("Value w/o freight")); 
        } else {
            double m = dg.getNumberBetween(10, 90)/100;
            columns.put(this.getMetaValues("header")[cnt++],dg.getItem(Double.parseDouble(this.getParentSchema("VAITM").getMapItem("Value w/o freight").toString())*m,0.247,this.getParentSchema("VAITM").getMapItem("Value w/o freight")));
        }
        if(this.getRowCount() > 1) {
            Double val = 0d;
            if(this.getParentSchema("VAITM").getMapItem("Ret") == "X") {
                val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]))*(-1);
            } else {
                val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]));
            }
            Double parentVal = Double.parseDouble(this.getParentSchema("VAITM").getMapItem("Value of all bills").toString());
            Double sum = 0d;
            for(Object d : this.getMasterData("Value of all bills").toArray()) {
                sum = sum + Double.parseDouble(d.toString());
            }
            if(abs(sum+val) > abs(parentVal)) {
                columns.put(this.getMetaValues("header")[cnt++],parentVal-sum); //Value of all bills
            } else {
                columns.put(this.getMetaValues("header")[cnt++],val); //Value of all bills
            }
            this.setMasterData("Value of all bills", columns.get("Value of all bills")); 
        } else {
            double m = dg.getNumberBetween(10, 90)/100;
            columns.put(this.getMetaValues("header")[cnt++],dg.getItem(Double.parseDouble(this.getParentSchema("VAITM").getMapItem("Value of all bills").toString())*m,0.247,this.getParentSchema("VAITM").getMapItem("Value of all bills")));
        }
        if(this.getRowCount() > 1) {
            Double val = 0d;
            if(this.getParentSchema("VAITM").getMapItem("Ret") == "X") {
                val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]))*(-1);
            } else {
                val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]));
            }
            Double parentVal = Double.parseDouble(this.getParentSchema("VAITM").getMapItem("Freight cost").toString());
            Double sum = 0d;
            for(Object d : this.getMasterData("Freight cost").toArray()) {
                sum = sum + Double.parseDouble(d.toString());
            }
            if(abs(sum+val) > abs(parentVal)) {
                columns.put(this.getMetaValues("header")[cnt++],parentVal-sum); //Freight cost
            } else {
                columns.put(this.getMetaValues("header")[cnt++],val); //Freight cost
            }
            this.setMasterData("Freight cost", columns.get("Freight cost")); 
        } else {
            double m = dg.getNumberBetween(10, 90)/100;
            columns.put(this.getMetaValues("header")[cnt++],dg.getItem(Double.parseDouble(this.getParentSchema("VAITM").getMapItem("Freight cost").toString())*m,0.247,this.getParentSchema("VAITM").getMapItem("Freight cost")));
        }
        if(this.getRowCount() > 1) {
            Double val = 0d;
            if(this.getParentSchema("VAITM").getMapItem("Ret") == "X") {
                val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]))*(-1);
            } else {
                val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]));
            }
            Double parentVal = Double.parseDouble(this.getParentSchema("VAITM").getMapItem("Subtotal 5").toString());
            Double sum = 0d;
            for(Object d : this.getMasterData("Subtotal 5").toArray()) {
                sum = sum + Double.parseDouble(d.toString());
            }
            if(abs(sum+val) > abs(parentVal)) {
                columns.put(this.getMetaValues("header")[cnt++],parentVal-sum); //Subtotal 5
            } else {
                columns.put(this.getMetaValues("header")[cnt++],val); //Subtotal 5
            }
            this.setMasterData("Subtotal 5", columns.get("Subtotal 5")); 
        } else {
            double m = dg.getNumberBetween(10, 90)/100;
            columns.put(this.getMetaValues("header")[cnt++],dg.getItem(Double.parseDouble(this.getParentSchema("VAITM").getMapItem("Subtotal 5").toString())*m,0.247,this.getParentSchema("VAITM").getMapItem("Subtotal 5")));
        }
        if(this.getRowCount() > 1) {
            Double val = 0d;
            if(this.getParentSchema("VAITM").getMapItem("Ret") == "X") {
                val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]))*(-1);
            } else {
                val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]));
            }
            Double parentVal = Double.parseDouble(this.getParentSchema("VAITM").getMapItem("Bonus paid").toString());
            Double sum = 0d;
            for(Object d : this.getMasterData("Bonus paid").toArray()) {
                sum = sum + Double.parseDouble(d.toString());
            }
            if(abs(sum+val) > abs(parentVal)) {
                columns.put(this.getMetaValues("header")[cnt++],parentVal-sum); //Bonus paid
            } else {
                columns.put(this.getMetaValues("header")[cnt++],val); //Bonus paid
            }
            this.setMasterData("Bonus paid", columns.get("Bonus paid")); 
        } else {
            double m = dg.getNumberBetween(10, 90)/100;
            columns.put(this.getMetaValues("header")[cnt++],dg.getItem(Double.parseDouble(this.getParentSchema("VAITM").getMapItem("Bonus paid").toString())*m,0.247,this.getParentSchema("VAITM").getMapItem("Bonus paid")));
        }
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("Profit Ctr")); //Profit Ctr
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("MG 2")); //MG 2
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("MG 3")); //MG 3
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("MG 4")); //MG 4
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("MG 5")); //MG 5
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("Material entered")); //Material entered
        columns.put(this.getMetaValues("header")[cnt++],columns.get("Net value")); //Rebbasis
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("COAr")); //Material entered
        columns.put(this.getMetaValues("header")[cnt++],this.fillString(dg.getNumberUpTo(39830114).toString(),"0",8)); //Profit Segment
        if(this.getRowCount() > 1) {
            Double val = 0d;
            if(this.getParentSchema("VAITM").getMapItem("Ret") == "X") {
                val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]))*(-1);
            } else {
                val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]));
            }
            Double parentVal = Double.parseDouble(this.getParentSchema("VAITM").getMapItem("Credit price").toString());
            Double sum = 0d;
            for(Object d : this.getMasterData("Credit price").toArray()) {
                sum = sum + Double.parseDouble(d.toString());
            }
            if(abs(sum+val) > abs(parentVal)) {
                columns.put(this.getMetaValues("header")[cnt++],parentVal-sum); //Credit price
            } else {
                columns.put(this.getMetaValues("header")[cnt++],val); //Credit price
            }
            this.setMasterData("Credit price", columns.get("Credit price")); 
        } else {
            double m = dg.getNumberBetween(10, 90)/100;
            columns.put(this.getMetaValues("header")[cnt++],dg.getItem(Double.parseDouble(this.getParentSchema("VAITM").getMapItem("Credit price").toString())*m,0.247,this.getParentSchema("VAITM").getMapItem("Credit price")));
        }        
        columns.put(this.getMetaValues("header")[cnt++],columns.get("Credit active")); //Credit active
        columns.put(this.getMetaValues("header")[cnt++],dg.getItem(this.getMasterData("Usage", cnt-1, this.getCurrentRow(), "B" + dg.getNumberBetween(10, 99).toString()),0.3857,"B0B")); //Usage
        columns.put(this.getMetaValues("header")[cnt++],"GB" + dg.getRandomChars(1).toUpperCase() + this.fillString(dg.getNumberUpTo(999).toString(),"0",3)); //DisO
        columns.put(this.getMetaValues("header")[cnt++],columns.get("Ctry")); //DCOr
        columns.put(this.getMetaValues("header")[cnt++],dg.getItem("A",0.46,null)); //MPr
        if(this.getCurrentRow() == 1) {
            columns.put(this.getMetaValues("header")[cnt++],dg.getRandomChars(2).toUpperCase()); //RO
        } else {
            columns.put(this.getMetaValues("header")[cnt++],this.getLastMapValue("RO")); //RO
        }
        columns.put(this.getMetaValues("header")[cnt++],this.getLastMapValue(columns.get("Ctry") + this.fillString(dg.getNumberUpTo(99).toString(),"0",2),this.getMetaValues("header")[cnt-1],0.85)); //SOOrd
        if(columns.get("Ret") == "X") {
            if(Double.parseDouble(columns.get("Net value").toString()) > 0) {
                columns.put(this.getMetaValues("header")[cnt++],"K"); //DocCa
            } else {
                columns.put(this.getMetaValues("header")[cnt++],"L"); //DocCa
            }
        } else {
            columns.put(this.getMetaValues("header")[cnt++],"C"); //DocCa
        }
        columns.put(this.getMetaValues("header")[cnt++],Double.parseDouble(columns.get("Net value").toString())*0.2); //Tax
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getParentSchema("VAHDR").getMapItem("OrdRs")); //OrdRs
        columns.put(this.getMetaValues("header")[cnt++],dg.getDate((Date)columns.get("Created on"), this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]))); //TranslDate

        
        
        this.setMap(columns);
        
        //Check if Child has new Parent
        if(dg.getItem(1,0.52,0) == 1){
            this.setForeignKey(foreignKey++);
            this.setNextForeignKeyCheck(true);
            this.dropMasterData("Gross weight for Header");
        } else {
            this.setNextForeignKeyCheck(false);
        }
        
        if(this.getRowCount() == this.getCurrentRow()) {
            this.dropMasterData("Billed Quantity");
            this.dropMasterData("Net value"); 
            this.dropMasterData("Cost");
            this.dropMasterData("Value of invgross");
            this.dropMasterData("Value w/o freight");
            this.dropMasterData("Value of all bills");
            this.dropMasterData("Freight cost"); 
            this.dropMasterData("Subtotal 5");
            this.dropMasterData("Bonus paid");
            this.dropMasterData("Credit price");
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
        this.rowCount = dg.getNumber(1.131756756, 0.76769849);
        return this.rowCount;
    }
    
    @Override
    public Integer getDefaultPrimaryKey() {
        return 1;
    }
    
}

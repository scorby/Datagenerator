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
        
        if(this.getCurrentRow() == 1) {
            this.setPrimaryKey(this.getDefaultPrimaryKey());
            //this.setGrossWeight(0d, false);
            this.setForeignKey(foreignKey++);
        } 
        
        this.setColumnNumber(0);
        
        Map<String, Object> columns = new HashMap<>();   
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getForeignKey()); //Bill Doc
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getNextPrimaryKey().toString() + "0"); //Item
        if(this.getRowCount() > 1) {
            Double val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]));
            Double parentVal = Double.parseDouble(this.getParentSchema("VAITM").getMapItem("ORDER_QUANTITY").toString());
            Double sum = 0d;
            for(Object d : this.getMasterData("BILLED_QUANTITY").toArray()) {
                sum = sum + Double.parseDouble(d.toString());
            }
            if(sum+val > parentVal) {
                columns.put(this.getMetaValues("header")[this.nextColumn()],parentVal-sum); //Billed quantity
            } else {
                columns.put(this.getMetaValues("header")[this.nextColumn()],val); //Billed quantity
            }
            this.setMasterData("BILLED_QUANTITY", columns.get("BILLED_QUANTITY"));
        } else {
            columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getItem(Double.parseDouble(this.getParentSchema("VAITM").getMapItem("BILLED_QUANTITY").toString())-dg.getCurrency(45.6803, 58.263),0.9164,this.getParentSchema("VAITM").getMapItem("ORDER_QUANTITY")));
        }
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("SU")); //SU
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("BUN")); //BUn
        columns.put(this.getMetaValues("header")[this.nextColumn()],columns.get("BILLED_QUANTITY")); //Billqty in SKU
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("ORDER_QUANTITY")); //Required Quantity
        columns.put(this.getMetaValues("header")[this.nextColumn()],Double.parseDouble(columns.get("BILLED_QUANTITY").toString()) * convert.get(columns.get("SU") + this.getParentSchema("VAITM").getMapItem("WUn").toString())); //Net Weight
        columns.put(this.getMetaValues("header")[this.nextColumn()],columns.get("NET_WEIGHT")); //Gross weight
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("WUN").toString()); //WUn
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("BUSA").toString()); //BusA
        columns.put(this.getMetaValues("header")[this.nextColumn()],tData.getUser(this.parseDouble(this.getMetaValues("sfactor")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("change")[this.getColumnNumber()-1]), this.getCurrentRow(),this.getLastMapValue(this.getMetaValues("header")[this.getColumnNumber()-1]),Integer.MAX_VALUE)); //created by
        if(this.getCurrentRow() == 1) {
            baseDate = dg.getDate((Date)this.getParentSchema("VAITM").getMapItem("CREATED_ON"),22.65806452,15.82757692);
            this.setBaseDate(baseDate);
            columns.put(this.getMetaValues("header")[this.nextColumn()],baseDate); //Created on
        } else {
            columns.put(this.getMetaValues("header")[this.nextColumn()],this.getLastMapValue("CREATED_ON")); //Created on            
        }
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getTime(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]))); //Time
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getDate(baseDate,this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]))); //Pricing dt
        columns.put(this.getMetaValues("header")[this.nextColumn()],columns.get("PRICING_DT")); //Servrend dt
        if(this.getRowCount() > 1) {
            Double val = 0d;
            if(this.getParentSchema("VAITM").getMapItem("RET") == "X") {
                val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]))*(-1);
            } else {
                val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]));
            }
            Double parentVal = Double.parseDouble(this.getParentSchema("VAITM").getMapItem("NET_VALUE").toString());
            Double sum = 0d;
            for(Object d : this.getMasterData("NET_VALUE").toArray()) {
                sum = sum + Double.parseDouble(d.toString());
            }
            if(abs(sum+val) > abs(parentVal)) {
                columns.put(this.getMetaValues("header")[this.nextColumn()],parentVal-sum); //Net value
            } else {
                columns.put(this.getMetaValues("header")[this.nextColumn()],val); //Net value
            }
            this.setMasterData("NET_VALUE", columns.get("NET_VALUE")); 
        } else {
            double m = dg.getNumberBetween(10, 90)/100.0;
            columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getItem(Double.parseDouble(this.getParentSchema("VAITM").getMapItem("NET_VALUE").toString())*m,0.247,this.getParentSchema("VAITM").getMapItem("NET_VALUE")));
        }
        this.setMasterData("Net value for Header", columns.get("NET_VALUE")); 
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("CURR"));
        if(this.getParentSchema("VAITM").getMapItem("PRCAT") == "M") {
            columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getItem("K",0.012,"L")); //PrCat
        } else {
            columns.put(this.getMetaValues("header")[this.nextColumn()],"J"); //PrCat
        }
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("SALES_DOC").toString()); //Sales Doc
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("ITEM").toString()); //Item
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("MATERIAL").toString()); //Material
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("DESCRIPTION").toString()); //Description
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("MATL_GROUP").toString()); //Matl Group
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("ITCA").toString()); //ItCa
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("PRODUCT_HIERARCHY").toString()); //Product hierarchy
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("SHPT").toString()); //ShPt
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("DV").toString()); //Dv
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("PLNT").toString()); //Plnt
        if(this.getCurrentRow() == 1) {
            columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getCountryCode()); //Ctry
            columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getRandomChars(2).toUpperCase()); //Reg
        } else {
            columns.put(this.getMetaValues("header")[this.nextColumn()],getLastMapValue("CTRY")); //Ctry
            columns.put(this.getMetaValues("header")[this.nextColumn()],getLastMapValue("REG")); //Reg
        }
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getLastMapValue(dg.getItem("X",0.8045,"B"),this.getMetaValues("header")[this.getColumnNumber()-1],this.parseDouble(this.getMetaValues("change")[this.getColumnNumber()-1]))); //Prcg
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getItem(this.getParentSchema("VAITM").getMapItem("AAG"),0.7313,this.getMasterData("AAG", this.getColumnNumber()-1, this.getCurrentRow(), dg.getNumberBetween(10, 99).toString()))); //AAG
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getParentSchema("VAHDR").getMapItem("SGRP")); //SGrp
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("SOFF")); //SOff
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getLastMapValue(dg.getNumberUpTo(9)*10,this.getMetaValues("header")[this.getColumnNumber()-1],this.parseDouble(this.getMetaValues("change")[this.getColumnNumber()-1]))); //Di
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("RET")); //Ret
        if(this.getRowCount() > 1) {
            Double val = 0d;
            if(this.getParentSchema("VAITM").getMapItem("RET") == "X") {
                val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]))*(-1);
            } else {
                val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]));
            }
            Double parentVal = Double.parseDouble(this.getParentSchema("VAITM").getMapItem("COST").toString());
            Double sum = 0d;
            for(Object d : this.getMasterData("COST").toArray()) {
                sum = sum + Double.parseDouble(d.toString());
            }
            if(abs(sum+val) > abs(parentVal)) {
                columns.put(this.getMetaValues("header")[this.nextColumn()],parentVal-sum); //Cost
            } else {
                columns.put(this.getMetaValues("header")[this.nextColumn()],val); //Cost
            }
            this.setMasterData("COST", columns.get("COST")); 
        } else {
            double m = dg.getNumberBetween(10, 90)/100.0;
            columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getItem(Double.parseDouble(this.getParentSchema("VAITM").getMapItem("COST").toString())*m,0.247,this.getParentSchema("VAITM").getMapItem("Cost")));
        }
        if(this.getRowCount() > 1) {
            Double val = 0d;
            if(this.getParentSchema("VAITM").getMapItem("RET") == "X") {
                val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]))*(-1);
            } else {
                val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]));
            }
            Double parentVal = Double.parseDouble(this.getParentSchema("VAITM").getMapItem("VALUE_OF_INVGROSS").toString());
            Double sum = 0d;
            for(Object d : this.getMasterData("VALUE_OF_INVGROSS").toArray()) {
                sum = sum + Double.parseDouble(d.toString());
            }
            if(abs(sum+val) > abs(parentVal)) {
                columns.put(this.getMetaValues("header")[this.nextColumn()],parentVal-sum); //Net value
            } else {
                columns.put(this.getMetaValues("header")[this.nextColumn()],val); //Net value
            }
            this.setMasterData("VALUE_OF_INVGROSS", columns.get("VALUE_OF_INVGROSS")); 
        } else {
            double m = dg.getNumberBetween(10, 90)/100.0;
            columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getItem(Double.parseDouble(this.getParentSchema("VAITM").getMapItem("VALUE_OF_INVGROSS").toString())*m,0.247,this.getParentSchema("VAITM").getMapItem("Value of invgross")));
        }
        if(this.getRowCount() > 1) {
            Double val = 0d;
            if(this.getParentSchema("VAITM").getMapItem("RET") == "X") {
                val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]))*(-1);
            } else {
                val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]));
            }
            Double parentVal = Double.parseDouble(this.getParentSchema("VAITM").getMapItem("VALUE_WO_FREIGHT").toString());
            Double sum = 0d;
            for(Object d : this.getMasterData("VALUE_WO_FREIGHT").toArray()) {
                sum = sum + Double.parseDouble(d.toString());
            }
            if(abs(sum+val) > abs(parentVal)) {
                columns.put(this.getMetaValues("header")[this.nextColumn()],parentVal-sum); //Value w/o freight
            } else {
                columns.put(this.getMetaValues("header")[this.nextColumn()],val); //Value w/o freight
            }
            this.setMasterData("VALUE_WO_FREIGHT", columns.get("VALUE_WO_FREIGHT")); 
        } else {
            double m = dg.getNumberBetween(10, 90)/100.0;
            columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getItem(Double.parseDouble(this.getParentSchema("VAITM").getMapItem("Value w/o freight").toString())*m,0.247,this.getParentSchema("VAITM").getMapItem("Value w/o freight")));
        }
        if(this.getRowCount() > 1) {
            Double val = 0d;
            if(this.getParentSchema("VAITM").getMapItem("RET") == "X") {
                val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]))*(-1);
            } else {
                val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]));
            }
            Double parentVal = Double.parseDouble(this.getParentSchema("VAITM").getMapItem("VALUE_OF_ALL_BILLS").toString());
            Double sum = 0d;
            for(Object d : this.getMasterData("VALUE_OF_ALL_BILLS").toArray()) {
                sum = sum + Double.parseDouble(d.toString());
            }
            if(abs(sum+val) > abs(parentVal)) {
                columns.put(this.getMetaValues("header")[this.nextColumn()],parentVal-sum); //Value of all bills
            } else {
                columns.put(this.getMetaValues("header")[this.nextColumn()],val); //Value of all bills
            }
            this.setMasterData("VALUE_OF_ALL_BILLS", columns.get("VALUE_OF_ALL_BILLS")); 
        } else {
            double m = dg.getNumberBetween(10, 90)/100.0;
            columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getItem(Double.parseDouble(this.getParentSchema("VAITM").getMapItem("VALUE_OF_ALL_BILLS").toString())*m,0.247,this.getParentSchema("VAITM").getMapItem("Value of all bills")));
        }
        if(this.getRowCount() > 1) {
            Double val = 0d;
            if(this.getParentSchema("VAITM").getMapItem("RET") == "X") {
                val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]))*(-1);
            } else {
                val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]));
            }
            Double parentVal = Double.parseDouble(this.getParentSchema("VAITM").getMapItem("FREIGHT_COST").toString());
            Double sum = 0d;
            for(Object d : this.getMasterData("FREIGHT_COST").toArray()) {
                sum = sum + Double.parseDouble(d.toString());
            }
            if(abs(sum+val) > abs(parentVal)) {
                columns.put(this.getMetaValues("header")[this.nextColumn()],parentVal-sum); //Freight cost
            } else {
                columns.put(this.getMetaValues("header")[this.nextColumn()],val); //Freight cost
            }
            this.setMasterData("FREIGHT_COST", columns.get("FREIGHT_COST")); 
        } else {
            double m = dg.getNumberBetween(10, 90)/100.0;
            columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getItem(Double.parseDouble(this.getParentSchema("VAITM").getMapItem("FREIGHT_COST").toString())*m,0.247,this.getParentSchema("VAITM").getMapItem("FREIGHT_COST")));
        }
        if(this.getRowCount() > 1) {
            Double val = 0d;
            if(this.getParentSchema("VAITM").getMapItem("RET") == "X") {
                val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]))*(-1);
            } else {
                val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]));
            }
            Double parentVal = Double.parseDouble(this.getParentSchema("VAITM").getMapItem("SUBTOTAL_5").toString());
            Double sum = 0d;
            for(Object d : this.getMasterData("SUBTOTAL_5").toArray()) {
                sum = sum + Double.parseDouble(d.toString());
            }
            if(abs(sum+val) > abs(parentVal)) {
                columns.put(this.getMetaValues("header")[this.nextColumn()],parentVal-sum); //Subtotal 5
            } else {
                columns.put(this.getMetaValues("header")[this.nextColumn()],val); //Subtotal 5
            }
            this.setMasterData("SUBTOTAL_5", columns.get("SUBTOTAL_5")); 
        } else {
            double m = dg.getNumberBetween(10, 90)/100.0;
            columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getItem(Double.parseDouble(this.getParentSchema("VAITM").getMapItem("SUBTOTAL_5").toString())*m,0.247,this.getParentSchema("VAITM").getMapItem("SUBTOTAL_5")));
        }
        if(this.getRowCount() > 1) {
            Double val = 0d;
            if(this.getParentSchema("VAITM").getMapItem("RET") == "X") {
                val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]))*(-1);
            } else {
                val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]));
            }
            Double parentVal = Double.parseDouble(this.getParentSchema("VAITM").getMapItem("BONUS_PAID").toString());
            Double sum = 0d;
            for(Object d : this.getMasterData("BONUS_PAID").toArray()) {
                sum = sum + Double.parseDouble(d.toString());
            }
            if(abs(sum+val) > abs(parentVal)) {
                columns.put(this.getMetaValues("header")[this.nextColumn()],parentVal-sum); //Bonus paid
            } else {
                columns.put(this.getMetaValues("header")[this.nextColumn()],val); //Bonus paid
            }
            this.setMasterData("BONUS_PAID", columns.get("BONUS_PAID")); 
        } else {
            double m = dg.getNumberBetween(10, 90)/100.0;
            columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getItem(Double.parseDouble(this.getParentSchema("VAITM").getMapItem("BONUS_PAID").toString())*m,0.247,this.getParentSchema("VAITM").getMapItem("BONUS_PAID")));
        }
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("PROFIT_CTR")); //Profit Ctr
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("MG_2")); //MG 2
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("MG_3")); //MG 3
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("MG_4")); //MG 4
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("MG_5")); //MG 5
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("MATERIAL_ENTERED")); //Material entered
        columns.put(this.getMetaValues("header")[this.nextColumn()],columns.get("NET_VALUE")); //Rebbasis
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("COAR")); //Material entered
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.fillString(dg.getNumberUpTo(39830114).toString(),"0",8)); //Profit Segment
        if(this.getRowCount() > 1) {
            Double val = 0d;
            if(this.getParentSchema("VAITM").getMapItem("RET") == "X") {
                val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]))*(-1);
            } else {
                val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]));
            }
            Double parentVal = Double.parseDouble(this.getParentSchema("VAITM").getMapItem("CREDIT_PRICE").toString());
            Double sum = 0d;
            for(Object d : this.getMasterData("CREDIT_PRICE").toArray()) {
                sum = sum + Double.parseDouble(d.toString());
            }
            if(abs(sum+val) > abs(parentVal)) {
                columns.put(this.getMetaValues("header")[this.nextColumn()],parentVal-sum); //Credit price
            } else {
                columns.put(this.getMetaValues("header")[this.nextColumn()],val); //Credit price
            }
            this.setMasterData("CREDIT_PRICE", columns.get("CREDIT_PRICE")); 
        } else {
            double m = dg.getNumberBetween(10, 90)/100.0;
            columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getItem(Double.parseDouble(this.getParentSchema("VAITM").getMapItem("CREDIT_PRICE").toString())*m,0.247,this.getParentSchema("VAITM").getMapItem("CREDIT_PRICE")));
        }        
        columns.put(this.getMetaValues("header")[this.nextColumn()],columns.get("CREDIT_PRICE")); //Credit active
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getItem(this.getMasterData("Usage", this.getColumnNumber()-1, this.getCurrentRow(), "B" + dg.getNumberBetween(10, 99).toString()),0.3857,"B0B")); //Usage
        columns.put(this.getMetaValues("header")[this.nextColumn()],"GB" + dg.getRandomChars(1).toUpperCase() + this.fillString(dg.getNumberUpTo(999).toString(),"0",3)); //DisO
        columns.put(this.getMetaValues("header")[this.nextColumn()],columns.get("CTRY")); //DCOr
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getItem("A",0.46,null)); //MPr
        if(this.getCurrentRow() == 1) {
            columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getRandomChars(2).toUpperCase()); //RO
        } else {
            columns.put(this.getMetaValues("header")[this.nextColumn()],this.getLastMapValue("RO")); //RO
        }
        if(columns.get("RET") == "X") {
            if(Double.parseDouble(columns.get("NET_VALUE").toString()) > 0) {
                columns.put(this.getMetaValues("header")[this.nextColumn()],"K"); //DocCa
            } else {
                columns.put(this.getMetaValues("header")[this.nextColumn()],"L"); //DocCa
            }
        } else {
            columns.put(this.getMetaValues("header")[this.nextColumn()],"C"); //DocCa
        }
        columns.put(this.getMetaValues("header")[this.nextColumn()],Double.parseDouble(columns.get("NET_VALUE").toString())*0.2); //Tax
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getParentSchema("VAHDR").getMapItem("ORDRS")); //OrdRs
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getDate((Date)columns.get("CREATED_ON"), this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]))); //TranslDate

        this.setMap(columns);
        
        //Check if Child has new Parent
        if(dg.getItem(1,0.52,0) == 1){
            this.setForeignKey(foreignKey++);
            this.setNextForeignKeyCheck(true);
        } else {
            this.setNextForeignKeyCheck(false);
        }
        
        if(this.getRowCount() == this.getCurrentRow()) {
            this.dropMasterData("BILLED_QUANTITY");
            this.dropMasterData("NET_VALUE"); 
            this.dropMasterData("COST");
            this.dropMasterData("VALUE_OF_INVGROSS");
            this.dropMasterData("VALUE_wo_FREIGHT");
            this.dropMasterData("VALUE_OF_ALL_BILLS");
            this.dropMasterData("FREIGHT_COST"); 
            this.dropMasterData("SUBTOTAL_5");
            this.dropMasterData("BONUS_PAID");
            this.dropMasterData("CREDIT_PRICE");
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

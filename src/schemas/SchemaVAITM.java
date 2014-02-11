package schemas;

/*
 * Copyright 2013, Martin Kleehaus
 * Technical University of Munich (TUM)
 * Professorship for Database Systems and Applications
 */

import datagenerator.DataGenerator;
import static java.lang.Math.abs;
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
        
        if(this.getCurrentRow() == 1) {
            this.setForeignKey(foreignKey++);
            this.setPrimaryKey(this.getDefaultPrimaryKey());
            this.dropMasterData("Net value");
        }
  
        this.setColumnNumber(0);
        
        Map<String, Object> columns = new HashMap<>();   
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAHDR").getMapItem("SALES_DOC")); //Sales Doc
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getNextPrimaryKey().toString() + "0"); //Item
        columns.put(this.getMetaValues("header")[this.nextColumn()],tData.getMaterial(this.parseDouble(this.getMetaValues("sfactor")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("change")[this.getColumnNumber()-1]), this.getCurrentRow(), this.getLastMapValue(this.getMetaValues("header")[this.getColumnNumber()-1]),Integer.MAX_VALUE)); //Material
        columns.put(this.getMetaValues("header")[this.nextColumn()],tData.getMaterial("MATERIAL_ENTERED", columns.get("MATERIAL").toString())); //Material entered
        columns.put(this.getMetaValues("header")[this.nextColumn()],tData.getMaterial("MATL_GROUP", columns.get("MATERIAL").toString())); //Material group
        columns.put(this.getMetaValues("header")[this.nextColumn()],tData.getMaterial("DESCRIPTION", columns.get("MATERIAL").toString())); //Description
        columns.put(this.getMetaValues("header")[this.nextColumn()],tData.getMaterial("ITCA", columns.get("MATERIAL").toString())); //ItCa
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getItem("X",0.15,null));
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getLastMapValue(dg.getItem(bil),this.getMetaValues("header")[this.getColumnNumber()-1], this.parseDouble(this.getMetaValues("change")[this.getColumnNumber()-1]))); //BilRl
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getLastMapValue(dg.getItem(dg.getRandomChars(2),0.05,null),this.getMetaValues("header")[this.getColumnNumber()-1], this.parseDouble(this.getMetaValues("change")[this.getColumnNumber()-1]))); //Rj
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getLastMapValue(dg.getNumberText(12),this.getMetaValues("header")[this.getColumnNumber()-1], this.parseDouble(this.getMetaValues("change")[this.getColumnNumber()-1]))); //Product Hierarchy
        columns.put(this.getMetaValues("header")[this.nextColumn()],1); //onversion factor
        columns.put(this.getMetaValues("header")[this.nextColumn()],columns.get("UOM")); //Bun
        String poItem = new String();
        for(int i = 1; i<=(6-columns.get("Item").toString().length() );i++) {
            poItem = poItem + "0";
        }
        columns.put(this.getMetaValues("header")[this.nextColumn()],poItem+columns.get("ITEM").toString()); //POItem
        columns.put(this.getMetaValues("header")[this.nextColumn()],tData.getMaterial("CUSTOMER_MATERIAL_NUMBER", columns.get("MATERIAL").toString())); //Customer Material Number
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getItem(this.getMasterData("USAGE", this.getColumnNumber()-1, this.getCurrentRow(), "B" + dg.getNumberBetween(10, 99).toString()),0.014,"B0B")); //Usage
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getNumber(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]))); //Overdel Tol
        columns.put(this.getMetaValues("header")[this.nextColumn()],columns.get("OVERDEL_TOL")); //Underdel Tol
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getLastMapValue(dg.getNumberUpTo(9)*10,this.getMetaValues("header")[this.getColumnNumber()-1], this.parseDouble(this.getMetaValues("change")[this.getColumnNumber()-1]))); //Dv
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getLastMapValue(dg.getNumberBetween(1000, 9999),this.getMetaValues("header")[this.getColumnNumber()-1], this.parseDouble(this.getMetaValues("change")[this.getColumnNumber()-1]))); //BusA
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAHDR").getMapItem("PRB")); //Prb
        if(this.getRowCount() > 1) {
            Double val = 0d;
            if(this.getParentSchema("VAHDR").getMapItem("RET") == "X") {
                val = dg.getCurrency(6149.3250, 39806.9660);
            } else {
                val = dg.getCurrency(-182.1036, 146.5462);
            }
            Double parentVal = Double.parseDouble(this.getParentSchema("VAHDR").getMapItem("NET_VALUE").toString());
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
            columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAHDR").getMapItem("NET_VALUE"));
        }
        this.setMasterData("NET_VALUE", columns.get("NET_VALUE")); 
        
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getLastMapValue(dg.getItem(curr), this.getMetaValues("header")[this.getColumnNumber()-1], 0.05)); //Curr
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getDecimal(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]),1)); //Order Quantity
        columns.put(this.getMetaValues("header")[this.nextColumn()],columns.get("ORDER_QUANTITY")); //Required deliv. qty
        columns.put(this.getMetaValues("header")[this.nextColumn()],columns.get("ORDER_QUANTITY")); //Cumul.confirmed qty
        columns.put(this.getMetaValues("header")[this.nextColumn()],columns.get("UOM")); //Su
        String WUn = dg.getItem("TO",0.66,"KG");
        columns.put(this.getMetaValues("header")[this.nextColumn()],Double.parseDouble(columns.get("ORDER_QUANTITY").toString()) * convert.get(columns.get("SU") + WUn)); //Gross Weight
        columns.put(this.getMetaValues("header")[this.nextColumn()],columns.get("GROSS_WEIGHT")); //Net Weight
        columns.put(this.getMetaValues("header")[this.nextColumn()],WUn); //WUn
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getItem("X",0.33,null)); //U
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getItem("B",0.29,null)); //ComRI
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getRange(prio)); //Prio
        columns.put(this.getMetaValues("header")[this.nextColumn()],columns.get("BUSA")); //PInt
        columns.put(this.getMetaValues("header")[this.nextColumn()],columns.get("BUSA")); //ShpT
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getMasterData("ROUTE", this.getColumnNumber()-1, this.getCurrentRow(), "GB" + this.fillString(dg.getNumberUpTo(9999).toString(), "0", 4))); //Route
        if(this.getCurrentRow() == 1) {
            baseDate = (Date)this.getParentSchema("VAHDR").getMapItem("CREATED_ON");
            columns.put(this.getMetaValues("header")[this.nextColumn()],baseDate); //Created on
        } else {
            columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getDate(baseDate, this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]))); //Created on            
        }
        columns.put(this.getMetaValues("header")[this.nextColumn()],tData.getUser(this.parseDouble(this.getMetaValues("sfactor")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("change")[this.getColumnNumber()-1]), this.getCurrentRow(),this.getLastMapValue(this.getMetaValues("header")[this.getColumnNumber()-1]),Integer.MAX_VALUE)); //created by
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getTime(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]))); //Time
        if(columns.get("COMRL") != null && columns.get("COMRL") != "B") {
            columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getDate((Date)columns.get("CREATED_ON"), this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]))); //BOM key dt.
        } else {
            columns.put(this.getMetaValues("header")[this.nextColumn()],null); //BOM key dt.
        }
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]))); //net Price
        Object uomstr = this.getLastMapValue(dg.getItem(uom),this.getMetaValues("header")[this.getColumnNumber()-1], this.parseDouble(this.getMetaValues("change")[this.getColumnNumber()-1])); //Uom
        if("MIN".equals(uomstr.toString())) {
            columns.put(this.getMetaValues("header")[this.nextColumn()],60); //per
        } else {
            columns.put(this.getMetaValues("header")[this.nextColumn()],1); //per
        }
        columns.put(this.getMetaValues("header")[this.nextColumn()], uomstr); //UoM
        if(Double.parseDouble(columns.get("NET_VALUE").toString()) < 0) {
            columns.put(this.getMetaValues("header")[this.nextColumn()],"X"); //Ret
        } else {
            columns.put(this.getMetaValues("header")[this.nextColumn()],null); //Ret
        }
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getMasterData("AAG", this.getColumnNumber()-1, this.getCurrentRow(), dg.getNumberBetween(10, 99).toString())); //AAG
        if(columns.get("Ret") != null && "X".equals(columns.get("RET").toString())) {
            columns.put(this.getMetaValues("header")[this.nextColumn()],"A"); //OK
        } else {
            columns.put(this.getMetaValues("header")[this.nextColumn()],null); //OK
        }
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]),Double.parseDouble(columns.get("NET_VALUE").toString()))); //Cost
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]),Double.parseDouble(columns.get("NET_VALUE").toString()))); //Value of inv.gross
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]),Double.parseDouble(columns.get("NET_VALUE").toString()))); //Value w/o freight
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]))); //Value of all bills
        columns.put(this.getMetaValues("header")[this.nextColumn()],columns.get("NET_VALUE")); //Freight cost and r
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]))); //Subtotal 5
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]))); //Bonus paid
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getDate((Date)columns.get("CREATED_ON"), this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]))); //changed on
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getMasterData("PROFIT_CTR", this.getColumnNumber()-1, this.getCurrentRow(), this.fillString(dg.getNumberUpTo(1000000000).toString(), "0", 10))); //Profit Center
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getMasterData("MG_2", this.getColumnNumber()-1, this.getCurrentRow(), dg.getItem("B" + this.fillString(dg.getNumberUpTo(99).toString(), "0", 2),0.33,null))); //MG 2
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getMasterData("MG_3", this.getColumnNumber()-1, this.getCurrentRow(), dg.getItem("B" + this.fillString(dg.getNumberUpTo(99).toString(), "0", 2),0.20,null))); //MG 3
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getMasterData("MG_4", this.getColumnNumber()-1, this.getCurrentRow(), dg.getItem("B" + this.fillString(dg.getNumberUpTo(99).toString(), "0", 2),0.08,null))); //MG 4
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getMasterData("MG_5", this.getColumnNumber()-1, this.getCurrentRow(), "B" + this.fillString(dg.getNumberUpTo(99).toString(), "0", 2))); //MG 5
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getItem("ZGB1",0.061,null)); //Reasn
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getItem("E",0.053,null)); //S
        if(columns.get("RET") != null && "X".equals(columns.get("RET").toString())) {
            columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getCurrency(-13.9147, 17.6974)); //Credit Price
            columns.put(this.getMetaValues("header")[this.nextColumn()],null); //Credit active
        } else {
            columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getCurrency(86.2176, 65.1477)); //Credit Price
            columns.put(this.getMetaValues("header")[this.nextColumn()],"X"); //Credit active
        }
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getItem("A",0.568,null)); //MPr
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getMasterData("PRCAT", this.getColumnNumber()-1, this.getCurrentRow(), dg.getItem(dg.getRandomChars(1).toUpperCase(),0.6294,null))); //PrCat
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getItem(dg.getNumberBetween(100000000, 122213206),0.5036,0)); //Cost EastNo
        if(!"0".equals(columns.get("COST_EST_NO").toString())) {
            columns.put(this.getMetaValues("header")[this.nextColumn()],"PPC4"); //Costing Variant
        } else {
            columns.put(this.getMetaValues("header")[this.nextColumn()],null); //Costing Variant
        }
        columns.put(this.getMetaValues("header")[this.nextColumn()],Double.parseDouble(columns.get("NET_VALUE").toString())*0.20); //Tax Amount
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getDecimal(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]),3)); //Distance
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getItem("MI",0.6267,"KM")); //Dis
        if(columns.get("CREDIT_ACTIVE") != null && "X".equals(columns.get("CREDIT_ACTIVE").toString())) {
            columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getItem(order));
            columns.put(this.getMetaValues("header")[this.nextColumn()],columns.get("PLNT"));
        } else {
            columns.put(this.getMetaValues("header")[this.nextColumn()],null);
            columns.put(this.getMetaValues("header")[this.nextColumn()],null);            
        }
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getDecimal(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]),3)); //Minimum
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getDecimal(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]),3)); //Maximum
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getDecimal(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]),3)); //Max Load
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getDecimal(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]),3)); //Min Load
        if(columns.get("CREDIT_ACTIVE") != null && "X".equals(columns.get("CREDIT_ACTIVE").toString())) {
            columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getDate((Date)columns.get("CREATED_ON"), this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]))); //First Load
        } else {
            columns.put(this.getMetaValues("header")[this.nextColumn()],null);
        }
        this.setMap(columns);

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

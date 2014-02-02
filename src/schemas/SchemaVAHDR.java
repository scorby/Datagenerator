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
public class SchemaVAHDR extends Schemas  {
    
    private Integer primaryKey = 3405000;
    
    @Override
    public void setUniqueNumber() {
        DataGenerator dg = DataGenerator.getInstance();
        dg.setUniqueNumber(3405000);
    }
    
    @Override
    public Map<String, Object> getData() throws Exception{
        DataGenerator dg = DataGenerator.getInstance();
        TableData tData = TableData.getInstance();
        
        String[] curr = {"EUR","GBP","US"};
        String[] sorg = {"GB30","GB40","GB50", "GB60"};
        String[] dchl = {"01","02","03"};
        String[] risk = {"GBA","GBC","GBH","GBM","GBP"};
        
        Map<String, Double> docCa = new HashMap<>();
        docCa.put("B", 0.55);
        docCa.put("C", 0.89);
        docCa.put("L", 0.898);
        docCa.put("K", 1.00);
        
        Map<String, Double> sc = new HashMap<>();
        sc.put("01", 0.895);
        sc.put("11", 0.995);
        sc.put("07", 1.000);
        
        this.setColumnNumber(0);
        
        Map<String, Object> columns = new HashMap<>();        
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getNextPrimaryKey().toString()); //Item
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getRange(docCa)); //DocCa
//        if(this.getSubschema("VAITM").getNetvalue() < 0) {
//            columns.put(this.getMetaValues("header")[this.nextColumn()],"L"); //DocCa
//        } else {
//            if(this.getSubschema("VAITM").getMapItem("Prb") != "100") {
//                columns.put(this.getMetaValues("header")[this.nextColumn()],"B"); //DocCa
//            } else {
//                if(this.getSubschema("VAITM").getMapItem("Ret") == "X") {
//                    columns.put(this.getMetaValues("header")[this.nextColumn()],"K"); //DocCa
//                } else {
//                    columns.put(this.getMetaValues("header")[this.nextColumn()],"C"); //DocCa
//                }
//            }
//        }
        //columns.put(this.getMetaValues("header")[this.nextColumn()],this.getSubschema("VAITM").getMapItem("Created on")); //Created on

        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getDateBetween(dg.getDate(2013, 1, 1), dg.getDate(2015,12,31))); //Created on
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getTime(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]))); //time
        columns.put(this.getMetaValues("header")[this.nextColumn()],tData.getUser(this.parseDouble(this.getMetaValues("sfactor")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("change")[this.getColumnNumber()-1]), this.getCurrentRow(),this.getLastMapValue(this.getMetaValues("header")[this.getColumnNumber()-1]),Integer.MAX_VALUE)); //created by
        if(columns.get("DocCa") == "B") {
            columns.put(this.getMetaValues("header")[this.nextColumn()],columns.get("Created on")); //Valid fr
            columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getDate(9999, 12, 31)); //Valid to
        } else {
            columns.put(this.getMetaValues("header")[this.nextColumn()],null); //Valid fr
            columns.put(this.getMetaValues("header")[this.nextColumn()],null); //Valid to            
        }
        columns.put(this.getMetaValues("header")[this.nextColumn()],columns.get("Created on")); //Doc Date

        docCateg dc = docCateg.valueOf(columns.get("DocCa").toString());
        switch (dc) {
            case B:
                columns.put(this.getMetaValues("header")[this.nextColumn()],2); //TrG
                columns.put(this.getMetaValues("header")[this.nextColumn()],"YQ02"); //SaTy
                break;
            case C:
                columns.put(this.getMetaValues("header")[this.nextColumn()],0); //TrG
                columns.put(this.getMetaValues("header")[this.nextColumn()],"YO1" + dg.getNumberBetween(2, 5).toString()); //SaTy
                break;
            case K:
                columns.put(this.getMetaValues("header")[this.nextColumn()],0); //TrG
                columns.put(this.getMetaValues("header")[this.nextColumn()],"YK03"); //SaTy
                break;
            case L:
                columns.put(this.getMetaValues("header")[this.nextColumn()],0); //TrG
                columns.put(this.getMetaValues("header")[this.nextColumn()],"YI04"); //SaTy
                break;     
        }
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getMasterData("ordrs", this.getColumnNumber()-1, this.getCurrentRow(), dg.getItem("B0" + dg.getNumberUpTo(9).toString(),0.7,null))); //OrdRs
        //Double sum = 0d;
//        for(Object d : this.getSubschema("VAITM").getMasterData("Net value").toArray()) {
//            sum = sum + Double.parseDouble(d.toString()); //Net value
//        }
        //columns.put(this.getMetaValues("header")[this.nextColumn()],sum); //Net value
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getItem(dg.getCurrency(25664.2536, 105343.7039),0.89,dg.getCurrency(-71.8258, 51.2785))); //Net value
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getLastMapValue(dg.getItem(curr), this.getMetaValues("header")[this.getColumnNumber()-1], 0.05)); //Curr
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getLastMapValue(dg.getItem(sorg), this.getMetaValues("header")[this.getColumnNumber()-1], 0.05)); //SOrg
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getLastMapValue(dg.getItem(dchl), this.getMetaValues("header")[this.getColumnNumber()-1], 0.05)); //DChl
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getLastMapValue(dg.getNumberUpTo(9)*10,this.getMetaValues("header")[this.getColumnNumber()-1], this.parseDouble(this.getMetaValues("change")[this.getColumnNumber()-1]))); //Dv
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getLastMapValue(dg.getNumberUpTo(9)*10,this.getMetaValues("header")[this.getColumnNumber()-1], this.parseDouble(this.getMetaValues("change")[this.getColumnNumber()-1]))); //Sgrp
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getLastMapValue(dg.getNumberUpTo(9)*10,this.getMetaValues("header")[this.getColumnNumber()-1], this.parseDouble(this.getMetaValues("change")[this.getColumnNumber()-1]))); //Soff
        columns.put(this.getMetaValues("header")[this.nextColumn()],"00" + dg.getNumberBetween(20000000, 29999999).toString()); //Doccond
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getDate((Date)columns.get("Created on"), this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]))); // Req.dlv.dt
        columns.put(this.getMetaValues("header")[this.nextColumn()], "1"); //
        if (columns.get("DocCa") == "C") {
            columns.put(this.getMetaValues("header")[this.nextColumn()],"F"); //I
        } else if (columns.get("DocCa") == "L") {
            columns.put(this.getMetaValues("header")[this.nextColumn()],"D"); //I
        } else {
            columns.put(this.getMetaValues("header")[this.nextColumn()],null); //I
        }
        columns.put(this.getMetaValues("header")[this.nextColumn()], "ZGB100"); //PriPbr
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getRange(sc)); //SC
        if (columns.get("DocCa") == "B") {
            columns.put(this.getMetaValues("header")[this.nextColumn()],"F5"); //OrBit
        } else if (columns.get("DocCa") == "K") {
            columns.put(this.getMetaValues("header")[this.nextColumn()],"YK03"); //OrBit
        } else if (columns.get("DocCa") == "L") {
            columns.put(this.getMetaValues("header")[this.nextColumn()],"YD03"); //OrBit
        } else {
            columns.put(this.getMetaValues("header")[this.nextColumn()],null); //OrBit
        }
        //columns.put(this.getMetaValues("header")[this.nextColumn()],this.getSubschema("VAITM").getMapItem("Prb")); //Prb
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getItem(dg.getNumberUpTo(9)*10,0.34,100)); //Prb
        if ("100".equals(columns.get("Prb").toString())) {
            columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getRandomChars(2, 10) + "/" + dg.getNumberUpTo(10000).toString()); //Order number
        }else {
            columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getItem(dg.getRandomChars(2, 10) + "/" + dg.getNumberUpTo(10000).toString(),0.10,null)); //Order number
        }
        if ("100".equals(columns.get("Prb").toString())) {
            columns.put(this.getMetaValues("header")[this.nextColumn()],this.getMasterData("potyp", this.getColumnNumber()-1, this.getCurrentRow(), dg.getItem(dg.getRandomChars(4).toUpperCase(),0.60,null))); //potyp
        } else {
             columns.put(this.getMetaValues("header")[this.nextColumn()],null); //potyp
        }
        columns.put(this.getMetaValues("header")[this.nextColumn()],columns.get("Created on")); //PO Date
        columns.put(this.getMetaValues("header")[this.nextColumn()],tData.getSoldto(this.parseDouble(this.getMetaValues("sfactor")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("change")[this.getColumnNumber()-1]), this.getCurrentRow(),this.getLastMapValue(this.getMetaValues("header")[this.getColumnNumber()-1]),Integer.MAX_VALUE)); //Sold-to pt
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getItem(dg.getLastName(),0.90,null)); //Name of the orderer
        columns.put(this.getMetaValues("header")[this.nextColumn()],tData.getSoldto("telephone", columns.get("Sold-to pt").toString()));
        columns.put(this.getMetaValues("header")[this.nextColumn()],columns.get("Created on")); //Last cont
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getDate((Date)columns.get("Created on"), this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]))); //changed on
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getLastMapValue("GB" + dg.getNumberUpTo(9) + "0",this.getMetaValues("header")[this.getColumnNumber()-1],0.5)); //COAr
        columns.put(this.getMetaValues("header")[this.nextColumn()],"GB00"); //CAr
        columns.put(this.getMetaValues("header")[this.nextColumn()],tData.getSoldto("Cred acct", columns.get("Sold-to pt").toString())); //Cred acct
        columns.put(this.getMetaValues("header")[this.nextColumn()],tData.getSoldto("Cred", columns.get("Sold-to pt").toString())); //Cred
        columns.put(this.getMetaValues("header")[this.nextColumn()],tData.getSoldto("Credrepgrp", columns.get("Sold-to pt").toString())); //Cred.rep.grp
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getLastMapValue(dg.getItem(risk),this.getMetaValues("header")[this.getColumnNumber()-1],this.parseDouble(this.getMetaValues("change")[this.getColumnNumber()-1]))); //risk
        if ("100".equals(columns.get("Prb").toString())) {
            columns.put(this.getMetaValues("header")[this.nextColumn()],"A"); //HPr
        }else {
            columns.put(this.getMetaValues("header")[this.nextColumn()],null); //HPr
        }
        if (columns.get("DocCa") == "C") {
            columns.put(this.getMetaValues("header")[this.nextColumn()],null); //Usage
        } else if (columns.get("DocCa") == "L") {
            columns.put(this.getMetaValues("header")[this.nextColumn()],"B0B"); //Usage
        } else if (columns.get("DocCa") == "K") {
            columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getItem("B0B",0.50,"B0E")); //Usage
        } else {
            columns.put(this.getMetaValues("header")[this.nextColumn()],"B" + dg.getNumberBetween(10, 99).toString()); //Usage
        }
        if (columns.get("DocCa") == "C") {
            columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getDate((Date)columns.get("Created on"), this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]))); //MatAvDt
        } else {
            columns.put(this.getMetaValues("header")[this.nextColumn()],columns.get("Created on")); //MatAvDt
        }
            
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
    
    
    private enum docCateg {
        B,
        C,
        K,
        L
    }
}

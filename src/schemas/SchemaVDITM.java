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
public class SchemaVDITM extends Schemas {
    
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
        if(this.getParentSchema("VAITM").getMapItem("Ret") == "X") {
            return null;
        }
        DataGenerator dg = DataGenerator.getInstance();
        TableData tData = TableData.getInstance();
        int foreignKey = 10000;
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
            this.setGrossWeight(0d, false);
            this.setForeignKey(foreignKey++);
        } 
  
        this.setColumnNumber(0);
        
        Map<String, Object> columns = new HashMap<>();   
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getForeignKey()); //Delivery
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getNextPrimaryKey().toString() + "0"); //Item
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("ItCa")); //ItCa
        columns.put(this.getMetaValues("header")[this.nextColumn()],tData.getUser(this.parseDouble(this.getMetaValues("sfactor")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("change")[this.getColumnNumber()-1]), this.getCurrentRow(),this.getLastMapValue(this.getMetaValues("header")[this.getColumnNumber()-1]),Integer.MAX_VALUE)); //created by
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getTime(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]))); //Time
        if(this.getCurrentRow() == 1) {
            baseDate = dg.getDate((Date)this.getParentSchema("VAITM").getMapItem("Created on"), 4.975609756, 11.59880111);
            this.setBaseDate(baseDate);
            columns.put(this.getMetaValues("header")[this.nextColumn()],baseDate); //Created on
        } else {
            columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getDate(baseDate, this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]))); //Created on            
        }
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("Material")); //Material
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("Material entered")); //Material entered
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("Matl Group")); //Matl Group
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("PInt")); //PInt
        if(this.getCurrentRow() == 1) {
            columns.put(this.getMetaValues("header")[this.nextColumn()],"000" + dg.getNumberUpTo(9)); //SLoc
        } else {
            columns.put(this.getMetaValues("header")[this.nextColumn()],null); //SLoc
        }
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("Customer material number")); //Customer material number
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("Product hierarchy")); //Product hierarchy
        if(this.getRowCount() > 1) {
            Double val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]));
            Double parentVal = Double.parseDouble(this.getParentSchema("VAITM").getMapItem("Order Quantity").toString());
            Double sum = 0d;
            for(Object d : this.getMasterData("Delivery quantity").toArray()) {
                sum = sum + Double.parseDouble(d.toString());
            }
            if(sum+val > parentVal) {
                columns.put(this.getMetaValues("header")[this.nextColumn()],parentVal-sum); //Delivery quantity
            } else {
                columns.put(this.getMetaValues("header")[this.nextColumn()],val); //Delivery quantity
            }
            this.setMasterData("Delivery quantity", columns.get("Delivery quantity"));
        } else {
            columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getItem(Double.parseDouble(this.getParentSchema("VAITM").getMapItem("Order Quantity").toString())-dg.getCurrency(42.9352, 56.4912),0.647,this.getParentSchema("VAITM").getMapItem("Order Quantity")));
        }
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("BUn")); //BUn
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("SU")); //SU
        columns.put(this.getMetaValues("header")[this.nextColumn()],Double.parseDouble(columns.get("Delivery quantity").toString()) * convert.get(columns.get("SU") + this.getParentSchema("VAITM").getMapItem("WUn").toString())); //Gross Weight
        this.setMasterData("Gross weight for Header", columns.get("Gross weight")); 
        columns.put(this.getMetaValues("header")[this.nextColumn()],columns.get("Gross weight")); //Net weight
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("WUn")); //WUn
        columns.put(this.getMetaValues("header")[this.nextColumn()],0); //Volume
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("Overdel Tol")); //Overdel. Tol.
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("Underdel Tol")); //Underdel.Tol.
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getParentSchema("VAHDR").getMapItem("Mat Av Dt")); //Mat.Av.Dt.
        columns.put(this.getMetaValues("header")[this.nextColumn()],columns.get("Delivery Quantity")); //Qty (stckpg unit)
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("Description")); //Description
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("Sales Doc")); //Refdoc
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("Item")); //Refitm
        columns.put(this.getMetaValues("header")[this.nextColumn()],null); //U
        columns.put(this.getMetaValues("header")[this.nextColumn()],null); //PckID
        columns.put(this.getMetaValues("header")[this.nextColumn()],null); //MvT
        columns.put(this.getMetaValues("header")[this.nextColumn()],null); //RqTy
        columns.put(this.getMetaValues("header")[this.nextColumn()],null); //PlTyp
        columns.put(this.getMetaValues("header")[this.nextColumn()],null); //MTyp
        columns.put(this.getMetaValues("header")[this.nextColumn()],null); //Preceding document has resulted from ref
        columns.put(this.getMetaValues("header")[this.nextColumn()],null); //Tot.
        columns.put(this.getMetaValues("header")[this.nextColumn()],null); //Av
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("BusA")); //BusA
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("SOff")); //SOff.
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("SGrp")); //SGrp
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("DChl")); //DChl
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("Dv")); //Dv
        columns.put(this.getMetaValues("header")[this.nextColumn()],null); //S
        columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getDate((Date)columns.get("Created on"), this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]))); //changed on
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("MG 2")); //MG 2
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("MG 3")); //MG 3
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("MG 4")); //MG 4
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("MG 5")); //MG 5
        columns.put(this.getMetaValues("header")[this.nextColumn()],"C"); //DocCat
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("COAr")); //COAr
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("Profit Ctr")); //Profit Ctr
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getLastMapValue("0" + dg.getNumberUpTo(99).toString(),this.getMetaValues("header")[this.getColumnNumber()-1], this.parseDouble(this.getMetaValues("change")[this.getColumnNumber()-1]))); //ReqCl
        columns.put(this.getMetaValues("header")[this.nextColumn()],"X"); //Credit active
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getLastMapValue(dg.getNumberUpTo(9999999).toString(),this.getMetaValues("header")[this.getColumnNumber()-1], this.parseDouble(this.getMetaValues("change")[this.getColumnNumber()-1]))); //Configuration
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("Usage")); //Usage
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("per")); //per
        columns.put(this.getMetaValues("header")[this.nextColumn()],this.getParentSchema("VAITM").getMapItem("UoM")); //UoM
        if(this.getRowCount() > 1) {
            Double val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]));
            Double parentVal = Double.parseDouble(this.getParentSchema("VAITM").getMapItem("Net price").toString());
            Double sum = 0d;
            for(Object d : this.getMasterData("Net price").toArray()) {
                sum = sum + Double.parseDouble(d.toString());
            }
            if(sum+val > parentVal) {
                columns.put(this.getMetaValues("header")[this.nextColumn()],parentVal-sum); //Net price
            } else {
                columns.put(this.getMetaValues("header")[this.nextColumn()],val); //Net price
            }
            this.setMasterData("Net price", columns.get("Net price")); 
        } else {
            double m = dg.getNumberBetween(10, 90)/100.0;
            columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getItem(Double.parseDouble(this.getParentSchema("VAITM").getMapItem("Net price").toString())*m,0.247,this.getParentSchema("VAITM").getMapItem("Net price")));
        }
        if(this.getRowCount() > 1) {
            Double val = dg.getCurrency(this.parseDouble(this.getMetaValues("avg")[this.getColumnNumber()-1]), this.parseDouble(this.getMetaValues("std")[this.getColumnNumber()-1]));
            Double parentVal = Double.parseDouble(this.getParentSchema("VAITM").getMapItem("Net value").toString());
            Double sum = 0d;
            for(Object d : this.getMasterData("Net value").toArray()) {
                sum = sum + Double.parseDouble(d.toString());
            }
            if(sum+val > parentVal) {
                columns.put(this.getMetaValues("header")[this.nextColumn()],parentVal-sum); //Net value
            } else {
                columns.put(this.getMetaValues("header")[this.nextColumn()],val); //Net value
            }
            this.setMasterData("Net value", columns.get("Net value")); 
        } else {
            double m = dg.getNumberBetween(10, 90)/100.0;
            columns.put(this.getMetaValues("header")[this.nextColumn()],dg.getItem(Double.parseDouble(this.getParentSchema("VAITM").getMapItem("Net value").toString())*m,0.247,this.getParentSchema("VAITM").getMapItem("Net value")));
        }
        this.setMasterData("Net value for Header", columns.get("Net value")); 
        columns.put(this.getMetaValues("header")[this.nextColumn()],null); //Mvt
        columns.put(this.getMetaValues("header")[this.nextColumn()],null); //MatFrtGp
        columns.put(this.getMetaValues("header")[this.nextColumn()],null); //Stag. Time
        columns.put(this.getMetaValues("header")[this.nextColumn()],null); //Spec. Stk Valuation
        columns.put(this.getMetaValues("header")[this.nextColumn()],null); //Spec.Stock No.
        columns.put(this.getMetaValues("header")[this.nextColumn()],null); //Current quantity
        columns.put(this.getMetaValues("header")[this.nextColumn()],null); //Qty in OPUn
        columns.put(this.getMetaValues("header")[this.nextColumn()],null); //Sales Value
        columns.put(this.getMetaValues("header")[this.nextColumn()],null); //Credit price
        columns.put(this.getMetaValues("header")[this.nextColumn()],null); //Not relevant for WMS
        
        this.setMap(columns);
        
        //Check if Child has new Parent
        if(dg.getItem(1,0.4,0) == 1){
            this.setForeignKey(foreignKey++);
            this.setNextForeignKeyCheck(true);
        } else {
            this.setNextForeignKeyCheck(false);
        }
        
        if(this.getRowCount() == this.getCurrentRow()) {
            this.dropMasterData("Gross weight");

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
        this.rowCount = dg.getNumber(1.2830,0.98168);
        return this.rowCount;
    }
    
    @Override
    public Integer getDefaultPrimaryKey() {
        return 1;
    }
    
}

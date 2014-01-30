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
        DataGenerator dg = DataGenerator.getInstance();
        TableData tData = TableData.getInstance();
        int foreignKey = 10000;
        
        //Definition of MasterData
        String[] bil = {"A","C",null};
        String[] uom = {"M3","MIN","TO","KG"};
        String[] curr = {"EUR","GBP","US"};
        String[] order = {"01","02","03"};
        
        Map<String, Double> prio = new HashMap<>();
        prio.put("0", 0.705);
        prio.put("1", 0.805);
        prio.put("2", 0.940);
        prio.put("3", 0.996);
        prio.put("11", 1.0);
        
        if(this.getCurrentRow() == 1) {
            this.setForeignKey(foreignKey++);
            this.setPrimaryKey(this.getDefaultPrimaryKey());
        } else {
            if(dg.getNumber(2.5758,4.4582) > this.getRowCount()){
                this.setForeignKey(foreignKey++);
            }
        }
        
        this.setCurrentRow();
        int cnt = 0;
        
        Map<String, Object> columns = new HashMap<>();   
        columns.put(this.getMetaValues("header")[cnt++],this.getForeignKey()); //Delivery
        columns.put(this.getMetaValues("header")[cnt++],this.getNextPrimaryKey().toString() + "0"); //Item
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VAITM").getMapItem("ItCa")); //ItCa
        columns.put(this.getMetaValues("header")[cnt++],tData.getUser(this.parseDouble(this.getMetaValues("sfactor")[cnt-1]), this.parseDouble(this.getMetaValues("change")[cnt-1]), this.getCurrentRow(),this.getLastMapValue(this.getMetaValues("header")[cnt - 1]),Integer.MAX_VALUE)); //created by
        columns.put(this.getMetaValues("header")[cnt++],dg.getTime(this.parseDouble(this.getMetaValues("avg")[cnt-1]), this.parseDouble(this.getMetaValues("std")[cnt-1]))); //Time
        columns.put(this.getMetaValues("header")[cnt++],dg.getDate((Date)this.getSubschema("VAITM").getMapItem("Created on"), this.parseDouble(this.getMetaValues("avg")[cnt-1])*(-1), this.parseDouble(this.getMetaValues("std")[cnt-1]))); //Created on
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VAITM").getMapItem("Material")); //Material
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VAITM").getMapItem("Material entered")); //Material entered
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VAITM").getMapItem("Matl Group")); //Matl Group
        if(this.getCurrentRow() == 1) {
            columns.put(this.getMetaValues("header")[cnt++],"000" + dg.getNumberUpTo(9)); //SLoc
        } else {
            columns.put(this.getMetaValues("header")[cnt++],null); //SLoc
        }
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VAITM").getMapItem("Customer material number")); //Customer material number
        columns.put(this.getMetaValues("header")[cnt++],this.getSubschema("VAITM").getMapItem("Product hierarchy")); //Product hierarchy
        
        columns.put(this.getMetaValues("header")[cnt++],tData.getMaterial(this.parseDouble(this.getMetaValues("sfactor")[cnt-1]), this.parseDouble(this.getMetaValues("change")[cnt-1]), this.getCurrentRow(), this.getLastMapValue(this.getMetaValues("header")[cnt - 1]),Integer.MAX_VALUE)); //Material
        columns.put(this.getMetaValues("header")[cnt++],tData.getMaterial("Material entered", columns.get("Material").toString())); //Material entered
        columns.put(this.getMetaValues("header")[cnt++],tData.getMaterial("Matl group", columns.get("Material").toString())); //Material group
        columns.put(this.getMetaValues("header")[cnt++],tData.getMaterial("Description", columns.get("Material").toString())); //Description
        columns.put(this.getMetaValues("header")[cnt++],tData.getMaterial("ItCa", columns.get("Material").toString())); //ItCa
        columns.put(this.getMetaValues("header")[cnt++],dg.getItem("X",0.15,null));
        columns.put(this.getMetaValues("header")[cnt++],this.getLastMapValue(dg.getItem(bil),this.getMetaValues("header")[cnt - 1], this.parseDouble(this.getMetaValues("change")[cnt - 1]))); //BilRl
        columns.put(this.getMetaValues("header")[cnt++],this.getLastMapValue(dg.getItem(dg.getRandomChars(2),0.05,null),this.getMetaValues("header")[cnt - 1], this.parseDouble(this.getMetaValues("change")[cnt - 1]))); //Rj
        columns.put(this.getMetaValues("header")[cnt++],this.getLastMapValue(dg.getNumberText(12),this.getMetaValues("header")[cnt - 1], this.parseDouble(this.getMetaValues("change")[cnt - 1]))); //Product Hierarchy
        columns.put(this.getMetaValues("header")[cnt++],this.getLastMapValue(dg.getItem(uom),this.getMetaValues("header")[cnt - 1], this.parseDouble(this.getMetaValues("change")[cnt - 1]))); //Uom
        columns.put(this.getMetaValues("header")[cnt++],1); //onversion factor
        columns.put(this.getMetaValues("header")[cnt++],columns.get("UoM")); //Bun

        //Delivery quantity
        //BUn
        //SU
        //Net weight
        //Gross weight
        //WUn
        //Volume
        //Overdel. Tol.
        //Underdel.Tol.
        //Mat.Av.Dt.
        //Qty (stckpg unit)
        //Description
        //OriginDoc.
        //Item
        //Ref.doc.
        //RefItm
        //U
        //PckID
        //MvT
        //MTy
        //RqTy
        //PlTyp
        //MTyp
        //Preceding document has resulted from ref
        //Tot.
        //Av
        //BusA
        //SOff.
        //SGrp
        //DChl
        //Dv
        //S
        //Chngd on
        //pack
        //MG 2
        //MG 5
        //DocCa
        //COAr
        //Prof. Seg.
        //Profit Ctr
        //A
        //ReqCl
        //Credit active
        //Configuration
        //Usage
        //ObjNo Hdr
        //ObjNo Item
        //Internal field/ Do not use / LFIMG in float / MUM
        //Internal field/ Do not use / LGMNG in float / MUM
        //per
        //UoM
        //Net price
        //Net Value
        //Mvt
        //MatFrtGp
        //Stag. Time
        //Spec. Stk Valuation
        //Spec.Stock No.
        //Current quantity
        //Qty in OPUn
        //Sales Value
        //Credit price
        //Not relevant for WMS

        
        
        this.setMap(columns);
        
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
        this.rowCount = dg.getNumber(1.2830,0.98168);
        return this.rowCount;
    }
    
    @Override
    public Integer getDefaultPrimaryKey() {
        return 1;
    }
    
}

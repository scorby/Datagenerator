package schemas;

/*
 * Copyright 2013, Martin Kleehaus
 * Technical University of Munich (TUM)
 * Professorship for Database Systems and Applications
 */

import datagenerator.DataGenerator;
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
        Calendar c = Calendar.getInstance(); 
        
        if(this.getParentSchema("VAITM").getMapItem("DOC_NUMBER") != this.getMapItem("REFER_DOC")) {
            this.setPrimaryKey(this.getDefaultPrimaryKey());
            if(this.getForeignKey() == null) {
                this.setForeignKey(40500);
            } else {
                this.setForeignKey(this.getForeignKey() + 1);
            }
        }
        
        //Build header values
        int cnt = 0;
        
        Map<String, Object> columns = new HashMap<>();  
        columns.put(this.getMetaValues("header")[cnt++],dg.getNumberBetween(100, 1000));
        c.setTime((Date)this.getParentSchema("VAITM").getMapItem("CREATEDON")); 
        c.add(Calendar.DAY_OF_MONTH, dg.getItem(1, 50, 0));
        columns.put(this.getMetaValues("header")[cnt++],this.getLastMapValue(dg.getDateBetween(c.getTime(), dg.getDate(2013,12,31)),"ACT_GI_DTE",80));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("BASE_UOM"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("BATCH"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("BILLTOPRTY"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("BILL_BLOCK"));
        columns.put(this.getMetaValues("header")[cnt++],"BU" + dg.getNumberBetween(1, 9).toString() + "0");
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("BWAPPLNM"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("CH_ON"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("COMP_CODE"));
        columns.put(this.getMetaValues("header")[cnt++],this.getLastMapValue(dg.getNumberBetween(1, 9),"CONSU_FLAG",80));
        columns.put(this.getMetaValues("header")[cnt++],this.getLastMapValue(dg.getLastName(),"CREATEDBY",80));
        columns.put(this.getMetaValues("header")[cnt++],dg.getDateBetween(c.getTime(), (Date)columns.get("ACT_GI_DTE")));
        columns.put(this.getMetaValues("header")[cnt++],dg.getDateBetween(new Date(28800000),new Date(64800000)));
        columns.put(this.getMetaValues("header")[cnt++],dg.getNumberBetween(1000000000, 2000000000));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("CUST_GROUP"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("CUST_GRP1"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("CUST_GRP2"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("CUST_GRP3"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("CUST_GRP4"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("CUST_GRP5"));
        columns.put(this.getMetaValues("header")[cnt++],this.getForeignKey());
        columns.put(this.getMetaValues("header")[cnt++],this.getNextPrimaryKey().toString() + "0");
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("DEL_BLOCK"));
        columns.put(this.getMetaValues("header")[cnt++],"1");
        columns.put(this.getMetaValues("header")[cnt++],dg.getNumberBetween(1, 9).toString() + "000");
        columns.put(this.getMetaValues("header")[cnt++],dg.getItem(0,80,dg.getNumberBetween(1, 20)));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("DENOMINTR"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("DISTR_CHAN"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("DIVISION"));
        columns.put(this.getMetaValues("header")[cnt++],columns.get("ACT_DL_QTY"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("DOC_CATEG"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("EANUPC"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("FISCVARNT"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("FORWAGENT"));
        columns.put(this.getMetaValues("header")[cnt++],columns.get("ACT_GI_DTE"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("GN_R3_SSY"));
        columns.put(this.getMetaValues("header")[cnt++],this.getLastMapValue(Character.toUpperCase(dg.getRandomChar()),"GOODSMV_ST",80));
        columns.put(this.getMetaValues("header")[cnt++],dg.getNumberBetween(10, 500));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("INCOTERMS"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("INCOTERMS2"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("ITEM_CATEG"));
        columns.put(this.getMetaValues("header")[cnt++],Character.toUpperCase(dg.getRandomChar()));
        columns.put(this.getMetaValues("header")[cnt++],this.getLastMapValue(dg.getRandomChars(2).toUpperCase(),"LOAD_PT",80));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("MATERIAL"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("MATL_GROUP"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("MATL_GRP_1"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("MATL_GRP_2"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("MATL_GRP_3"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("MATL_GRP_4"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("MATL_GRP_5"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("MAT_ENTRD"));
        columns.put(this.getMetaValues("header")[cnt++],Integer.parseInt(columns.get("GRS_WGT_DL").toString())*0.95);
        columns.put(this.getMetaValues("header")[cnt++],"1");
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("NUMERATOR"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("PAYER"));
        columns.put(this.getMetaValues("header")[cnt++],null);
        columns.put(this.getMetaValues("header")[cnt++],null);
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("PLANT"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("PROCESSKEY"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("PROD_HIER"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("PRVDOC_CTG"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("RECORDMODE"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("DOC_NUMBER"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("S_ORD_ITEM"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("ROUTE"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("RT_PROMO"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("SALESEMPLY"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("SALESORG"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("SALES_DIST"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("SALES_GRP"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("SALES_OFF"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("SALES_UNIT"));
        columns.put(this.getMetaValues("header")[cnt++],dg.getDateBetween((Date) columns.get("ACT_GI_DTE"), dg.getDate(2013,12,31)));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("SHIP_POINT"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("SHIP_TO"));
        columns.put(this.getMetaValues("header")[cnt++],dg.getDecimal(2,100,1));
        columns.put(this.getMetaValues("header")[cnt++],dg.getDecimal(2,100,1));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("SOLD_TO"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("STAT_DATE"));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("STOR_LOC"));
        columns.put(this.getMetaValues("header")[cnt++],null);
        columns.put(this.getMetaValues("header")[cnt++],this.getLastMapValue(dg.getRandomChars(3).toUpperCase(),"STRGE_TYPE",80));
        columns.put(this.getMetaValues("header")[cnt++],dg.getItem("F",90,"W"));
        columns.put(this.getMetaValues("header")[cnt++],columns.get("CREATEDON"));
        columns.put(this.getMetaValues("header")[cnt++],"KG");
        columns.put(this.getMetaValues("header")[cnt++],this.getLastMapValue(dg.getRandomChars(2).toUpperCase(),"UNLOAD_PT",80));
        columns.put(this.getMetaValues("header")[cnt++],this.getParentSchema("VAITM").getMapItem("VOLUMEUNIT"));
        columns.put(this.getMetaValues("header")[cnt++],null);
        columns.put(this.getMetaValues("header")[cnt++],null);
        columns.put(this.getMetaValues("header")[cnt++],this.getLastMapValue(dg.getRandomChars(3).toUpperCase(),"WHSE_NUM",80));

        this.setMap(columns);
        
        return columns;
    }
   
    
    @Override
    public String getName() {
        return this.getClass().getSimpleName().replace("Schema", "");
    }
    
    @Override
    public Integer getRandomRowCount () {
        DataGenerator dg = DataGenerator.getInstance();
        this.rowCount = dg.getItem(1,80,0);
        return this.rowCount;
    }
    
    @Override
    public Integer getDefaultPrimaryKey() {
        return 1;
    }
    
}

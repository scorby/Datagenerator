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
        
        //Definition of MasterData
        String[] orderprb = {"25","50","75","100"};
        String[] prockey = {"1","2"};
        String[] condprun = {"TO","ST","MIN","M3","STD"};
        String[] matlgrp2 = {"B12","B13"};
        String[] matlgrp4 = {"702","803","A02","BDB","CBN","CDN","CEN","CKO"};
        String[] matlgrp5 = {"702","B01","B04","B10","B11"};
        String[] unlpoint = {"3","5","Unloadingpoint"};
        String[] reasonrej = {"B1","Z1","Y0"};
        
        if(this.getParentSchema("VAHDR").getMapItem("DOC_NUMBER") != this.getMapItem("DOC_NUMBER")) {
            this.setPrimaryKey(this.getDefaultPrimaryKey());
        }
        
        int cnt = 0;
        
        Map<String, Object> columns = new HashMap<>();   
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAHDR").getMapItem("DOC_NUMBER"));
        columns.put(this.getHeader()[cnt++],dg.getItem("A", 95,"C"));
        columns.put(this.getHeader()[cnt++],this.getNextPrimaryKey().toString() + "0");
        columns.put(this.getHeader()[cnt++],"C");
        columns.put(this.getHeader()[cnt++],"C");
        columns.put(this.getHeader()[cnt++],"C");
        columns.put(this.getHeader()[cnt++],"C");
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAHDR").getMapItem("QUOT_FROM"));
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAHDR").getMapItem("DOC_TYPE"));
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAHDR").getMapItem("ORD_REASON"));
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAHDR").getMapItem("QUOT_TO"));
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAHDR").getMapItem("COMP_CODE"));
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAHDR").getMapItem("BILL_BLOCK"));
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAHDR").getMapItem("LOC_CURRCY"));
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAHDR").getMapItem("SOLD_TO"));
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAHDR").getMapItem("RATE_TYPE"));
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAHDR").getMapItem("GN_CUSTOM"));
        columns.put(this.getHeader()[cnt++],"C" + dg.getNumberBetween(1, 9).toString());
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAHDR").getMapItem("CUST_GRP1"));
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAHDR").getMapItem("CUST_GRP2"));
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAHDR").getMapItem("CUST_GRP3"));
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAHDR").getMapItem("CUST_GRP4"));
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAHDR").getMapItem("CUST_GRP5"));
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAHDR").getMapItem("DEL_BLOCK"));
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAHDR").getMapItem("STAT_CURR"));
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAHDR").getMapItem("DOC_CATEG"));
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAHDR").getMapItem("SALES_OFF"));
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAHDR").getMapItem("SALES_GRP"));
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAHDR").getMapItem("SALESORG"));
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAHDR").getMapItem("DISTR_CHAN"));
        columns.put(this.getHeader()[cnt++],dg.getNumberBetween(1, 9).toString() + "00"); //Plant
        columns.put(this.getHeader()[cnt++],dg.getItem(reasonrej, 25));
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getItem(dg.getDateBetween(dg.getDate(2013, 1, 1), dg.getDate(2013,12,31)),50,null),"CH_ON",90)); //Ch_On
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getItem(orderprb, 100),"ORDER_PROB",50));
        columns.put(this.getHeader()[cnt++],dg.getCurrency(1, 200000)); //-30000
        columns.put(this.getHeader()[cnt++],"SD");
        columns.put(this.getHeader()[cnt++],null);
        columns.put(this.getHeader()[cnt++],dg.getItem(prockey, 100));
        columns.put(this.getHeader()[cnt++],null);
        columns.put(this.getHeader()[cnt++],dg.getItem("1",95,"2"));
        columns.put(this.getHeader()[cnt++],dg.getItem("4020000000000",25,null));
        columns.put(this.getHeader()[cnt++],dg.getDateBetween((Date) this.getParentSchema("VAHDR").getMapItem("CREATEDON"), dg.getDate(2013,12,31))); //40
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getLastName(),"CREATEDBY",75));
        columns.put(this.getHeader()[cnt++],dg.getDateBetween(new Date(28800000),new Date(64800000)));
        columns.put(this.getHeader()[cnt++],dg.getItem("G3",5,null));
        columns.put(this.getHeader()[cnt++],dg.getItem("KG",95,"TO"));
        columns.put(this.getHeader()[cnt++],null);
        columns.put(this.getHeader()[cnt++],dg.getCurrency(1, 2000)); //-1000
        columns.put(this.getHeader()[cnt++],dg.getCurrency(1, 2000)); //-1000
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getItem(condprun,100),"COND_PR_UN",80));
        columns.put(this.getHeader()[cnt++],null);
        columns.put(this.getHeader()[cnt++],"1"); //50
        columns.put(this.getHeader()[cnt++],null);
        columns.put(this.getHeader()[cnt++],dg.getCurrency(1, 2000)); //-1000
        columns.put(this.getHeader()[cnt++],dg.getCurrency(1, 400000)); //-7000
        columns.put(this.getHeader()[cnt++],dg.getCurrency(1, 400000)); //-7000
        columns.put(this.getHeader()[cnt++],dg.getCurrency(1, 400000)); //-7000
        columns.put(this.getHeader()[cnt++],dg.getCurrency(1, 400000)); //-7000
        columns.put(this.getHeader()[cnt++],dg.getCurrency(1, 400000)); //-7000
        columns.put(this.getHeader()[cnt++],dg.getCurrency(1, 400000)); //-7000
        columns.put(this.getHeader()[cnt++],"0");
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getStorLoc(),"STOR_LOC",50));
        columns.put(this.getHeader()[cnt++],dg.getCurrency(1, 1000)); //-1000
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(Integer.toString(dg.getNumberBetween(1, 8)) + "0" + Integer.toString(dg.getNumberBetween(1, 8)),"MATL_GROUP",80));
        columns.put(this.getHeader()[cnt++],dg.getNumberBetween(100000,108000));
        columns.put(this.getHeader()[cnt++],dg.getNumberBetween(100000,108000));
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getItem(condprun,100),"COND_PR_UN",80));
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getNumberBetween(100,900),"MATL_GRP_1",75));
        columns.put(this.getHeader()[cnt++],dg.getItem(matlgrp2, 10));
        columns.put(this.getHeader()[cnt++],dg.getItem("R01",5,null));
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getItem(matlgrp4,20),"MATL_GRP_4",80));
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getItem(matlgrp5,20),"MATL_GRP_5",80));
        columns.put(this.getHeader()[cnt++],dg.getCurrency(1, 300000)); //-2000
        columns.put(this.getHeader()[cnt++],dg.getCurrency(0, 40000));
        columns.put(this.getHeader()[cnt++],dg.getCurrency(8000, 2000000));
        columns.put(this.getHeader()[cnt++],dg.getCurrency(1, 250000)); //-100000
        columns.put(this.getHeader()[cnt++],dg.getItem(unlpoint, 10));
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getNumberBetween(10000000,30000000),"BILLTOPRTY",75));
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getNumberBetween(10000000,30000000),"PAYER",75));
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getNumberBetween(1010043601,1030043601),"SHIP_TO",60));
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getNumberBetween(110043601,230043601),"PROD_HIER",70));
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getNumberBetween(104856,204856),"FORWAGENT",85));
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getItemCateg(),"ITEM_CATEG",50));
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAHDR").getMapItem("SALESEMPLY")); //sales Emply
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getRoute(),"ROUTE",85));
        columns.put(this.getHeader()[cnt++],dg.getItem("E",5,null));
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAHDR").getMapItem("DIV_HEAD").toString().substring(0, 1) + dg.getNumberBetween(1, 5).toString());
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAHDR").getMapItem("DIV_HEAD"));
        columns.put(this.getHeader()[cnt++],null);
        columns.put(this.getHeader()[cnt++],"1");
        columns.put(this.getHeader()[cnt++],null);
        columns.put(this.getHeader()[cnt++],null);
        columns.put(this.getHeader()[cnt++],"0");
        columns.put(this.getHeader()[cnt++],dg.getItem("1",90,"25"));
        columns.put(this.getHeader()[cnt++],dg.getItem("1",90,"1000"));
        
        this.setMap(columns);
        
        return columns;
    }
    
    @Override
    public String getName() {
        return this.getClass().getSimpleName().replace("Schema", "");
    }
    
    @Override
    public Integer getDefaultPrimaryKey() {
        return 1;
    }
    
    
}

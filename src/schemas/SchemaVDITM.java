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
        
        //Definition of MasterData
        String[] rType = {"KZ","M"};
        String[] cGrp = {"102","103","104","106"};
        String[] cGrp2 = {"1","2","3"};
        String[] dCateg = {"B", "C", "G", "H", "L"};
        String[] condprun = {"TO","ST","MIN","M3","STD"};
        String[] matlgrp2 = {"B12","B13"};
        String[] matlgrp4 = {"702","803","A02","BDB","CBN","CDN","CEN","CKO"};
        String[] matlgrp5 = {"702","B01","B04","B10","B11"};
        String[] costArea = {"C01","C02","C03","C04","C05"};
        
        if(this.getParentSchema("VAITM").getMapItem("DOC_NUMBER") != this.getMapItem("REFER_DOC")) {
            this.setPrimaryKey(this.getDefaultPrimaryKey());
            if(this.getForeignKey() == null) {
                this.setForeignKey(140500);
            } else {
                this.setForeignKey(this.getForeignKey() + 1);
            }
        }
        int cnt = 0;
        
        Map<String, Object> columns = new HashMap<>();   
        columns.put(this.getHeader()[cnt++],this.getForeignKey());
        columns.put(this.getHeader()[cnt++],this.getNextPrimaryKey().toString() + "0");
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getItem(rType, 100),"BILL_TYPE",50));
        columns.put(this.getHeader()[cnt++],"I");
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getDateBetween(dg.getDate(2013, 1, 1), dg.getDate(2013,12,31)),"BILL_DATE",80));
        columns.put(this.getHeader()[cnt++],dg.getItem(dCateg,100));
        columns.put(this.getHeader()[cnt++],dg.getNumberBetween(1, 15));
        columns.put(this.getHeader()[cnt++],dg.getItem(condprun,100));
        columns.put(this.getHeader()[cnt++],"1");
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getNumberBetween(2418000, 2419000),"BILLTOPRTY",70));
        columns.put(this.getHeader()[cnt++],dg.getCompCode());
        columns.put(this.getHeader()[cnt++],dg.getNumberBetween(1, 9).toString() + "00");
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getNumberBetween(1, 20),"COSTCENTER",80));
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getItem(costArea,100),"CO_AREA",80));
        columns.put(this.getHeader()[cnt++],dg.getDateBetween((Date) this.getParentSchema("VAITM").getMapItem("CREATEDON"), dg.getDate(2013,12,31)));
        columns.put(this.getHeader()[cnt++],dg.getNumberBetween(1, 15));
        columns.put(this.getHeader()[cnt++],null);
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getDateBetween(dg.getDate(2013, 1, 1), dg.getDate(2013,12,31)),"",80));
        columns.put(this.getHeader()[cnt++],dg.getItem(1, 90, 9));
        columns.put(this.getHeader()[cnt++],this.getLastMapValue("S" + dg.getNumberBetween(1, 9).toString(),"DIVISION",90));
        columns.put(this.getHeader()[cnt++],"S0");
        columns.put(this.getHeader()[cnt++],null);
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAITM").getMapItem("GN_CUSTOM"));
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAITM").getMapItem("CUST_GROUP"));
        columns.put(this.getHeader()[cnt++],dg.getItem(cGrp, 25));
        columns.put(this.getHeader()[cnt++],dg.getItem(cGrp2, 10));
        columns.put(this.getHeader()[cnt++],dg.getItem(dg.getCustGrp(),10,null));
        columns.put(this.getHeader()[cnt++],null);
        columns.put(this.getHeader()[cnt++],null);
        columns.put(this.getHeader()[cnt++],"1");
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getItem(dCateg, 100),"DOC_CATEG",50));
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getLocCurrency(),"DOC_CURRCY",90));
        columns.put(this.getHeader()[cnt++],null);
        columns.put(this.getHeader()[cnt++],dg.getCurrency(1, 25));
        columns.put(this.getHeader()[cnt++],"1");
        columns.put(this.getHeader()[cnt++],null);
        columns.put(this.getHeader()[cnt++],null);
        columns.put(this.getHeader()[cnt++],"K4");
        columns.put(this.getHeader()[cnt++],null);
        columns.put(this.getHeader()[cnt++],dg.getCurrency(1, 300000));
        columns.put(this.getHeader()[cnt++],dg.getCurrency(0, 40000));
        columns.put(this.getHeader()[cnt++],dg.getNumberBetween(1, 10000));
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getItemCateg(),"ITEM_CATEG",50));
        columns.put(this.getHeader()[cnt++],null);
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getLocCurrency(),"LOC_CURRCY",90));   
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getNumberBetween(100,900),"MATERIAL",75));
        columns.put(this.getHeader()[cnt++],null);
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getNumberBetween(100,900),"MATL_GRP_1",75));
        columns.put(this.getHeader()[cnt++],dg.getItem(matlgrp2, 10));
        columns.put(this.getHeader()[cnt++],dg.getItem("R01",5,null));
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getItem(matlgrp4,20),"MATL_GRP_4",80));
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getItem(matlgrp5,20),"MATL_GRP_5",80));
        columns.put(this.getHeader()[cnt++],dg.getNumberBetween(1, 10000));
        columns.put(this.getHeader()[cnt++],dg.getCurrency(1, 4000000));
        columns.put(this.getHeader()[cnt++],dg.getCurrency(1, 400000));
        columns.put(this.getHeader()[cnt++],dg.getNumberBetween(1, 10));
        columns.put(this.getHeader()[cnt++],dg.getCurrency(1,10));
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getNumberBetween(10000000,30000000),"PAYER",75));
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getNumberBetween(100, 110),"PLANT",80));
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getDateBetween(dg.getDate(2013, 1, 1), dg.getDate(2013,12,31)),"PRICE_DATE",80));
        columns.put(this.getHeader()[cnt++],"1");
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getNumberBetween(110043601,230043601),"PROD_HIER",70));
        columns.put(this.getHeader()[cnt++],"G1");
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getItem(rType, 100),"RATE_TYPE",50));
        columns.put(this.getHeader()[cnt++],dg.getCurrency(1, 400000));
        columns.put(this.getHeader()[cnt++],"B1");
        columns.put(this.getHeader()[cnt++],null);
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAITM").getMapItem("DOC_NUMBER"));
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAITM").getMapItem("S_ORD_ITEM"));
        columns.put(this.getHeader()[cnt++],dg.getNumberBetween(1, 1000));
        columns.put(this.getHeader()[cnt++],null);
        columns.put(this.getHeader()[cnt++],null);
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAITM").getMapItem("SALESEMPLY")); //Sales Emply
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAITM").getMapItem("SALESORG")); //Sales Org
        columns.put(this.getHeader()[cnt++],"1");
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAITM").getMapItem("SALES_GRP"));
        columns.put(this.getHeader()[cnt++],this.getLastMapValue("OF" + dg.getNumberBetween(1, 12).toString(),"SALES_OFF",80));
        columns.put(this.getHeader()[cnt++],null);
        columns.put(this.getHeader()[cnt++],dg.getNumberBetween(1, 1000));
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getDateBetween(dg.getDate(2013, 1, 1), dg.getDate(2013,12,31)),"SERV_DATE",80));
        columns.put(this.getHeader()[cnt++],this.getLastMapValue("SP" + dg.getNumberBetween(1, 15).toString(),"SHIP_POINT",70));
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAITM").getMapItem("SHIP_TO"));
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAITM").getMapItem("SOLD_TO"));
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getLocCurrency(),"STAT_CURR",90)); 
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getDateBetween(dg.getDate(2013, 1, 1), dg.getDate(2013,12,31)),"STAT_DATE",80));
        columns.put(this.getHeader()[cnt++],this.getLastMapValue("LO" + dg.getNumberBetween(1, 20).toString(),"STOR_LOC",80));
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getDateBetween(dg.getDate(2013, 1, 1), dg.getDate(2013,12,31)),"ST_UP_DTE",80));
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAITM").getMapItem("SUBTOTAL_1"));
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAITM").getMapItem("SUBTOTAL_2"));
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAITM").getMapItem("SUBTOTAL_3"));
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAITM").getMapItem("SUBTOTAL_4"));
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAITM").getMapItem("SUBTOTAL_5"));
        columns.put(this.getHeader()[cnt++],this.getParentSchema("VAITM").getMapItem("SUBTOTAL_6"));
        columns.put(this.getHeader()[cnt++],dg.getCurrency(1, 400000));
        columns.put(this.getHeader()[cnt++],dg.getDateBetween(dg.getDate(2013, 1, 1), dg.getDate(2013,12,31)));
        columns.put(this.getHeader()[cnt++],"SD");
        columns.put(this.getHeader()[cnt++],null);
        columns.put(this.getHeader()[cnt++],null);
        columns.put(this.getHeader()[cnt++],null);
        
        
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

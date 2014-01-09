package schemas;

/*
 * Copyright 2013, Martin Kleehaus
 * Technical University of Munich (TUM)
 * Professorship for Database Systems and Applications
 */

import datagenerator.DataGenerator;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.supercsv.cellprocessor.*;
import org.supercsv.cellprocessor.ift.*;
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
    public String[] getHeader() {
        final String[] header = new String[] { 
        "DOC_NUMBER",
        "REJECTN_ST",
        "S_ORD_ITEM",
        "STS_ITM",
        "STS_BILL",
        "STS_PRC",
        "STS_DEL",
        "QUOT_FROM",
        "DOC_TYPE",
        "ORD_REASON",
        "QUOT_TO",
        "COMP_CODE",
        "BILL_BLOCK",
        "LOC_CURRCY",
        "SOLD_TO",
        "RATE_TYPE",
        "GN_CUSTOM",
        "CUST_GRP1",
        "CUST_GRP2",
        "CUST_GRP3",
        "CUST_GRP4",
        "CUST_GRP5",
        "DEL_BLOCK",
        "STAT_CURR",
        "DOC_CATEG",
        "SALES_OFF",
        "SALES_GRP",
        "SALESORG",
        "DISTR_CHAN",
        "PLANT",
        "REASON_REJ",
        "CH_ON",
        "ORDER_PROB",
        "GROSS_WGT",
        "UNIT_OF_WT",
        "BWAPPLNM",
        "PROCESSKEY",
        "BATCH",
        "EXCHG_CRD",
        "EANUPC",
        "CREATEDON",
        "CREATEDBY",
        "CREA_TIME",
        "BILBLK_ITEM",
        "UNIT_OF_WI",
        "SALES_UNIT",
        "CML_CF_QTY",
        "CML_CD_QTY",
        "COND_PR_UN",
        "SALESDEAL",
        "COND_UNIT",
        "DOC_CURRCY",
        "CML_OR_QTY",
        "SUBTOTAL_1",
        "SUBTOTAL_2",
        "SUBTOTAL_3",
        "SUBTOTAL_4",
        "SUBTOTAL_5",
        "SUBTOTAL_6",
        "MIN_DL_QTY",
        "STOR_LOC",
        "REQDEL_QTY",
        "MATL_GROUP",
        "MATERIAL",
        "MAT_ENTRD",
        "BASE_UOM",
        "MATL_GRP_1",
        "MATL_GRP_2",
        "MATL_GRP_3",
        "MATL_GRP_4",
        "MATL_GRP_5",
        "TAX_VALUE",
        "NET_PRICE",
        "NET_VALUE",
        "NET_WT_AP",
        "UNLD_PT_WE",
        "BILLTOPRTY",
        "PAYER",
        "SHIP_TO",
        "PROD_HIER",
        "FORWAGENT",
        "ITEM_CATEG",
        "SALESEMPLY",
        "ROUTE",
        "SOBKZ",
        "DIVISION",
        "DIV_HEAD",
        "STAT_DATE",
        "EXCHG_STAT",
        "SUB_REASON",
        "UEBTK",
        "UEBTO",
        "DENOMINTR",
        "DENOMINTRZ"
        };
        
        return header;
    }   
    
    @Override
    public List<Object> getData() throws Exception {
        
        DataGenerator dg = DataGenerator.getInstance();
        
        //Definition of MasterData
        String[] rType = {"KZ","M"};
        String[] cGrp = {"102","103","104","106"};
        String[] cGrp2 = {"1","2","3"};
        String[] dCateg = {"B", "C", "G", "H", "L"};
        String[] orderprb = {"25","50","75","100"};
        String[] prockey = {"1","2"};
        String[] condprun = {"TO","ST","MIN","M3","STD"};
        String[] matlgrp2 = {"B12","B13"};
        String[] matlgrp4 = {"702","803","A02","BDB","CBN","CDN","CEN","CKO"};
        String[] matlgrp5 = {"702","B01","B04","B10","B11"};
        String[] unlpoint = {"3","5","Unloadingpoint"};
        String[] reasonrej = {"B1","Z1","Y0"};
        
        if(this.getParentSchema(0).getListItem(0) != this.getListItem(0)) {
            this.setPrimaryKey(this.getDefaultPrimaryKey());
        }
        
        //Build header values
        final List<Object> csvList;
        csvList = Arrays.asList(new Object[] {
            this.getParentSchema(0).getListItem(0),
            dg.getItem("A", 95,"C"),
            this.getNextPrimaryKey().toString() + "0",
            "C",
            "C",
            "C",
            "C",
            this.getParentSchema(0).getListItem(1),
            this.getParentSchema(0).getListItem(2),
            this.getParentSchema(0).getListItem(3),
            this.getParentSchema(0).getListItem(4),
            this.getParentSchema(0).getListItem(5),
            this.getParentSchema(0).getListItem(6),
            this.getParentSchema(0).getListItem(7),
            this.getParentSchema(0).getListItem(8),
            this.getParentSchema(0).getListItem(9),
            this.getParentSchema(0).getListItem(10),//Kunde
            this.getParentSchema(0).getListItem(11),
            this.getParentSchema(0).getListItem(12),
            this.getParentSchema(0).getListItem(13),
            this.getParentSchema(0).getListItem(14),
            this.getParentSchema(0).getListItem(15),
            this.getParentSchema(0).getListItem(16),
            this.getParentSchema(0).getListItem(17),
            this.getParentSchema(0).getListItem(19),
            this.getParentSchema(0).getListItem(20),
            this.getParentSchema(0).getListItem(21),
            this.getParentSchema(0).getListItem(22),
            this.getParentSchema(0).getListItem(23),
            dg.getNumberBetween(1, 9).toString() + "00", //Plant
            dg.getItem(reasonrej, 25),
            this.getLastListValue(dg.getItem(dg.getDateBetween(dg.getDate(2013, 1, 1), dg.getDate(2013,12,31)),50,null),31,90), //Ch_On
            this.getLastListValue(dg.getItem(orderprb, 100),32,50),
            dg.getCurrency(1, 200000), //-30000
            "SD",
            null,
            dg.getItem(prockey, 100),
            null,
            dg.getItem("1",95,"2"),
            dg.getItem("4020000000000",25,null),
            dg.getDateBetween((Date) this.getParentSchema(0).getListItem(24), dg.getDate(2013,12,31)), //40
            this.getLastListValue(dg.getLastName(),41,75),
            dg.getDateBetween(new Date(28800000),new Date(64800000)),
            dg.getItem("G3",5,null),
            dg.getItem("KG",95,"TO"),
            null,
            dg.getCurrency(1, 2000), //-1000
            dg.getCurrency(1, 2000), //-1000
            this.getLastListValue(dg.getItem(condprun,100),48,80),
            null,
            "1", //50
            null,
            dg.getCurrency(1, 2000), //-1000
            dg.getCurrency(1, 400000), //-7000
            dg.getCurrency(1, 400000), //-7000
            dg.getCurrency(1, 400000), //-7000
            dg.getCurrency(1, 400000), //-7000
            dg.getCurrency(1, 400000), //-7000
            dg.getCurrency(1, 400000), //-7000
            "0",
            this.getLastListValue(dg.getStorLoc(),60,50),
            dg.getCurrency(1, 1000), //-1000
            this.getLastListValue(Integer.toString(dg.getNumberBetween(1, 8)) + "0" + Integer.toString(dg.getNumberBetween(1, 8)),62,80),
            dg.getNumberBetween(100000,108000),
            dg.getNumberBetween(100000,108000),
            this.getLastListValue(dg.getItem(condprun,100),65,80),
            this.getLastListValue(dg.getNumberBetween(100,900),66,75),
            dg.getItem(matlgrp2, 10),
            dg.getItem("R01",5,null),
            this.getLastListValue(dg.getItem(matlgrp4,20),69,80),
            this.getLastListValue(dg.getItem(matlgrp5,20),70,80),
            dg.getCurrency(1, 300000), //-2000
            dg.getCurrency(0, 40000),
            dg.getCurrency(8000, 2000000),
            dg.getCurrency(1, 250000), //-100000
            dg.getItem(unlpoint, 10),
            this.getLastListValue(dg.getNumberBetween(10000000,30000000),76,75),
            this.getLastListValue(dg.getNumberBetween(10000000,30000000),76,75),
            this.getLastListValue(dg.getNumberBetween(1010043601,1030043601),78,60),
            this.getLastListValue(dg.getNumberBetween(110043601,230043601),79,70),
            this.getLastListValue(dg.getNumberBetween(104856,204856),80,85),
            this.getLastListValue(dg.getItemCateg(),81,50),
            this.getParentSchema(0).getListItem(26), //sales Emply
            this.getLastListValue(dg.getRoute(),83,85),
            dg.getItem("E",5,null),
            this.getParentSchema(0).getListItem(27).toString().substring(0, 1) + dg.getNumberBetween(1, 5).toString(),
            this.getParentSchema(0).getListItem(27),
            null,
            "1",
            null,
            null,
            "0",
            dg.getItem("1",90,"25"),
            dg.getItem("1",90,"1000"),
        });
        
        this.setList(csvList);
        
        return csvList;
    }
    

    @Override
    public CellProcessor[] getProcessors() {
     
        final CellProcessor[] processors = new CellProcessor[] { 
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(new FmtDate("YYYY-MM-dd")),    
                new Optional(),
                new Optional(),
                new Optional(new FmtDate("YYYY-MM-dd")),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),       
                new Optional(),
                new Optional(),
                new Optional(new FmtDate("YYYY-MM-dd")),
                new Optional(),
                new Optional(new FmtNumber("#,##0.00")),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(new FmtDate("YYYY-MM-dd")),
                new Optional(),
                new Optional(new FmtDate("HH:mm:ss")),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(new FmtNumber("#,##0.00")),
                new Optional(new FmtNumber("#,##0.00")),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(new FmtNumber("#,##0.00")),
                new Optional(new FmtNumber("#,##0.00")),
                new Optional(new FmtNumber("#,##0.00")),
                new Optional(new FmtNumber("#,##0.00")),
                new Optional(new FmtNumber("#,##0.00")),
                new Optional(new FmtNumber("#,##0.00")),
                new Optional(new FmtNumber("#,##0.00")),
                new Optional(),
                new Optional(),
                new Optional(new FmtNumber("#,##0.00")),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(new FmtNumber("#,##0.00")),
                new Optional(new FmtNumber("#,##0.00")),
                new Optional(new FmtNumber("#,##0.00")),
                new Optional(new FmtNumber("#,##0.00")),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional()
        };
        
        return processors;
    }
    
    @Override
    public String getName() {
        return this.getClass().getSimpleName().replace("Schema", "");
    }
    
    @Override
    public Integer getRowCount () {
        DataGenerator dg = DataGenerator.getInstance();
        return dg.getNumberBetween(0, 10);
    }
    
    @Override
    public void setRowCount (Integer rowCount) {
        this.rowCount = rowCount;
    }
    
    @Override
    public Integer getDefaultPrimaryKey() {
        return 1;
    }
    
    
}

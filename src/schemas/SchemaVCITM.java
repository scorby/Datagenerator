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
public class SchemaVCITM extends Schemas {
    
    private Integer primaryKey = 0;
    private Integer foreignKey = 140500;
    
    @Override
    public void setUniqueNumber() {
        DataGenerator dg = DataGenerator.getInstance();
        dg.setUniqueNumber(1);
    }
    
    @Override
    public String[] getHeader() {
        final String[] header = new String[] { 
            "ACT_DL_QTY",
            "ACT_GI_DTE",
            "BASE_UOM",
            "BATCH",
            "BILBLK_DL",
            "BILLTOPRTY",
            "BILL_BLOCK",
            "BUS_AREA",
            "BWAPPLNM",
            "CH_ON",
            "COMP_CODE",
            "CONSU_FLAG",
            "CREATEDBY",
            "CREATEDON",
            "CREA_TIME",
            "CREDITOR",
            "CUST_GROUP",
            "CUST_GRP1",
            "CUST_GRP2",
            "CUST_GRP2",
            "CUST_GRP3",
            "CUST_GRP4",
            "CUST_GRP5",
            "DELIV_ITEM",
            "DELIV_NUMB",
            "DEL_BLOCK",
            "S_DEL_I_CN",
            "DEL_TYPE",
            "DEL_WA_DH",
            "DENOMINTR",
            "DISTR_CHAN",
            "DIVISION",
            "DLV_QTY",
            "DOC_CATEG",
            "EANUPC",
            "FISCVARNT",
            "FORWAGENT",
            "GI_DATE",
            "GN_R3_SSY",
            "GOODSMV_ST",
            "GRS_WGT_DL",
            "INCOTERMS",
            "INCOTERMS2",
            "ITEM_CATEG",
            "ITM_TYPE",
            "LOAD_PT",
            "MATERIAL",
            "MATL_GROUP",
            "MATL_GRP_1",
            "MATL_GRP_2",
            "MATL_GRP_3",
            "MATL_GRP_4",
            "MATL_GRP_5",
            "MAT_ENTRD",
            "NET_WGT_DL",
            "NO_DEL_IT",
            "NUMERATOR",
            "PAYER",
            "PICK_CONF",
            "PICK_INDC",
            "PLANT",
            "PROCESSKEY",
            "PROD_HIER",
            "PRVDOC_CTG",
            "RECORDMODE",
            "REFER_DOC",
            "REFER_ITM",
            "ROUTE",
            "RT_PROMO",
            "SALESEMPLY",
            "SALESORG",
            "SALES_DIST",
            "SALES_GRP",
            "SHIP_DATE",
            "SHIP_POINT",
            "SHIP_TO",
            "SHP_PR_TMF",
            "SHP_PR_TMV",
            "SOLD_TO",
            "STAT_DATE",
            "STOR_LOC",
            "STRGE_BIN",
            "STRGE_TYPE",
            "STS_PICK",
            "ST_UP_DTE",
            "UNLOAD_PT",
            "VOLUMEUNIT",
            "VOLUME_DL",
            "WBS_ELEMT",
            "WHSE_NUM",
            "SALES_UNIT",
            "UNIT_OF_WT"

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
        String[] condprun = {"TO","ST","MIN","M3","STD"};
        String[] matlgrp2 = {"B12","B13"};
        String[] matlgrp4 = {"702","803","A02","BDB","CBN","CDN","CEN","CKO"};
        String[] matlgrp5 = {"702","B01","B04","B10","B11"};
        String[] costArea = {"C01","C02","C03","C04","C05"};
        
        if(this.getParentSchema(0).getListItem(0) != this.getListItem(67)) {
            this.setPrimaryKey(this.getDefaultPrimaryKey());
            if(this.getForeignKey() == null) {
                this.setForeignKey(140500);
            } else {
                this.setForeignKey(this.getForeignKey() + 1);
            }
        }
        
        //Build header values
        final List<Object> csvList;
        csvList = Arrays.asList(new Object[] {
            this.getForeignKey(),
            this.getNextPrimaryKey().toString() + "0",
            this.getLastListValue(dg.getItem(rType, 100),2,50),
            "I",
            this.getLastListValue(dg.getDateBetween(dg.getDate(2013, 1, 1), dg.getDate(2013,12,31)),4,80),
            dg.getItem(dCateg,100),
            dg.getNumberBetween(1, 15),
            dg.getItem(condprun,100),
            "1",
            this.getLastListValue(dg.getNumberBetween(2418000, 2419000),9,70),
            dg.getCompCode(),
            dg.getNumberBetween(1, 9).toString() + "00",
            this.getLastListValue(dg.getNumberBetween(1, 20),12,80),
            this.getLastListValue(dg.getItem(costArea,100),13,80),
            dg.getDateBetween((Date) this.getParentSchema(0).getListItem(40), dg.getDate(2013,12,31)),
            dg.getNumberBetween(1, 15),
            null,
            this.getLastListValue(dg.getDateBetween(dg.getDate(2013, 1, 1), dg.getDate(2013,12,31)),17,80),
            dg.getItem(1, 90, 9),
            this.getLastListValue("S" + dg.getNumberBetween(1, 9).toString(),19,90),
            "S0",
            null,
            this.getParentSchema(0).getListItem(16),
            "CCROUP" + dg.getNumberBetween(1, 12).toString(),
            dg.getItem(cGrp, 25),
            dg.getItem(cGrp2, 10),
            dg.getItem(dg.getCustGrp(),10,null),
            null,
            null,
            "1",
            this.getLastListValue(dg.getItem(dCateg, 100),30,50),
            this.getLastListValue(dg.getLocCurrency(),31,90),
            null,
            dg.getCurrency(1, 25),
            "1",
            null,
            null,
            "K4",
            null,
            dg.getCurrency(1, 300000), //-2000
            dg.getCurrency(0, 40000),
            dg.getNumberBetween(1, 10000),
            this.getLastListValue(dg.getItemCateg(),42,50),
            null,
            this.getLastListValue(dg.getLocCurrency(),44,90),    
            this.getLastListValue(dg.getNumberBetween(100,900),45,75),
            null,
            this.getLastListValue(dg.getNumberBetween(100,900),47,75),
            dg.getItem(matlgrp2, 10),
            dg.getItem("R01",5,null),
            this.getLastListValue(dg.getItem(matlgrp4,20),50,80), //50
            this.getLastListValue(dg.getItem(matlgrp5,20),51,80),
            dg.getNumberBetween(1, 10000),
            dg.getCurrency(1, 4000000),
            dg.getCurrency(1, 400000),
            dg.getNumberBetween(1, 10),
            dg.getCurrency(1,10),
            this.getLastListValue(dg.getNumberBetween(10000000,30000000),57,75),
            this.getLastListValue(dg.getNumberBetween(100, 110),58,80),
            this.getLastListValue(dg.getDateBetween(dg.getDate(2013, 1, 1), dg.getDate(2013,12,31)),59,80),
            "1", //60
            this.getLastListValue(dg.getNumberBetween(110043601,230043601),61,70),
            "G1",
            this.getLastListValue(dg.getItem(rType, 100),63,50),
            dg.getCurrency(1, 400000),
            "B1",
            null,
            this.getParentSchema(0).getListItem(0),
            this.getParentSchema(0).getListItem(2),
            dg.getNumberBetween(1, 1000),
            null, //70
            null, 
            this.getParentSchema(0).getListItem(82), //Sales Emply
            this.getParentSchema(0).getListItem(27), //Sales Org
            "1",
            this.getParentSchema(0).getListItem(26),
            this.getLastListValue("OF" + dg.getNumberBetween(1, 12).toString(),76,80),
            null,
            dg.getNumberBetween(1, 1000),
            this.getLastListValue(dg.getDateBetween(dg.getDate(2013, 1, 1), dg.getDate(2013,12,31)),79,80),
            this.getLastListValue("SP" + dg.getNumberBetween(1, 15).toString(),80,70), //80
            this.getParentSchema(0).getListItem(82), 
            this.getParentSchema(0).getListItem(14), 
            this.getLastListValue(dg.getLocCurrency(),83,90), 
            this.getLastListValue(dg.getDateBetween(dg.getDate(2013, 1, 1), dg.getDate(2013,12,31)),84,80),
            this.getLastListValue("LO" + dg.getNumberBetween(1, 20).toString(),85,80),
            this.getLastListValue(dg.getDateBetween(dg.getDate(2013, 1, 1), dg.getDate(2013,12,31)),86,80),
            this.getParentSchema(0).getListItem(52),
            this.getParentSchema(0).getListItem(53),
            this.getParentSchema(0).getListItem(54),
            this.getParentSchema(0).getListItem(55),
            this.getParentSchema(0).getListItem(56),
            this.getParentSchema(0).getListItem(57),
            dg.getCurrency(1, 400000), //-7000
            dg.getDateBetween(dg.getDate(2013, 1, 1), dg.getDate(2013,12,31)),
            "SD",
            null,
            null,
            null
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
                new Optional(new FmtDate("YYYY-MM-dd")),  
                new Optional(),
                new Optional(),
                new Optional(),   
                new Optional(),
                new Optional(),
                new Optional(), //10
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(new FmtDate("YYYY-MM-dd")), 
                new Optional(),
                new Optional(),
                new Optional(new FmtDate("YYYY-MM-dd")), 
                new Optional(),
                new Optional(),
                new Optional(), //20
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(), 
                new Optional(), //30
                new Optional(), 
                new Optional(),
                new Optional(new FmtNumber("#,##0.00")),       
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(new FmtNumber("#,##0.00")), 
                new Optional(new FmtNumber("#,##0.00")), //40
                new Optional(), 
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(), //50
                new Optional(), 
                new Optional(),
                new Optional(new FmtNumber("#,##0.00")),
                new Optional(new FmtNumber("#,##0.00")),
                new Optional(),
                new Optional(new FmtNumber("#,##0.00")),
                new Optional(),
                new Optional(),
                new Optional(new FmtDate("YYYY-MM-dd")), 
                new Optional(), //60
                new Optional(), 
                new Optional(), 
                new Optional(),
                new Optional(new FmtNumber("#,##0.00")),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(), //70
                new Optional(), 
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(new FmtDate("YYYY-MM-dd")),
                new Optional(), //80
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional(new FmtDate("YYYY-MM-dd")),
                new Optional(),
                new Optional(new FmtDate("YYYY-MM-dd")),
                new Optional(new FmtNumber("#,##0.00")),
                new Optional(new FmtNumber("#,##0.00")),
                new Optional(new FmtNumber("#,##0.00")),
                new Optional(new FmtNumber("#,##0.00")), //90
                new Optional(new FmtNumber("#,##0.00")),
                new Optional(),
                new Optional(new FmtNumber("#,##0.00")),
                new Optional(new FmtDate("YYYY-MM-dd")),
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
        return dg.getItem(1, 80, 0);
    }
    
    @Override
    public Integer getDefaultPrimaryKey() {
        return 1;
    }
    
}

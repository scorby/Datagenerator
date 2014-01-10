package schemas;

/*
 * Copyright 2013, Martin Kleehaus
 * Technical University of Munich (TUM)
 * Professorship for Database Systems and Applications
 */

import datagenerator.DataGenerator;
import java.util.Arrays;
import java.util.List;

import org.supercsv.cellprocessor.*;
import org.supercsv.cellprocessor.ift.*;
import org.supercsv.cellprocessor.constraint.*;
/**
 *
 * @author Martin Kleehaus
 */
public class SchemaVCHDR extends Schemas  {
    
    private Integer primaryKey = 1405000;
    
    @Override
    public void setUniqueNumber() {
        DataGenerator dg = DataGenerator.getInstance();
        dg.setUniqueNumber(this.primaryKey);
    }
    
    @Override
    public String[] getHeader() {
        final String[] header = new String[] { 
            "DELIV_NUMB",
            "ACT_GI_DTE",
            "BILLTOPRTY",
            "BILL_BLOCK",
            "COMP_CODE",
            "CREATEDON",
            "CREDITOR",
            "CUST_GROUP",
            "DEL_BLOCK",
            "DEL_GR_WT",
            "DEL_H_CN",
            "DEL_TYPE",
            "DEL_WA_DH",
            "DOC_CATEG",
            "FISCVARNT",
            "FORWAGENT",
            "GI_DATE",
            "GN_R3_SSY",
            "INCOTERMS",
            "INCOTERMS",
            "LOAD_PT",
            "NET_WGT_DL",
            "NO_SHPUN",
            "PAYER",
            "RECORDMODE",
            "ROUTE",
            "SALESEMPLY",
            "SALESORG",
            "SALES_DIST",
            "SHIP_TO",
            "SOLD_TO",
            "UNIT_OF_WT",
            "UNLOAD_PT",
            "VOLUMEUNIT",
            "VOLUME_DL"
        };
        
        return header;
    }
    
    @Override
    public List<Object> getData() throws Exception {
        if(this.getListItem(0) == this.getSubschema(0).getListItem(0)) {
            return null;
        }
        DataGenerator dg = DataGenerator.getInstance();
        String[] rType = {"KZ","M"};
        String[] cGrp = {"102","103","104","106"};
        String[] cGrp2 = {"1","2","3"};
        String[] dCateg = {"B", "C", "G", "H", "L"};
        
        final List<Object> csvList;
        this.setPrimaryKey(Integer.parseInt(this.getSubschema(0).getListItem(0).toString()));
        
        csvList = Arrays.asList(new Object[] {
            this.getSubschema(0).getListItem(0),
            this.getSubschema(0).getListItem(2),
            this.getSubschema(0).getListItem(3),
            this.getSubschema(0).getListItem(4),
            this.getSubschema(0).getListItem(11),
            this.getLastListValue(dg.getDateBetween(dg.getDate(2013, 1, 1), dg.getDate(2013,12,31)),5,80),
            dg.getNumberBetween(1, 10),
            this.getSubschema(0).getListItem(22),
            "C" + dg.getNumberBetween(1, 9).toString(),
            dg.getItem(1, 90, 9),
            this.getLastListValue(dg.getItem(dCateg),10,80),
            this.getLastListValue(dg.getLocCurrency(),11,80),
            null,
            "K4",
            null,
            this.getLastListValue(dg.getLocCurrency(),11,100),
            this.getLastListValue(dg.getNumberBetween(10000000,30000000),16,75),
            this.getSubschema(0).getListItem(61),
            null,
            this.getSubschema(0).getListItem(72),
            this.getSubschema(0).getListItem(73),
            "1",
            this.getSubschema(0).getListItem(81),
            this.getLastListValue(dg.getLocCurrency(),11,80),
            
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
                new Optional(new FmtDate("YYYY-MM-dd")),
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
                new Optional()
        };
        
        return processors;
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
    public Integer getDefaultPrimaryKey() {
        return 1;
    }
    
}

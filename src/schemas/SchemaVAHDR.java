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
public class SchemaVAHDR extends Schemas  {
    
    private Integer primaryKey = 3405000;
    
    @Override
    public void setUniqueNumber() {
        DataGenerator dg = DataGenerator.getInstance();
        dg.setUniqueNumber(3405000);
    }
    
    @Override
    public String[] getHeader() {
        final String[] header = new String[] { 
            "DOC_NUMBER", 
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
            "DOC_CATEGR",
            "SALES_OFF",
            "SALES_GRP",
            "SALESORG",
            "DISTR_CHAN",
            "CREATEDON",
            "DOC_CURRCY",
            "SALESEMPLY",
            "DIV_HEAD",
            "REQ_DATE",
            "FISCVARNT",
            "ORDERS"};
        
        return header;
    }
    
    @Override
    public List<Object> getData() throws Exception {
        DataGenerator dg = DataGenerator.getInstance();
        String[] rType = {"KZ","M"};
        String[] cGrp = {"102","103","104","106"};
        String[] cGrp2 = {"1","2","3"};
        String[] dCateg = {"B", "C", "G", "H", "L"};
        
        final List<Object> csvList;
        csvList = Arrays.asList(new Object[] {
            this.getNextPrimaryKey(),
            this.getLastListValue(dg.getDateBetween(dg.getDate(2013, 1, 1), dg.getDate(2013,12,31)),1,80),
            this.getLastListValue(dg.getDocType(),2,50),
            this.getLastListValue(dg.getOrdReason(),3,50),
            dg.getDate(9999, 12, 31),
            this.getLastListValue(dg.getCompCode(),5,50),
            "G" + dg.getNumberBetween(0, 1),
            this.getLastListValue(dg.getLocCurrency(),7,90),
            this.getLastListValue(dg.getNumberBetween(1030040600, 1090000000),8,80),
            dg.getItem(rType, 25),
            this.getLastListValue(dg.getBusinessName().replaceAll(" ", "").toUpperCase(),10,85),
            dg.getItem(cGrp, 25),
            dg.getItem(cGrp2, 10),
            dg.getItem(dg.getCustGrp(),10,null),
            "",
            "",
            dg.getItem("ZG", 10,null),
            this.getLastListValue(dg.getLocCurrency(),7,90),
            dg.getItem(dCateg),
            "",
            dg.getSalesOff(),
            dg.getItem("2" + Integer.toString(dg.getNumberBetween(0, 13)), 40,null),
            this.getLastListValue(dg.getSalesOrg(),22,70),
            "1",
            this.getLastListValue(dg.getDateBetween(dg.getDate(2013, 1, 1), dg.getDate(2013,12,31)),24,80),
            this.getListItem(7),
            this.getLastListValue(dg.getNumberBetween(50000, 60000),26,80),
            this.getLastListValue(dg.getRandomChar() + "0",27,80).toString().toUpperCase(),
            this.getLastListValue(dg.getDateBetween(dg.getDate(2013, 1, 1), dg.getDate(2013,12,31)),28,80),
            "K4",
            dg.getNumberBetween(1, 9)
                //dg.getFirstName(), dg.getLastName(),dg.getAddress(), dg.getBirthDate(), dg.getEmailAddress(), dg.getBoolean(), dg.getCurrency(100, 10000), dg.getRandomText(10,100)});
        });
        
        this.setList(csvList);
        
        return csvList;
    }
    
    @Override
    public CellProcessor[] getProcessors() {
     
        final CellProcessor[] processors = new CellProcessor[] { 
                new UniqueHashCode(), // customerNo (must be unique)
                new FmtDate("YYYY-MM-dd"),    
                new Optional(),
                new Optional(),
                new FmtDate("YYYY-MM-dd"), 
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
                new FmtDate("YYYY-MM-dd"), 
                new Optional(),
                new Optional(),
                new Optional(),       
                new FmtDate("YYYY-MM-dd"), 
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

package schemas;

/*
 * Copyright 2013, Martin Kleehaus
 * Technical University of Munich (TUM)
 * Professorship for Database Systems and Applications
 */

import datagenerator.DataGenerator;
import java.util.HashMap;
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
        String[] rType = {"KZ","M"};
        String[] cGrp = {"102","103","104","106"};
        String[] cGrp2 = {"1","2","3"};
        String[] dCateg = {"B", "C", "G", "H", "L"};
        int cnt = 0;
        
        Map<String, Object> columns = new HashMap<>();        
        columns.put(this.getHeader()[cnt++],this.getNextPrimaryKey());
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getDateBetween(dg.getDate(2013, 1, 1), dg.getDate(2013,12,31)),this.getHeader()[cnt - 1],80));
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getDocType(),this.getHeader()[cnt - 1],50));
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getOrdReason(),this.getHeader()[cnt - 1],50));
        columns.put(this.getHeader()[cnt++],dg.getDate(9999, 12, 31));
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getCompCode(),this.getHeader()[cnt - 1],50));
        columns.put(this.getHeader()[cnt++],"G" + dg.getNumberBetween(0, 1));
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getLocCurrency(),this.getHeader()[cnt - 1],90));
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getNumberBetween(1030040600, 1090000000),this.getHeader()[cnt - 1],80));
        columns.put(this.getHeader()[cnt++],dg.getItem(rType, 25));
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getBusinessName().replaceAll(" ", "").toUpperCase(),this.getHeader()[cnt - 1],85));
        columns.put(this.getHeader()[cnt++],dg.getItem(cGrp, 25));
        columns.put(this.getHeader()[cnt++],dg.getItem(cGrp2, 10));
        columns.put(this.getHeader()[cnt++],dg.getItem(dg.getCustGrp(),10,null));
        columns.put(this.getHeader()[cnt++],"");
        columns.put(this.getHeader()[cnt++],"");
        columns.put(this.getHeader()[cnt++],dg.getItem("ZG", 10,null));
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getLocCurrency(),this.getHeader()[cnt - 1],90));
        columns.put(this.getHeader()[cnt++],dg.getItem(dCateg));
        columns.put(this.getHeader()[cnt++],"");
        columns.put(this.getHeader()[cnt++],dg.getSalesOff());
        columns.put(this.getHeader()[cnt++],dg.getItem("2" + Integer.toString(dg.getNumberBetween(0, 13)), 40,null));
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getSalesOrg(),this.getHeader()[cnt - 1],70));
        columns.put(this.getHeader()[cnt++],"1");
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getDateBetween(dg.getDate(2013, 1, 1), dg.getDate(2013,12,31)),this.getHeader()[cnt - 1],80));
        columns.put(this.getHeader()[cnt++],this.getMapItem("LOC_CURRCY"));
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getNumberBetween(50000, 60000),this.getHeader()[cnt - 1],80));
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getRandomChar() + "0",this.getHeader()[cnt - 1],80).toString().toUpperCase());
        columns.put(this.getHeader()[cnt++],this.getLastMapValue(dg.getDateBetween(dg.getDate(2013, 1, 1), dg.getDate(2013,12,31)),this.getHeader()[cnt - 1],80));
        columns.put(this.getHeader()[cnt++],"K4");
        columns.put(this.getHeader()[cnt++],dg.getNumberBetween(1, 9));
        
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
    
}

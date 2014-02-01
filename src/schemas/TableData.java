/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package schemas;

import datagenerator.DataGenerator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Martin
 */
public class TableData {
    private final Map<String, Map<String, Object>> soldto = new HashMap<>();
    private final Map<String, Map<String, Object>> material = new HashMap<>();
    private final Map<String, Map<String, Object>> user = new HashMap<>();
    private static TableData instance = null;
    
    public TableData() {}
    
    public static TableData getInstance() {
        if(instance == null) {
            instance = new TableData();
        }
        
        return instance;
    }
    
    public Object getSoldto(Double sfactor, Double change, int row, Object lastvalue, long maxEntries) throws Exception{
        DataGenerator dg = DataGenerator.getInstance();
        if(row > 0) {
            Double factor = sfactor;
            Integer rowCount = this.soldto.size();

            if(rowCount < maxEntries) {
                if (rowCount / row < factor) {
                    this.extendSoldto();
                }
            }
        } else {
            this.extendSoldto();
        }
        if(lastvalue == null) {
            return dg.getItem(this.soldto.get("Sold-to pt").values().toArray());
        } else {
            return dg.getItem(dg.getItem(this.soldto.get("Sold-to pt").values().toArray()),change,lastvalue);
        }

    }
    
    public Object getSoldto(String attribute, String primarykey) {
        if(this.soldto.containsKey(attribute)) {
             if(this.soldto.get(attribute).containsKey(primarykey)) {
                    return this.soldto.get(attribute).get(primarykey);
                }  
        }
        return null;
    }
    
    public Object getSoldto(String attribute, int pos) {
        if(this.soldto.containsKey(attribute)) {
             if(this.soldto.get(attribute).size() >= pos) {
                 return this.soldto.get(attribute).values().toArray()[pos];
             }
        }
        
        return null;
    }
    
    public void extendSoldto() {
        DataGenerator dg = DataGenerator.getInstance();
        String key =  dg.getNumberBetween(1019102192, 1919102192).toString(); //sold-to pt
        Integer cred = dg.getNumberUpTo(99);
        String s = cred < 10 ? "0" + cred.toString() : cred.toString();
        
        this.setSoldto("Sold-to pt", key, key);
        this.setSoldto("Telephone", key, dg.getItem("01" + dg.getNumberBetween(600000, 900000),90,null)); //Telephone
        this.setSoldto("Cred. acct", key, key);
        this.setSoldto("Cred", key, dg.getItem("M" + s,50,null));
        this.setSoldto("Cred.rep.grp", key, dg.getRandomChar() + dg.getNumberUpTo(9).toString());
        
    }
    
    public void setSoldto(String attribute, String key, Object value) {
        if(this.soldto.containsKey(attribute)) {
            this.soldto.get(attribute).put(key, value);
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put(key, value);
            this.soldto.put(attribute, map);
        }
    }
    
    public Object getMaterial(Double sfactor, Double change, int row, Object lastvalue, long maxEntries) throws Exception{
        DataGenerator dg = DataGenerator.getInstance();
        if(row > 0) {
            Double factor = sfactor;
            Integer rowCount = this.material.size();
            
            if(rowCount < maxEntries) {
                if (rowCount / row < factor) {
                    this.extendMaterial();
                }
            }
        } else {
            this.extendMaterial();
        }
        if(lastvalue == null) {
            return dg.getItem(this.material.get("Material").values().toArray());
        } else {
            return dg.getItem(dg.getItem(this.material.get("Material").values().toArray()),change,lastvalue);
        }

    }
    
    public Object getMaterial(String attribute, String primarykey) {
        if(this.material.containsKey(attribute)) {
             if(this.material.get(attribute).containsKey(primarykey)) {
                    return this.material.get(attribute).get(primarykey);
                }  
        }
        return null;
    }
    
    public Object getMaterial(String attribute, int pos) {
        if(this.material.containsKey(attribute)) {
             if(this.material.get(attribute).size() >= pos) {
                 return this.material.get(attribute).values().toArray()[pos];
             }
        }
        
        return null;
    }
    
    public void extendMaterial() {
        DataGenerator dg = DataGenerator.getInstance();
        String key =  dg.getNumberBetween(1, 80000000).toString(); //Material
        String finalkey = new String();
        for(int i = 1;i<=(18-key.length());i++) {
            finalkey = finalkey + "0";
        }
        finalkey = finalkey + key;
        
        String mentered = dg.getNumberBetween(60000000, 80000000).toString();
        String finalmentered = new String();
        for(int i = 1;i<=(18-mentered.length());i++) {
            finalmentered = finalmentered + "0";
        }
        finalmentered = dg.getItem(finalmentered + mentered,0.75,null);
        String group = dg.getNumberBetween(1, 90000).toString();
        String finalgroup = new String();
        for(int i = 1;i<=(6-group.length());i++) {
            finalgroup = finalgroup + "0";
        }
        finalgroup = finalgroup + group;
        
        Integer cred = dg.getNumberUpTo(99);
        String s = cred < 10 ? "0" + cred.toString() : cred.toString();
        
        this.setMaterial("Material", finalkey, finalkey);
        this.setMaterial("Material entered", finalkey, finalmentered);
        this.setMaterial("Matl Group", finalkey, finalgroup);
        this.setMaterial("Description", finalkey, dg.getRandomText(30));
        this.setMaterial("Customer Material Number", finalkey, dg.getItem(dg.getRandomText(30),0.8,null));
        this.setMaterial("Overdel. Tol.", finalkey, dg.getNumberUpTo(20));
        this.setMaterial("Underdel. Tol.", finalkey, this.getMaterial("Overdel. Tol.", finalkey));
        this.setMaterial("ItCa", finalkey, dg.getRandomChars(2)+dg.getNumberText(2));
    }
    
    public void setMaterial(String attribute, String key, Object value) {
        if(this.material.containsKey(attribute)) {
            this.material.get(attribute).put(key, value);
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put(key, value);
            this.material.put(attribute, map);
        }
    }
    
    public Object getUser(Double sfactor, Double change, long row, Object lastvalue, long maxEntries) throws Exception{
        DataGenerator dg = DataGenerator.getInstance();
        if(row > 0) {
            Double factor = sfactor;
            int rowCount = this.user.size();
            
            if(rowCount < maxEntries) {
                if (rowCount / row < factor) {
                    this.extendUser();
                }
            }
        } else {
            this.extendUser();
        }
        if(lastvalue == null) {
            return dg.getItem(this.user.get("user").values().toArray());
        } else {
            return dg.getItem(dg.getItem(this.user.get("user").values().toArray()),change,lastvalue);
        }

    }
    
    public Object getUser(String attribute, String primarykey) {
        if(this.soldto.containsKey(attribute)) {
             if(this.soldto.get(attribute).containsKey(primarykey)) {
                    return this.soldto.get(attribute).get(primarykey);
                }  
        }
        return null;
    }
    
    public Object getUser(String attribute, int pos) {
        if(this.user.containsKey(attribute)) {
             if(this.user.get(attribute).size() >= pos) {
                 return this.user.get(attribute).values().toArray()[pos];
             }
        }
        
        return null;
    }
    
    public Object getUser(String attribute) {
        DataGenerator dg = DataGenerator.getInstance();
        if(this.user.containsKey(attribute)) {
            int i = dg.getNumberUpTo(this.user.get(attribute).size());
            return this.user.get(attribute).values().toArray()[i];
        }
        return null;
    }
    
    public void extendUser() {
        DataGenerator dg = DataGenerator.getInstance();
        String key = dg.getLastName() + "." + dg.getFirstName();
        
        this.setUser("user", key, key);

    }
    
    public void setUser(String attribute, String key, Object value) {
        if(this.user.containsKey(attribute)) {
            this.user.get(attribute).put(key, value);
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put(key, value);
            this.user.put(attribute, map);
        }
    }
    


    
}

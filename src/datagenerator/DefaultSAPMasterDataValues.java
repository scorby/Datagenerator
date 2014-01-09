package datagenerator;

/*
 * Copyright 2013, Martin Kleehaus
 * Technical University of Munich (TUM)
 * Professorship for Database Systems and Applications
 */

import interfaces.SAPMasterDataValues;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;

	
public class DefaultSAPMasterDataValues implements SAPMasterDataValues {
        
        private static final String path = "./sapmd";
        private static List<String> docType;
        private static List<String> compCode;
        private static List<String> custGrp;
        private static List<String> locCurrency;
        private static List<String> ordReason;
        private static List<String> salesOff;
        private static List<String> salesOrg;
        private static List<String> korks;
        private static List<String> storLoc;
        private static List<String> itemCateg;
        private static List<String> route;
        
        
	/**
         * @param filename
	 * @return List of Document Type
         * @throws java.lang.Exception
	 */
	public static List<String> readCSVFile(String filename) throws Exception{
            
            ICsvListReader listReader = null;
            List<String> csvContent = new ArrayList<String>();

            try {
                listReader = new CsvListReader (new FileReader(path + "/" + filename),CsvPreference.STANDARD_PREFERENCE);

                listReader.getHeader(true);
                
                while( (listReader.read()) != null ) {
                    csvContent.add(listReader.getUntokenizedRow()); 
                }

            }
            finally {
                if( listReader != null ) {
                    listReader.close();
                }
            }
            
            return csvContent;
        }

        /**
	 * @return List of Document Type
	 */
        @Override
	public List<String> getDocType() throws Exception{
            
            if (docType == null) {
                docType = readCSVFile("doctype.csv");   
            }
            
            return docType;
        }
        
         /**
	 * @return List of Order Reason
	 */
        @Override
	public List<String> getOrdReason() throws Exception{
            
            if (ordReason == null) {
                ordReason = readCSVFile("ordreason.csv");    
            }
            
            return ordReason;
        }
        
         /**
	 * @return List of Company Code
	 */
        @Override
	public List<String> getCompCode() throws Exception{
            
            if (compCode == null) {
                compCode = readCSVFile("compcode.csv");   
            }
            
            return compCode;
        }
        
        
         /**
	 * @return List of CCustomer Group
	 */
        @Override
	public List<String> getCustGrp() throws Exception{
            
            if (custGrp == null) {
                custGrp = readCSVFile("custgrp.csv");
            }
            
            return custGrp;
        }
        
        
         /**
	 * @return List of Local Currency
	 */
        @Override
	public List<String> getLocCurrency() throws Exception{
            
            if (locCurrency == null) {
                locCurrency = readCSVFile("loccur.csv");    
            }
            
            return locCurrency;
        }
        
         /**
	 * @return List of Sales Office
	 */
        @Override
	public List<String> getSalesOff() throws Exception{
            
            if (salesOff == null) {
                salesOff = readCSVFile("salesoff.csv");    
            }
            
            return salesOff;
        }
        
         /**
	 * @return List of Sales Office
	 */
        @Override
	public List<String> getSalesOrg() throws Exception{
            
            if (salesOrg == null) {
                salesOrg = readCSVFile("salesorg.csv");   
            }
            
            return salesOrg;
        }
        
         /**
	 * @return List of korks
	 */
        @Override
	public List<String> getKorks() throws Exception{
            
            if (korks == null) {
                korks = readCSVFile("korks.csv");   
            }
            
            return korks;
        }
        
         /**
	 * @return List of Storage Location
	 */
        @Override
	public List<String> getStorLoc() throws Exception{
            
            if (storLoc == null) {
                storLoc = readCSVFile("storloc.csv");   
            }
            
            return storLoc;
        }
        
        /**
	 * @return List of Item Category
	 */
        @Override
	public List<String> getItemCateg() throws Exception{
            
            if (itemCateg == null) {
                itemCateg = readCSVFile("itemcateg.csv");   
            }
            
            return itemCateg;
        } 
        
        /**
	 * @return List of Route
	 */
        @Override
	public List<String> getRoute() throws Exception{
            
            if (route == null) {
                route = readCSVFile("route.csv");   
            }
            
            return route;
        } 

}

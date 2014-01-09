package interfaces;

import java.util.List;

/*
 * Copyright 2013, Martin Kleehaus
 * Technical University of Munich (TUM)
 * Professorship for Database Systems and Applications
 */


public interface SAPMasterDataValues {
	
	/**
	 * @return Array of Document Type
         * @throws java.lang.Exception
	 */
	List<String> getDocType() throws Exception;

	/**
	 * @return Array of Order Reason
         * @throws java.lang.Exception
	 */
	List<String> getOrdReason() throws Exception;

	/**
	 * @return Array of Company Code
         * @throws java.lang.Exception
	 */
	List<String> getCompCode() throws Exception;
        
        /**
	 * @return Array of Local Currency
         * @throws java.lang.Exception
	 */
	List<String> getLocCurrency() throws Exception;
        
         /**
	 * @return Array of Customer Group
         * @throws java.lang.Exception
	 */
	List<String> getCustGrp() throws Exception;
        
        /**
	 * @return Array of Sales Office
         * @throws java.lang.Exception
	 */
	List<String> getSalesOff() throws Exception;
        
         /**
	 * @return Array of Sales Organisation
         * @throws java.lang.Exception
	 */
	List<String> getSalesOrg() throws Exception;
        
         /**
	 * @return Array of Sales Organisation
         * @throws java.lang.Exception
	 */
	List<String> getKorks() throws Exception;
        
        /**
	 * @return Array of Sales Organisation
         * @throws java.lang.Exception
	 */
	List<String> getStorLoc() throws Exception;
        
        /**
	 * @return Array of Sales Organisation
         * @throws java.lang.Exception
	 */
	List<String> getItemCateg() throws Exception;
        
        /**
	 * @return Array of Sales Organisation
         * @throws java.lang.Exception
	 */
	List<String> getRoute() throws Exception;

}

package interfaces;


/*
 * Copyright 2013, Martin Kleehaus
 * Technical University of Munich (TUM)
 * Professorship for Database Systems and Applications
 */


public interface AddressDataValues {
	
	/**
	 * @return Array of street address
	 */
	String[] getStreetNames();

	/**
	 * @return Array of cities
	 */
	String[] getCities();

	/**
	 * Returns a list of address suffixes such as "Lane", "Drive","Parkway"
	 * @return Array of address suffixes
	 */
	String[] getAddressSuffixes();
        
        /**
	 * @return Array of country codes
	 */
	String[] getCountryCode();

}

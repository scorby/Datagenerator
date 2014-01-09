package interfaces;


/*
 * Copyright 2013, Martin Kleehaus
 * Technical University of Munich (TUM)
 * Professorship for Database Systems and Applications
 */

public interface ContentDataValues {

	String[] getWords();

	String[] getBusinessTypes();

	String[] getEmailHosts();

	String[] getTlds();
        
        String[] getCurrency();
        
        String[] getWeightType();
}

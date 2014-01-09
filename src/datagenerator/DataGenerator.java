
package datagenerator;

/*
 * Copyright 2013, Martin Kleehaus
 * Technical University of Munich (TUM)
 * Professorship for Database Systems and Applications
 */

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.io.FileWriter;

import interfaces.*;
import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.spi.CurrencyNameProvider;
import javax.swing.JProgressBar;

import org.supercsv.cellprocessor.*;
import org.supercsv.cellprocessor.constraint.*;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.*;
import org.supercsv.prefs.CsvPreference;
/**
 *
 * @author Martin Kleehaus
 */
public class DataGenerator {
    
    private static Random random = new Random(93285);
    private Integer uniqueNumber = 1;
    private static DataGenerator instance = null;
    
    private NameDataValues nameDataValues = new DefaultNameDataValues();
    private AddressDataValues addressDataValues = new DefaultAddressDataValues();
    private ContentDataValues contentDataValues = new DefaultContentDataValues();
    private SAPMasterDataValues sapMasterDataValues = new DefaultSAPMasterDataValues();
    
    private DataGenerator() {}
    
    public static DataGenerator getInstance() {
        if(instance == null) {
            instance = new DataGenerator();
        }
        
        return instance;
    }
    
     /**
     * Returns a random item from a list of items.
     * 
     * @param <T>
     *            Item type in the list and to return
     * @param item
     *            single item to choose from
     * @param probability
     *            chance (in %, 100 being guaranteed) of returning the value of the item
     * @param defaultItem
     *            value to return if the probability test fails
     * @return Item from the list
     */
    public <T> T getItem(T item, int probability, T defaultItem) {
            List<T> list = new ArrayList<>();
            list.add(item);
            return getItem(list, probability, defaultItem);
    }

    /**
     * Returns a random item from a list of items.
     * 
     * @param <T>
     *            Item type in the list and to return
     * @param items
     *            List of items to choose from
     * @return Item from the list
     */
    public <T> T getItem(List<T> items) {
            return getItem(items, 100, null);
    }

    /**
     * Returns a random item from a list of items or the null depending on the
     * probability parameter. The probability determines the chance (in %) of
     * returning an item off the list versus null.
     * 
     * @param <T>
     *            Item type in the list and to return
     * @param items
     *            List of items to choose from
     * @param probability
     *            chance (in %, 100 being guaranteed) of returning an item from
     *            the list
     * @return Item from the list or null if the probability test fails.
     */
    public <T> T getItem(List<T> items, int probability) {
            return getItem(items, probability, null);
    }

    /**
     * Returns a random item from a list of items or the defaultItem depending
     * on the probability parameter. The probability determines the chance (in
     * %) of returning an item off the list versus the default value.
     * 
     * @param <T>
     *            Item type in the list and to return
     * @param items
     *            List of items to choose from
     * @param probability
     *            chance (in %, 100 being guaranteed) of returning an item from
     *            the list
     * @param defaultItem
     *            value to return if the probability test fails
     * @return Item from the list or the default value
     */
    public <T> T getItem(List<T> items, int probability, T defaultItem) {
            if (items == null) {
                    throw new IllegalArgumentException("Item list cannot be null");
            }
            if (items.isEmpty()) {
                    throw new IllegalArgumentException("Item list cannot be empty");
            }

            return chance(probability) ? items.get(random.nextInt(items.size())) : defaultItem;
    }

    /**
     * Returns a random item from an array of items
     * 
     * @param <T>
     *            Array item type and the type to return
     * @param items
     *            Array of items to choose from
     * @return Item from the array
     */
    public <T> T getItem(T[] items) {
            return getItem(items, 100, null);
    }

    /**
     * Returns a random item from an array of items or null depending on the
     * probability parameter. The probability determines the chance (in %) of
     * returning an item from the array versus null.
     * 
     * @param <T>
     *            Array item type and the type to return
     * @param items
     *            Array of items to choose from
     * @param probability
     *            chance (in %, 100 being guaranteed) of returning an item from
     *            the array
     * @return Item from the array or the default value
     */
    public <T> T getItem(T[] items, int probability) {
            return getItem(items, probability, null);
    }

    /**
     * Returns a random item from an array of items or the defaultItem depending
     * on the probability parameter. The probability determines the chance (in
     * %) of returning an item from the array versus the default value.
     * 
     * @param <T>
     *            Array item type and the type to return
     * @param items
     *            Array of items to choose from
     * @param probability
     *            chance (in %, 100 being guaranteed) of returning an item from
     *            the array
     * @param defaultItem
     *            value to return if the probability test fails
     * @return Item from the array or the default value
     */
    public <T> T getItem(T[] items, int probability, T defaultItem) {
            if (items == null) {
                    throw new IllegalArgumentException("Item array cannot be null");
            }
            if (items.length == 0) {
                    throw new IllegalArgumentException("Item array cannot be empty");
            }
            return chance(probability) ? items[random.nextInt(items.length)]
                            : defaultItem;
    }

    /**
     * @return A random first name
     */
    public String getFirstName() {
            return getItem(nameDataValues.getFirstNames());
    }

    /**
     * Returns a combination of first and last name values in one string
     * 
     * @return
     */
    public String getName() {
            return getItem(nameDataValues.getFirstNames()) + " "
                            + getItem(nameDataValues.getLastNames());
    }

    /**
     * @return A random last name
     */
    public String getLastName() {
            return getItem(nameDataValues.getLastNames());
    }

    /**
     * @return A random street name
     */
    public String getStreetName() {
            return getItem(addressDataValues.getStreetNames());
    }

    /**
     * @return A random street suffix
     */
    public String getStreetSuffix() {
            return getItem(addressDataValues.getAddressSuffixes());
    }

    /**
     * Generates a random city value
     * 
     * @return City as a string
     */
    public String getCity() {
            return getItem(addressDataValues.getCities());
    }

    /**
     * Generates an address value consisting of house number, street name and
     * street suffix. i.e. <code>543 Larkhill Road</code>
     * 
     * @return Address as a string
     */
    public String getAddress() {
            int num = 404 + random.nextInt(1400);
            return num + " " + getStreetName() + " " + getStreetSuffix();
    }

    /**
     * Generates line 2 for a street address (usually an Apt. or Suite #).
     * Returns null if the probabilty test fails.
     * 
     * @param probability
     *            Chance as % of have a value returned instead of null.
     * @return Street address line two or null if the probability test fails
     */
    public String getAddressLine2(int probability) {
            return getAddressLine2(probability, null);
    }

    /**
     * Generates line 2 for a street address (usually an Apt. or Suite #).
     * Returns default value if the probabilty test fails.
     * 
     * @param probability
     *            Chance as % of have a value returned instead of null.
     * @param defaultValue
     *            Value to return if the probability test fails.
     * @return Street address line two or null if the probability test fails
     */
    public String getAddressLine2(int probability, String defaultValue) {
            return chance(probability) ? getAddressLine2() : defaultValue;
    }

    /**
     * Generates line 2 for a street address (usually an Apt. or Suite #).
     * Returns default value if the probabilty test fails.
     * 
     * @return Street address line 2
     */
    public String getAddressLine2() {
            int test = random.nextInt(100);
            if (test < 50) {
                    return "Apt #" + 100 + random.nextInt(1000);
            } else {
                    return "Suite #" + 100 + random.nextInt(1000);
            }
    }

    /**
     * Creates a random birthdate within the range of 1955 to 1985
     * 
     * @return Date representing a birthdate
     */
    public Date getBirthDate() {
            Date base = new Date(0);
            return getDate(base, -365 * 15, 365 * 15);
    }

    /**
     * Returns a random int value.
     * 
     * @return random number
     */
    public int getNumber() {
            return getNumberBetween(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    public Integer getUniqueNumber() {
        return this.uniqueNumber++;
    }
    

    /**
     * Returns a random number between 0 and max
     * 
     * @param max
     *            Maximum value of result
     * @return random number no more than max
     */
    public int getNumberUpTo(int max) {
            return getNumberBetween(0, max);
    }

    /**
     * Returns a number betwen min and max
     * 
     * @param min
     *            minimum value of result
     * @param max
     *            maximum value of result
     * @return Random number within range
     */
    public Integer getNumberBetween(int min, int max) {

        validateMinMaxParams(min, max);
        return min + random.nextInt(max - min);
    }
    
    public Double getCurrency(int min, int max) {
        validateMinMaxParams(min, max);
        Double currency = random.nextDouble();
        
        return round(min + (currency * (max - min)),2);
    }
    
    public boolean getBoolean() {

        return this.getNumberBetween(0, 2) == 1;
    }

    /**
     * Builds a date from the year, month, day values passed in
     * 
     * @param year
     *            The year of the final {@link Date} result
     * @param month
     *            The month of the final {@link Date} result (from 1-12)
     * @param day
     *            The day of the final {@link Date} result
     * @return Date representing the passed in values.
     */
    public Date getDate(int year, int month, int day) {
            Calendar cal = Calendar.getInstance();
            cal.clear();
            cal.set(year, month - 1, day, 0, 0, 0);
            return cal.getTime();
    }

    /**
     * Returns a random date which is in the range <code>baseData</code> +
     * <code>minDaysFromData</code> to <code>baseData</code> +
     * <code>maxDaysFromData</code>. This method does not alter the time
     * component and the time is set to the time value of the base date.
     * 
     * @param baseDate
     *            Date to start from
     * @param minDaysFromDate
     *            minimum number of days from the baseDate the result can be
     * @param maxDaysFromDate
     *            maximum number of days from the baseDate the result can be
     * @return A random date
     */
    public Date getDate(Date baseDate, int minDaysFromDate, int maxDaysFromDate) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(baseDate);
            int diff = minDaysFromDate
                            + (random.nextInt(maxDaysFromDate - minDaysFromDate));
            cal.add(Calendar.DATE, diff);
            return cal.getTime();
    }

    /**
     * Returns a random date between two dates. This method will alter the time
     * component of the dates
     * 
     * @param minDate
     *            Minimum date that can be returned
     * @param maxDate
     *            Maximum date that can be returned
     * @return random date between these two dates.
     */
    public Date getDateBetween(Date minDate, Date maxDate) {
            // this can break if seconds is an int
            long seconds = (maxDate.getTime() - minDate.getTime()) / 1000;
            seconds = (long) (random.nextDouble() * seconds);
            Date result = new Date();
            result.setTime(minDate.getTime() + (seconds * 1000));
            return result;
    }
    

    /**
     * Returns random text made up of english words of length
     * <code>length</code>
     * 
     * @param length
     *            length of returned string
     * 
     * @return string made up of actual words with length <code>length</code>
     */
    public String getRandomText(int length) {
            return getRandomText(length, length);
    }

    /**
     * Returns random text made up of english words
     * 
     * @param minLength
     *            minimum length of returned string
     * @param maxLength
     *            maximum length of returned string
     * @return string of length between min and max length
     */
    public String getRandomText(int maxWordLength, int maxTextLength) {

            StringBuilder sb = new StringBuilder(maxTextLength);
            int length = maxTextLength;

            while (length > 0) {
                    if (sb.length() != 0) {
                            sb.append(" ");
                            length--;
                    }
                    String word = getRandomWord(1,maxWordLength);
                    sb.append(word);
                    length = length - word.length();
            }
            return sb.toString();
    }

    private void validateMinMaxParams(int minLength, int maxLength) {
            if (minLength < 0) {
                    throw new IllegalArgumentException(
                                    "Minimum length must be a non-negative number");
            }

            if (maxLength < 0) {
                    throw new IllegalArgumentException(
                                    "Maximum length must be a non-negative number");
            }

            if (maxLength < minLength) {
                    throw new IllegalArgumentException(
                                    String.format(
                                                    "Minimum length must be less than maximum length (min=%d, max=%d)",
                                                    minLength, maxLength));
            }
    }

    /**
     * @return a random character
     */
    public char getRandomChar() {
            return (char) (random.nextInt(26) + 'a');
    }

    /**
     * Return a string containing <code>length</code> random characters
     * 
     * @param length
     *            number of characters to use in the string
     * @return A string containing <code>length</code> random characters
     */
    public String getRandomChars(int length) {
            return getRandomChars(length, length);
    }

    /**
     * Return a string containing between <code>length</code> random characters
     * 
     * @param length
     *            number of characters to use in the string
     * @return A string containing <code>length</code> random characters
     */
    public String getRandomChars(int minLength, int maxLength) {
            validateMinMaxParams(minLength, maxLength);
            StringBuilder sb = new StringBuilder(maxLength);

            int length = minLength;
            if (maxLength != minLength) {
                    length = length + random.nextInt(maxLength - minLength);
            }
            while (length > 0) {
                    sb.append(getRandomChar());
                    length--;
            }
            return sb.toString();
    }
    
     /**
     * Returns a random character in specified array list.
     * 
     * @param list
     *          array list with type String
     * 
     * @return A string
     */
    
    public String getRandomStringIn(String list[]) {
        if(list.length > 0) {
            return list[random.nextInt(list.length)];
        }
        
        return null;
    }

    /**
     * Returns a word of a length between 1 and 10 characters.
     * 
     * @return A work of max length 10
     */
    public String getRandomWord() {
            return getItem(contentDataValues.getWords());
    }

    /**
     * Returns a valid word with a length of <code>length</code>
     * characters.
     * 
     * @param length
     *            maximum length of the word
     * @return a word of a length up to <code>length</code> characters
     */
    public String getRandomWord(int length) {
            return getRandomWord(length, length);
    }

    /**
     * Returns a valid word with a length of up to <code>length</code>
     * characters. If the <code>exactLength</code> parameter is set, then the
     * word will be exactly <code>length</code> characters in length.
     * 
     * @param length
     *            maximum length of the returned string
     * @param exactLength
     *            indicates if the word should have a length of
     *            <code>length</code>
     * @return a string with a length that matches the specified parameters.
     */
    public String getRandomWord(int length, boolean exactLength) {
            if (exactLength) {
                    return getRandomWord(length, length);
            }
            return getRandomWord(0, length);
    }

    /**
     * Returns a valid word based on the length range passed in. The length will
     * always be between the min and max length range inclusive.
     * 
     * @param minLength minimum length of the word
     * @param maxLength maximum length of the word
     * @return a word of a length between min and max length
     */
    public String getRandomWord(int minLength, int maxLength) {
            validateMinMaxParams(minLength, maxLength);

            // special case if we need a single char
            if (maxLength == 1) {
                    if (chance(50)) {
                            return "a";
                    }
                    return "I";
            }

            String value = null;

            // start from random pos and find the first word of the right size
            String[] words = contentDataValues.getWords();
            int pos = random.nextInt(words.length);
            for (int i = 0; i < words.length; i++) {
                    int idx = (i + pos) % words.length;
                    String test = words[idx];
                    if (test.length() >= minLength && test.length() <= maxLength) {
                            return test;
                    }
            }
            // we haven't a word for this length so generate one
            return getRandomChars(minLength, maxLength);
    }

    /**
     * 
     * @param chance
     *            Chance of a suffix being returned
     * @return
     */
    public String getSuffix(int chance) {
            return getItem(nameDataValues.getSuffixes(), chance);
    }

    /**
     * Return a person prefix or null if the odds are too low.
     * 
     * @param chance
     *            Odds of a prefix being returned
     * @return Prefix string
     */
    public String getPrefix(int chance) {
            return getItem(nameDataValues.getPrefixes(), chance);
    }

    /**
     * Returns a string containing a set of numbers with a fixed number of
     * digits
     * 
     * @param digits
     *            number of digits in the final number
     * @return Random number as a string with a fixed length
     */
    public String getNumberText(int digits) {
            String result = "";
            for (int i = 0; i < digits; i++) {
                    result = result + random.nextInt(10);
            }
            return result;
    }

    /**
     * Generates a random business name by taking a city name and additing a
     * business onto it.
     * 
     * @return A random business name
     */
    public String getBusinessName() {
        String s = getCity() + "-" + getItem(contentDataValues.getBusinessTypes());
        return s;
    }

    /**
     * Generates an email address
     * 
     * @return an email address
     */
    public String getEmailAddress() {
            int test = random.nextInt(100);
            String email = "";
            if (test < 50) {
                    // name and initial
                    email = getFirstName().charAt(0) + getLastName();
            } else {
                    // 2 words
                    email = getItem(contentDataValues.getWords())
                                    + getItem(contentDataValues.getWords());
            }
            if (random.nextInt(100) > 80) {
                    email = email + random.nextInt(100);
            }
            email = email + "@" + getItem(contentDataValues.getEmailHosts()) + "."
                            + getItem(contentDataValues.getTlds());
            return email.toLowerCase();
    }

    /**
     * Gives you a true/false based on a probability with a random number
     * generator. Can be used to optionally add elements.
     * 
     * <pre>
     * if (DataFactory.chance(70)) {
     * 	// 70% chance of this code being executed
     * }
     * </pre>
     * 
     * @param chance
     *            % chance of returning true
     * @return
     */
    public boolean chance(int chance) {
            return random.nextInt(100) < chance;
    }

    public NameDataValues getNameDataValues() {
            return nameDataValues;
    }
    
    // public methods for SAP Master Data
    public String getLocCurrency () throws Exception{
        return this.getItem(this.sapMasterDataValues.getLocCurrency()); 
    }
    
    public String getCompCode () throws Exception{
        return this.getItem(this.sapMasterDataValues.getCompCode()); 
    }

    public String getSalesOff () throws Exception{
        return this.getItem(this.sapMasterDataValues.getSalesOff()); 
    }
            
    public String getSalesOrg () throws Exception{
        return this.getItem(this.sapMasterDataValues.getSalesOrg()); 
    }
    
    public String getOrdReason () throws Exception{
        return this.getItem(this.sapMasterDataValues.getOrdReason()); 
    }
    
    public String getCustGrp () throws Exception{
        return this.getItem(this.sapMasterDataValues.getCustGrp()); 
    }
    
    public String getDocType () throws Exception{
        return this.getItem(this.sapMasterDataValues.getDocType()); 
    }
    
    public String getKorks () throws Exception{
        return this.getItem(this.sapMasterDataValues.getKorks()); 
    }
    
    public String getStorLoc () throws Exception{
        return this.getItem(this.sapMasterDataValues.getStorLoc()); 
    }
    
    public String getRoute () throws Exception{
        return this.getItem(this.sapMasterDataValues.getRoute()); 
    }
    
    public String getItemCateg () throws Exception{
        return this.getItem(this.sapMasterDataValues.getItemCateg()); 
    }
        
    

    /**
     * Call randomize with a seed value to reset the random number generator. By
     * using the same seed over different tests, you will should get the same
     * results out for the same data generation calls.
     * 
     * @param seed
     *            Seed value to use to generate random numbers
     */
    public void randomize(int seed) {
            random = new Random(seed);
    }

    /**
     * Set this to provide your own list of name data values by passing it a
     * class that implements the {@link NameDataValues} interface which just
     * returns the String arrays to use for the test data.
     * 
     * @param nameDataValues
     *            Object holding the set of data values to use
     */
    public void setNameDataValues(NameDataValues nameDataValues) {
            this.nameDataValues = nameDataValues;
    }

    /**
     * Set this to provide your own list of address data values by passing it a
     * class that implements the {@link AddressDataValues} interface which just
     * returns the String arrays to use for the test data.
     * 
     * @param addressDataValues
     *            Object holding the set of data values to use
     */
    public void setAddressDataValues(AddressDataValues addressDataValues) {
            this.addressDataValues = addressDataValues;
    }
    
    public void setUniqueNumber(int number) {
        this.uniqueNumber = number;
    }

    /**
     * Set this to provide your own list of content data values by passing it a
     * class that implements the {@link ContentDataValues} interface which just
     * returns the String arrays to use for the test data.
     * 
     * @param contentDataValues
     *            Object holding the set of data values to use
     */
    public void setContentDataValues(ContentDataValues contentDataValues) {
            this.contentDataValues = contentDataValues;
    }
    
    
    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
}

    
   
}
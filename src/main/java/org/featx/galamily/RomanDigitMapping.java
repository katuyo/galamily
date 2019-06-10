package org.featx.galamily;

/**
 * A mapping for recording some symbol words to roman digit symbols
 *
 * @author excepts
 * @since 2019-03-27
 */
public interface RomanDigitMapping {

    /**
     * Record a mapping
     *
     * @param numberString Some word
     * @param romanString Some roman digit symbol
     */
    void put(String numberString, String romanString);

    /**
     * Calculate the double value of a words sequence as roman digit symbol
     *
     * @param segments The sequence segments
     * @return The calculated out value
     */
    Double valueOf(String... segments);
}

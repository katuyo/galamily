package org.featx.galamily;

/**
 * The validate defines for validating if some Roman symbol Sequence is legal
 *
 * @author excepts
 * @since 2019-03-25
 */
public interface RomanNumeralValidate {
    /**
     * Check if some roman symbol sequence has the legal repeatable chars
     * If not @RomanNumeralValidateException would be throw out
     *
     * @param romanSymbolSequence The sequence would be test/validate
     * @throws RomanNumeralValidateException 1001, if 'V','L','D' repeated more than 1 time
     *                                       1002, if 'I','X','C', 'M' repeated more than 3 times
     */
    void repeatable(String romanSymbolSequence) throws RomanNumeralValidateException;

    /**
     * Check if some roman symbol sequence has the legal subtractive
     * If not, @RomanNumeralValidateException would be throw out
     *
     * @param romanSymbolSequence The sequence would be test/validate
     * @throws RomanNumeralValidateException 1003, Not match the roman regex expression
     */
    void subtractive(String romanSymbolSequence) throws RomanNumeralValidateException;

}

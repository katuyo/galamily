package org.featx.galamily;

/**
 * The feature described a feature of formatting int value as roman symbol sequence,
 * And the reverse function.
 *
 * @author excepts
 * @since 2019-03-25
 */
public interface RomanNumeralFormat {
    /**
     * Parse the roman symbol sequence to arabic number
     *
     * @param romanSymbolSequence A roman symbol sequence, The parsing target
     * @return Integer Expressing an arabic number, parsed output.
     * @throws RomanNumeralValidateException 1001, if 'V','L','D' repeated more than 1 time
     *                                       1002, if 'I','X','C', 'M' repeated more than 3 times
     *                                       1003, Not match the roman regex expression
     */
    Integer parse(String romanSymbolSequence) throws RomanNumeralValidateException;

    /**
     * Format an arabic number as a roman symbol sequence.
     *
     * @param value A arabic number, the format target
     * @return String Express a roman symbol sequence, formatted output.
     * @throws RomanNumeralValidateException 1000, It can only support 3999 as max limit for now.
     */
    String format(Integer value) throws RomanNumeralValidateException;

}

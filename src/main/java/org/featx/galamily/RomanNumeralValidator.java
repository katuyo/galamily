package org.featx.galamily;

import java.util.Objects;
import java.util.regex.Pattern;

import static org.featx.galamily.Constants.EMPTY_STR;
import static org.featx.galamily.Constants.ROMAN_NUM_REGEX;

/**
 *
 *
 * @author excepts
 * @since 2019-03-26
 */
public class RomanNumeralValidator implements RomanNumeralValidate {

    @Override
    public void repeatable(String romanSymbolSequence) throws RomanNumeralValidateException {
        if (romanSymbolSequence == null || EMPTY_STR.equals(romanSymbolSequence.trim())) {
            return;
        }
        final int[] repeats = new int[3];
        for (int c : romanSymbolSequence.toCharArray()) {
            if (Objects.equals(repeats[0], 0)) {
                repeats[0] = c;
            } else if (Objects.equals(repeats[2], c)) {
                throw new RomanNumeralValidateException(RomanNumeralValidateError.REPEAT_MORE);
            } else if (Objects.equals(repeats[1], c)) {
                repeats[2] = c;
            } else if (Objects.equals(repeats[0], c)) {
                if (RomanDigit.isFive(new String(new char[]{(char) c}))) {
                    throw new RomanNumeralValidateException(RomanNumeralValidateError.REPEAT_NEVER);
                }
                repeats[1] = c;
            } else {
                repeats[0] = c;
                repeats[1] = 0;
                repeats[2] = 0;
            }
        }
    }

    @Override
    public void subtractive(String romanSymbolSequence) throws RomanNumeralValidateException {
        if (!Pattern.matches(ROMAN_NUM_REGEX, romanSymbolSequence)) {
            throw new RomanNumeralValidateException(RomanNumeralValidateError.SUBSTRACTIVE);
        }
    }
}

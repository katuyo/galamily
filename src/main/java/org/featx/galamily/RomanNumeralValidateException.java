package org.featx.galamily;

/**
 * Common Business Exception
 *
 * @author excepts
 * @since 2019-03-26
 */
public class RomanNumeralValidateException extends Exception {

    private String code;

    RomanNumeralValidateException(RomanNumeralValidateError error) {
        super(error.getMessage());
        this.code = error.getCode();
    }

    public String getCode() {
        return code;
    }
}

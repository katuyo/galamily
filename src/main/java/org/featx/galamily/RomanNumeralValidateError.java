package org.featx.galamily;

/**
 * Error enums
 *
 * @author excepts
 * @since 2019-03-26
 */
public enum RomanNumeralValidateError {

    MAX_SUPPORT("1000", "It can only support 3999 as max limit for now."),
    REPEAT_NEVER("1001", "\"D\", \"L\", and \"V\" can never be repeated"),
    REPEAT_MORE("1002", "Only permit 3 times repeats in succession"),
    SUBSTRACTIVE("1003", "Not match the roman regex expression");

    private String code;

    private String message;

    RomanNumeralValidateError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

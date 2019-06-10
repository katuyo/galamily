package org.featx.galamily;

/**
 * App using constants.
 *
 * @author excepts
 * @since 2019-03-26
 */
class Constants {

    static final String SEPARATOR = "\\s+";

    static final String EQUAL_SYMBOL = "is";

    static final String[] EQUAL_SYMBOLES = {"=", "is", "equals", "equal"};

    static final double DEFAULT_DOUBLE = 0.0;

    static final int DEFAULT_INTEGER = 0;

    static final int MAX_VALUE = 3999;

    static final String ROMAN_NUM_REGEX = "M{0,3}(D{0,1}C{0,3}|CM|CD)(L{0,1}X{0,3}|XL|XC)(V{0,1}I{0,3}|IX|IV)";

    static final String EMPTY_STR = "";

    static final String EXCHANGE_RATES_MORE = "Built up error rate system. More than one exchange rate for exchange {0} and {1}";

    static final String CANNOT_DEAL = "I have no idea what you are talking about";

    static final String FQA_SYMBOL = "?";

    static final String NO_COMMAND = "Nothing required to do.";

    static final String NUMERIC_QUESTION_SYMBOL = "how much";

    static final String EXCHANGE_QUESTION_SYMBOL = "how many";

    private Constants() {

    }
}

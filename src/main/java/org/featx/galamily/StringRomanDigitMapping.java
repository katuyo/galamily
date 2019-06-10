package org.featx.galamily;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.featx.galamily.Constants.DEFAULT_DOUBLE;

/**
 *
 *
 * @author excepts
 * @since 2019-03-26
 */
public class StringRomanDigitMapping implements RomanDigitMapping {

    private Map<String, String> map;

    private RomanNumeralFormat romanNumeralFormat;

    public StringRomanDigitMapping() {
        map = new HashMap<>();
        romanNumeralFormat = new RomanNumeralFormatter();
    }

    @Override
    public void put(String numberString, String romanString) {
        map.put(numberString, romanString);
    }

    @Override
    public Double valueOf(String... segments) {
        StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(segments).forEach(string -> stringBuilder.append(
                Optional.ofNullable(map.get(string)).orElse(string)
        ));
        try {
            return romanNumeralFormat.parse(stringBuilder.toString()).doubleValue();
        } catch (RomanNumeralValidateException e) {
            return DEFAULT_DOUBLE;
        }
    }
}

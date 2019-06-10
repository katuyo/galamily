package org.featx.galamily;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RomanNumeralFormatterTest {
    private RomanNumeralFormat target;

    RomanNumeralFormatterTest() {
        RomanNumeralFormatter romanNumeralFormatter = new RomanNumeralFormatter();
        romanNumeralFormatter.setRomanNumeralValidate(new RomanNumeralValidator());
        target = romanNumeralFormatter;
    }

    @ParameterizedTest(name = "{0} arabic: {1}")
    @CsvSource({
            "4000,",
            "MMMCMXCIX, 3999",
            "MMMCMLXXXVII, 3987",
            "MMDIX, 2509",
            "MCCXXXIV, 1234",
            "MMXXXIII, 2033",
            "X, 10",
            "L, 50",
            "CM, 900",
            "M, 1000",
            "LI, 51",
            "CCCLXXXI, 381"
    })
    void testParse(String roman, Integer expected) {
        try {
            assertEquals(expected, target.parse(roman));
        } catch (RomanNumeralValidateException e) {
            assertNull(expected);
        }
    }

    @ParameterizedTest(name = "{0} roman: {1}")
    @CsvSource({
            "4000,",
            "3999, MMMCMXCIX",
            "3987, MMMCMLXXXVII",
            "2509, MMDIX",
            "1234, MCCXXXIV",
            "2033, MMXXXIII"
    })
    void testFormat(Integer param, String expected) {
        try {
            assertEquals(expected, target.format(param));
        } catch (RomanNumeralValidateException e) {
            assertNull(expected);
        }
    }
}

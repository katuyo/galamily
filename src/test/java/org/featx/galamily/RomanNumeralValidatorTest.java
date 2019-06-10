package org.featx.galamily;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class RomanNumeralValidatorTest {

    private RomanNumeralValidator target;

    RomanNumeralValidatorTest() {
        target = new RomanNumeralValidator();
    }

    @ParameterizedTest(name = "Repeatable test: {0} would throw exception {1}")
    @CsvSource({
            "IMMMCCCVXXXIII,",
            "XXIXXXXIXX, 1002",
            "MMMCCCDDI, 1001"
    })
    void testRepeatableMore(String romanNumberSeq, String exceptionCode) {
        try {
            target.repeatable(romanNumberSeq);
            assertNull(exceptionCode);
        } catch (RomanNumeralValidateException rnve) {
            assertEquals(rnve.getCode(), exceptionCode);
        }
    }

    @ParameterizedTest(name = "Subtractive test: {0}")
    @CsvSource({
            "MMMCCCXXXIII,",
            "XXIXXXXIXX, 1003",
            "MMMCCCDDI, 1003"
    })
    void testSubtractive(String test, String exceptionCode) {
        try {
            target.subtractive(test);
            assertNull(exceptionCode);
        } catch (RomanNumeralValidateException e) {
            assertEquals(exceptionCode, e.getCode());
        }
    }
}

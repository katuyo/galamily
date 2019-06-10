package org.featx.galamily;

import org.junit.jupiter.api.Test;
import static org.featx.galamily.Constants.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

public class StringRomanDigitMappingTest {
    private RomanDigitMapping romanDigitMapping;

    public StringRomanDigitMappingTest() {
        this.romanDigitMapping = new StringRomanDigitMapping();
    }

    @Test
    public void testM() {
        String[] pairs = {"blmg, M", "bddj, C", "qddkf, L", "fdjvb, X", "zkdfj, V", "jfdn, D", "tvnc, I"};
        Arrays.stream(pairs).forEach(pair -> {
            var parz = pair.split("," + SEPARATOR);
            romanDigitMapping.put(parz[0], parz[1]);
        });
        Double actual = romanDigitMapping.valueOf("blmg", "blmg", "blmg", "bddj", "blmg", "qddkf",
                "fdjvb", "fdjvb", "fdjvb", "zkdfj", "tvnc", "tvnc");
        assertEquals(3987.0, actual);
    }
}

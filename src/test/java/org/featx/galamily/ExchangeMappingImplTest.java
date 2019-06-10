package org.featx.galamily;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.featx.galamily.Constants.SEPARATOR;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExchangeMappingImplTest {

    private ExchangeMapping exchangeMapping;

    public ExchangeMappingImplTest() {
        this.exchangeMapping = new ExchangeMappingImpl();
    }

    @ParameterizedTest
    @CsvSource({
            "Banana/Piece, 1, Banana/kg, 0.8",
            "Banana/Piece, 3, CNY, 15.0",
            "Banana/Piece, 5, Apple, 7",
    })
    void testMapping(String ask, Double askAmount, String bid, Double bidAmount) {
        ExchangeRate rate = new ExchangeRate(new Exchange(ask, askAmount), new Exchange(bid, bidAmount));
        exchangeMapping.put(rate);
        List<ExchangeRate> exchangeList = exchangeMapping.find(ask, bid);
        assertEquals(1, exchangeList.size());
        assertEquals(rate, exchangeList.get(0));
    }

    @Test
    void testMappingCross() {
        String[] mappings = {"Banana/Piece, 1, Banana/kg, 0.8", "Banana/kg, 3, CNY, 15.0", "Banana/Piece, 5, Apple/Piece, 7"};
        Arrays.stream(mappings).forEach(s -> {
            String[] segs = s.split("," + SEPARATOR);
            exchangeMapping.put(new ExchangeRate(new Exchange(segs[0], Double.parseDouble(segs[1])),
                    new Exchange(segs[2], Double.parseDouble(segs[3]))));
        });

        List<ExchangeRate> exchangeList = exchangeMapping.find("Apple/Piece", "CNY");
        assertEquals(1, exchangeList.size());
        assertEquals(new ExchangeRate(new Exchange("Apple/Piece", 7.0),
                        new Exchange("CNY", 20.0)),
                exchangeList.get(0));
    }

    @Test
    void testMappingCrossMore() {
        String[] mappings = {"CNY, 6.7291, USD, 1.0", "JPY, 1.0, CNY, 0.06107", "USD, 0.009076, JPY, 1.0"};
        Arrays.stream(mappings).forEach(s -> {
            String[] segs = s.split("," + SEPARATOR);
            exchangeMapping.put(new ExchangeRate(new Exchange(segs[0], Double.parseDouble(segs[1])),
                    new Exchange(segs[2], Double.parseDouble(segs[3]))));
        });

        List<ExchangeRate> exchangeList = exchangeMapping.find("JPY", "USD");
        assertEquals(2, exchangeList.size());
    }
}

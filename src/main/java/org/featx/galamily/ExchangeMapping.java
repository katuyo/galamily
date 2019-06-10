package org.featx.galamily;

import java.util.List;

public interface ExchangeMapping {

    void put(ExchangeRate exchangeRate);

    List<ExchangeRate> find(String ask, String bid);
}

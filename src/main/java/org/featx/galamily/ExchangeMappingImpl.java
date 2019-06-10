package org.featx.galamily;

import java.util.*;
import java.util.stream.Collectors;

public class ExchangeMappingImpl implements ExchangeMapping {

    private Map<String, List<Exchange>> mapping;

    ExchangeMappingImpl() {
        this.mapping = new HashMap<>();
    }

    @Override
    public void put(ExchangeRate exchangeRate) {
        mapping.computeIfAbsent(exchangeRate.getAsk().getName(), k -> new ArrayList<>())
                .add(exchangeRate.rate(true));

        mapping.computeIfAbsent(exchangeRate.getBid().getName(), k -> new ArrayList<>())
                .add(exchangeRate.rate(false));
    }

    @Override
    public List<ExchangeRate> find(String ask, String bid) {
        Finder finder = new Finder(mapping, ask, bid);
        final HashSet<String> pathNodes = new HashSet<>();
        pathNodes.add(ask);
        finder.find(ask, 1.0, pathNodes);
        return finder.getResult().stream().distinct().collect(Collectors.toList());
    }
}

class Finder {
    private List<ExchangeRate> result;

    private Map<String, List<Exchange>> mapping;
    private String ask;
    private String bid;

    Finder(Map<String, List<Exchange>> mapping, String ask, String bid) {
        result = new ArrayList<>();
        this.mapping = mapping;
        this.ask = ask;
        this.bid = bid;
    }

    List<ExchangeRate> getResult() {
        return result;
    }

    void find(final String name, final Double amount, final Set<String> pathNodes) {
        Optional.ofNullable(mapping.get(name)).ifPresent(l -> l.stream().filter(exchange -> {
            boolean never = !pathNodes.contains(exchange.getName());
            pathNodes.add(exchange.getName());
            return never;
        }).forEach(exchange -> {
            if (exchange.getName().equals(bid)) {
                result.add(new ExchangeRate(exchange, new Exchange(ask, amount)));
            } else {
                find(exchange.getName(), amount / exchange.getAmount(), new HashSet<>(pathNodes));
            }
        }));
    }
}

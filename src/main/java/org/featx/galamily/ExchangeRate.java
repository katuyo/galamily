package org.featx.galamily;

import java.util.Objects;

/**
 * The exchange rate between 2 exchange.
 *
 * @author excepts
 * @since 2019-03-26
 */
public class ExchangeRate {

    private Exchange ask;

    private Exchange bid;

    ExchangeRate(Exchange ask, Exchange bid) {
        if (ask == null || !ask.exchangeble() ||
                bid == null || !bid.exchangeble()) {
            throw new RuntimeException("Condition of exchange not satisfied");
        }
        this.ask = ask;
        this.bid = bid;
    }

    Exchange getAsk() {
        return ask;
    }

    Exchange getBid() {
        return bid;
    }

    Exchange rate(boolean baseAsk) {
        return baseAsk ? new Exchange(bid.getName(), bid.getAmount() / ask.getAmount()) :
                new Exchange(ask.getName(), ask.getAmount() / bid.getAmount());
    }

    Exchange exchange(Exchange exchange) {
        if (Objects.equals(exchange.getName(), ask.getName())) {
            Double bidAmount = (exchange.getAmount() * bid.getAmount()) / ask.getAmount();
            return new Exchange(bid.getName(), bidAmount);
        } else if (Objects.equals(exchange.getName(), bid.getName())) {
            Double askAmount = (exchange.getAmount() * ask.getAmount()) / bid.getAmount();
            return new Exchange(ask.getName(), askAmount);
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExchangeRate)) return false;
        ExchangeRate that = (ExchangeRate) o;
        if (ask.getName().equals(that.ask.getName()) &&
                bid.getName().equals(that.bid.getName()) &&
                ask.getAmount() / bid.getAmount() == that.ask.getAmount() / that.bid.getAmount()) {
            return true;
        }
        return (ask.getName().equals(that.bid.getName()) &&
                bid.getName().equals(that.ask.getName()) &&
                ask.getAmount() / bid.getAmount() == that.bid.getAmount() / that.ask.getAmount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(ask, bid);
    }
}

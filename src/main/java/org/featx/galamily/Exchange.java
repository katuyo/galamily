package org.featx.galamily;

import java.util.Objects;

import static org.featx.galamily.Constants.DEFAULT_DOUBLE;
import static org.featx.galamily.Constants.EMPTY_STR;

/**
 * The name and amount of something to be exchanged
 *
 * @author excepts
 * @since 2019-03-26
 */
public class Exchange {

    private String name;

    private Double amount;

    Exchange(String name, Double amount) {
        this.name = name;
        this.amount = amount;
    }

    boolean exchangeble() {
        return name != null && !EMPTY_STR.equals(name.trim())
                && amount != null && amount > DEFAULT_DOUBLE;
    }

    String getName() {
        return name;
    }

    Double getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Exchange)) return false;
        Exchange exchange = (Exchange) o;
        return name.equals(exchange.name) &&
                amount.equals(exchange.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, amount);
    }
}

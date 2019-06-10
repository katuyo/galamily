package org.featx.galamily;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.featx.galamily.Constants.*;

/**
 * @author excepts
 * @since 2019-03-27
 */
public class GalamilyApplication implements GalamilyApplicate {

    private RomanDigitMapping romanDigitMapping;

    private ExchangeMapping exchangeMapping;

    GalamilyApplication() {
        this.romanDigitMapping = new StringRomanDigitMapping();
        this.exchangeMapping = new ExchangeMappingImpl();
    }

    public List<String> run(List<String> lines) {
        return lines.stream().map(line -> {
            if (line == null || EMPTY_STR.equals(line.trim())) {
                return NO_COMMAND;
            }
            String[] strings = line.split(SEPARATOR + EQUAL_SYMBOL + SEPARATOR);
            if ((strings[0] == null || EMPTY_STR.equals(strings[0].trim())) &&
                    (strings[1] == null || EMPTY_STR.equals(strings[1].trim()))) {
                return NO_COMMAND;
            } else if (strings.length < 2 ||
                    (strings[0] == null || EMPTY_STR.equals(strings[0].trim())) ||
                    (strings[1] == null || EMPTY_STR.equals(strings[1].trim()))) {
                return CANNOT_DEAL;
            } else if (strings[0].matches(ROMAN_NUM_REGEX)) {//Case of words number setting
                romanDigitMapping.put(strings[1], strings[0]);
                return EMPTY_STR;
            } else if (strings[1].matches(ROMAN_NUM_REGEX)) {//Case of words number setting
                romanDigitMapping.put(strings[0], strings[1]);
                return EMPTY_STR;
            } else if (!strings[1].endsWith(FQA_SYMBOL)) {//Case of exchange rate setting
                ExchangeRate exchangeRate = recognize(strings[0], strings[1]);
                exchangeMapping.put(exchangeRate);
                return EMPTY_STR;
            } else if (strings[0].contains(NUMERIC_QUESTION_SYMBOL)) {
                String string = strings[1].replaceAll("\\" + FQA_SYMBOL, EMPTY_STR);
                return MessageFormat.format("{0}{1} {2}", string, EQUAL_SYMBOL,
                        romanDigitMapping.valueOf(string.split(SEPARATOR)));
            } else if (line.contains(EXCHANGE_QUESTION_SYMBOL)) {
                return exchangeResult(strings[0], strings[1].substring(0, strings[1].length() - 1));
            }
            return CANNOT_DEAL;
        }).collect(Collectors.toList());
    }

    private Exchange extractExchange(String amountThing) {
        String[] splits = amountThing.split(SEPARATOR);
        String name = splits[splits.length - 1];

        String[] numbers = Arrays.copyOf(splits, splits.length - 1);
        if (numbers.length > 1) {
            return new Exchange(name, romanDigitMapping.valueOf(numbers));
        }
        Double amount;
        try {
            amount = Double.parseDouble(numbers[0]);
        } catch (Exception ignore) {
            amount = romanDigitMapping.valueOf(numbers);
        }
        return new Exchange(name, amount);
    }

    private ExchangeRate recognize(String ask, String bid) {
        Exchange exchange0 = extractExchange(ask);
        Exchange exchange1 = extractExchange(bid);
        return new ExchangeRate(exchange0, exchange1);
    }

    private String exchangeResult(final String ask, final String bid) {
        Exchange excAsk = extractExchange(ask);
        Exchange excBid = extractExchange(bid);
        List<ExchangeRate> exchangeRateList = exchangeMapping.find(excAsk.getName(), excBid.getName());
        if (exchangeRateList.size() > 1) {
            return MessageFormat.format(EXCHANGE_RATES_MORE, excAsk.getName(), excBid.getName());
        } else if (exchangeRateList.size() == 1) {
            Double exchangeAmount = doExchange(exchangeRateList.get(0), excAsk, excBid);
            double test = exchangeAmount - (double) exchangeAmount.intValue();
            if (Pattern.matches("0\\.9{10,}\\d+", Double.toString(test))) {
                exchangeAmount = Math.ceil(exchangeAmount);
            }
            return MessageFormat.format("{0} {1} {2}",
                    ask.replace(EXCHANGE_QUESTION_SYMBOL, exchangeAmount.toString()), EQUAL_SYMBOL,
                    bid.replace(EXCHANGE_QUESTION_SYMBOL, exchangeAmount.toString()));
        } else {
            return CANNOT_DEAL;
        }
    }

    private Double doExchange(ExchangeRate exchangeRate, Exchange ask, Exchange bid) {
        Exchange exchanged = null;
        if (ask.exchangeble()) {
            exchanged = exchangeRate.exchange(ask);
        } else if (bid.exchangeble()) {
            exchanged = exchangeRate.exchange(bid);
        }
        return Optional.ofNullable(exchanged).map(Exchange::getAmount).orElse(DEFAULT_DOUBLE);
    }
}

package org.featx.galamily;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

import static org.featx.galamily.Constants.MAX_VALUE;

/**
 * @author excepts
 * @since 2019-03-26
 */
public class RomanNumeralFormatter implements RomanNumeralFormat {

    private RomanNumeralValidate romanNumeralValidate;

    RomanNumeralFormatter() {
        this.romanNumeralValidate = new RomanNumeralValidator();
    }

    void setRomanNumeralValidate(RomanNumeralValidate romanNumeralValidate) {
        this.romanNumeralValidate = romanNumeralValidate;
    }

    @Override
    public Integer parse(String romanSymbolSequence) throws RomanNumeralValidateException {
        romanNumeralValidate.repeatable(romanSymbolSequence);
        romanNumeralValidate.subtractive(romanSymbolSequence);

        final AtomicInteger sum = new AtomicInteger(0);
        final int[] chars = new int[4];
        final AtomicInteger idx = new AtomicInteger(0);
        final AtomicInteger power = new AtomicInteger(3);

        final IntConsumer nextDigit = c -> {
            int digit = checkDigit(chars, idx.get());
            for (int i = 0; i < power.get(); i++) {
                digit *= 10;
            }
            sum.addAndGet(digit);
            for (int i = 0; i < 4; i++) {
                chars[i] = 0;
            }
            idx.set(0);
            power.set(checkPower(c));
        };

        romanSymbolSequence.chars().forEach(c -> {
            if (power.get() == 3 && c != 'M') {
                nextDigit.accept(c);
            } else if (power.get() == 2 && c != 'M' && c != 'D' && c != 'C') {
                nextDigit.accept(c);
            } else if (power.get() == 1 && c != 'C' && c != 'L' && c != 'X') {
                nextDigit.accept(c);
            }
            chars[idx.getAndIncrement()] = c;
        });
        nextDigit.accept(0);
        return sum.get();
    }

    private int checkPower(int cha) {
        if (cha == 'C' || cha == 'D') {
            return 2;
        } else if (cha == 'X' || cha == 'L') {
            return 1;
        } else if (cha == 'I' || cha == 'V') {
            return 0;
        }
        return 0;
    }

    /**
     * 1: 1, 5
     * 2: 2, 4, 6, 9
     * 3: 3, 7
     * 4: 8
     *
     * @param chars The roman symbol sequence in char array
     * @param n     How many chars appended in the chars
     * @return int The digit
     */
    private int checkDigit(int[] chars, int n) {
        switch (n) {
            case 4:
                return 8;
            case 1:
                return RomanDigit.isFive(String.valueOf((char) chars[0])) ? 5 : 1;
            case 3:
                return chars[0] == chars[1] ? 3 : 7;
            case 2:
                int result = RomanDigit.isFive(String.valueOf((char) chars[0])) ? 6 : 9;
                result = RomanDigit.isFive(String.valueOf((char) chars[1])) ? 4 : result;
                return chars[0] == chars[1] ? 2 : result;
            default:
                return 0;
        }
    }

    /**
     * 900:  CM     90   XC      9   IX
     * 800:  DCCC        LXXX        VIII
     * 700   DCC         LXX         VII
     * 600   DC          LX          VI
     * 500   D           L           V
     * 400   CD          XL          IV
     * 300   CCC         XXX         III
     * 200   CC          XX          II
     * 100   C           X           I
     */
    @Override
    public String format(Integer value) throws RomanNumeralValidateException {
        if (MAX_VALUE < value) {
            throw new RomanNumeralValidateException(RomanNumeralValidateError.MAX_SUPPORT);
        }
        Integer v = value;
        StringBuilder stringBuilder = new StringBuilder();
        int t = v / 1000;
        stringBuilder.append(String.valueOf(RomanDigit.M).repeat(Math.max(0, t)));
        v = v - t * 1000;

        int h = v / 100;
        append(stringBuilder, h, 2);
        v = v - h * 100;

        int d = v / 10;
        append(stringBuilder, d, 1);
        v = v - d * 10;
        append(stringBuilder, v, 0);
        return stringBuilder.toString();
    }

    private void append(StringBuilder stringBuilder, int v, int pow) {
        int i = pow == 2 ? 4 : 0;
        i = pow == 1 ? 2 : i;
        RomanDigit[] romanDigits = RomanDigit.values();
        RomanDigit bitSingle = romanDigits[i];
        RomanDigit bitFive = romanDigits[i + 1];
        RomanDigit bitTen = romanDigits[i + 2];
        switch (v) {
            case 9:
                stringBuilder.append(bitSingle).append(bitTen);
                break;
            case 8:
            case 7:
            case 6:
            case 5:
                stringBuilder.append(bitFive);
                stringBuilder.append(String.valueOf(bitSingle).repeat(Math.max(0, v - 5)));
                break;
            case 4:
                stringBuilder.append(bitSingle).append(bitFive);
                break;
            case 3:
            case 2:
            case 1:
                stringBuilder.append(String.valueOf(bitSingle).repeat(Math.max(0, v)));
                break;
            case 0:
            default:
        }
    }
}

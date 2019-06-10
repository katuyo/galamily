package org.featx.galamily;

/**
 * Roman digit enums
 *
 * @author excepts
 * @since 2019-03-26
 */
public enum RomanDigit {
    I(1),
    V(5),
    X(10),
    L(50),
    C(100),
    D(500),
    M(1000),

    T(1000),// TODO As "--" upper on the symbol to express 1000
    ;

    private Integer value;

    RomanDigit(Integer value) {
        this.value = value;
    }

    public static boolean isFive(String value) {
        return V.toString().equals(value) ||
                L.toString().equals(value) ||
                D.toString().equals(value);
    }

    public Integer getValue() {
        return value;
    }
}

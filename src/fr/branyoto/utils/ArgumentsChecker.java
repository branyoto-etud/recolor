package fr.branyoto.utils;

import static java.util.Objects.requireNonNull;

public final class ArgumentsChecker {
    public static final int MIN_PORT = 1024;
    public static final int MAX_PORT = 65535;

    /**
     * Compare two objects.
     *
     * @param value the value to compare
     * @param comparator the comparing element
     * @param <T> the object's type
     * @return  a negative integer, zero, or a positive integer as comparator
     *          is less than, equal to, or greater than value.
     */
    private static <T> int compareTwo(T value, Comparable<? super T> comparator) {
        requireNonNull(value);
        requireNonNull(comparator);
        return comparator.compareTo(value);
    }

    // ----------------
    // -- COMPARISON --
    // ----------------

    /**
     * Checks if value is below comparator
     *
     * @param value the value to compare
     * @param comparator the comparing element
     * @param <T> the object's type
     * @return true if value is less than or equal to comparator
     */
    public static <T> boolean isBelow(T value, Comparable<? super T> comparator) {
        return compareTwo(value, comparator) >= 0;
    }

    /**
     * Checks if value is strictly below comparator
     *
     * @param value the value to compare
     * @param comparator the comparing element
     * @param <T> the object's type
     * @return true if value is less than comparator
     */
    public static <T> boolean isStrictlyBelow(T value, Comparable<? super T> comparator) {
        return compareTwo(value, comparator) > 0;
    }

    /**
     * Checks if value is above comparator
     *
     * @param value the value to compare
     * @param comparator the comparing element
     * @param <T> the object's type
     * @return true if value is greater than or equal to comparator
     */
    public static <T> boolean isAbove(T value, Comparable<? super T> comparator) {
        return compareTwo(value, comparator) <= 0;
    }

    /**
     * Checks if value is strictly below comparator
     *
     * @param value the value to compare
     * @param comparator the comparing element
     * @param <T> the object's type
     * @return true if value is greater than comparator
     */
    public static <T> boolean isStrictlyAbove(T value, Comparable<? super T> comparator) {
        return compareTwo(value, comparator) < 0;
    }

    /**
     * Checks if value is between min and max
     *
     * @param value the value to compare
     * @param min the minimum bound
     * @param max the maximum bound
     * @param <T> the object's type
     * @return true if value is between min and max (or equal to one of them)
     */
    public static <T> boolean isBetween(T value, Comparable<? super T> min, Comparable<? super T> max) {
        requireNonNull(min);
        return isBelow(value, max) && isAbove(value, min);
    }

    /**
     * Checks if value is strictly between min and max
     *
     * @param value the value to compare
     * @param min the minimum bound
     * @param max the maximum bound
     * @param <T> the object's type
     * @return true if value is between min and max
     */
    public static <T> boolean isStrictlyBetween(T value, Comparable<? super T> min, Comparable<? super T> max) {
        requireNonNull(min);
        return isStrictlyBelow(value, max) && isStrictlyAbove(value, min);
    }

    // -----------------
    // -- REQUIREMENT --
    // -----------------

    /**
     * Checks if value is below comparator
     *
     * @param value the value to compare
     * @param comparator the comparing element
     * @param <T> the object's type
     * @throws IllegalArgumentException if {@code value} is not below {@code comparator}
     */
    public static <T> T requireBelow(T value, Comparable<? super T> comparator) {
        if (isStrictlyAbove(value, comparator)) {
            throw new IllegalArgumentException(value + " needs to be below " + comparator);
        }
        return value;
    }

    /**
     * Checks if value is strictly below comparator
     *
     * @param value the value to compare
     * @param comparator the comparing element
     * @param <T> the object's type
     * @throws IllegalArgumentException if {@code value} is not strictly below {@code comparator}
     */
    public static <T> T requireStrictlyBelow(T value, Comparable<? super T> comparator) {
        if (isAbove(value, comparator)) {
            throw new IllegalArgumentException(value + " needs to be below " + comparator);
        }
        return value;
    }

    /**
     * Checks if value is above comparator
     *
     * @param value the value to compare
     * @param comparator the comparing element
     * @param <T> the object's type
     * @throws IllegalArgumentException if {@code value} is not above {@code comparator}
     */
    public static <T> T requireAbove(T value, Comparable<? super T> comparator) {
        if (isStrictlyBelow(value, comparator)) {
            throw new IllegalArgumentException(value + " needs to be above " + comparator);
        }
        return value;
    }

    /**
     * Checks if value is strictly above comparator
     *
     * @param value the value to compare
     * @param comparator the comparing element
     * @param <T> the object's type
     * @throws IllegalArgumentException if {@code value} is not strictly above {@code comparator}
     */
    public static <T> T requireStrictlyAbove(T value, Comparable<? super T> comparator) {
        if (isBelow(value, comparator)) {
            throw new IllegalArgumentException(value + " needs to be strictly above " + comparator);
        }
        return value;
    }

    /**
     * Checks if value is between min and max
     *
     * @param value the value to compare
     * @param min the minimum bound
     * @param max the maximum bound
     * @param <T> the object's type
     * @throws IllegalArgumentException if {@code value} is not between {@code min} and {@code max}
     */
    public static <T> T requireBetween(T value, Comparable<? super T> min, Comparable<? super T> max) {
        if (!isBetween(value, min, max)) {
            throw new IllegalArgumentException(value + " needs to be between " + min + " and " + max);
        }
        return value;
    }

    /**
     * Checks if value is strictly between min and max
     *
     * @param value the value to compare
     * @param min the minimum bound
     * @param max the maximum bound
     * @param <T> the object's type
     * @throws IllegalArgumentException if {@code value} is not strictly between {@code min} and {@code max}
     */
    public static <T> T requireStrictlyBetween(T value, Comparable<? super T> min, Comparable<? super T> max) {
        if (!isStrictlyBetween(value, min, max)) {
            throw new IllegalArgumentException(value + " needs to be strictly between " + min + " and " + max);
        }
        return value;
    }

    /**
     * Check if the value is between {@code SocketUtils.PORT_RANGE_MIN}
     *      and {@code SocketUtils.PORT_RANGE_MAX}.
     * @param value the value to check.
     * @return {@code value} if correct.
     * @throws IllegalArgumentException if {@code value} is not in bounds.
     */
    public static int requirementOnPort(int value) {
        return requireBetween(value, MIN_PORT, MAX_PORT);
    }

    public static void requirementOnRGB(int r, int g, int b, int a) {
        requireBetween(r, 0, 255);
        requireBetween(g, 0, 255);
        requireBetween(b, 0, 255);
        requireBetween(a, 0, 255);
    }
    public static void requirementOnRGB(int r, int g, int b) {
        requireBetween(r, 0, 255);
        requireBetween(g, 0, 255);
        requireBetween(b, 0, 255);
    }
    public static void requirementOnHSV(double h, double s, double v, double a) {
        requireAbove(h, 0.);
        requireStrictlyBelow(h, 360.);
        requireBetween(s, 0., 1.);
        requireBetween(v, 0., 1.);
        requireBetween(a, 0., 1.);
    }
    public static void requirementOnHSV(double h, double s, double v) {
        requireAbove(h, 0.);
        requireStrictlyBelow(h, 360.);
        requireBetween(s, 0., 1.);
        requireBetween(v, 0., 1.);
    }
    public static void requirementOnHex(String hex) {
        requireNonNull(hex);
        if (!hex.matches("#[0-9A-Fa-f]{8}"))
            throw new IllegalArgumentException("Hex code does not respect the following format #RRGGBBAA. (" + hex + ")");
    }
}

package org.dnttr.zephyr.toolset.exceptions;

/**
 * @author dnttr
 */

public class InvalidLengthException extends Exception {

    public InvalidLengthException(int expected, int provided, boolean isMultipleOfExpected) {
        super(String.format(isMultipleOfExpected ? "Provided length is not a multiple of required number (%d %% %d)" : "Provided length does not match expected length (%d != %d)", provided, expected));
    }

    public InvalidLengthException(String message) {
        super(message);
    }

    public InvalidLengthException(int expected, int provided) {
        this(expected, provided, false);
    }
}

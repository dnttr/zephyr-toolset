package org.dnttr.zephyr.toolset.operators;

/**
 * @author dnttr
 */

public class BooleanOperator {

    public static byte getByteFromBoolean(boolean value) {
        return (byte) (value ? 1 : 0);
    }

    public static boolean getBooleanFromByte(byte value) {
        return value == 1;
    }

    public static byte[] getBytesFromBooleanArray(boolean... array) {
        byte[] buffer = new byte[array.length];

        for (int i = 0; i < array.length; i++) {
            buffer[i] = (byte) (array[i] ? 1 : 0);
        }

        return buffer;
    }

    public static boolean[] getBooleanArrayFromBytes(byte... array) {
        boolean[] buffer = new boolean[array.length];

        for (int i = 0; i < array.length; i++) {
            buffer[i] = (array[i] == 1);
        }

        return buffer;
    }
}
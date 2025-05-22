package org.dnttr.zephyr.toolset.operators;

import org.dnttr.zephyr.toolset.exceptions.InvalidLengthException;

import static org.dnttr.zephyr.toolset.operators.Operations.getValue64;
import static org.dnttr.zephyr.toolset.operators.Operations.shiftRightAndMask64;
import static org.dnttr.zephyr.toolset.types.Type.LONG;

/**
 * @author dnttr
 */

public class LongOperator {

    private static final int size = LONG.getBytes();

    public static byte[] getBytesFromLong(long value) {
        return shiftRightAndMask64(value, size);
    }

    public static long getLongFromBytes(byte[] array) throws InvalidLengthException {
        return getValue64(array);
    }

    public static byte[] getBytesFromLongArray(long[] array) {
        byte[] buffer = new byte[array.length * size];

        for (int i = 0; i < array.length; i++) {
            byte[] bytes = getBytesFromLong(array[i]);

            System.arraycopy(bytes, 0, buffer, i * size, bytes.length);
        }

        return buffer;
    }

    
    public static long[] getLongArrayFromBytes(byte[] array) throws InvalidLengthException {
        if (array.length % size != 0) {
            throw new InvalidLengthException(size, array.length, true);
        }

        int position = 0;
        int epsilon = array.length / size;
        long[] buffer  = new long[epsilon];

        for (int i = 0; i < epsilon; i++) {
            byte[] temporary = new byte[size];

            for (int j = 0; j < size; j++) {
                temporary[j] = array[position];
                position++;
            }

            buffer[i] = getLongFromBytes(temporary);
        }

        return buffer;
    }
}
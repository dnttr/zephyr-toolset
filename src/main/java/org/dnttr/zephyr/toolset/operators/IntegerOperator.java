package org.dnttr.zephyr.toolset.operators;

import org.dnttr.zephyr.toolset.exceptions.InvalidLengthException;

import static org.dnttr.zephyr.toolset.operators.Operations.shiftRightAndMask32;
import static org.dnttr.zephyr.toolset.types.Type.INT;

/**
 * @author dnttr
 */

public class IntegerOperator {

    private static final int size = INT.getBytes();

    public static int getIntegerFromBytes(byte[] array) throws InvalidLengthException {
        return Operations.getValue32(array);
    }

    public static byte[] getBytesFromInteger(int value) {
        return shiftRightAndMask32(value, size);
    }

    public static byte[] getBytesFromIntegerArray(int[] array) {
        byte[] buffer = new byte[array.length * size];

        for (int i = 0; i < array.length; i++) {
            byte[] bytes = getBytesFromInteger(array[i]);

            System.arraycopy(bytes, 0, buffer, i * size, bytes.length);
        }

        return buffer;
    }

    
    public static int[] getIntegerArrayFromBytes(byte[] array) throws InvalidLengthException {
        if (array.length % size != 0) {
            throw new InvalidLengthException(size, array.length, true);
        }

        int position = 0;
        int epsilon = array.length / size;
        int[] buffer  = new int[epsilon];

        for (int i = 0; i < epsilon; i++) {
            byte[] temporary = new byte[size];

            for (int j = 0; j < size; j++) {
                temporary[j] = array[position];
                position++;
            }

            buffer[i] = getIntegerFromBytes(temporary);
        }

        return buffer;
    }
}
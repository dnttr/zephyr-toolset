package org.dnttr.zephyr.toolset.operators;

import org.dnttr.zephyr.toolset.exceptions.InvalidLengthException;

import static org.dnttr.zephyr.toolset.operators.Operations.getValue16;
import static org.dnttr.zephyr.toolset.operators.Operations.shiftRightAndMask16;
import static org.dnttr.zephyr.toolset.types.Type.SHORT;

/**
 * @author dnttr
 */

public class ShortOperator {

    private static final int size = SHORT.getBytes();

    public static short getShortFromBytes(byte[] array) throws InvalidLengthException {
        return getValue16(array);
    }

    public static byte[] getBytesFromShort(short value) {
        return shiftRightAndMask16(value, size);
    }

    public static byte[] getBytesFromShortArray(short[] array) {
        byte[] buffer = new byte[array.length * size];

        for (int i = 0; i < array.length; i++) {
            byte[] bytes = getBytesFromShort(array[i]);

            System.arraycopy(bytes, 0, buffer, i * size, bytes.length);
        }

        return buffer;
    }

    
    public static short[] getShortArrayFromBytes(byte[] array) throws InvalidLengthException {
        if (array.length % size != 0) {
            throw new InvalidLengthException(size, array.length, true);
        }

        int position = 0;
        int epsilon = array.length / size;
        short[] buffer  = new short[epsilon];

        for (int i = 0; i < epsilon; i++) {
            byte[] temporary = new byte[size];

            for (int j = 0; j < size; j++) {
                temporary[j] = array[position];
                position++;
            }

            buffer[i] = getShortFromBytes(temporary);
        }

        return buffer;
    }
}
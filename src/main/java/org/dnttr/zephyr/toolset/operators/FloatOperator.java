package org.dnttr.zephyr.toolset.operators;

import org.dnttr.zephyr.toolset.exceptions.InvalidLengthException;

import static org.dnttr.zephyr.toolset.operators.Operations.shiftRightAndMask32;
import static org.dnttr.zephyr.toolset.types.Type.FLOAT;

/**
 * @author dnttr
 */

public class FloatOperator {

    private static final int size = FLOAT.getBytes();

    public static float getFloatFromBytes(byte[] array) throws InvalidLengthException {
        int value32 = Operations.getValue32(array);

        return Float.intBitsToFloat(value32);
    }

    public static byte[] getBytesFromFloat(float value) {
        int intBits = Float.floatToIntBits(value);

        return shiftRightAndMask32(intBits, size);
    }

    public static byte[] getBytesFromFloatArray(float[] array) {
        byte[] buffer = new byte[array.length * size];

        for (int i = 0; i < array.length; i++) {
            byte[] bytes = getBytesFromFloat(array[i]);

            System.arraycopy(bytes, 0, buffer, i * size, bytes.length);
        }

        return buffer;
    }

    
    public static float[] getFloatArrayFromBytes(byte[] array) throws InvalidLengthException {
        if (array.length % size != 0) {
            throw new InvalidLengthException(size, array.length, true);
        }

        int position = 0;
        int epsilon = array.length / size;
        float[] buffer  = new float[epsilon];

        for (int i = 0; i < epsilon; i++) {
            byte[] temporary = new byte[size];

            for (int j = 0; j < size; j++) {
                temporary[j] = array[position];
                position++;
            }

            buffer[i] = getFloatFromBytes(temporary);
        }

        return buffer;
    }
}
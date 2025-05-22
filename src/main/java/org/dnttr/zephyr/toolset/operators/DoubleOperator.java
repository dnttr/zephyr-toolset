package org.dnttr.zephyr.toolset.operators;

import org.dnttr.zephyr.toolset.exceptions.InvalidLengthException;

import static org.dnttr.zephyr.toolset.operators.Operations.shiftRightAndMask64;
import static org.dnttr.zephyr.toolset.types.Type.DOUBLE;

/**
 * @author dnttr
 */

public class DoubleOperator {

    private static final int size = DOUBLE.getBytes();

    public static byte[] getBytesFromDouble(double value) {
        long longBits = Double.doubleToLongBits(value);

        return shiftRightAndMask64(longBits, size);
    }

    public static double getDoubleFromBytes(byte[] array) throws InvalidLengthException {
        long value64 = Operations.getValue64(array);

        return Double.longBitsToDouble(value64);
    }

    public static byte[] getBytesFromDoubleArray(double[] array) {
        byte[] buffer = new byte[array.length * size];

        for (int i = 0; i < array.length; i++) {
            byte[] bytes = getBytesFromDouble(array[i]);

            System.arraycopy(bytes, 0, buffer, i * size, bytes.length);
        }

        return buffer;
    }

    
    public static double[] getDoubleArrayFromBytes(byte[] array) throws InvalidLengthException {
        if (array.length % size != 0) {
            throw new InvalidLengthException(size, array.length, true);
        }

        int position = 0;
        int epsilon = array.length / size;
        double[] buffer  = new double[epsilon];

        for (int i = 0; i < epsilon; i++) {
            byte[] temporary = new byte[size];

            for (int j = 0; j < size; j++) {
                temporary[j] = array[position];
                position++;
            }

            buffer[i] = getDoubleFromBytes(temporary);
        }

        return buffer;
    }
}
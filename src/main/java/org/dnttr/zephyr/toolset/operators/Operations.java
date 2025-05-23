package org.dnttr.zephyr.toolset.operators;

import org.dnttr.zephyr.toolset.exceptions.InvalidLengthException;

import static org.dnttr.zephyr.toolset.types.Type.*;

/**
 * @author dnttr
 */

public class Operations {

    static short getValue16(byte[] array) throws InvalidLengthException {
        int size = SHORT.getBytes();

        if (array.length % size != 0) {
            throw new InvalidLengthException(size, array.length, false);
        }

        short value = 0;

        for (byte b : array){
            value <<= 8;
            value |= (short) (b & 0xFF);
        }

        return value;
    }

    
    static int getValue32(byte[] array) throws InvalidLengthException {
        int size = INT.getBytes();

        if (array.length % size != 0) {
            throw new InvalidLengthException(size, array.length, false);
        }

        int intBits = 0;

        for (byte b : array){
            intBits <<= 8;
            intBits |= b & 0xFF;
        }

        return intBits;
    }

    
    static long getValue64(byte[] array) throws InvalidLengthException {
        int size = LONG.getBytes(); //64 bit

        if (array.length % size != 0) {
            throw new InvalidLengthException(size, array.length, false);
        }

        long value = 0;

        for (byte b : array){
            value <<= 8;
            value |= b & 0xFF;
        }

        return value;
    }

    static byte[] shiftRightAndMask16(short value, int length) {
        byte[] bytes = new byte[length];

        bytes[0] = (byte) ((value >> 8) & 0xFF);
        bytes[1] = (byte) (value & 0xFF);

        return bytes;
    }

    static byte[] shiftRightAndMask32(int value, int length) {
        byte[] bytes = new byte[length];

        bytes[0] = (byte) ((value >> 24) & 0xFF);
        bytes[1] = (byte) ((value >> 16) & 0xFF);
        bytes[2] = (byte) ((value >> 8) & 0xFF);
        bytes[3] = (byte) (value & 0xFF);

        return bytes;
    }

    static byte[] shiftRightAndMask64(long value, int length) {
        byte[] bytes = new byte[length];

        bytes[0] = (byte) ((value >> 56) & 0xFF);
        bytes[1] = (byte) ((value >> 48) & 0xFF);
        bytes[2] = (byte) ((value >> 40) & 0xFF);
        bytes[3] = (byte) ((value >> 32) & 0xFF);
        bytes[4] = (byte) ((value >> 24) & 0xFF);
        bytes[5] = (byte) ((value >> 16) & 0xFF);
        bytes[6] = (byte) ((value >> 8) & 0xFF);
        bytes[7] = (byte) (value & 0xFF);

        return bytes;
    }
}
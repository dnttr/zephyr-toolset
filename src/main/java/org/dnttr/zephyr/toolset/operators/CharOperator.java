package org.dnttr.zephyr.toolset.operators;

import org.dnttr.zephyr.toolset.exceptions.InvalidLengthException;

import static org.dnttr.zephyr.toolset.types.Type.CHAR;

/**
 * @author dnttr
 */

public class CharOperator {

    public static byte getByteFromChar(char c) {
        return (byte) c;
    }

    public static char getCharFromByte(byte b) {
        return (char) b;
    }

    static char[] getCharArrayFromBytes8(byte... array) {
        char[] buffer = new char[array.length];

        for (int i = 0; i < array.length; i++) {
            buffer[i] = (char) array[i];
        }

        return buffer;
    }

    static byte[] getBytesFromCharArray8(char... array) {
        byte[] buffer = new byte[array.length];

        for (int index = 0; index < array.length; index++) {
            byte b = getByteFromChar(array[index]);

            buffer[index] = b;
        }

        return buffer;
    }

    public static byte[] getBytesFromCharArray(char... array) {
        byte[] buffer = new byte[array.length * CHAR.getBytes()];

        for (int i = 0; i < array.length; i++) {
            char c = array[i];

            byte[] bytes = Internal.getBytesFromChar16(c);

            buffer[i * CHAR.getBytes()] = bytes[0];
            buffer[i * CHAR.getBytes() + 1] = bytes[1];
        }

        return buffer;
    }

    public static char[] getCharArrayFromBytes(byte... bytes) throws InvalidLengthException {
        if (bytes.length % CHAR.getBytes() != 0) {
            throw new InvalidLengthException(CHAR.getBytes(), bytes.length, true);
        }

        int epsilon = bytes.length / CHAR.getBytes();
        char[] buffer = new char[epsilon];

        for (int i = 0; i < epsilon; i++) {
            int position = i * CHAR.getBytes();

            byte high = bytes[position];
            byte low = bytes[position + 1];

            buffer[i] = Internal.getCharFromBytes16(high, low);
        }

        return buffer;
    }

    static class Internal {

        static byte[] getBytesFromChar16(char c) {
            byte[] buffer = new byte[2];
            buffer[0] = (byte) ((c >> 8) & 0xFF);
            buffer[1] = (byte) (c & 0xFF);

            return buffer;
        }

        
        static char getCharFromBytes16(byte[] b) throws InvalidLengthException {
            if (b.length != CHAR.getBytes()) {
                throw new InvalidLengthException(CHAR.getBytes(), b.length);
            }

            return getCharFromBytes16(b[0], b[1]);
        }

        static char getCharFromBytes16(byte high, byte low) {
            return (char) ((high << 8 & 0xFF) | low & 0xFF);
        }
    }
}
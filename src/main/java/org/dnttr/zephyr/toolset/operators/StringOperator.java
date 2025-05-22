package org.dnttr.zephyr.toolset.operators;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.PooledByteBufAllocator;
import org.dnttr.zephyr.toolset.exceptions.InvalidLengthException;

import java.nio.charset.StandardCharsets;

import static org.dnttr.zephyr.toolset.types.Type.INT;

/**
 * <b><i>Defines the structure of a byte array that holds multiple strings:</b></i>
 * <p>
 * - <b>The first byte:</b> The number of strings (n) contained in the array (1 byte). <br>
 * - <b>The next bytes (1...4n):</b> Each string's length is stored as a 4-byte integer <br>
 * - <b>Remaining bytes:</b> The actual string data encoded in UTF-8 format, following
 *   the string lengths.
 * <p>
 * The length of each string is defined in bytes, accounting for UTF-8 <br>
 * encoding, where characters may use more than one byte.
 * <p>
 * <b><i>Example of buffer structure: </b></i><br>
 * [<b>1 byte:</b> Number of strings] [<b>1 + 4 bytes each:</b> Length of each string, ...] [<b>Remaining bytes:</b> UTF-8 string data, ...] <br>
 * @see org.dnttr.zephyr.toolset.types.Type
 * @implNote <br>It is important to note that the encoding may change in the future
 *
 * @author dnttr
 *
 * @version 1.0E
 */
public class StringOperator {

    public static byte[] getBytesFromStringArray(String... array) {
        ByteBuf buffer = PooledByteBufAllocator.DEFAULT.heapBuffer();

        buffer.writeInt(array.length);

        for (String s : array) {
            byte[] content = s.getBytes(StandardCharsets.UTF_8);

            buffer.writeInt(content.length);
            buffer.writeBytes(content);
        }

        byte[] output = ByteBufUtil.getBytes(buffer);
        buffer.release();

        return output;
    }

    public static String[] getStringArrayFromBytes(byte[] array) throws InvalidLengthException {
        ByteBuf buffer = PooledByteBufAllocator.DEFAULT.buffer();
        int intBytes = INT.getBytes();

        buffer.writeBytes(array);

        if (!buffer.isReadable(intBytes)) {
            throw new InvalidLengthException(intBytes, buffer.readableBytes());
        }

        int total = buffer.readInt();

        if (total < 0) {
            throw new InvalidLengthException("Provided length cannot be negative (" + total + ")");
        }

        String[] output = new String[total];

        for (int i = 0; i < total; i++) {
            if (!buffer.isReadable(intBytes)) {
                throw new InvalidLengthException(intBytes, buffer.readableBytes());
            }

            int length = buffer.readInt();

            if (length < 0) {
                throw new InvalidLengthException("Provided length cannot be negative (" + length + ")");
            }

            if (!buffer.isReadable(length)) {
                throw new InvalidLengthException(buffer.readableBytes(), length);
            }

            byte[] content = new byte[length];
            buffer.readBytes(content);

            output[i] = new String(content, StandardCharsets.UTF_8);
        }

        buffer.release();

        return output;
    }
}
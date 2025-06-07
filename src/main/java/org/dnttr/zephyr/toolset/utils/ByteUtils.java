package org.dnttr.zephyr.toolset.utils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import org.dnttr.zephyr.toolset.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author dnttr
 */

public class ByteUtils {

    @NotNull
    public static ByteBuf getBuffer(byte[] bytes) {
        ByteBuf buf = Unpooled.buffer();
        buf.writeBytes(bytes);
        return buf;
    }

    @Nullable
    public static Pair<@NotNull ByteBuf, @NotNull ByteBuf> slice(@NotNull ByteBuf buffer, int beginIndex, int endIndex) {
        Objects.requireNonNull(buffer);

        if (beginIndex > endIndex || endIndex - beginIndex == 0 || endIndex > buffer.readableBytes() || beginIndex < 0) {
            return null;
        }

        ByteBuf content = buffer.slice(beginIndex, endIndex - beginIndex).retain();
        ByteBuf begin = buffer.slice(0, beginIndex).retain();
        ByteBuf end = buffer.slice(endIndex, buffer.writerIndex() - endIndex).retain();

        CompositeByteBuf slices = Unpooled.compositeBuffer();

        slices.addComponent(true, begin);
        slices.addComponent(true, end);

        begin.release();
        end.release();

        return new Pair<>(content, slices);
    }

    @NotNull
    public static ByteBuf getBuffer(String string) {
        return getBuffer(string.getBytes(StandardCharsets.UTF_8));
    }

    @NotNull
    public static ByteBuf getBuffer(int size, byte[]... bytes) {
        ByteBuf buffer = Unpooled.buffer(size);

        Arrays.stream(bytes).forEachOrdered(buffer::writeBytes);
        return buffer;
    }
}

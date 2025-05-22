package org.dnttr.zephyr.toolset;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author dnttr
 */

public record Tuple<L, M, R>(@Nullable L left, @Nullable M middle, @Nullable R right) {

    @Override
    public @NotNull String toString() {
        return "Tuple{" +
                "left=" + left +
                ", middle=" + middle +
                ", right=" + right +
                '}';
    }
}

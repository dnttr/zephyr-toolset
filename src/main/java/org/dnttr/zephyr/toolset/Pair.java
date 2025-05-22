package org.dnttr.zephyr.toolset;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author dnttr
 */

public record Pair<K, V>(@NotNull K key, @Nullable V value) {

    @Override
    public @NotNull String toString() {
        return "Pair{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}

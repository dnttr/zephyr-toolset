package org.dnttr.zephyr.toolset.types;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import static org.dnttr.zephyr.toolset.types.Type.*;

/**
 * @author dnttr
 */

public class TypeMatch {

    private static final Map<Class<?>, Integer> TYPE_MODIFIERS = Map.of(
            byte.class, BYTE.getModifier(),
            int.class, INT.getModifier(),
            long.class, LONG.getModifier(),
            short.class, SHORT.getModifier(),
            float.class, FLOAT.getModifier(),
            double.class, DOUBLE.getModifier(),
            char.class, CHAR.getModifier(),
            boolean.class, BOOLEAN.getModifier(),
            String.class, STRING.getModifier()
    );

    public static int getModifier(Field field) {
        Class<?> parentType = field.getType();

        if (parentType.isArray()) {
            parentType = parentType.getComponentType();
        }

        return getModifier(parentType);
    }

    public static int getModifier(Class<?> type) {
        Integer modifier = TYPE_MODIFIERS.get(type);

        if (modifier != null) {
            return modifier;
        }

        throw new IllegalArgumentException(String.format("Unsupported type: %s", type));
    }

    public static Optional<Type> getType(Field field) {
        int modifier = getModifier(field);
        return getType(modifier);
    }

    public static Optional<Type> getType(int modifier) {
        return Arrays.stream(Type.values())
                .filter(type -> type.getModifier() == modifier)
                .findFirst();
    }
}

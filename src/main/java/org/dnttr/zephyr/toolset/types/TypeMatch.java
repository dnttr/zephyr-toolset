package org.dnttr.zephyr.toolset.types;

import java.lang.reflect.Field;
import java.util.Arrays;

import static org.dnttr.zephyr.toolset.types.Type.*;

/**
 * @author dnttr
 */

public class TypeMatch {

    public static int getModifier(Field field) {
        final Class<?> parentType = field.getType();
        final Class<?> componentType = parentType.getComponentType();

        if (componentType == byte.class) {
            return BYTE.getModifier();
        } else if (componentType == boolean.class) {
            return BOOLEAN.getModifier();
        } else if (componentType == char.class) {
            return CHAR.getModifier();
        } else if (componentType == short.class) {
            return SHORT.getModifier();
        } else if (componentType == int.class) {
            return INT.getModifier();
        } else if (componentType == long.class) {
            return LONG.getModifier();
        } else if (componentType == float.class) {
            return FLOAT.getModifier();
        } else if (componentType == double.class) {
            return DOUBLE.getModifier();
        } else if (componentType == String.class) {
            return STRING.getModifier();
        } else {
            throw new IllegalArgumentException("Unsupported type: " + componentType);
        }
    }

    public static Type getType(Field field) {
        int modifier = getModifier(field);
        return getType(modifier);
    }

    public static Type getType(int modifier) {
        return Arrays.stream(Type.values()).filter(type -> type.getModifier() == modifier).findFirst().orElse(null);
    }
}

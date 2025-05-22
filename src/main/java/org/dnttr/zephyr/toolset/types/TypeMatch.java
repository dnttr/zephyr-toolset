package org.dnttr.zephyr.toolset.types;

import java.lang.reflect.Field;

import static org.dnttr.zephyr.toolset.types.Type.*;

/**
 * @author dnttr
 */

public class TypeMatch {

    public static int getModifiers(Field field) {
        final Class<?> parentType = field.getType();
        final Class<?> componentType = parentType.getComponentType();

        if (componentType == byte.class) {
            return BYTE.getModifiers();
        } else if (componentType == boolean.class) {
            return BOOLEAN.getModifiers();
        } else if (componentType == char.class) {
            return CHAR.getModifiers();
        } else if (componentType == short.class) {
            return SHORT.getModifiers();
        } else if (componentType == int.class) {
            return INT.getModifiers();
        } else if (componentType == long.class) {
            return LONG.getModifiers();
        } else if (componentType == float.class) {
            return FLOAT.getModifiers();
        } else if (componentType == double.class) {
            return DOUBLE.getModifiers();
        } else if (componentType == String.class) {
            return STRING.getModifiers();
        } else {
            throw new IllegalArgumentException("Unsupported type: " + componentType);
        }
    }
}

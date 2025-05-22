package org.dnttr.zephyr.toolset.types;

import java.lang.reflect.Field;

/**
 * @author dnttr
 */

public class TypeMatch {

    public static int getModifiers(Field field) {
        final Class<?> parentType = field.getType();
        final Class<?> componentType = parentType.getComponentType();

        if (componentType == byte.class) {
            return Type.BYTE.getModifiers();
        } else if (componentType == boolean.class) {
            return Type.BOOLEAN.getModifiers();
        } else if (componentType == char.class) {
            return Type.CHAR.getModifiers();
        } else if (componentType == short.class) {
            return Type.SHORT.getModifiers();
        } else if (componentType == int.class) {
            return Type.INT.getModifiers();
        } else if (componentType == long.class) {
            return Type.LONG.getModifiers();
        } else if (componentType == float.class) {
            return Type.FLOAT.getModifiers();
        } else if (componentType == double.class) {
            return Type.DOUBLE.getModifiers();
        } else if (componentType == String.class) {
            return Type.STRING.getModifiers();
        } else {
            throw new IllegalArgumentException("Unsupported type: " + componentType);
        }
    }
}

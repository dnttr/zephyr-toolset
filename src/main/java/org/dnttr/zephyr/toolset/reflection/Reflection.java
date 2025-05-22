package org.dnttr.zephyr.toolset.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.stream.Stream;

public class Reflection {

    public static Stream<Field> getFields(Class<?> klass, Class<? extends Annotation> annotation, boolean marked) {
        return Arrays.stream(klass.getDeclaredFields()).filter(field -> {
            if (marked) {
                return field.isAnnotationPresent(annotation);
            } else {
                return !field.isAnnotationPresent(annotation);
            }
        });
    }
    public static Constructor<?> getConstructor(Class<?> klass, int address) {
        Constructor<?>[] constructors = klass.getDeclaredConstructors();

        if (constructors.length < address) {
            throw new RuntimeException("Constructor doesn't exist.");
        }

        Constructor<?> constructor = constructors[address];
        constructor.setAccessible(true);

        return constructor;
    }

    public static Object newInstance(Constructor<?> constructor, Object[] parameters) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        if (constructor.getParameterCount() != parameters.length) {
            throw new MalformedParametersException();
        }

        return constructor.newInstance(parameters);
    }

    public static boolean isConcreteClass(Class<?> klass) {
        int modifier = klass.getModifiers();
        boolean flag = Modifier.isAbstract(modifier) || klass.isEnum() || klass.isInterface() || klass.isAnnotation();

        return !flag;
    }

    public static boolean isRegularField(Field field) {
        int modifier = field.getModifiers();
        boolean flag = Modifier.isNative(modifier) || Modifier.isTransient(modifier);

        return !flag;
    }
}
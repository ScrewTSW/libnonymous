package com.davenonymous.libnonymous.serialization;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldUtils {
    private static Map<Class<?>, List<Field>> lookupCache = new HashMap<>();

    public static List<Field> getAllDeclaredFields(Class<?> clz) {
        if(!lookupCache.containsKey(clz)) {
            List<Field> fields = FieldUtils.getAllDeclaredFields(new ArrayList<>(), clz);
            fields.sort(Comparator.comparing(o -> o.getClass().getName()));
            lookupCache.put(clz, fields);
        }

        return lookupCache.get(clz);
    }

    private static List<Field> getAllDeclaredFields(List<Field> fields, Class<?> type) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));

        if (type.getSuperclass() != null) {
            getAllDeclaredFields(fields, type.getSuperclass());
        }

        return fields;
    }



}

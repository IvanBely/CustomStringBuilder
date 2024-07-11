package org.example;

import java.lang.reflect.Array;

public class Main {

    public interface Filter {
        Object apply(Object o);
    }
    public static <T> T[] filter(T[] array, Filter filter, Class<T> tClass) {
        T[] result = (T[]) Array.newInstance(tClass, array.length);

        for (int i = 0; i < array.length; i++) {
            result[i] = (T) filter.apply(array[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        String[] s = {"Hi", "Hello", "Ho", "Ho Ho", ""};

        Filter HiFilter = new Filter() {
            @Override
            public Object apply(Object o) {
                if (o instanceof String) {
                    return (String) o + " Hi";
                }
                return o;
            }
        };

        String[] result = filter(s, HiFilter, String.class);

        for (String str : result) {
            System.out.println(str);
        }
    }
}
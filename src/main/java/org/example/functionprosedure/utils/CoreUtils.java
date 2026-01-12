package org.example.functionprosedure.utils;


import java.util.List;

public class CoreUtils {

    public static boolean isPresent(Object o) {
        return o != null;
    }

    public static boolean isPresent(List<?> o) {
        return o != null && !o.isEmpty();
    }

    public static boolean isPresent(String o) {
        return o != null && !o.isEmpty();
    }
}

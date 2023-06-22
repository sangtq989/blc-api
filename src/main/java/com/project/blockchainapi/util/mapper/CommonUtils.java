package com.project.blockchainapi.util.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CommonUtils {
    public static List<String> convertStringToList(String value) {
        if (Objects.isNull(value)) {
            return new ArrayList<>();
        }
        return Arrays.asList(value
                .trim()
                .substring(1, value.length() - 1)
                .split(", "));
    }

    public static <T> T nullOrDefault(T request, T db) {
        if (request == null && db == null) {
            return null;
        }

        return Objects.requireNonNullElse(request, db);
    }
}

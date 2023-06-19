package com.project.blockchainapi.util.mapper;

import java.util.Arrays;
import java.util.List;

public class CommonUtils {
    public static List<String> convertStringToList(String value) {
        return Arrays.asList(value
                .trim()
                .substring(1, value.length() - 1)
                .split(", "));
    }
}

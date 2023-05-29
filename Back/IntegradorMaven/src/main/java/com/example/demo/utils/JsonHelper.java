package com.example.demo.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public final class JsonHelper {
    private static final ObjectMapper mapper =
            JsonMapper.builder().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).build();

    private JsonHelper() {}

    public static <T> T readJsonFromFile(String fileName, TypeReference<T> clazz) {
        try {
            return mapper
                    .readValue(new ClassPathResource(fileName).getInputStream(), clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

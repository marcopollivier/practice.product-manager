package com.github.marcopollivier.avenuecode.productmanager.utils;

import com.google.gson.Gson;

public final class JsonUtils {

    /**
     * private constructor
     */
    private JsonUtils() {// prevent instantiation
    }

    public static String convertToJson(Object obj) {
        Gson body = new Gson();
        return body.toJson(obj);
    }

    public static <T> T convertFromJson(String json, Class<T> clazz){
        Gson body = new Gson();
        return body.fromJson(json, clazz);
    }

}

package com.zylear.internalcontrol.admin.util;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by xiezongyu on 2018/4/13.
 */
public class JsonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    private static Gson gson = new Gson();

    public static <T> List<T> parseJsonToList(String jsonString, Class<T> clazz) {
        List<T> list;
        try {
            list = gson.fromJson(jsonString, new TypeToken<List<T>>() {
            }.getType());
        } catch (JsonSyntaxException e) {
            logger.info("parseJsonToList error. string:{}", jsonString);
            throw new RuntimeException(e);
        }
        return list;
    }


}

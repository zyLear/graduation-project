package com.zylear.internalcontrol.admin.util;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.ArrayList;
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
            Type type = com.google.gson.internal.$Gson$Types.newParameterizedTypeWithOwner(null, ArrayList.class, clazz);
            list = gson.fromJson(jsonString, type);
        } catch (JsonSyntaxException e) {
            logger.info("parseJsonToList error. string:{}", jsonString);
            throw new RuntimeException(e);
        }
        return list;

//        List<T> list = new ArrayList<>();
//        JsonParser parser = new JsonParser();
//        JsonArray jsonarray = parser.parse(jsonString).getAsJsonArray();
//        for (JsonElement element : jsonarray) {
//            list.add(gson.fromJson(element, clazz));
//        }
//        return list;
    }

 /*   Type type = com.google.gson.internal.$Gson$Types.newParameterizedTypeWithOwner(null, ArrayList.class, clas);
    List<T> list = gson.fromJson(json, type);
    需要Gson 2.2.4以上的版本

    另外一种方法是：

    public static <T> List<T> jsonToBeanList(String json, Class<T> t) {
        List<T> list = new ArrayList<>();
        JsonParser parser = new JsonParser();
        JsonArray jsonarray = parser.parse(json).getAsJsonArray();
        for (JsonElement element : jsonarray
                ) {
            list.add(gson.fromJson(element, t));
        }
        return list;
    }*/


}

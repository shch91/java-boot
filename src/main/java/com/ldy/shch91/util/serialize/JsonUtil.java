package com.ldy.shch91.util.serialize;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JsonUtil {


    public static <T> JSON serializer(T t) {
        return (JSON) JSONObject.toJSON(t);
    }


    public static <T> T deserializer(JSON json, Class<T> c) {
        return JSONObject.parseObject(json.toJSONString(), c);
    }
}

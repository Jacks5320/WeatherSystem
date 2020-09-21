package com.iotat.weather.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class ResultJSON {

    public static String resultStatus(int code, String msg) {
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        return JSON.toJSONString(json, SerializerFeature.WriteMapNullValue, SerializerFeature.DisableCircularReferenceDetect);
    }

    public static String resultData(int status, String msg, Object data) {
        JSONObject json = new JSONObject();
        json.put("status", status);
        json.put("msg", msg);
        json.put("data", data);
        return JSON.toJSONString(json, SerializerFeature.WriteMapNullValue, SerializerFeature.DisableCircularReferenceDetect);
    }
}

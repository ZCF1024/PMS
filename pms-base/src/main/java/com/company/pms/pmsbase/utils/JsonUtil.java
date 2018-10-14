package com.company.pms.pmsbase.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @User: zcf
 * @Date: 2017/10/12
 * @Desc: fastjson 工具类
 */
public class JsonUtil {

    /**
     * 获取Json字符串中的某个key对应的值
     *
     * @param json json字符串
     * @param key json字符串中的某个key
     * @return
     */
    public static String getJsonValue(String json, String key){
        try{
            JSONObject jsonObject = JSON.parseObject(json);
            return jsonObject.getString(key);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 对象转Json
     *
     * @param object
     * @return 转化后的Json字符串
     */
    public static String objectToJson(Object object) {
        String string = null;
        try {
            string = JSON.toJSONString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }

    /**
     * Json字符串转Map
     *
     * @param json
     * @return 转化后的Map
     */
    public static Map<String, Object> jsonToMap(String json) {
        return JSON.parseObject(json, Map.class);
    }

    /**
     * json转对象
     *
     * @param text
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T jsonToBean(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }

    /**
     * json转对象的集合
     *
     * @param text
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonToList(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz);
    }

    private  JsonUtil(){}

}

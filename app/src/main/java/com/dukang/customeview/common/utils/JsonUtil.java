package com.dukang.customeview.common.utils;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @Description : json解析工具类
 * @Author : wdk
 * @CreatTime :2017/8/8 16:58
 * @LastModify(最终修改人) : wdk
 * @LastModifyTime(最终修改时间) : 2017/8/8 16:58
 * @LastCheckedBy : wdk
 */
public class JsonUtil {

    //fastjson 解析时异常处理
    private static final Feature[] features = {Feature.InitStringFieldAsEmpty, Feature.CustomMapDeserializer, Feature.UseObjectArray};

    /**
     * 将json转换为数组
     *
     * @param json   json
     * @param mClass 转换类型的类名
     * @param <T>    泛型 model
     * @return 结果
     */
    public static <T> List<T> fastJsonToArray(String json, Class<T> mClass) {
        if (!TextUtils.isEmpty(json) && json.startsWith("[")) {
            try {
                return JSON.parseArray(json, mClass);
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 将json转换为bean 对象
     *
     * @param json json
     * @param type 转换的类型 obj 还是list
     * @param <T>  泛型 model
     * @return 结果
     */
    public static <T> T fastJsonToBean(String json, Type type) {
        if (!TextUtils.isEmpty(json) && json.startsWith("{")) {
            try {
                return JSON.parseObject(json, type, features);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public static String toString(String json, String urg1) {
        try {
            JSONObject object = new JSONObject(json);
            return object.optString(urg1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 如果字符串是null 处理成""
     *
     * @param value 传递过来的字符串
     * @return 处理后的字符串
     */
    public static String dealNullStr(String value) {
        return value == null ? "" : value;
    }

    /**
     * 将返回的额json转换为JsonObject
     *
     * @param json json
     * @return JsonObject
     */
    public static JSONObject getRootJsonObject(String json) {

        if (TextUtils.isEmpty(json)) {
            return null;
        }

        try {
            JSONObject jsonObject = new JSONObject(json);
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将返回的额json转换为JsonObject
     *
     * @param json json
     * @return JsonObject
     */
    public static JSONArray getRootJsonArray(String json) {

        if (TextUtils.isEmpty(json)) {
            return new JSONArray();
        }

        try {
            JSONArray jsonArray = new JSONArray(json);
            return jsonArray;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new JSONArray();
    }

    /**
     * 使用安卓自带的json解析工具得到jsonObject
     * 1、处理解析到为空的情况
     * 2、处理对象没有值的情况{}
     * 3、将以上结果处理为null，有值返回jsonObject
     */
    public static JSONObject getOwnJsonObject(JSONObject jsonObject, String key) {
        if (jsonObject == null) {
            return null;
        }

        if (jsonObject.has(key)) {
            Object obj = jsonObject.opt(key);
            if (obj instanceof JSONObject) {//如果是JsonObject 类型
                JSONObject jsonObj = (JSONObject) obj;
                if (jsonObj.length() > 0) {//jsonObj有值
                    return jsonObj;
                }
            }
        }
        return null;
    }

    /**
     * 使用安卓自带的json解析工具得到jsonArray
     * 1、处理解析到为空的情况
     * 2、有值返回jsonArray
     */
    public static JSONArray getOwnJsonArray(JSONObject jsonObject, String key) {
        if (jsonObject == null) {
            return null;
        }

        if (jsonObject.has(key)) {
            Object obj = jsonObject.opt(key);
            if (obj != null) {
                if (obj instanceof JSONArray) {
                    JSONArray jsonArray = (JSONArray) obj;
                    return jsonArray;
                }
            }
        }
        return null;
    }


    /**
     * 获得字符串的 json字段
     *
     * @param jsonObject
     * @param key
     * @return
     */
    public static String getJsonStrValue(JSONObject jsonObject, String key) {
        if (jsonObject == null) {
            return "";
        }

        if (jsonObject.isNull(key)) {
            return "";
        } else {
            return jsonObject.optString(key, "");
        }
    }

    /**
     * 获得int类型的字段值
     *
     * @param jsonObject
     * @param key
     * @return
     */
    public static long getJsonLongValue(JSONObject jsonObject, String key) {
        if (jsonObject == null) {
            return -1;
        }

        if (jsonObject.isNull(key))
            return -1;
        else
            return jsonObject.optLong(key, -1);
    }

    /**
     * 获得int类型的字段值
     *
     * @param jsonObject
     * @param key
     * @return
     */
    public static int getJsonIntValue(JSONObject jsonObject, String key) {
        if (jsonObject == null) {
            return -1;
        }

        if (jsonObject.isNull(key))
            return -1;
        else
            return jsonObject.optInt(key, 0);
    }

    /**
     * 获得int类型的字段值
     *
     * @param json
     * @param key
     * @return
     */
    public static String getStringValue(JSONObject json, String key) {
        return getJsonStrValue(json, key);
    }

    /**
     * 获得int类型的字段值
     *
     * @param json
     * @param key
     * @return
     */
    public static int getIntValue(JSONObject json, String key) {
        return getJsonIntValue(json, key);
    }

    /**
     * 获得int类型的字段值
     *
     * @param json
     * @param key
     * @return
     */
    public static long getLongValue(JSONObject json, String key) {
        if (!json.has(key))
            return -1;
        else
            try {
                return json.getLong(key);
            } catch (Exception e) {
                e.printStackTrace();
            }
        return -1;
    }

    /**
     *
     * ━━━━━━神兽出没━━━━━━
     *      ┏┓　 ┏┓
     * 　　┏┛┻━━━┛┻┓
     * 　　┃　　　     ┃
     * 　　┃　　　━    ┃
     * 　　┃　┳┛　┗┳   ┃
     * 　　┃　　　     ┃
     * 　　┃　　　┻    ┃
     * 　　┃　　　　   ┃
     * 　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting
     * 　　  ┃　　　┃    神兽保佑,代码无bug
     *       ┃　　　┃
     *       ┃　　　┗━━━┓
     *       ┃　　　　　 ┣┓
     *       ┃　　　　　┏┛
     *       ┗┓┓┏━┳┓┏┛
     *        ┃┫┫　┃┫┫
     *        ┗┻┛　┗┻┛
     *
     * ━━━━━━感觉萌萌哒━━━━━━
     */

/**
 * 　　　　　　　　┏┓　　　┏┓
 * 　　　　　　　┏┛┻━━━┛┻┓
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━　　　┃
 * 　　　　　　　┃　＞　　　＜　┃
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃...　⌒　...　┃
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃　Code is far away from bug with the animal protecting
 * 　　　　　　　　　┃　　　┃   神兽保佑,代码无bug
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┗━━━┓
 * 　　　　　　　　　┃　　　　　　　┣┓
 * 　　　　　　　　　┃　　　　　　　┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛
 */

/**
 *　　　　　　　　┏┓　　　┏┓+ +
 *　　　　　　　┏┛┻━━━┛┻┓ + +
 *　　　　　　　┃　　　　　　　┃
 *　　　　　　　┃　　　━　　　┃ ++ + + +
 *　　　　　　 ████━████ ┃+
 *　　　　　　　┃　　　　　　　┃ +
 *　　　　　　　┃　　　┻　　　┃
 *　　　　　　　┃　　　　　　　┃ + +
 *　　　　　　　┗━┓　　　┏━┛
 *　　　　　　　　　┃　　　┃
 *　　　　　　　　　┃　　　┃ + + + +
 *　　　　　　　　　┃　　　┃　　　　Code is far away from bug with the animal protecting
 *　　　　　　　　　┃　　　┃ + 　　　　神兽保佑,代码无bug
 *　　　　　　　　　┃　　　┃
 *　　　　　　　　　┃　　　┃　　+
 *　　　　　　　　　┃　 　　┗━━━┓ + +
 *　　　　　　　　　┃ 　　　　　　　┣┓
 *　　　　　　　　　┃ 　　　　　　　┏┛
 *　　　　　　　　　┗┓┓┏━┳┓┏┛ + + + +
 *　　　　　　　　　　┃┫┫　┃┫┫
 *　　　　　　　　　　┗┻┛　┗┻┛+ + + +
 */
}

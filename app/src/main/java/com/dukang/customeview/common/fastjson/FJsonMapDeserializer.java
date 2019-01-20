package com.dukang.customeview.common.fastjson;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.MapDeserializer;

import java.lang.reflect.Type;

/**
 * @Description:
 * @Author: wdk 引用商家帮王旭的功能，忽略解析异常的字段继续往下解析
 * @CreatTime: 2018/10/17 15:54
 * @LastModify: wdk
 * @LastModifyTime: 2018/10/17 15:54
 * @LastCheckedBy: wdk
 */
public class FJsonMapDeserializer extends MapDeserializer {
    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        try {
            return super.deserialze(parser, type, fieldName);

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }
}

package com.example.oauth2server.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  客户响应信息 ，接口之间的响应协议
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponeData {

    /**
     * 状态码 0 表示成功
     */

    private Integer code;
    /**
     * 数据
     */
    private Object data;
    /**
     * 描述
     */
    private String msg;


    public <T> T getData(TypeReference<T> typeReference){
        return JSON.parseObject(JSON.toJSONString(data),typeReference);
    }

    /**
     * 成功，不传入数据
     * @return
     */
    public static ResponeData buildSuccess() {
        return new ResponeData(0, null, null);
    }

    /**
     * 成功，不传入数据
     * @return
     */
    public static ResponeData buildSuccess(String msg) {
        return new ResponeData(0, null, msg);
    }

    /**
     *  成功，传入数据
     * @param data
     * @return
     */
    public static ResponeData buildSuccess(Object data) {
        return new ResponeData(0, data, null);
    }

    /**
     * 失败，传入描述信息
     * @param msg
     * @return
     */
    public static ResponeData buildError(String msg) {
        return new ResponeData(-1, null, msg);
    }



    /**
     * 自定义状态码和错误信息
     * @param code
     * @param msg
     * @return
     */
    public static ResponeData buildCodeAndMsg(int code, String msg) {
        return new ResponeData(code, null, msg);
    }

    public String toString(){
        return JSONObject.toJSONString(this);
    }
}
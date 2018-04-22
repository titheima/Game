package com.anoyi.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一响应结果
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBean {

    private int code;

    private String message;

    private Object data;

    /**
     * 正常返回
     */
    public static ResponseBean success(Object object){
        return new ResponseBean(0, "success", object);
    }

    /**
     * 错误返回
     */
    public static ResponseBean error(Exception exception){
        return new ResponseBean(-1, "error", exception.getMessage());
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

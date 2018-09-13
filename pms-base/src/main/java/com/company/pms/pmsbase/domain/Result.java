package com.company.pms.pmsbase.domain;

/**
 * Description: API 最外层对象
 * User: zcf
 * Time: 2018/8/1  8:39
 *
 * @version V1.0
 */
public class Result<T> {

    /** 错误代码*/
    private Integer code;

    /** 错误信息*/
    private String msg;

    /** 数据*/
    private T data;

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }
}

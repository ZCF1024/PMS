package com.company.pms.pmsbase.utils;

/**
 * Description: Result API 返回数据定义
 * User: zcf
 * Time: 2018/8/1  8:39
 *
 * @version V1.0
 */
public enum ResultEnum {

    UNKONW_ERROR(-1, "未知错误"),
    LOAD_FILE_ERROR(-2, "读取文件出错"),
    FILE_TITLE_ERROR(-3, "文件格式不符合要求"),
    SUCCESS(0, "成功"),
    NO_AUTH_ERROR(1, "没有权限访问"),
    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

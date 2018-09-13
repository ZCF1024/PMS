package com.company.pms.pmsbase.utils;

import com.company.pms.pmsbase.domain.Result;

/**
 * Description: Result API返回数据格式
 * User: zcf
 * Time: 2018/8/1  8:39
 *
 * @version V1.0
 */
public class ResultUtil {

    public static Result success(Object object) {
        Result result = new Result(ResultEnum.SUCCESS.getCode(),
                ResultEnum.SUCCESS.getMsg(),
                object);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(Integer code, String msg) {
        return new Result(code, msg, null);
    }

    private ResultUtil(){ }
}

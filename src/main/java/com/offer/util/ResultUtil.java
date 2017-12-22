package com.offer.util;


/**
 * Created by lizhen on 2017/5/19.
 */
public class ResultUtil {
    public static Result success(Object object) {
        Result result = new ResultSupport();
        result.setCode("200");
        result.setMsg("成功");
        result.setModel(object);
        return result;
    }

    public static Result error(String code, String msg) {
        Result result = new ResultSupport();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /**
     * 字段校验是否为空
     *
     * @param msg
     * @return
     */
    public static Result errorField(String msg) {
        Result result = new ResultSupport();
        result.setCode("1400");
        result.setMsg(msg);
        return result;
    }
}

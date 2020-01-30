package com.zjxu97.costume.commons;

import com.baomidou.mybatisplus.extension.api.R;

/**
 * date    2019-08-23
 * time    12:33
 *
 * @author thisxzj - 中建
 */


public class Ans {

    /**
     * 执行成功
     */
    private static final String SUCCESS_MSG = "执行成功";

    /**
     * 执行失败
     */
    private static final String FAILURE_MSG = "执行失败:";

    /**
     * 执行成功
     */
    private static final Long SUCCESS_CODE = 200L;

    /**
     * 执行失败
     */
    private static final Long FAILURE_CODE = 500L;


    public static <T> R<T> success(T data) {
        R<T> r = new R<>();
        r.setCode(SUCCESS_CODE);
        r.setData(data);
        r.setMsg(SUCCESS_MSG);
        return r;
    }

    public static <T> R<T> failure(Exception e) {
        R<T> r = new R<>();
        r.setCode(FAILURE_CODE);
        r.setData(null);
        r.setMsg(FAILURE_MSG + e.toString());
        return r;
    }
}

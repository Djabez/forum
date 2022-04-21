package com.danli.common.lang;

import lombok.Data;

import java.io.Serializable;

/**
 * Uniform JSON object passed to the front end
 *
 * @author Yicong Wang
 * @date  2022
 */
@Data
public class Result implements Serializable {

    // 200 indicates normal; non-200 indicates abnormal
    private int code;
    private String msg;
    private Object data;

    public static Result succ(Object data) {
        return succ(200, "Operation is successful", data);
    }

    public static Result succ(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static Result fail(String msg) {
        return fail(400, msg, null);
    }

    public static Result fail(String msg, Object data) {
        return fail(400, msg, data);
    }

    public static Result fail(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

}

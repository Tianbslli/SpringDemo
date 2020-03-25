package com.tianbsli.business.constant;

/**
 * @author Jorsh
 * @version 1.0
 * @date 2020/3/25 4:56 下午
 *
 *  时间格式枚举类
 *  1为返回日期
 *  2为返回时间
 *  3为返回日期和时间
 */
public enum TimeStyle {

    DATE(1,"日期"),
    TIME(2,"时间"),
    DATETIME(3,"日期和时间");

    private int code;
    private String msg;

    TimeStyle(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

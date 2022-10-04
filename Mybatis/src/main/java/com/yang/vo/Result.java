package com.yang.vo;



public class Result {

    public static final String OK = "200";
    public static final String ERROR = "500";
    public static final String OPERATION_SUCCEED = "操作成功";


    private final String code;
    private final String msg;
    private final Object data;

    public Result() {
        this(OPERATION_SUCCEED, null);
    }

    public Result(Object data) {
        this(OK, data);
    }

    public Result(String msg) {
        this(OK, msg);
    }

    public Result(String msg, Object data) {
      this(OK, msg, data);
    }

    public Result(String code, String msg) {
        this(code, msg, null);
    }


    public Result(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public Object getData() {
        return this.data;
    }
}

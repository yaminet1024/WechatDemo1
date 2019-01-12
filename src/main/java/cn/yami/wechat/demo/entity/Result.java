package cn.yami.wechat.demo.entity;

import cn.yami.wechat.demo.enums.CodeMessage;

public class Result<T> {
    private int code;
    private String msg;
    private T data;

    private Result(T data) {
        this.data = data;
    }

    private Result(CodeMessage message) {
        this.code = message.getResultCode();
        this.msg = message.getResultMessage();
    }

    public static <T> Result<T> success(T data){
        return new Result<T>(data);
    }

    public static <T> Result<T> error(CodeMessage message){
        return new Result<T>(message);
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" + "code:" + code + ",Message:" + msg + ",data:" + data.toString() + "}";
    }
}

package cn.yami.wechat.demo.enums;

/**
 * 利用枚举结果信息
 */
public enum  CodeMessage {
    SUCCESS(0,"成功"),
    SERVER_ERROR(500100,"服务端异常"),
    BIND_ERROR(500101,"参数校验异常:%s"),
    SESSION_ERROR(500102,"Session不存在或者已经失效"),
    PASSWORD_EMPTY(500103,"登陆密码不能为空"),
    MOBILE_EMPTY(500104,"手机号不能为空"),
    MOBILE_ERROR(500105,"手机号格式错误"),
    MOBILE_NOT_EXIST(500106,"手机号不存在"),
    PASSWORD_ERROR(500107,"密码错误"),
    REPEAT_SPIKE(500108,"重复秒杀"),
    SPIKE_OVER(500200,"秒杀结束");


    private int resultCode;
    private String resultMessage;

    CodeMessage(int resultCode, String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public int getResultCode() {
        return resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public CodeMessage fillArgs(Object... args){
        resultMessage = String.format(BIND_ERROR.resultMessage,args);
        return CodeMessage.BIND_ERROR;
    }
}

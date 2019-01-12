package cn.yami.wechat.demo.exception;

import cn.yami.wechat.demo.enums.CodeMessage;

public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private CodeMessage codeMessage;


    public GlobalException(CodeMessage codeMessage) {
        super();
        this.codeMessage = codeMessage;
    }

    public CodeMessage getCodeMessage() {
        return codeMessage;
    }
}

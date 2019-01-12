package cn.yami.wechat.demo.exception;

import cn.yami.wechat.demo.entity.Result;
import cn.yami.wechat.demo.enums.CodeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request,Exception e){
        e.printStackTrace();
        if (e instanceof GlobalException){
            GlobalException exception = (GlobalException) e;
            return Result.error(exception.getCodeMessage());
        }
        else if (e instanceof BindException){
            BindException exception = (BindException) e;
            List<ObjectError> errorList = exception.getAllErrors();
            return Result.error(CodeMessage.BIND_ERROR.fillArgs(errorList.get(0).getDefaultMessage()));
        }else {
            return Result.error(CodeMessage.SERVER_ERROR);
        }
    }
}
package com.yang.exception;


import com.yang.aspect.MethodArgumentException;
import com.yang.vo.Result;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ValidationException;

@ControllerAdvice
public class GlobalException {

    @ResponseBody
    @ExceptionHandler(MethodArgumentException.class)
    public Result customExceptionHandler(MethodArgumentException argumentException) {
        return new Result(Result.ERROR, argumentException.getMessage());
    }


    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result commonException(Exception e) {
        System.out.println(e.getClass().getName());
        return new Result(Result.ERROR, "内部异常");
    }

    @ResponseBody
    @ExceptionHandler(ValidationException.class)
    public Result validationExceptionHandler(ValidationException validationException) {
        return new Result(Result.ERROR, validationException.getMessage().split(":")[1].trim());
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException) {
        return new Result(Result.ERROR, methodArgumentNotValidException.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(BindException.class)
    public Result bindExceptionHandler(BindException  bindException) {
        return new Result(Result.ERROR, bindException.getMessage());
    }
}

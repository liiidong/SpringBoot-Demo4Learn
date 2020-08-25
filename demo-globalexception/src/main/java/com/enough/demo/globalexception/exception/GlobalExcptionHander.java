package com.enough.demo.globalexception.exception;

import com.enough.common.model.ReturnResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @program: demo
 * @description: 自定义全局异常处理器
 * @author: lidong
 * @create: 2020/01/22
 */
@ControllerAdvice
//@RestControllerAdvice
public class GlobalExcptionHander {

    /**
     * 异常处理器
     * <p>
     * 注解ResponseBody必须的，使用//@RestControllerAdvice无需ResponseBody
     * ExceptionHandler 的 value指向最基本异常Exception,因为自定义的异常一般继承RuntimeException，RuntimeException继承Exception
     * 当然也value可以赋值为自己的异常，或在方法里根据Exception.class进行分类逻辑处理
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ReturnResult<?> exceptionHander(Exception e) {
        return ReturnResult.failed(Object.class).msg(e.getMessage()).build();
    }

    @ResponseBody
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ReturnResult<?> handle(ValidationException exception) {
        if(exception instanceof ConstraintViolationException){
            ConstraintViolationException exs = (ConstraintViolationException) exception;
            Set <ConstraintViolation <?>> violations = exs.getConstraintViolations();
            List <String> msgs = new ArrayList <>();
            for (ConstraintViolation<?> item : violations) {
                //打印验证不通过的信息
                msgs.add(item.getMessage());
            }
            return ReturnResult.failed(String.class).msg(StringUtils.join(msgs,",\n")).build();
        }
        return ReturnResult.failed(Object.class).msg(exception.getMessage()).build();
    }
}

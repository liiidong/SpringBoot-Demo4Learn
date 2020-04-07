package com.enough.demo.globalexception.exception;

import com.enough.common.model.ReturnResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: demo
 * @description: 自定义全局异常处理器
 * @author: lidong
 * @create: 2020/01/22
 */
@ControllerAdvice
public class GlobalExcptionHander {

    /**
     * 异常处理器
     * <p>
     * 注解ResponseBody必须的
     * ResponseBody 的 value指向最基本异常Exception,因为自定义的异常一般继承RuntimeException，RuntimeException继承Exception
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
}

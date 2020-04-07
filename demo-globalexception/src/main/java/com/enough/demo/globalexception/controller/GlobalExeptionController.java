package com.enough.demo.globalexception.controller;

import com.enough.common.model.GlobalException;
import com.enough.common.model.ReturnResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: demo
 * @description:
 * @author: lidong
 * @create: 2020/01/22
 */
@RestController
@RequestMapping("/exception")
public class GlobalExeptionController {

    @GetMapping("/test")
    public ReturnResult<String> getExceptionTest(){
        throw new GlobalException("手动抛出异常！");
    }
    @GetMapping("/enough")
    public ReturnResult<String> getException() throws Exception {
        throw new Exception("Exception异常！");
    }
    @GetMapping("/runtime")
    public ReturnResult<String> geRruntimeException() {
        throw new RuntimeException("runtimeException异常！");
    }
}

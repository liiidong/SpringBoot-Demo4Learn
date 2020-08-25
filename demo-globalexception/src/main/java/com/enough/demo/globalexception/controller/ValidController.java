package com.enough.demo.globalexception.controller;

import com.enough.demo.globalexception.domain.UserInfo;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: SpringBoot-Demo4Learn
 * @description: 参数校验
 * @author: liiidong
 * @create: 2020/08/25
 */
@RestController
@RequestMapping("/valid")
@Validated
public class ValidController {

    List <UserInfo> userInfos = new ArrayList <>();

    @GetMapping("/getUser")
    public String getUserStr(@NotNull(message = "name 不能为空") String name, @Max(value = 99, message = "age 不能大于99岁") Integer age) {
        return "name: " + name + " ,age:" + age;
    }

    @PostMapping("/add")
    public List <UserInfo> addUserInfo(@RequestBody(required = false) @Validated @NotNull(message = "userInfo不能为null") UserInfo userInfo,
            BindingResult bindingResult) {
        validData(bindingResult);
        userInfos.add(userInfo);
        return userInfos;
    }

    private void validData(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(";")));
        }
    }
}

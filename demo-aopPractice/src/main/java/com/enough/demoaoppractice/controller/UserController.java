package com.enough.demoaoppractice.controller;

import com.enough.common.model.ReturnResult;
import com.enough.demoaoppractice.commontypes.UserDTO;
import com.enough.demoaoppractice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: SpringBoot-Demo4Learn
 * @description:
 * @author: liiidong
 * @create: 2020/04/07
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/query")
    public ReturnResult <List> getUsers() {
        return ReturnResult.success(List.class).data(userService.getUsers()).build();
    }

    @PostMapping("/add")
    public ReturnResult <?> addUser(@RequestBody UserDTO userDTO) {
        return ReturnResult.success(Boolean.class).data(userService.addUser(userDTO)).build();
    }

    @PostMapping("/delete")
    public ReturnResult <?> deleteUsers(@RequestParam("id") String id) {
        return ReturnResult.success(Boolean.class).data(userService.deleteUser(id)).build();
    }

    @GetMapping("/{id}")
    public ReturnResult <UserDTO> getUser(@PathVariable("id") String id) {
        return ReturnResult.success(UserDTO.class).data(userService.getUser(id)).build();
    }

}

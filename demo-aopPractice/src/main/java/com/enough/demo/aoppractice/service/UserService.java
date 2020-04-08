package com.enough.demo.aoppractice.service;

import com.enough.demo.aoppractice.commontypes.UserDTO;

import java.util.List;

/**
 * @program: SpringBoot-Demo4Learn
 * @description: 用户服务类
 * @author: liiidong
 * @create: 2020/04/07
 */
public interface UserService {

    boolean addUser(UserDTO userDTO);

    List <UserDTO> getUsers();

    boolean deleteUser(String id);

    UserDTO getUser(String id);
}

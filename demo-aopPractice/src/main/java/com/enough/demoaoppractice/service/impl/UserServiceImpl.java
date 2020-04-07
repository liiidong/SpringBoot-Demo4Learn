package com.enough.demoaoppractice.service.impl;

import com.enough.common.model.CommonBuilder;
import com.enough.demoaoppractice.commontypes.UserDTO;
import com.enough.demoaoppractice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: SpringBoot-Demo4Learn
 * @description:
 * @author: liiidong
 * @create: 2020/04/07
 */
@Slf4j
@Component
public class UserServiceImpl implements UserService {
    private static List<UserDTO> userDTOList;

    static {
        userDTOList = new ArrayList <>();
        userDTOList.add(CommonBuilder.of(UserDTO::new)
                .with(UserDTO::setName,"张三")
                .with(UserDTO::setId,"zhangsan")
                .with(UserDTO::setAge,22)
                .with(UserDTO::setAddress,"xi'an").build());
    }
    @Override
    public boolean addUser(UserDTO userDTO) {
        return userDTOList.add(userDTO);
    }

    @Override
    public List <UserDTO> getUsers() {
        return userDTOList;
    }

    @Override
    public boolean deleteUser(String id) {
        return userDTOList.removeIf(u -> StringUtils.equalsIgnoreCase(u.getId(), id));
    }

    @Override
    public UserDTO getUser(String id) {
        List<UserDTO> userDTOS = userDTOList.stream().filter(u -> StringUtils.equalsIgnoreCase(u.getId(), id)).collect(Collectors.toList());
        return CollectionUtils.isNotEmpty(userDTOS) ? userDTOS.get(0) : null;
    }
}

package com.enough.demo.aoppractice.commontypes;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: SpringBoot-Demo4Learn
 * @description: 用户实体类
 * @author: liiidong
 * @create: 2020/04/07
 */
@Getter
@Setter
public class UserDTO {

    private String id;
    private String name;
    private String address;
    private int age;
    private Sex sex;

    public enum Sex {
        FEMALE, MALE
    }
}

package com.enough.demo.designpatterns.decorator;

/**
 * @program: SpringBoot-Demo4Learn
 * @description: 具体构件 Car
 * @author: lidong
 * @create: 2020/05/21
 */
public class Car implements ICar {
    @Override
    public void getCar() {
        System.out.println("this is a car");
    }
}

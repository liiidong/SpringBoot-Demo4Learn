package com.enough.demo.designpatterns.decorator;

/**
 * @program: SpringBoot-Demo4Learn
 * @description: 轮子装饰器
 * @author: lidong
 * @create: 2020/05/21
 */
public class WheelDecoratorImpl extends AttachedPropertiesDecorator {
    public WheelDecoratorImpl(ICar iCar) {
        super(iCar);
    }

    @Override
    public void getCar() {
        super.getCar();
        addWheel();
    }

    private void addWheel() {
        System.out.print("hava 4 wheels!");
    }
}

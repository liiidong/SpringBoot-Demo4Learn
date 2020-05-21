package com.enough.demo.designpatterns.decorator;

/**
 * @program: SpringBoot-Demo4Learn
 * @description: Gas汽油装饰器
 * @author: lidong
 * @create: 2020/05/21
 */
public class GasDecoratorImpl extends AttachedPropertiesDecorator {
    public GasDecoratorImpl(ICar iCar) {
        super(iCar);
    }

    @Override
    public void getCar() {
        super.getCar();
        addWheel();
    }

    private void addWheel() {
        System.out.println("hava some gas");
    }
}

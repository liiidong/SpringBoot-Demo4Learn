package com.enough.demo.designpatterns.decorator;

/**
 * @program: SpringBoot-Demo4Learn
 * @description: GPS装饰器
 * @author: lidong
 * @create: 2020/05/21
 */
public class GPSDecoratorImpl extends AttachedPropertiesDecorator {
    public GPSDecoratorImpl(ICar iCar) {
        super(iCar);
    }

    @Override
    public void getCar() {
        super.getCar();
        addWheel();
    }

    private void addWheel() {
        System.out.print("hava a GPS!");
    }
}

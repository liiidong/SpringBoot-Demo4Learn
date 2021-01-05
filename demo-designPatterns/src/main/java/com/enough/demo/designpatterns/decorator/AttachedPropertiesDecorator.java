package com.enough.demo.designpatterns.decorator;

/**
 * @program: SpringBoot-Demo4Learn
 * @description: 装饰着角色构建器--Car附加属性装饰器
 * @author: lidong
 * @create: 2020/05/21
 */
public abstract class AttachedPropertiesDecorator implements ICar {

    private ICar iCar;

    public AttachedPropertiesDecorator(ICar iCar) {
        this.iCar = iCar;
    }

    @Override
    public void getCar() {
        iCar.getCar();
    }
}

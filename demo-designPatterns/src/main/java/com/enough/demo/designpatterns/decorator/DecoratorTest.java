package com.enough.demo.designpatterns.decorator;

/**
 * @program: SpringBoot-Demo4Learn
 * @description:
 * @author: lidong
 * @create: 2020/05/21
 */
public class DecoratorTest {

    @Test
    public void carDecoratorTest(){
        ICar car1 = new Car();
        ICar car2 = new WheelDecoratorImpl(car1);
        ICar car3 = new GasDecoratorImpl(car2);
        ICar car4 = new GPSDecoratorImpl(car3);
        car4.getCar();
    }
}

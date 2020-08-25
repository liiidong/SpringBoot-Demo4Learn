package com.enough.demo.designpatterns.singleton;

/**
 * @program: SpringBoot-Demo4Learn
 * @description:
 * @author: lidong
 * @create: 2020/05/20
 */
public class Singleton {

    private static Singleton instance;

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    private static Singleton instance2 = new Singleton();

    public static Singleton getInstance2() {
        return instance2;
    }

    public static Singleton getInstance3() {
        synchronized (Singleton.class) {
            if (instance == null) {
                instance = new Singleton();
            }
        }
        return instance;
    }

    // 对于volatile变量_instance，所有的写（write）都将先行发生于读（read）,可见性、原子性、有序性
    private volatile static Singleton instance4;

    /**
     * 双重校验
     * @return
     */
    public static Singleton getInstance4() {
        if (instance4 == null) {
            synchronized (Singleton.class) {
                if (instance4 == null) {
                    instance4 = new Singleton();
                }
            }
        }
        return instance4;
    }

}

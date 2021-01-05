package com.enough.demo.aoppractice.aop;

import com.enough.common.utils.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: SpringBoot-Demo4Learn
 * @description:
 * @author: lidong
 * @create: 2020/04/08
 */
@Component
@Aspect
@Slf4j
public class UserControllerAspect {
    /**
     * 定义切点，为UserController所有公有方法
     * execution(正则)
     * <p>
     * SpringBoot 需要使用前两种匹配方式
     * 1、public * com.enough.demoaoppractice.controller..*.*(..) √
     * 2、public * com.enough.demoaoppractice.controller..*(..))  √
     * 3、public * com.enough.demoaoppractice.controller.*(..) ×
     * 4、* ×
     * public 方法修饰符，可不写或*代替
     * (..)不限方法参数
     * </p>
     */
    @Pointcut(value = "execution(public * com.enough.demo.aoppractice.controller.*.*(..))")
    public void userControllerMethods() {
    }

    /**
     * 被拦截的方法，须显式的抛出异常，且不能做任何处理，
     * 这样AOP才能捕获到方法中的异常，进而进行回滚。
     *
     * @param point
     * @return
     */
    @Around("userControllerMethods()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        //方法名
        String methodStr = point.getSignature().getDeclaringTypeName().concat(".").concat(point.getSignature().getName());
        String argsStr = getArgsJsonStr(point);
        log.info(methodStr.concat(",入参：").concat(argsStr));
        long startDate = System.currentTimeMillis();
        Object result = point.proceed();
        long endDate = System.currentTimeMillis();
        log.info("请求耗时：".concat(String.valueOf(endDate - startDate).concat("ms")));
        log.info(methodStr.concat(",返回结果：").concat(result == null ? "无" : JSONUtils.toJSONString(result)));
        return result;
    }

    /**
     * 在返回后通知（@AfterReturning）和抛出异常后通知（@AfterThrowing）、Before、After
     * 的方法中不能使用ProceedingJoinPoint，
     * 使用JoinPoint
     *
     * @param point
     */
    @Before(value = "execution(* com.enough.demo.aoppractice.controller.UserController.getUsers())")
    public void afterAround(JoinPoint point) {
        System.out.println("*******获取全部用户信息********");
    }

    /**
     * 获取方法
     *
     * @param joinPoint ProceedingJoinPoint
     * @return 方法
     */
    private Method getMethod(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (method.getDeclaringClass().isInterface()) {
            try {
                method = joinPoint.getTarget().getClass().getDeclaredMethod(joinPoint.getSignature().getName(), method.getParameterTypes());
            } catch (SecurityException | NoSuchMethodException e) {
                log.error("" + e);
            }
        }
        return method;
    }

    /**
     * 获取参数
     * 字符串表述
     *
     * @param point
     * @return
     */
    private String getArgsJsonStr(ProceedingJoinPoint point) {
        if (point.getArgs() != null && point.getArgs().length > 0) {
            List <String> argList = new ArrayList <>();
            for (Object arg : point.getArgs()) {
                // 判断是基本类型或者String
                if (arg.getClass().isPrimitive() || arg instanceof String) {
                    argList.add(arg.toString());
                } else {
                    // JOSN序列化
                    argList.add(JSONUtils.toJSONString(arg));
                }
            }
            return CollectionUtils.isNotEmpty(argList) ? String.join(",", argList) : StringUtils.EMPTY;
        }
        return StringUtils.EMPTY;
    }
}

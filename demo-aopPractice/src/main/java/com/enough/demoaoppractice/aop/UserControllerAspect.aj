package com.enough.demoaoppractice.aop;

import com.enough.common.model.ReturnResult;
import com.enough.common.utils.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: SpringBoot-Demo4Learn
 * @description:
 * @author: liiidong
 * @create: 2020/04/07
 */
@Aspect
@Component
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
    @Pointcut(value = "execution(public * com.enough.demoaoppractice.controller..*.*(..))")
    public void userControllerMethods() {
    }

    @Around("userControllerMethods()")
    public Object doAround(ProceedingJoinPoint point) {
        ReturnResult <Object> returnResult = null;
        //方法名
        String methodStr = point.getSignature().getDeclaringTypeName().concat(".").concat(point.getSignature().getName());
        String argsStr = getArgsJsonStr(point);
        log.info(methodStr.concat("入参：").concat(argsStr));
        long startDate = System.currentTimeMillis();
        Object result = null;
        try {
            result = point.proceed();
            returnResult = ReturnResult.success(Object.class).data(result).build();
        } catch (Throwable throwable) {
            log.error("方法请求异常：".concat(throwable.getMessage()));
            returnResult = ReturnResult.failed(Object.class).msg(throwable.getMessage()).build();
        } finally {
            long endDate = System.currentTimeMillis();
            log.info("请求耗时：".concat(String.valueOf(endDate - startDate).concat("ms")));
            log.info("返回结果：".concat(result == null ? "无" : JSONUtils.toJSONString(result)));
        }
        return returnResult;
    }

    @Before(value = "execution(* com.enough.demoaoppractice.controller.UserController.getUsers())")
    public void afterAround(ProceedingJoinPoint point){
        System.out.println("***************");
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
package com.jianchen.guava.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author: jian.cai@qunar.com
 * @Date: 15/4/10 Time: 下午2:33
 */
public class MyInvocationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("do my job");
        return null;
    }
}

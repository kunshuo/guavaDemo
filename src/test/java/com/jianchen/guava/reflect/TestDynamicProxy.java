package com.jianchen.guava.reflect;

import com.google.common.reflect.Reflection;
import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * 利用Reflection.newProxy创建动态代理
 *
 * @author: jian.cai@qunar.com
 * @Date: 15/4/10 Time: 下午2:26
 */
public class TestDynamicProxy {
    @Test
    public void test() {
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler();
        //guava的动态代理实现
        Worker worker = Reflection.newProxy(Worker.class, myInvocationHandler);
        worker.doSomething();

        //jdk的动态代理实现
        Worker jdkworker = (Worker) Proxy.newProxyInstance(
                Worker.class.getClassLoader(),
                new Class<?>[]{Worker.class},
                myInvocationHandler);
        jdkworker.doSomething();
    }
}

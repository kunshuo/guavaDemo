package com.jianchen.guava.concurrent;

import com.google.common.util.concurrent.MoreExecutors;
import org.junit.Test;

import java.util.Date;
import java.util.concurrent.*;

/**
 * MoreExcutores是对java中Executor、
 * ExecutorService和ThreadFactory进行封装，和java中的Executors类似。
 *
 * @author: jian.cai@qunar.com
 * @Date: 15/4/12 Time: 下午10:01
 */
public class TestMoreExecutors {

    @Test
    public void test() {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(8);
        //可以理解为线程池1
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 1000, TimeUnit.MILLISECONDS, blockingQueue);

        ExecutorService ex = Executors.newSingleThreadExecutor();//可以理解为线程池2
        //封装ThreadPoolExecutor类以及添加线程池1关闭时候的钩子
        //getExitingExecutorService内部实际上将threadPoolExecutor中的线程设置为后台线程
        ExecutorService executorService = MoreExecutors.getExitingExecutorService(threadPoolExecutor, 100000,
                TimeUnit.MILLISECONDS);

        //给线程池2单独添加钩子
        MoreExecutors.addDelayedShutdownHook(ex, 1000, TimeUnit.MILLISECONDS);

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                System.out.println("hello 123");
                try {
                    Thread.sleep(10000);

                } catch (Exception e) {

                }
                System.out.println("123");
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                System.out.println("hello 456");
                try {
                    Thread.sleep(10000);

                } catch (Exception e) {

                }
                System.out.println("123");
            }
        });
        System.out.println(new Date());
        ex.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                int i = 0;
                while (i++ < 1000444455)
                    ;
                System.out.println("hahahhhaha!");
            }
        });
        ex.shutdownNow();//使用shutdownNow是用来测试当立刻shutdown时，仍然可以执行线程相应操作
        executorService.shutdownNow();
    }


    /**
     * 执行后输出内容如下;
     *
     pool-1-thread-1
     hello 123
     pool-1-thread-2
     hello 456
     Sun Apr 12 22:27:00 CST 2015
     pool-2-thread-1
     123
     123
     hahahhhaha!
     */
}

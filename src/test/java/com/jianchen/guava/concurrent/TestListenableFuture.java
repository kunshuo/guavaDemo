package com.jianchen.guava.concurrent;

import com.google.common.util.concurrent.*;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 可以监听Future执行完成后主动执行回调逻辑
 * <p/>
 * ListenableFuture顾名思义就是可以监听的Future，它是对java原生Future的扩展增强。我们知道Future表示一个异步计算任务，
 * 当任务完成时可以得到计算结果。如果我们希望一旦计算完成就拿到结果展示给用户或者做另外的计算，就必须使用另一个线程不断的查询计算状态。
 * 这样做，代码复杂，而且效率低下。使用ListenableFuture Guava帮我们检测Future是否完成了，如果完成就自动调用回调函数，
 * 这样可以减少并发程序的复杂度。
 *
 * @author: jian.cai@qunar.com
 * @Date: 15/4/9 Time: 下午4:35
 */
public class TestListenableFuture {
    @Test
    public void test() throws Exception {

        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

        final ListenableFuture<Integer> listenableFuture = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("call execute..");
                TimeUnit.SECONDS.sleep(1);
                return 7;
            }
        });

        //监听方式1---回调逻辑
        listenableFuture.addListener(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("=======监听器1-begin========");
                    System.out.println("得到的Future的结果为" + listenableFuture.get());
                    System.out.println("=======监听器1-end========");


                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }, executorService);


        //监听方式2---回调逻辑
        //因为第二种方法可以直接得到Future的返回值，或者处理错误情况。本质上第二种方法是通过调动第一种方法实现的，做了进一步的封装。
        Futures.addCallback(listenableFuture, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                System.out.println("=======监听器2-begin========");
                System.out.println("get listenable future's result with callback " + result);
                System.out.println("=======监听器2-begin========");

            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });

        //主线程等待几秒,等待后处理线程执行
        TimeUnit.SECONDS.sleep(5);
    }


    /**
     * 总结:
     * 1,MoreExecutors:Factory and utility methods for java.util.concurrent.Executor, ExecutorService, and ThreadFactory.
     * 2,可以两种方式来添加监听处理
     *
     */

}

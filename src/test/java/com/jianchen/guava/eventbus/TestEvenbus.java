package com.jianchen.guava.eventbus;

import com.google.common.eventbus.EventBus;
import com.jianchen.guava.listener.BornEvent;
import com.jianchen.guava.listener.BornListener;
import com.jianchen.guava.listener.SubBornEvent;
import org.junit.Test;

/**
 * User: jian.cai
 * Date: 14-1-20 上午11:43
 */
public class TestEvenbus {
    @Test
    public void testSingleEvent(){
        EventBus eventBus = new EventBus();
        BornEvent bornEvent  = new BornEvent("2014-01-20","China");
        BornListener bornListener = new BornListener();
        eventBus.register(bornListener);
        eventBus.post(bornEvent);
    }

    /**
     * 子类事件的发生也会触发处理器执行
     */
    @Test
    public void testSubEvent(){
        EventBus eventBus = new EventBus();
        BornEvent bornEvent  = new SubBornEvent("2014-01-20","China");
        BornListener bornListener = new BornListener();
        eventBus.register(bornListener);
        eventBus.post(bornEvent);
    }
}

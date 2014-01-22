package com.jianchen.guava.listener;

import com.google.common.eventbus.Subscribe;

/**
 * 出生时间
 * <p/>
 * User: jian.cai
 * Date: 14-1-20 下午5:14
 */
public class BornListener {

    @Subscribe
     public void registerToCountry(BornEvent event) {
        System.out.println("向公安局登记注册，一个婴儿出生了，出生日期为" +
                event.getBirthday() + ",出生地为" + event.getBornPlace());
    }
}

package com.jianchen.guava.listener;

/**
 * 出生时间
 * User: jian.cai
 * Date: 14-1-20 下午5:07
 */
public class BornEvent {
    private String birthday;
    private String bornPlace;

    public BornEvent(String birthday, String bornPlace) {
        this.birthday = birthday;
        this.bornPlace = bornPlace;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBornPlace() {
        return bornPlace;
    }

    public void setBornPlace(String bornPlace) {
        this.bornPlace = bornPlace;
    }
}

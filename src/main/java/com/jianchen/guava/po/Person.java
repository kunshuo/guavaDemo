package com.jianchen.guava.po;

import com.google.common.base.Objects;

/**
 * User: kunshuo
 * Date: 13-12-17 Time: ÏÂÎç3:48
 * company:jianchen.com
 */
public class Person {
    private  String name;
    private  Integer age;
    private  String sex;

    public Person(String name, Integer age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("name", name)
                .add("age", age)
                .add("sex", sex)
                .toString();
    }
}

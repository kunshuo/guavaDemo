package com.jianchen.guava.base;

import com.jianchen.guava.po.Person;
import junit.framework.Assert;
import org.junit.Test;

/**
 * User: kunshuo
 * Date: 13-12-17 Time: обнГ3:57
 * company:jianchen.com
 */
public class TestBase {
    @Test
    public  void testToString(){
        Person person = new Person("jianchen",25,"MALE");
        Assert.assertNotNull(person.toString());
        Assert.assertEquals("Person{name=jianchen, age=25, sex=MALE}",person.toString());
    }
}

package com.jianchen.guava.base;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;
import com.jianchen.guava.po.Person;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * User: kunshuo
 * Date: 13-12-17 Time: ????3:57
 * company:jianchen.com
 */
@RunWith(JUnit4.class)
public class TestBase {
    @Test
    public void testToString() {
        Person person = new Person("jianchen", 25, "MALE");
        Assert.assertNotNull(person.toString());
        Assert.assertEquals("Person{name=jianchen, age=25, sex=MALE}", person.toString());
    }

    @Test
    public void testOptional() {
        Optional<Integer> optional = Optional.of(5);
        Assert.assertNotNull(optional.get());
    }

    @Test
    public void testSplitter(){
        String str1 = "my,name:is;jianchen";
        System.out.println(Splitter.on(CharMatcher.anyOf(":,;")).split(str1));  //[my, name, is, jianchen]

    }
    @Test
    public void testJoiner(){
        // Bad! Do not do this!
        Joiner joiner = Joiner.on(',');
        joiner.skipNulls(); // does nothing!  added by jianchen:这么写就对了  joiner = joiner.skipNulls();
        System.out.println(joiner.join("wrong", null, "wrong"));
    }

}

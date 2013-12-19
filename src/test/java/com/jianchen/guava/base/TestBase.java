package com.jianchen.guava.base;

import com.google.common.base.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.jianchen.guava.po.Person;
import junit.framework.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;
import java.util.Map;

/**
 * User: kunshuo
 * Date: 13-12-17
 * company:jianchen.com
 */
@RunWith(JUnit4.class)
public class TestBase {
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

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
    public void testSplitter() {
        String str1 = "my,name:is;jianchen";
        System.out.println(Splitter.on(CharMatcher.anyOf(":,;")).split(str1));  //[my, name, is, jianchen]

    }

    @Test
    public void testJoiner() {
        // Bad! Do not do this!
        Joiner joiner = Joiner.on(',');
        joiner.skipNulls(); // does nothing!  added by jianchen:这么写就对了  joiner = joiner.skipNulls();
        System.out.println(joiner.join("wrong", null, "wrong"));


    }

    @Test
    public void testJoinForMap() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("mame", "jianchen");
        map.put("age", 25);
        map.put("sex", "Male");
        System.out.println(Joiner.on("&").withKeyValueSeparator("=").join(map));//sex=Male&mame=jianchen&age=25

        //可以进一步了解下Multimap的用法

    }

    @Test(expected = NullPointerException.class)
    public void testPreconditionsForNull() {
        Preconditions.checkNotNull(null);
        Preconditions.checkArgument(1 < 0);
    }

    @Test
    public void testPreconditionsForArgument() {
        expectedException.expect(IllegalArgumentException.class);//换一种写法
        Preconditions.checkArgument(1 < 0);
    }

    @Test
    public void testOrdering() {

        List<Integer> numbers = Lists.newArrayList(1, 14, 5, 9, 20);
        List<Integer> naturalNumbers = Ordering.natural().sortedCopy(numbers);
        System.out.println(naturalNumbers); //[1, 5, 9, 14, 20]
        System.out.println(Ordering.natural().reverse().sortedCopy(numbers)); //[20, 14, 9, 5, 1]
        Integer maxNumber = Ordering.natural().max(numbers);
        Integer minNumber = Ordering.natural().min(numbers);
        System.out.println(minNumber); //1
        System.out.println(maxNumber); //20

        List<Integer> list1 = Lists.newArrayList(1, 14, 5, 9, 20, null);
        List<Integer> result = Ordering.natural().nullsFirst().sortedCopy(list1);
        System.out.println(result);//[null, 1, 5, 9, 14, 20]
        System.out.println(Ordering.natural().nullsLast().sortedCopy(list1)); //[1, 5, 9, 14, 20, null]

        System.out.println(Ordering.natural().isOrdered(list1));//false
        System.out.println(Ordering.natural().binarySearch(result, 14));  //返回在数组中的索引位置
        System.out.println(Ordering.natural().leastOf(numbers, 3)); //[1, 5, 9]
        System.out.println(numbers); //[1, 14, 5, 9, 20] ---number集合没有变化
    }


}

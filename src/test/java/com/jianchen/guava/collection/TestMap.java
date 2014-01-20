package com.jianchen.guava.collection;

import com.google.common.base.Supplier;
import com.google.common.collect.*;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static org.fest.assertions.Assertions.assertThat;

/**
 * User: kunshuo
 * Date: 14-1-14 Time: 下午3:29
 * company:jianchen.com
 */
public class TestMap {

    @Test
    public void testMapDifference() {
        Map<String, String> map1 = ImmutableMap.of("name", "jianchen", "sex", "male", "age", "25");
        Map<String, String> map2 = ImmutableMap.of("name1", "jianchen", "sex", "male", "age", "25");
        MapDifference<String, String> mapDifference = Maps.difference(map1, map2);

        assertThat(mapDifference.areEqual()).isFalse();
        assertThat(mapDifference.entriesInCommon()).hasSize(2);
        assertThat(mapDifference.entriesOnlyOnLeft()).isEqualTo(ImmutableMap.of("name", "jianchen")); //返回key不同的entry

        Map<String, String> map3 = ImmutableMap.of("name", "jianchen", "sex", "male", "age", "25");
        Map<String, String> map4 = ImmutableMap.of("name", "jianchen2", "sex", "male", "age", "25");
        MapDifference<String, String> mapDifference2 = Maps.difference(map3, map4);
        assertThat(mapDifference2.entriesDiffering().containsKey("name")).isTrue();   //返回key相同，但是value不同的


    }

    @Test
    public void testSets() {
        HashSet setA = Sets.newHashSet(1, 2, 3, 4, 5);
        HashSet setB = Sets.newHashSet(4, 5, 6, 7, 8);

        Sets.SetView union = Sets.union(setA, setB);
        assertThat(union).hasSize(8);

        Sets.SetView difference = Sets.difference(setA, setB);
        assertThat(difference).hasSize(3);
        assertThat(difference).contains(1, 2, 3);

        Sets.SetView intersection = Sets.intersection(setA, setB);
        assertThat(intersection).hasSize(2);
        assertThat(intersection).contains(4, 5);

    }

    /**
     * supplier用于创建value的集合，可以控制该multiMap是否支持重复的key-value对。可以对比下testListMultiMap方法
     */
    @Test
    public void testSetMultiMap() {
        SetMultimap smm = Multimaps.newSetMultimap(new ConcurrentHashMap<String, Collection<String>>(), new Supplier<Set<String>>() {
            @Override
            public Set<String> get() {
                return new HashSet<String>();
            }
        });

        smm.put("name", "jianchen");
        smm.put("name", "jianchen");

        assertThat(smm.size() == 1);
        assertThat(smm.asMap().entrySet().toString()).isEqualTo("[name=[jianchen]]");

        smm.put("name", "jianchen1");
        smm.put("sex", "male");
        assertThat(smm.size() == 2);
        assertThat(smm.asMap().entrySet().toString()).isEqualTo("[name=[jianchen, jianchen1], sex=[male]]");
    }


    @Test
    public void testListMultiMap() {
        ListMultimap smm = Multimaps.newListMultimap(new ConcurrentHashMap<String, Collection<String>>(), new Supplier<List<String>>() {
            @Override
            public List<String> get() {
                return new ArrayList<String>();
            }
        });

        smm.put("name", "jianchen");
        smm.put("name", "jianchen");

        assertThat(smm.size() == 1);
        assertThat(smm.asMap().entrySet().toString()).isEqualTo("[name=[jianchen, jianchen]]");

        smm.put("name", "jianchen1");
        smm.put("sex", "male");
        assertThat(smm.size() == 2);
        assertThat(smm.asMap().entrySet().toString()).isEqualTo("[name=[jianchen, jianchen, jianchen1], sex=[male]]");
    }

    @Test
    public void testHashMultiMap() {
        Multimap<String, String> map1 = HashMultimap.create();
        map1.put("name", "jianchen");
        map1.put("name", "jianchen");
        assertThat(map1.size()).isEqualTo(1);

        Multimap<String, String> map2 = HashMultimap.create();
        map2.put("name", "jianchen");
        map2.put("name", "jianchen1");
        assertThat(map2.size()).isEqualTo(2);

        SetMultimap smm = Multimaps.newSetMultimap(new ConcurrentHashMap<String, Collection<String>>(), new Supplier<Set<String>>() {
            @Override
            public Set<String> get() {
                return new HashSet<String>();
            }
        });
        smm.putAll(map1);
        assertThat(smm.size()).isEqualTo(1);

        smm.putAll(map2);
        assertThat(smm.size()).isEqualTo(2); //name对应到两个value。所以size=2
    }
}

package com.jianchen.guava.collection;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;

/**
 * User: kunshuo
 * Date: 14-1-14 Time: 下午3:29
 * company:jianchen.com
 */
public class TestMap {

    @Test
    public void testMapDifference(){
        Map<String,String> map1 = ImmutableMap.of("name","jianchen","sex","male","age","25");
        Map<String,String> map2 = ImmutableMap.of("name1","jianchen","sex","male","age","25");
        MapDifference<String,String>  mapDifference = Maps.difference(map1,map2);

        assertThat(mapDifference.areEqual()).isFalse();
        assertThat(mapDifference.entriesInCommon()).hasSize(2);
        assertThat(mapDifference.entriesOnlyOnLeft()).isEqualTo(ImmutableMap.of("name","jianchen")); //返回key不同的entry

        Map<String,String> map3 = ImmutableMap.of("name","jianchen","sex","male","age","25");
        Map<String,String> map4 = ImmutableMap.of("name","jianchen2","sex","male","age","25");
        MapDifference<String,String>  mapDifference2 = Maps.difference(map3,map4);
        assertThat(mapDifference2.entriesDiffering().containsKey("name")).isTrue();   //返回key相同，但是value不同的


    }

    @Test
    public void testSets(){
        HashSet setA = Sets.newHashSet(1, 2, 3, 4, 5);
        HashSet setB = Sets.newHashSet(4, 5, 6, 7, 8);

        Sets.SetView union = Sets.union(setA,setB);
        assertThat(union).hasSize(8);

        Sets.SetView difference = Sets.difference(setA,setB);
        assertThat(difference).hasSize(3);
        assertThat(difference).contains(1,2,3);

        Sets.SetView intersection = Sets.intersection(setA,setB);
        assertThat(intersection).hasSize(2);
        assertThat(intersection).contains(4,5);

    }
}

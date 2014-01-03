package com.jianchen.guava.collection;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.Weigher;
import com.google.common.collect.MapMaker;
import com.sun.org.apache.xalan.internal.xsltc.dom.CachedNodeListIterator;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.fest.assertions.Assertions.assertThat;

/**
 * User: jian.cai
 * Date: 13-12-30 下午7:28
 */
public class TestCollection {
    @Test
    public void testMapMaker() throws Exception {
        CacheLoader<String, String> loader = new CacheLoader<String, String>() {
            public String load(String key) {
                return key.toUpperCase();
            }
        };
        LoadingCache<String, String> cache =
                CacheBuilder.newBuilder()
                        .build(loader);
        assertThat(cache.get("test")).isEqualTo("TEST");
        assertThat(cache.size() == 1);
    }

    @Test
    public void testWeakkeys() throws Exception {
        CacheLoader<String, String> loader = new CacheLoader<String, String>() {
            public String load(String key) {
                return key.toUpperCase();
            }
        };

        LoadingCache<String, String> cache = CacheBuilder.newBuilder().weakKeys().build(loader);

        String key = "test";
        assertThat(cache.get(key)).isEqualTo("TEST");
        assertThat(cache.size() == 1);
        key = null;
        System.gc();
        assertThat(cache.size() == 0);

    }

    @Test
    public void testWeigher() throws Exception {
        CacheLoader<String, String> loader = new CacheLoader<String, String>() {
            public String load(String key) {
                return key.toUpperCase();
            }
        };

        Weigher<String, String> weighByLength = new Weigher<String, String>() {
            public int weigh(
                    String key, String value) {
                return value.length();
            }
        };

        LoadingCache<String, String> cache =
                CacheBuilder.newBuilder()
                        .maximumWeight(15)
                        .weigher(weighByLength)
                        .build(loader);

        cache.put("test", "TEST");
        cache.put("test1", "TEST1");
        cache.put("test2", "TEST2");
        cache.put("test3", "TEST3");
        assertThat(cache.size()).isEqualTo(3);
        assertThat(cache.asMap().toString()).isEqualTo("{test3=TEST3, test2=TEST2, test1=TEST1}");
    }


}

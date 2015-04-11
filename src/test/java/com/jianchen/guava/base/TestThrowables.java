package com.jianchen.guava.base;

import com.google.common.base.Throwables;
import org.junit.Test;

import java.sql.SQLException;

/**
 * 测试Throwables工具类的使用
 *
 * @author: jian.cai@qunar.com
 * @Date: 15/4/11 Time: 下午8:05
 */
public class TestThrowables {

    /**
     * 在实际场景中,该方法可能抛出不同类型的异常或者错误
     *
     * @throws Throwable
     */
    public void doSomething() throws Throwable {
        //ignore method body
        throw new Exception("sql error");
    }

    /**
     * 在实际场景中,该方法可能抛出不同类型的异常或者错误
     *
     * @throws Throwable
     */
    public void doSomethingElse() throws Exception {
        //ignore method body
        throw new SQLException("sql error");

    }

    public void doIt() throws SQLException {
        try {
            doSomething();
        } catch (Throwable throwable) {
            //Propagates throwable exactly as-is, if and only if it is an instance of declaredType
            Throwables.propagateIfInstanceOf(throwable, SQLException.class);

            //Propagates throwable exactly as-is, if and only if it is an instance of RuntimeException or Error.
            Throwables.propagateIfPossible(throwable);
        }

        /**
         * 使用上面捕获异常的方式,是为了确保往外抛出的异常的可控性
         */
    }


    public void doItElse() throws SQLException {
        try {
            doSomethingElse();
        } catch (Throwable throwable) {
            //方法表示:如果是指定类型的异常的话,则抛出
            Throwables.propagateIfInstanceOf(throwable, SQLException.class);

            //方法表示:如果是RuntimeException or Error类型的异常则抛出
            Throwables.propagateIfPossible(throwable);
        }
    }

    @Test
    public void test1() throws Throwable {
        TestThrowables testThrowables = new TestThrowables();
        testThrowables.doIt();//默认抛出的Exception类型,所以try住后都不满足,所以异常被吞掉了,没有进一步往外扩散
    }


    @Test(expected = SQLException.class)
    public void test2() throws Throwable {
        TestThrowables testThrowables = new TestThrowables();
        testThrowables.doItElse(); //默认抛出的是SQLException,符合第一个,所以继续向外抛出
    }
}




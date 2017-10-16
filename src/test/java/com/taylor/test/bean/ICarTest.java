package com.taylor.test.bean;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/10/16 15:55
 */
public class ICarTest {
    Mockery context = new JUnit4Mockery();

    @Test
    public void testCar() {
        final ICar car = context.mock(ICar.class);
        IDriver driver = new Driver();//内部类
        context.checking(new Expectations() {{
            oneOf(car).run();
        }});
        driver.drive(car);
    }
}

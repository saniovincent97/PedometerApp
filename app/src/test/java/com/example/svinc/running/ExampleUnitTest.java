package com.example.svinc.running;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void testCalculateBMI() throws Exception{
        int result = 0;
        int weight = 65;
        int height = 165;
        assertEquals(result, weight / (height * height));
    }

    @Test
    public void testSwitchTheme() throws Exception{
        boolean DarkTheme = false;
        boolean LightTheme = true;
        assertEquals(LightTheme, DarkTheme);
    }

    @Test
    public void testNumOfSteps() throws Exception{
        int numSteps = 5;
        assertEquals(5, 3 + 2);
    }


}


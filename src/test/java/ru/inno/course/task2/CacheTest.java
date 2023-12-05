package ru.inno.course.task2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CacheTest{
   @Test
    public void testCache(){
        Integer result;
        Testable t = Utils.cache(new TestMethods());
        result = t.testCalculateSumm(8,5);
        assertTrue(result.equals(t.testCalculateSumm(8,10)));
        t.resetCalculate();
        result = t.testCalculateSumm(8,10);
        assertTrue(result.equals(t.testCalculateSumm(8,10)));
    }
    public static class TestMethods implements Testable {
        @Cache
        @Override
        public Integer testCalculateSumm(Integer a, Integer b) {
            return a+b;
        }
        @Setter
        @Override
        public void resetCalculate() {
        }
    }
}


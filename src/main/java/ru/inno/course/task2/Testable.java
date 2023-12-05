package ru.inno.course.task2;

public interface Testable {
    public default Integer testCalculateSumm(Integer a, Integer b){
        return null;
    }
    public default void resetCalculate(){
    }
}

package com.dromdev.mvcframework.model;

/**
 * Created by faren on 10/25/14.
 */
public class Fibonacci {
    private int fibonacciCounter = 0;
    private int[] fibonacciNumbers;
    private int fibonacciSize;

    public Fibonacci(int fibonacciSize){
        this.fibonacciSize = fibonacciSize;
        this.fibonacciNumbers = new int[fibonacciSize];
    }

    public int size(){
        return fibonacciSize;
    }

    public void add(int fibonacciNumber){
        fibonacciNumbers[fibonacciCounter] = fibonacciNumber;
        fibonacciCounter++;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i =0; i<size(); i++ ){
            result += fibonacciNumbers[i] + " ";
        }

        return result;
    }
}

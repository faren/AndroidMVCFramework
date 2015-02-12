package com.dromdev.mvcframework.controller;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.dromdev.mvcframework.model.Fibonacci;
import com.dromdev.mvcframework.view.HandlerMain;

/**
 * Created by faren on 10/25/14.
 */
public class ControllerMain {

    private Handler handler;
    private Activity activity;

    public ControllerMain(Handler handler){
        this.handler = handler;
    }

    public ControllerMain(Handler handler, Activity activity){
        this.handler = handler;
        this.activity = activity;
    }

    public void findFibonacciNumber(int fibonacciSize){
        Fibonacci fibonacci = new Fibonacci(fibonacciSize);
        for(int i=0; i<fibonacciSize; i++){
            fibonacci.add(fibonacciRecursive(i));
        }

        Message msg = new Message();
        msg.what = HandlerMain.MAIN_FIBONACI;
        msg.obj = fibonacci;
        getHandler().sendMessage(msg);
    }

    private int fibonacciRecursive(int number){

        if (number==0){
            return 0;
        }

        if ((number==1)||(number==2)){
            return 1;
        }

        return fibonacciRecursive(number-1)+fibonacciRecursive(number-2);
    }

    public Activity getActivity(){
        return this.activity;
    }

    public Handler getHandler(){
        return this.handler;
    }

}

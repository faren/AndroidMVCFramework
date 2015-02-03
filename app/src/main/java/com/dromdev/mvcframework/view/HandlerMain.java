package com.dromdev.mvcframework.view;

import android.os.Handler;
import android.os.Message;

import com.dromdev.mvcframework.model.Fibonacci;

/**
 * Created by faren on 10/25/14.
 */
public class HandlerMain extends Handler {
    public static final int MAIN_FIBONACI = 0;
    public static final int MAIN_OTHER = 1;

    private ActivityMain activity;

    public HandlerMain(ActivityMain activity){
        this.activity = activity;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what){
            case MAIN_FIBONACI:
                displayFibonaci((Fibonacci) msg.obj);
                break;
            default:
                break;
        }
    }

    private void displayFibonaci(Fibonacci fibonacci){
        activity.getTvResultFib().setText(fibonacci.toString());
    }
}

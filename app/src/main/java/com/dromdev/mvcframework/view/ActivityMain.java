package com.dromdev.mvcframework.view;

import android.app.Activity;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dromdev.mvcframework.R;
import com.dromdev.mvcframework.controller.ControllerMain;

public class ActivityMain extends Activity {

    private Handler handler;
    private ControllerMain controller;
    private Button btnExecuteFib;
    private EditText etFibonaciSize;
    private TextView tvResultFib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Debug.startMethodTracing("mvc");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new HandlerMain(this);
        controller = new ControllerMain(handler, this);

        etFibonaciSize = (EditText)findViewById(R.id.et_fibonaci);
        btnExecuteFib = (Button)findViewById(R.id.bt_fibonaci);

        btnExecuteFib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.findFibonacciNumber(Integer.parseInt(etFibonaciSize.getText().toString()));
            }
        });

        tvResultFib = (TextView)findViewById(R.id.tv_fibonaci_result);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Debug.stopMethodTracing();
    }

    public TextView getTvResultFib(){
        return tvResultFib;
    }

}

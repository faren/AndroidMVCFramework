package com.dromdev.mvcframework;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.test.AndroidTestCase;

import com.dromdev.mvcframework.controller.ControllerMain;
import com.dromdev.mvcframework.model.Fibonacci;
import com.dromdev.mvcframework.view.HandlerMain;

import org.mockito.ArgumentCaptor;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by faren on 2/3/15.
 */
public class ControllerMainTest extends AndroidTestCase {
    private ControllerMain mController = null;
    private Handler mHandler = null;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        mController = mock(ControllerMain.class);
        mHandler = mock(Handler.class);

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    class mActivity extends Activity{

    }

    public void testFindFibonacciNumberSize1(){

        when(mController.getHandler()).thenReturn(mHandler);

        //initial fibonaci numbers: 0, 1, 1, 2, 3, 5, 8, 13, .....
        // input
        int valueA = 1;

        //output expected
        Fibonacci mFibonacci = new Fibonacci(valueA);
        mFibonacci.add(0);

        doCallRealMethod().when(mController).findFibonacciNumber(valueA);
        mController.findFibonacciNumber(valueA);

        ArgumentCaptor<Message> msgA = ArgumentCaptor.forClass(Message.class);
        verify(mHandler).sendMessageAtTime(msgA.capture(), anyInt());

        assertEquals(HandlerMain.MAIN_FIBONACI, msgA.getValue().what);
        assertNotNull(msgA.getValue().obj);
        assertEquals(mFibonacci.toString(), ((Fibonacci)msgA.getValue().obj).toString());
    }

    public void testFindFibonacciNumberSize5(){

        when(mController.getHandler()).thenReturn(mHandler);

        //initial fibonaci numbers: 0, 1, 1, 2, 3, 5, 8, 13, .....
        int valueB = 5;

        //output expected
        Fibonacci mFibonacci = new Fibonacci(valueB);
        mFibonacci.add(0);
        mFibonacci.add(1);
        mFibonacci.add(1);
        mFibonacci.add(2);
        mFibonacci.add(3);

        doCallRealMethod().when(mController).findFibonacciNumber(valueB);
        mController.findFibonacciNumber(valueB);

        ArgumentCaptor<Message> msgB = ArgumentCaptor.forClass(Message.class);
        verify(mHandler).sendMessageAtTime(msgB.capture(), anyInt());

        assertEquals(HandlerMain.MAIN_FIBONACI, msgB.getValue().what);
        assertNotNull(msgB.getValue().obj);
        assertEquals(mFibonacci.toString(), ((Fibonacci)msgB.getValue().obj).toString());

    }
}

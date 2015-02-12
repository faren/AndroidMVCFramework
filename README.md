# AndroidMVCFramework<a href="http://s669.photobucket.com/user/far-mit/media/mvc_android.png.html" target="_blank"><img src="http://i669.photobucket.com/albums/vv55/far-mit/mvc_android.png" border="0" alt=" photo mvc_android.png"/></a>

This is equivalent with MVC. This framework has been used by a lot of android projects.
And this repository contains example of framework MVC for android. 

Please feel free to give feedback for this framework, or if you want more discussion for improvement, you can reach me at faren.faren@gmail.com.


##How to use?

**View** is defined by xml layout and activity. **View** should be cleared from the core/main processing. It just contains view definition, eventcallback or listener.

```java
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
```


**Controller** is defined by controller itself. Controller can be drived by view or if it is possible more to modul.


```java
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
```

Once controller has done its job, to update the view, controller has to send message to handler.
**Handler** is UI handle to update all UI component that need to update.

```java
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
```

For **model**, actually it could be from content provider, POJO or database helper.

```java
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
```

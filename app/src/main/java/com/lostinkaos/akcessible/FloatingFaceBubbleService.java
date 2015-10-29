package com.lostinkaos.akcessible;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Created by keya on 29/10/15.
 */
public class FloatingFaceBubbleService extends Service {

    private WindowManager windowManager;
    private ImageView floatingFaceBubble;

    Context context;

    MyApplication application;

    public void onCreate() {
        super.onCreate();
        floatingFaceBubble = new ImageView(this);
        context = getApplicationContext();

        application = (MyApplication) context;

        //a face floating bubble as imageView
        floatingFaceBubble.setImageResource(R.mipmap.ic_launcher);
        floatingFaceBubble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AlertDialog alertDialog = new AlertDialog.Builder(FloatingFaceBubbleService.this).create();
//                alertDialog.show();
                Log.d(MainActivity.DEBUG_TAG, "I was touched");
                Intent intent = new Intent(FloatingFaceBubbleService.this, application.getFloatingMessage().getClass());
                startService(intent);

//                application.getFloatingMessage().getTranslated().setText("asdasdasd");
            }
        });
        windowManager = (WindowManager)getSystemService(WINDOW_SERVICE);
//        //here is all the science of params
        final WindowManager.LayoutParams myParams = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        myParams.gravity = Gravity.TOP | Gravity.LEFT;
        myParams.x=0;
        myParams.y=100;
//        // add a floatingfacebubble icon in window
        windowManager.addView(floatingFaceBubble, myParams);
        try{
            //for moving the picture on touch and slide
            floatingFaceBubble.setOnTouchListener(new View.OnTouchListener() {
                WindowManager.LayoutParams paramsT = myParams;
                private int initialX;
                private int initialY;
                private float initialTouchX;
                private float initialTouchY;
                private long touchStartTime = 0;
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    //remove face bubble on long press
                    if(System.currentTimeMillis()-touchStartTime> ViewConfiguration.getLongPressTimeout() && initialTouchX== event.getX()){
                        windowManager.removeView(floatingFaceBubble);
                        stopSelf();
                        return false;
                    }
                    switch(event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            touchStartTime = System.currentTimeMillis();
                            initialX = myParams.x;
                            initialY = myParams.y;
                            initialTouchX = event.getRawX();
                            initialTouchY = event.getRawY();
                            break;
                        case MotionEvent.ACTION_UP:
                            break;
                        case MotionEvent.ACTION_MOVE:
                            myParams.x = initialX + (int) (event.getRawX() - initialTouchX);
                            myParams.y = initialY + (int) (event.getRawY() - initialTouchY);
                            windowManager.updateViewLayout(v, myParams);
                            break;
                    }
                    return false;
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

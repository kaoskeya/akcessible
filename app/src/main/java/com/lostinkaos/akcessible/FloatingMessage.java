package com.lostinkaos.akcessible;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.zip.Inflater;

/**
 * Created by keya on 29/10/15.
 */
public class FloatingMessage extends Service {

    private WindowManager windowManager;
    private View floatingMessageLayout;
    Context context;
    MyApplication application;

    public View getFloatingMessageLayout() {
        return floatingMessageLayout;
    }

    public void setFloatingMessageLayout(View floatingMessageLayout) {
        this.floatingMessageLayout = floatingMessageLayout;
    }

    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
        application = (MyApplication) getApplicationContext();

        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        floatingMessageLayout = inflater.inflate(R.layout.activity_floating, null);

        application.setTranslated( (TextView) floatingMessageLayout.findViewById(R.id.translated) );

        application.getTranslated().setText("qweqeqweqweqweqwew");

        windowManager = (WindowManager)getSystemService(WINDOW_SERVICE);
        //here is all the science of params
        final WindowManager.LayoutParams myParams = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        myParams.gravity = Gravity.BOTTOM | Gravity.LEFT;
        myParams.x = 0;
        myParams.y = 100;
        // add a floatingfacebubble icon in window
        windowManager.addView(floatingMessageLayout, myParams);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}

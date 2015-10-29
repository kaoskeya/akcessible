package com.lostinkaos.akcessible;

import android.app.Application;
import android.widget.TextView;

/**
 * Created by keya on 29/10/15.
 */
public class MyApplication extends Application {

    FloatingMessage floatingMessage;
    TextView translated;

    public TextView getTranslated() {
        return translated;
    }

    public void setTranslated(TextView translated) {
        this.translated = translated;
    }

    public FloatingMessage getFloatingMessage() {
        return floatingMessage;
    }

    public void setFloatingMessage(FloatingMessage floatingMessage) {
        this.floatingMessage = floatingMessage;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        floatingMessage = new FloatingMessage();

    }
}

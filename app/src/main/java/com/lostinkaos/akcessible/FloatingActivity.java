package com.lostinkaos.akcessible;

import android.os.Bundle;
import android.app.Activity;
import android.view.WindowManager;

public class FloatingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating);

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.x = -100;
        params.height = 200;
        params.width = 1000;
        params.y = -50;

        this.getWindow().setAttributes(params);

    }
}

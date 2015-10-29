package com.lostinkaos.akcessible;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityEventSource;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by keya on 29/10/15.
 */
public class MyAccessibilityService extends AccessibilityService {

    List<String> mstringsToBeTranslated;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.v(MainActivity.DEBUG_TAG, String.format(
                "onAccessibilityEvent: [type] %s [class] %s [package] %s [time] %s [text] %s",
                getEventType(event), event.getClassName(), event.getPackageName(),
                event.getEventTime(), getEventText(event)));

        Log.d(MainActivity.DEBUG_TAG, event.getClassName().toString());
        Log.d(MainActivity.DEBUG_TAG, event.getClassName().toString().equals("android.widget.TextView") + "");


        mstringsToBeTranslated.clear();
        AccessibilityNodeInfo node = event.getSource();
        huntForTextView(node);
        Log.d(MainActivity.DEBUG_TAG, mstringsToBeTranslated.toString());

//        Log.d(MainActivity.DEBUG_TAG, "Window State Changed!");
//        AccessibilityNodeInfo rootNode = getRootInActiveWindow();
//        huntForTextView(rootNode);
//        Toast.makeText(getApplicationContext(), "[Translate] " + event.getText().toString(), Toast.LENGTH_SHORT ).show();

    }

    public void huntForTextView(AccessibilityNodeInfo rootNode) {
//        Log.d(MainActivity.DEBUG_TAG, "huntForTextView called");
        if( rootNode != null ) {
            int childCount = rootNode.getChildCount();
            if (childCount == 0) {
//                Log.d(MainActivity.DEBUG_TAG, "Childless node className: " + rootNode.getClassName() + "");
                if( rootNode.getClassName().toString().equals("android.widget.TextView") ) {
                    try {
                        mstringsToBeTranslated.add(rootNode.getText().toString());
                    } catch (Exception e) {
                        Log.e(MainActivity.DEBUG_TAG, "string add exception caught");
                    }
//                    Log.d(MainActivity.DEBUG_TAG, "Text: " + rootNode.getText());
                }

            } else {
                for (int i = 0; i < childCount; i++) {
                    huntForTextView(rootNode.getChild(i));
                }
            }
        }
    }

    @Override
    public void onInterrupt() {
        Log.d(MainActivity.DEBUG_TAG, "There goes an AccessibilityEvent Interrupted");
    }

    private String getEventType(AccessibilityEvent event) {
        switch (event.getEventType()) {
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
                return "TYPE_NOTIFICATION_STATE_CHANGED";
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                return "TYPE_VIEW_CLICKED";
            case AccessibilityEvent.TYPE_VIEW_FOCUSED:
                return "TYPE_VIEW_FOCUSED";
            case AccessibilityEvent.TYPE_VIEW_LONG_CLICKED:
                return "TYPE_VIEW_LONG_CLICKED";
            case AccessibilityEvent.TYPE_VIEW_SELECTED:
                return "TYPE_VIEW_SELECTED";
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                return "TYPE_WINDOW_STATE_CHANGED";
            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED:
                return "TYPE_VIEW_TEXT_CHANGED";
        }
        return "default";
    }

    private String getEventText(AccessibilityEvent event) {
        StringBuilder sb = new StringBuilder();
        for (CharSequence s : event.getText()) {
            sb.append(s);
        }
        return sb.toString();
    }


    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();

        mstringsToBeTranslated = new ArrayList<>();

        Log.v(MainActivity.DEBUG_TAG, "onServiceConnected");
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.flags = AccessibilityServiceInfo.DEFAULT;
//        info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
        info.eventTypes = AccessibilityEvent.TYPE_VIEW_CLICKED | AccessibilityEvent.TYPE_VIEW_FOCUSED;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
        setServiceInfo(info);
    }

}

package com.edtest.xcpbuttonintentreceiverapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class XCPButtonReceiver extends BroadcastReceiver {
    public static final String TAG = "XCP_BUTTON_INTENT_RECEIVER_APP";
    public static final String TAG2 = "XCP_BUTTON_RECEIVER: ";

    ListView listView;
    ArrayList<String> list;
    ArrayAdapter arrayAdapter;

    boolean noUI;

    XCPButtonReceiver (ListView listView) {
        this.listView = listView;
        list = new ArrayList<>();
        list.add(0,"Starting");
        Log.w(TAG, TAG2 + "SETUP");
        noUI = false;
    }

    XCPButtonReceiver() {
        noUI = true;
        Log.w(TAG, TAG2 + "NO_UI_SETUP");
    }

    Context c;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.w(TAG, TAG2 + "ON_RECEIVE");

        String status = "BLANK";

        if ("com.edtest.xcpbuttonintentreceiverapp.intent.action.PTT_PRESS".equals(intent.getAction())) {
            // XCover key pressed
            Log.w(TAG, TAG2 + "XCP_KEY_PRESSED");
            status = "XCP_KEY_PRESSED";
        } else if ("com.edtest.xcpbuttonintentreceiverapp.intent.action.PTT_RELEASE".equals(intent.getAction())) {
            //XCover Key Released
            Log.w(TAG, TAG2 + "XCP_KEY_RELEASED");
            status = "XCP_KEY_RELEASED";
        }

        if (!noUI) {
            list.add(0, status);
            ArrayAdapter arrayAdapter = new ArrayAdapter(context, R.layout.row_layout, R.id.label, list.toArray());
            listView.setAdapter(arrayAdapter);
        }
    }
}
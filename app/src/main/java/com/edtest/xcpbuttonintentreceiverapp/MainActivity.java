package com.edtest.xcpbuttonintentreceiverapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "XCP_BUTTON_INTENT_RECEIVER_APP";
    public static final String TAG2 = "MAIN_ACTIVITY: ";

    ArrayList<String> buttonActions;
    ListView listView;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        buttonActions = new ArrayList<>();
        buttonActions.add("STARTING");

        arrayAdapter = new ArrayAdapter(this, R.layout.row_layout, R.id.label, buttonActions);

        listView.setAdapter(arrayAdapter);

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        Log.w(TAG, TAG2 + "ON_POST_RESUME");
        XCPButtonReceiver xcpButtonReceiver = new XCPButtonReceiver(listView);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.edtest.xcpbuttonintentreceiverapp.intent.action.PTT_PRESS");
        intentFilter.addAction("com.edtest.xcpbuttonintentreceiverapp.intent.action.PTT_RELEASE");
        registerReceiver(xcpButtonReceiver,intentFilter);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}
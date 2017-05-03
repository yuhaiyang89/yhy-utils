package com.yhy.utils.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yhy.utils.SyncResultReceiver;

public class Activity2 extends AppCompatActivity {

    SyncResultReceiver mReceiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mReceiver = getIntent().getParcelableExtra("callback");

        // 给MainActivity回调值
        Bundle bundle = new Bundle();
        bundle.putInt("value", 1);
        mReceiver.send(1, bundle);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 给MainActivity回调值
        Bundle bundle = new Bundle();
        bundle.putInt("value", 2);
        mReceiver.send(1, bundle);
    }

    @Override
    protected void onDestroy() {
        // 给MainActivity回调值
        Bundle bundle = new Bundle();
        bundle.putInt("value", 3);
        mReceiver.send(1, bundle);
        super.onDestroy();
    }
}

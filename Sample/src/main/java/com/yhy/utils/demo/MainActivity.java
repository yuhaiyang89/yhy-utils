package com.yhy.utils.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.yhy.utils.SyncResultReceiver;

public class MainActivity extends AppCompatActivity {

    private int value = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 重写SyncResultReceiver的onReceiveResult方法接收返回值
        SyncResultReceiver mResultReceiver = new SyncResultReceiver(){
            @Override
            protected void onReceiveResult(int code, Bundle bundle) {

                value = bundle.getInt("value");
                Log.d("TEST", "code=" + code + " | value = " + value);
            }
        };

        // 开启Activity2
        Intent intent = new Intent(this, Activity2.class);
        intent.putExtra("callback", mResultReceiver);
        startActivity(intent);
    }
}

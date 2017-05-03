package com.yhy.utils;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class SyncResultReceiver implements Parcelable {

    public static final Creator<SyncResultReceiver> CREATOR = new Creator<SyncResultReceiver>() {

        public SyncResultReceiver createFromParcel(Parcel in) {
            return new SyncResultReceiver(in);
        }

        public SyncResultReceiver[] newArray(int size) {
            return new SyncResultReceiver[size];
        }
    };

    // 是本地回调还是远程回调
    final boolean mLocal;

    IChannel mBridge;


    public SyncResultReceiver() {
        mLocal = true;
    }

    SyncResultReceiver(Parcel in) {
        mLocal = false;
        mBridge = IChannel.Stub.asInterface(in.readStrongBinder());
    }

    public void send(int code, Bundle data) {
        if (mLocal) {
            onReceiveResult(code, data);
            return;
        }

        if (mBridge != null) {
            try {
                mBridge.send(code, data);
            } catch (Exception e) {
                Log.e("", "",e);
            }
        }
    }

    protected void onReceiveResult(int code, Bundle bundle) {
        // 等待子类去实现此方法，接收消息
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        synchronized (this) {
            if (mBridge == null) {
                mBridge = new MyChannel();
            }
            out.writeStrongBinder(mBridge.asBinder());
        }
    }

    class MyChannel extends IChannel.Stub {
        public void send(int code, Bundle data) {
            onReceiveResult(code, data);
        }
    }
}

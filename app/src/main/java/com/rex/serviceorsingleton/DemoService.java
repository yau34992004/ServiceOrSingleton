package com.rex.serviceorsingleton;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.rex.boilerplate.task.CommonTask;
import com.rex.boilerplate.task.Execute;
import com.rex.boilerplate.task.ThreadModule;

import de.greenrobot.event.EventBus;

/**
 * Created by rex.yau on 6/9/2015.
 */
public class DemoService extends Service {

    private static final String TAG = "DemoService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ThreadModule.getInstance().dispatch(new CommonTask(getApplicationContext()) {

            @Execute(thread = Execute.SAFE_THREAD)
            @Override
            public void run() {
                Log.d(TAG, "startRunning");
                //long running code , maybe download a binary file from server
                new SomethingStupid().doIt();
                Log.d(TAG, "stopRunning");
                EventBus.getDefault().post(new Result("test"));
            }
        });

        return super.onStartCommand(intent, flags, startId);
    }

}

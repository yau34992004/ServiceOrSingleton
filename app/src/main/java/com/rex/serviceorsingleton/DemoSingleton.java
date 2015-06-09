package com.rex.serviceorsingleton;

import de.greenrobot.event.EventBus;

/**
 * Created by rex.yau on 6/9/2015.
 */
public class DemoSingleton {

    private static DemoSingleton mDemoSingleton;

    private DemoSingleton() {

    }

    public static DemoSingleton newInstance() {
        if (mDemoSingleton == null) {
            synchronized (DemoSingleton.class) {
                if (mDemoSingleton == null) {
                    mDemoSingleton = new DemoSingleton();
                }
            }
        }
        return mDemoSingleton;
    }

    public void doSomething() {
        new SomethingStupid().doIt();
        EventBus.getDefault().post(new Result("resultFromSingleton"));
    }
}

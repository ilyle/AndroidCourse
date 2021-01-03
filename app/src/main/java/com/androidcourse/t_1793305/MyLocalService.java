package com.androidcourse.t_1793305;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MyLocalService extends Service {
    public MyLocalService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new MyBinder();
    }

    static class MyBinder extends IMyLocalService.Stub {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int compare(int num1, int num2) throws RemoteException {
            return Math.max(num1, num2);
        }
    }


}

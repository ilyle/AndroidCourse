package com.androidcourse.t_1793305;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Exp5Activity extends AppCompatActivity {

    private IMyLocalService iMyLocalnterface;

    private EditText mEtNum1;
    private EditText mEtNum2;
    private TextView mTvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp5);
        mEtNum1 = findViewById(R.id.et_num_1);
        mEtNum2 = findViewById(R.id.et_num_2);
        mTvResult = findViewById(R.id.tv_result);

        bindService(new Intent(this, MyLocalService.class), new ServiceConnection() {

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

                iMyLocalnterface = IMyLocalService.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, BIND_AUTO_CREATE);
    }

    public void compare(View view) {
        int num1 = Integer.parseInt(mEtNum1.getText().toString().trim());
        int num2 = Integer.parseInt(mEtNum2.getText().toString().trim());

        try {
            int result = iMyLocalnterface.compare(num1, num2);
            mTvResult.setText("大数为：" + result);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
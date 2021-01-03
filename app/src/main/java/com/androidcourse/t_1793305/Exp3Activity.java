package com.androidcourse.t_1793305;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Exp3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp_3);
    }

    public void launchActivity(View view) {
        //生成一个Intent对象
        Intent intent = new Intent();
        //在Intent对象当中添加一个键值对
        intent.putExtra("testIntent", "123");
        //设置Intent对象要启动的Activity
        intent.setClass(this, NewActivity.class);
        //通过Intent对象启动另外一个Activity
        startActivity(intent);
    }
}
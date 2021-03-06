package com.androidcourse.t_1793305;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.androidcourse.t_1793305.exp7.Exp7Activity;
import com.androidcourse.t_1793305.exp7.TestContentResolverActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setListener();
    }

    private void initView() {
    }

    private void setListener() {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            default:
                break;
        }
    }

    public void nav2Exp2(View view) {
        Intent intent = new Intent(this, Exp2Activity.class);
        startActivity(intent);
    }

    public void nav2Exp3(View view) {
        Intent intent = new Intent(this, Exp3Activity.class);
        startActivity(intent);
    }

    public void nav2Exp4(View view) {
        Intent intent = new Intent(this, LifeCycleActivity.class);
        startActivity(intent);
    }

    public void nav2Exp5(View view) {
        Intent intent = new Intent(this, Exp5Activity.class);
        startActivity(intent);
    }

    public void nav2Exp6(View view) {
        Intent intent = new Intent(this, Exp6Activity.class);
        startActivity(intent);
    }

    public void nav2Exp7(View view) {
        Intent intent = new Intent(this, Exp7Activity.class);
        startActivity(intent);
    }

    public void nav2Exp7ContentResolver(View view) {
        Intent intent = new Intent(this, TestContentResolverActivity.class);
        startActivity(intent);
    }
}
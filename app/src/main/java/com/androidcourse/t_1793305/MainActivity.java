package com.androidcourse.t_1793305;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnExp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setListener();
    }

    private void initView() {
        mBtnExp2 = findViewById(R.id.btn_2);
    }

    private void setListener() {
        mBtnExp2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_2:
                nav2Exp2();
                break;
            default:
                break;
        }
    }

    private void nav2Exp2() {
        Intent intent = new Intent(this, Exp2Activity.class);
        startActivity(intent);
    }
}
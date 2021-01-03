package com.androidcourse.t_1793305;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp_3_1);
        TextView mTv = findViewById(R.id.tv_exp_3_1);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String name = bundle.getString("testIntent");
            mTv.setText(name);
        }
    }

    public void sendMsg(View view) {
        Uri uri = Uri.parse("smsto: 13912345678");
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", "The SMS text");
        startActivity(intent);
    }
}
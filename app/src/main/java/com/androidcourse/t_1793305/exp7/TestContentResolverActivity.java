package com.androidcourse.t_1793305.exp7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.androidcourse.t_1793305.R;

import java.util.ArrayList;

public class TestContentResolverActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView listView;
    private Button query;
    private TextView tv_sum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_content_resolver);
        initView();
    }

    private void initView() {
        listView = findViewById(R.id.listView);
        query = findViewById(R.id.btn_query);
        query.setOnClickListener(this);
        tv_sum = findViewById(R.id.tv_sum);
    }


    private void query() {
        ContentResolver contentResolver = getContentResolver();
        Uri uri = Uri.parse("content://com.androidcourse.t_1793305.contentprovider");
        //通过ContentProvider返回的Cursor结果集放到SimpleCursorAdapter上显示
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        //参数1：上下文 参数2：资源布局 参数3：cursor 参数4：属性名 参数5：显示控件id的数组 参数6：flag
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(
                this,
                R.layout.item_staff_info,
                cursor,
                new String[]{"_id", "name", "sex", "department", "salary"},
                new int[]{R.id.tv_info_id, R.id.tv_info_name, R.id.tv_info_sex, R.id.tv_infp_department, R.id.tv_info_salary},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        );
        listView.setAdapter(cursorAdapter);
        int count = listView.getAdapter().getCount();
        tv_sum.setText("数据库：" + count + "记录");
        Toast.makeText(this, "查询成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        query();
    }

}

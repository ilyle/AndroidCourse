package com.androidcourse.t_1793305.exp7;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.androidcourse.t_1793305.R;

public class Exp7Activity extends AppCompatActivity implements View.OnClickListener {

    private EditText name;
    private EditText sex;
    private EditText department;
    private EditText salary;
    private EditText id;

    private Button btn_add;
    private Button btn_show;
    private Button btn_clear;
    private Button btn_delete;

    private Button btn_id_delete;
    private Button btn_id_query;
    private Button btn_id_update;


    private ListView list;
    SimpleCursorAdapter cursorAdapter;
    private StaffDao dao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp7);
        initView();
        checkPermission(this);

        dao = new StaffDao(this);

    }

    private void initView() {
        name = findViewById(R.id.et_name);
        sex = findViewById(R.id.et_sex);
        department = findViewById(R.id.et_department);
        salary = findViewById(R.id.et_salary);
        id = findViewById(R.id.et_id);

        list = findViewById(R.id.listView);

        btn_add = findViewById(R.id.btn_add);
        btn_show = findViewById(R.id.btn_show);
        btn_clear = findViewById(R.id.btn_clear);
        btn_delete = findViewById(R.id.btn_delete);

        btn_id_delete = findViewById(R.id.btn_id_delete);
        btn_id_query = findViewById(R.id.btn_id_query);
        btn_id_update = findViewById(R.id.btn_id_update);

        btn_add.setOnClickListener(this);
        btn_show.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_delete.setOnClickListener(this);


        btn_id_delete.setOnClickListener(this);
        btn_id_query.setOnClickListener(this);
        btn_id_update.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        String nameStr = name.getText().toString();
        String sexStr = sex.getText().toString();
        String departmentStr = department.getText().toString();
        String salaryStr = salary.getText().toString();

        String idStr = id.getText().toString();


        StaffInfo staffInfo = new StaffInfo();
        staffInfo.setName(nameStr);
        staffInfo.setSex(sexStr);
        staffInfo.setDepartment(departmentStr);
        staffInfo.setSalary(salaryStr);

        operator(v, idStr, staffInfo);

        name.setText("");
        sex.setText("");
        department.setText("");
        salary.setText("");
        id.setText("");

    }

    private void operator(View v, String idStr, StaffInfo staffInfo) {
        switch (v.getId()) {
            case R.id.btn_add:
                dao.addStaff(staffInfo);
                Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_show:
                Cursor cursor = dao.showAllStaff();
                Toast.makeText(this, "全部显示成功", Toast.LENGTH_SHORT).show();
                //参数1：上下文 参数2：资源布局 参数3：cursor 参数4：属性名 参数5：显示控件id的数组 参数6：flag
                cursorAdapter = makeAdapter(cursor);
                list.setAdapter(cursorAdapter);
                break;
            case R.id.btn_clear:
                list.setAdapter(null);
                Toast.makeText(this, "清除显示成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_delete:
                dao.deleteAllStaff();
                list.setAdapter(null);
                Toast.makeText(this, "全部删除成功", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_id_delete:
                if (!idStr.equals("")) {
                    int idInt = Integer.parseInt(idStr);
                    dao.deleteById(idInt);
                }
                Toast.makeText(this, "id删除成功", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_id_query:
                list.setAdapter(null);
                if (!idStr.equals("")) {
                    int idInt = Integer.parseInt(idStr);
                    Cursor c = dao.queryById(idInt);
                    cursorAdapter = makeAdapter(c);
                    list.setAdapter(cursorAdapter);
                }
                Toast.makeText(this, "id查询成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_id_update:
                if (!idStr.equals("")) {
                    int idInt = Integer.parseInt(idStr);
                    staffInfo.setId(idInt);
                    dao.updateById(staffInfo, idInt);
                }
                Toast.makeText(this, "更新成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private SimpleCursorAdapter makeAdapter(Cursor cursor) {
        return new SimpleCursorAdapter(
                this,
                R.layout.item_staff_info,
                cursor,
                new String[]{"_id", "name", "sex", "department", "salary"},
                new int[]{R.id.tv_info_id, R.id.tv_info_name, R.id.tv_info_sex, R.id.tv_infp_department, R.id.tv_info_salary},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        );
    }


    //android6.0之后要动态获取权限
    private void checkPermission(Activity activity) {
        // Storage Permissions
        final int REQUEST_EXTERNAL_STORAGE = 1;
        String[] PERMISSIONS_STORAGE = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};

        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(Exp7Activity.this,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(Exp7Activity.this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

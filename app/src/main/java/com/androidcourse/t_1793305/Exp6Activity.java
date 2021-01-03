package com.androidcourse.t_1793305;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Exp6Activity extends AppCompatActivity {

    /*定义访问模式*/
    public static int MODE = MODE_PRIVATE;
    /*定义一个SharedPreferences名。之后将以这个名字保存在Android文件系统中*/
    public static final String PREFERENCE_NAME = "SaveSetting";


    private EditText mEtName;
    private EditText mEtAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp6);
        mEtName = findViewById(R.id.et_name);
        mEtAge = findViewById(R.id.et_age);
        init();
    }

    private void init() {
        /*获取SharedPreferences实例。如果不存在将新建一个  */
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE);
        /*读取SharedPreferences中保存的键值:如果文件或键值不在，则用缺省值 */
        String name = sharedPreferences.getString("Name","Default Name");
        int age = sharedPreferences.getInt("Age", 20);
        mEtName.setText(name);
        mEtAge.setText(String.valueOf(age));

    }

    public void save(View view) {
        String name = mEtName.getText().toString().trim();
        int age = Integer.parseInt(mEtAge.getText().toString().trim());
        /*获取SharedPreferences实例  */
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE);
        /*通过SharedPreferences.Editor类向SharedPreferences中写键值，调用commit()保存修改内容*/
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Name", name);
        editor.putInt("Age", age);
        editor.apply();
    }
}
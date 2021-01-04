package com.androidcourse.t_1793305.exp7;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StaffDao {

    private SQLiteDatabase db;

    public StaffDao(Context context) {
        String path = "Staff.db";
        SQLiteOpenHelper helper = new SQLiteOpenHelper(context, path, null, 1) {
            @Override
            public void onCreate(SQLiteDatabase db) {
                String sql = "create table Staffinfo(_id integer primary key autoincrement," +
                        "name varchar(20)," +
                        "sex varchar(20)," +
                        "department varchar(20)," +
                        "salary float)";
                db.execSQL(sql);
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            }
        };
        //拿到SQLiteDatabase实例
        db = helper.getReadableDatabase();
    }

    //添加操作
    public void addStaff(StaffInfo Info) {
        String sql = "insert into Staffinfo(name,sex,department,salary) values(?,?,?,?)";
        db.execSQL(sql, new String[]{Info.getName(), Info.getSex(), Info.getDepartment(), Info.getSalary()});
    }


    //全部显示
    public Cursor showAllStaff() {
        String sql = "select * from Staffinfo";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    //全部清除
    public void deleteAllStaff() {
        String sql = "delete from Staffinfo";
        db.execSQL(sql);
    }

    //id删除
    public void deleteById(int id) {
        String sql = "delete from Staffinfo where  _id" + "='" + id + "'";
        db.execSQL(sql);
    }

    //id查询
    public Cursor queryById(int id) {
        String sql = "select * from Staffinfo where  _id" + "='" + id + "'";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }


    //根据id更新
    public void updateById(StaffInfo info,int id) {
        String sql = "update Staffinfo set name=?,sex=? ,department=? ,salary=? where _id=?";
        db.execSQL(sql, new Object[]{info.getName(), info.getSex(), info.getDepartment(),info.getSalary(),id});
    }


}

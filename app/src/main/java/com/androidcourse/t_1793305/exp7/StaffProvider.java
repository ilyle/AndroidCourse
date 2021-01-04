package com.androidcourse.t_1793305.exp7;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

public class StaffProvider extends ContentProvider {
    private SQLiteDatabase db;


    @Override
    public boolean onCreate() {
        String path = "Staff.db";
        SQLiteOpenHelper helper = new SQLiteOpenHelper(getContext(), path, null, 1) {
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
        //拿到读写数据库对象，如果没有数据库则创建，有则打开
        db = helper.getReadableDatabase();

        //返回true 后面的方法才能被调用
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor c = db.query("Staffinfo",
                projection,//指定在哪些列中查询，填null是查询所有
                selection,//查询条件
                selectionArgs,//查询条件值数组
                null,//指定分组的列名
                null,//去除不符合条件的列条件
                null,//排序
                sortOrder);
        return c;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        //想数据库中插入从contentResolver传来的数据,返回id
        long id = db.insert("Staffinfo", null, values);
        //ContentUris 将id追加到uri后面
        Uri returnUri = ContentUris.withAppendedId(uri, id);
        Log.e("Tag", "执行了contentprovider的insert操作");
        //返回给contentResolver
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        //返回的是int类似删除操作影响了多少行
        return db.delete("Staffinfo", selection, selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // update info_tb set name=?,age=?,gender=? where _id=?
        return db.update("Staffinfo", values, selection, selectionArgs);

    }
}

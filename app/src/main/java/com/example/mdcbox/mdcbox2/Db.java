package com.example.mdcbox.mdcbox2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class Db extends SQLiteOpenHelper {

    public static final String DATABACE_NAME = "Medicbox.db";
    public static final String TABLE_NAME = "md_table";
    public static final String COL_1 = "_id";
    public static final String COL_2 = "md_name";
    public static final String COL_3 = "md_count";
    public static final String COL_4 = "md_unit";
    public static final String COL_5 = "md_time";
//資料庫名稱  吃藥計畫資料庫
    public static final String TABLE_NAME2 = "p_table";
    public static final String COL_p1 = "_id";
    public static final String COL_p2 = "p_account";
    public static final String COL_p3 = "p_password";
//資料庫名稱 帳號管理資料庫
    public Db(@Nullable Context context) {
        super(context, DATABACE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " +TABLE_NAME+
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "md_name TEXT," +
                "md_count INTEGER," +
                "md_unit TEXT," +
                "md_time INTEGER)");

        db.execSQL("CREATE TABLE " +TABLE_NAME2+
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "p_account TEXT," +
                "p_password TEXT)");

    }
//建立資料庫database

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

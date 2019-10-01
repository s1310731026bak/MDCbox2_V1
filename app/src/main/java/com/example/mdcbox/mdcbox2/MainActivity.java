package com.example.mdcbox.mdcbox2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private LinearLayout mBtnButton1;
    private LinearLayout mBtnButton2;
    private LinearLayout mBtnButton3;
    Db myDb;//DB.java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnButton1 = (LinearLayout) findViewById(R.id.btn_Button1);
        mBtnButton1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //跳到TextView頁面
                Intent intent = new Intent(MainActivity.this,CalendarPickerViewActivity.class);
                startActivity(intent);
            }
        });

        mBtnButton2 = (LinearLayout) findViewById(R.id.btn_Button2);
        mBtnButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳轉道Button頁面
                Intent intent = new Intent(MainActivity.this,SetAlarm2Activity.class);
                startActivity(intent);
            }
        });

        mBtnButton3 = (LinearLayout) findViewById(R.id.btn_Button3);
        mBtnButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳轉道Button頁面
                Intent intent = new Intent(MainActivity.this, BluetoothActivity.class);
                startActivity(intent);
            }
        });

        myDb = new Db(this);
//
//        SQLiteDatabase dbWrite = myDb.getWritableDatabase();
//        ContentValues cv =new ContentValues();
//        cv.put("md_name","藥物名稱");
//        cv.put("md_count",5);
//        cv.put("md_unit","藥物單位");
//        cv.put("md_time","鬧鐘時間");
//        dbWrite.insert("md_table",null,cv);
//        dbWrite.close();
        SQLiteDatabase dbRead = myDb.getReadableDatabase();
        Cursor c = dbRead.query("md_table", null, null, null, null, null, null);

        while (c.moveToNext()) {
            String md_name = c.getString(c.getColumnIndex("md_name"));
            String md_count = c.getString(c.getColumnIndex("md_count"));
            String md_unit = c.getString(c.getColumnIndex("md_unit"));
            String md_time = c.getString(c.getColumnIndex("md_time"));
            System.out.println(String.format("md_name=%s,md_count=%s,md_unit=%s,md_time=%s", md_name,md_count,md_unit,md_time));

        }


    }
}

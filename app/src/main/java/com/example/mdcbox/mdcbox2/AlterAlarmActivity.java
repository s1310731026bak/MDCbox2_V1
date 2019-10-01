package com.example.mdcbox.mdcbox2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class AlterAlarmActivity extends AppCompatActivity {

    private Db mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter_alarm);

        final EditText MD_NAME = findViewById(R.id.tv_name);
        final EditText MD_COUNT = findViewById(R.id.tv_count);
        final Spinner MD_UNIT = findViewById(R.id.spnner1);
        final TextView MD_TIME = findViewById(R.id.tv_time);

        final Button Btn_alter = findViewById(R.id.button_alter);


        Btn_alter.setOnClickListener(new View.OnClickListener() {
            //點擊修改按鈕
            @Override
            public void onClick(View view) {


//                SQLiteDatabase dbWrite = mydb.getWritableDatabase();
//                ContentValues cv =new ContentValues();
//                cv.put("md_name", String.valueOf(MD_NAME.getText()));
//                cv.put("md_count", String.valueOf(MD_COUNT.getText()));
//                cv.put("md_unit",String.valueOf(MD_UNIT.getSelectedItem()));
//                cv.put("md_time",String.valueOf(MD_TIME.getText()));
//                dbWrite.insert("md_table",null,cv);
//                dbWrite.close();
//

                //修改資料
                SQLiteDatabase dbRead = mydb.getReadableDatabase();
                Cursor c = dbRead.query("md_table", null, null, null, null, null, null);

                while (c.moveToNext()) {
                    String md_name = c.getString(c.getColumnIndex("md_name"));
                    String md_count = c.getString(c.getColumnIndex("md_count"));
                    String md_unit = c.getString(c.getColumnIndex("md_unit"));
                    String md_time = c.getString(c.getColumnIndex("md_time"));
                    System.out.println(String.format("md_name=%s,md_count=%s,md_unit=%s,md_time=%s", md_name,md_count,md_unit,md_time));
                }
                //讀取資料


//                Intent intent = new Intent(SetAlarmActivity.this,SetAlarm2Activity.class);
//                startActivity(intent);
                //跳轉道LISTVIEW頁面
            }
        });


    }
////修改function
//    public void update(View v)
//    {
//        SQLiteDatabase db = mydb.getWritableDatabase();
//        ContentValues cv =new ContentValues();
//        cv.put("md_name", String.valueOf(MD_NAME.getText()));
//        cv.put("md_count", String.valueOf(MD_COUNT.getText()));
//        cv.put("md_unit",String.valueOf(MD_UNIT.getSelectedItem()));
//        cv.put("md_time",String.valueOf(MD_TIME.getText()));
//        db.execSQL("UPDATE person SET name = ?,time = ? WHERE personid = ?",
//                new String[]{v.getName(),v.getPhone(),v.getId()});
//    }
}

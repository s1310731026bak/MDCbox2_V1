package com.example.mdcbox.mdcbox2;

import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class SetAlarmActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    Db myDb;
    TimePickerDialog timePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);



        final EditText MD_NAME = findViewById(R.id.tv_name);
        final EditText MD_COUNT = findViewById(R.id.tv_count);
        final Spinner MD_UNIT = findViewById(R.id.spnner1);
        final TextView MD_TIME = findViewById(R.id.tv_time);

        final Button Btn_insert = findViewById(R.id.button_insert);
        myDb = new Db(this);

        MD_TIME.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(SetAlarmActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        MD_TIME.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
//Spinner UNIT設定下拉式選單
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.spn_list, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MD_UNIT.setAdapter(adapter);
        MD_UNIT.setOnItemSelectedListener(this);


        Btn_insert.setOnClickListener(new View.OnClickListener() {
            //點擊新增按鈕
            @Override
            public void onClick(View view) {


                SQLiteDatabase dbWrite = myDb.getWritableDatabase();
                ContentValues cv =new ContentValues();
                cv.put("md_name", String.valueOf(MD_NAME.getText()));
                cv.put("md_count", String.valueOf(MD_COUNT.getText()));
                cv.put("md_unit",String.valueOf(MD_UNIT.getSelectedItem()));
                cv.put("md_time",String.valueOf(MD_TIME.getText()));
                dbWrite.insert("md_table",null,cv);
                dbWrite.close();


                //輸入資料
                SQLiteDatabase dbRead = myDb.getReadableDatabase();
                Cursor c = dbRead.query("md_table", null, null, null, null, null, null);

                while (c.moveToNext()) {
                    String md_name = c.getString(c.getColumnIndex("md_name"));
                    String md_count = c.getString(c.getColumnIndex("md_count"));
                    String md_unit = c.getString(c.getColumnIndex("md_unit"));
                    String md_time = c.getString(c.getColumnIndex("md_time"));
                    System.out.println(String.format("md_name=%s,md_count=%s,md_unit=%s,md_time=%s", md_name,md_count,md_unit,md_time));
                }
                //讀取資料


                Intent intent = new Intent(SetAlarmActivity.this,SetAlarm2Activity.class);
                startActivity(intent);
                //跳轉道LISTVIEW頁面
            }
        });




    }



    private Runnable mutiThread = new Runnable(){
        public void run()
        {
            // 當這個執行緒完全跑完後執行
            runOnUiThread(new Runnable() {
                public void run() {

                }
            });
        }
    };
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
//        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

}




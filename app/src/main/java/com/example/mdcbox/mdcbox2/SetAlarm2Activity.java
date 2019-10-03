package com.example.mdcbox.mdcbox2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class SetAlarm2Activity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ImageButton mBtninstertplan;
    private Db myDb;
    private SimpleCursorAdapter adapter = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm2);

        final ListView listView = findViewById(R.id.asa2Listview);
        myDb = new Db(this);
        //TEST

//        ListView list_test = (ListView) findViewById(R.id.asa2Listview);
//        //读取联系人
//        Cursor cursor = getContentResolver()
//                .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
//        SimpleCursorAdapter spcAdapter = new SimpleCursorAdapter(this,R.layout.list_item,c,
//                new String[]{Db.COL_2,Db.COL_5},
//                new int[]{R.id.list_name,R.id.list_phone});
//        list_test.setAdapter(spcAdapter);



        refreshListView();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

//TEST



        mBtninstertplan = (ImageButton) findViewById(R.id.btn_insertplan);
        mBtninstertplan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //跳到TextView頁面
                Intent intent = new Intent(SetAlarm2Activity.this,SetAlarmActivity.class);
                startActivity(intent);
            }
        });



    }

    private void refreshListView() {
        Cursor c = myDb.getReadableDatabase().query("md_table", null, null, null, null, null, null);
        adapter = new SimpleCursorAdapter(this,R.layout.activity_set_alarm2_cell,c,
                new String[]{"md_name","md_time"},
                new int[]{R.id.Lv_tv,R.id.Lv_tv2});
        adapter.changeCursor(c);

    }


    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(SetAlarm2Activity.this,"你點擊了第" + position + "項",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SetAlarm2Activity.this,AlterAlarmActivity.class);
        startActivity(intent);
    }


}

package com.example.mdcbox.mdcbox2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {

    private Button mBtnlogin;
    private Button mBtnregister;



    Db myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText P_ACCOUNT = findViewById(R.id.p_account);
        final EditText P_PASSWORD = findViewById(R.id.p_password);

        mBtnlogin = (Button) findViewById(R.id.btn_login);
        mBtnregister = (Button) findViewById(R.id.btn_register);

        //login
        mBtnlogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {


                myDb = new Db(loginActivity.this);

                //讀取資料
                SQLiteDatabase dbRead = myDb.getReadableDatabase();
                Cursor c = dbRead.query("p_table", null, null, null, null, null, null);

                //get db p_account
                //get db p_password

                if( c.moveToFirst()){

                    final String p_account = c.getString(c.getColumnIndex("p_account"));
                    final String p_password = c.getString(c.getColumnIndex("p_password"));
                    System.out.println(P_ACCOUNT.getText().equals(p_account));
                if(P_ACCOUNT.getText().toString().equals(p_account) & P_PASSWORD.getText().toString().equals(p_password) ){
                    System.out.println("驗證成功");
                    Toast.makeText(loginActivity.this,"登入成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(loginActivity.this,MainActivity.class);
                    startActivity(intent);
                }else {
                    System.out.println("驗證失敗");
                    Toast.makeText(loginActivity.this,"登入失敗", Toast.LENGTH_SHORT).show();
                }
              }
                dbRead.close();
                c.close();
            }
        });

        //register
        mBtnregister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                myDb = new Db(loginActivity.this);
/////////////////IF判斷
                SQLiteDatabase dbRead = myDb.getReadableDatabase();
                Cursor c = dbRead.query("p_table", null, null, null, null, null, null);
                System.out.println("帳號數量為"+c.getCount());
//                String p2_account = CC2.getString(CC2.getColumnIndex("p_account"));
//                String p2_password = CC2.getString(CC2.getColumnIndex("p_password"));
//                System.out.println(String.format("p_account=%s,p_password=%s", p2_account,p2_password));
                while (c.moveToNext()) {
                    String p_account = c.getString(c.getColumnIndex("p_account"));
                    String p_password = c.getString(c.getColumnIndex("p_password"));
                    System.out.println(String.format("p_account=%s,p_password=%s", p_account,p_password));

                }
                //讀取資料
                if (c.getCount()==0){
                SQLiteDatabase dbWrite = myDb.getWritableDatabase();
                ContentValues cv =new ContentValues();
                cv.put("p_account", String.valueOf(P_ACCOUNT.getText()));
                cv.put("p_password", String.valueOf(P_PASSWORD.getText()));
                dbWrite.insert("p_table",null,cv);
                dbWrite.close();
                    System.out.println("註冊成功");
                    Toast.makeText(loginActivity.this,"註冊成功", Toast.LENGTH_SHORT).show();

                }
                //輸入資料
                if (c.getCount()==1){
                    System.out.println("已註冊帳號");
                    Toast.makeText(loginActivity.this,"已註冊帳號", Toast.LENGTH_SHORT).show();
                }
                dbRead.close();
                //已註冊帳號
            }
        });



    }
}

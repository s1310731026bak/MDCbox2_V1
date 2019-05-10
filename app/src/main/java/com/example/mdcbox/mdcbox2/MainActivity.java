package com.example.mdcbox.mdcbox2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class MainActivity extends AppCompatActivity {
    private Button mBtnButton1;
    private Button mBtnButton2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnButton1 = (Button) findViewById(R.id.btn_Button1);
        mBtnButton1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //跳到TextView頁面
                Intent intent = new Intent(MainActivity.this,BluetoothActivity.class);
                startActivity(intent);
            }
        });

        mBtnButton2 = (Button) findViewById(R.id.btn_Button2);
        mBtnButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳轉道Button頁面
                Intent intent = new Intent(MainActivity.this,MedicTimeActivity.class);
                startActivity(intent);
            }
        });
    }
}

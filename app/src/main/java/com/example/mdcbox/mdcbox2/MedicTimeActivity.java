package com.example.mdcbox.mdcbox2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class MedicTimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medic_time);

        tabHost= (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();//對tableHost初始化

        tabHost.addTab(tabHost.newTabSpec("tabTime").setIndicator("時鐘").setContent(R.id.tabTime));
        tabHost.addTab(tabHost.newTabSpec("tabAlarm").setIndicator("鬧鐘").setContent(R.id.tabAlarm));
        tabHost.addTab(tabHost.newTabSpec("tabTimer").setIndicator("").setContent(R.id.tabTimer));
        tabHost.addTab(tabHost.newTabSpec("tabStopWatch").setIndicator("").setContent(R.id.tabStopWatch));
    }

    private TabHost tabHost;

}

package com.example.mdcbox.mdcbox2;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TimePicker;

import java.util.Calendar;

public class AlarmView extends LinearLayout {
//    public AlarmView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
    public AlarmView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AlarmView(Context context) {
        super(context);
        init();

    }

    private void init(){
        alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);

    }
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        btnAddAlarm=(Button) findViewById(R.id.btnAddAlarm);
        lvAlarmList=(ListView) findViewById(R.id.lvAlarmList);

        adapter=new ArrayAdapter<AlarmData>(getContext(),android.R.layout.simple_list_item_1);
        lvAlarmList.setAdapter(adapter);
        readSavedAlarmList();

        adapter.add(new AlarmData(System.currentTimeMillis()));


        btnAddAlarm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //add_Alarm Buttom 觸發按鈕
                addAlarm();
            }
        });

        lvAlarmList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                new AlertDialog.Builder(getContext()).setTitle("操作選項").setItems(new CharSequence[]{"刪除"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch (which){
                            case 0:
                                deleteAlarm(position);
                                break;
                            default:
                                break;
                        }
                    }
                }).setNegativeButton("取消",null).show();

            }
        });
    }

    private void deleteAlarm(int position){
        adapter.remove(adapter.getItem(position));
    }

    private void addAlarm(){
        //TODO

        Calendar c=Calendar.getInstance();


        new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                System.out.println(">>>>>>");

                Calendar calendar= Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar.set(Calendar.MINUTE,minute);

                Calendar currentTime=calendar.getInstance();

                if (calendar.getTimeInMillis()<=currentTime.getTimeInMillis()){
                    calendar.setTimeInMillis(calendar.getTimeInMillis()+24*60*60*1000);
                }
                adapter.add(new AlarmData(calendar.getTimeInMillis()));
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000*60*5 , PendingIntent.getBroadcast(getContext(),0,new Intent(getContext(),AlarmReceiver.class),0));

            }
        },c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE),true).show();
        saveAlarmList();

    }

    private void saveAlarmList(){
        SharedPreferences.Editor editor = getContext().getSharedPreferences(AlarmView.class.getName(),Context.MODE_PRIVATE).edit();
        //getContext().getSharedPreferences(創建xml的名稱,Context.MODE_PRIVATE).edit();

        StringBuffer sb =new StringBuffer();
        //讀取時間串 append進入 sb StringBuffer
        for (int i=0;i<adapter.getCount();i++){
            sb.append(adapter.getItem(i).getTime()).append(",");
        }

        if (sb.length()>1){

        String content= sb.toString().substring(0,sb.length()-1);

        editor.putString(KEY_ALARM_LIST,content);
        System.out.println(content);
        }

        editor.commit();
    }

    private void readSavedAlarmList(){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(AlarmView.class.getName(),Context.MODE_PRIVATE);
        String content = sharedPreferences.getString(KEY_ALARM_LIST,null);

        if (content!=null){
            String[] timeStrings = content.split(",");
            for (String string : timeStrings){
                adapter.add(new AlarmData(Long.parseLong(string)));
            }
        }

    }
    private Button btnAddAlarm;
    private ListView lvAlarmList;
    private AlarmManager alarmManager;
    private static final String KEY_ALARM_LIST = "alarmlist";
    private ArrayAdapter<AlarmData> adapter;

    private static class AlarmData{
        public AlarmData(long time){
            //鬧鐘響起的時間
            this.time = time;

            date=Calendar.getInstance();
            date.setTimeInMillis(time);

//            timeLabel = date.get(Calendar.HOUR_OF_DAY)+":"+date.get(Calendar.MINUTE) ;
            timeLabel = String.format("%d月%d日 %d:%d",
                    date.get(Calendar.MONTH)+1,
                    date.get(Calendar.DAY_OF_MONTH),
                    date.get(Calendar.HOUR_OF_DAY),
                    date.get(Calendar.MINUTE)) ;
        }


        public long getTime(){
            return time;
        }

        public String getTimeLabel() {
            return timeLabel;
        }

        @Override
        public String toString() {
            return getTimeLabel();
        }

        private String timeLabel="";
        private long time = 0;
        private Calendar date;
    }



}

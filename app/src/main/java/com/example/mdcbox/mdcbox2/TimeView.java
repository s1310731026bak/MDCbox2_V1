package com.example.mdcbox.mdcbox2;

import android.content.Context;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.Handler;

import java.util.Calendar;


public class TimeView extends LinearLayout {
    public TimeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        //初始化使用 可指定stytle
        super(context, attrs, defStyleAttr);
    }
    public TimeView(Context context, @Nullable AttributeSet attrs) {
        //被初始化使用
        super(context, attrs);
    }
    public TimeView(Context context) {
        //被這個代碼使用
        super(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tvTime=findViewById(R.id.tvTime);
        tvTime.setText("Hello");

        timerHandler.sendEmptyMessage(0);
    }
    private TextView tvTime;

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);

        if (visibility==View.VISIBLE){
            timerHandler.sendEmptyMessage(0);
        }else{
            timerHandler.removeMessages(0);
        }
    }

    private void refreshTime(){
        Calendar c =Calendar.getInstance();

        tvTime.setText(String.format("%d:%d:%d",c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE),c.get(Calendar.SECOND)));
    }
    private Handler timerHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            refreshTime();

            if(getVisibility()== View.VISIBLE){
            timerHandler.sendEmptyMessageDelayed(0,1000);
            }
        }
    };
}

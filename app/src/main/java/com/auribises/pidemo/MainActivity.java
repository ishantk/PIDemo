package com.auribises.pidemo;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {


    @InjectView(R.id.textView)
    TextView txt;


    DatePickerDialog datePickerDialog;

    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

            txt.setText(i+"/"+(i1+1)+"/"+i2);

        }
    };

    TimePickerDialog timePickerDialog;
    TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            txt.setText(i+" : "+i1);
        }
    };

    /*View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);
    }

    public void clickHandler(View view){
        if(view.getId()==R.id.button){
            //showNotification();
            //showDatePicker();
            showTimePicker();
        }
    }

    void showNotification(){

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("This is Title");
        builder.setContentText("This is Text");
        builder.setSmallIcon(R.drawable.music);
        builder.setDefaults(Notification.DEFAULT_ALL); // Permission VIBRATE

        Intent intent = new Intent(MainActivity.this,HomeActivity.class);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(this,101,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(pendingIntent);

        Notification notification = builder.build();

        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(Util.NOTIFICATION_ID,notification);
    }

    void showDatePicker(){

        Calendar calendar = Calendar.getInstance();
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        int mm = calendar.get(Calendar.MONTH);
        int yy = calendar.get(Calendar.YEAR);

        datePickerDialog = new DatePickerDialog(this,dateSetListener,yy,mm,dd);
        datePickerDialog.show();

    }

    void showTimePicker(){
        Calendar calendar = Calendar.getInstance();
        int hh = calendar.get(Calendar.HOUR_OF_DAY);
        int mm = calendar.get(Calendar.MINUTE);

        timePickerDialog = new TimePickerDialog(this,timeSetListener,hh,mm,true);
        timePickerDialog.show();
    }
}

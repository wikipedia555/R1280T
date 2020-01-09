package com.example.r1280t;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.ConsumerIrManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageButton volumePlus;
    ImageButton volumeMinus;
    ImageButton  mute;
    boolean state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        volumePlus = (ImageButton) findViewById(R.id.plus);
        volumeMinus = (ImageButton) findViewById(R.id.minus);
        mute = (ImageButton) findViewById(R.id.mute);
        final Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        final String errIr = "The device is not equipped with an IR port";
        final ConsumerIrManager consumerIrManager = (ConsumerIrManager) this.getSystemService(Context.CONSUMER_IR_SERVICE);
        final int frequency = 36000;
        final int vibratemilsec = 50;
        final int delaysender = 150;
        final int[] vplus = new int[]{9000,4400, 600,550, 600,550, 600,550, 650,500, 600,1700, 600,550, 600,550, 650,550, 600,1650, 600,1650, 650,1650, 600,550, 600,550, 600,1650, 650,1650, 600,1650, 650,500, 600,550, 650,1650, 600,1700, 550,1650, 650,1650, 600,550, 600,500, 650,1650, 600,1650, 600,550, 600,550, 600,550, 600,550, 650,1650, 600,1650, 600};
        final int[] vminus = new int[]{8950,4450, 550,550, 600,600, 600,550, 550,550, 600,1700, 550,550, 600,600, 600,550, 550,1700, 550,1700, 600,1700, 550,600, 550,600, 550,1700, 550,1700, 600,1650, 600,1700, 600,550, 550,1700, 600,1700, 550,600, 550,600, 550,1700, 600,550, 600,550, 600,1650, 600,600, 550,550, 600,1650, 600,1700, 600,550, 600,1650, 600};
        final int[] mutecode = new int[]{8850,4450, 550,550, 600,600, 550,600, 550,550, 600,1700, 550,550, 600,600, 550,550, 600,1650, 600,1650, 600,1650, 600,600, 550,550, 600,1650, 600,1700, 600,1650, 600,1650, 600,1650, 600,600, 550,1700, 550,600, 600,1650, 550,650, 550,550, 550,600, 600,550, 550,1700, 550,600, 600,1650, 600,600, 550,1650, 600,1650, 600};
        SwitchCompat onOffSwitch = (SwitchCompat)  findViewById(R.id.on_off_switch);
        onOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.v("Switch State=", ""+isChecked);
                state = isChecked;
            }

        });

        mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(state == true) {
                        v.vibrate(vibratemilsec);
                    }
                    consumerIrManager.transmit(frequency, mutecode);
                }
                catch(UnsupportedOperationException e)
                {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            errIr, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        volumePlus.setOnTouchListener(new View.OnTouchListener() {

            private Handler mHandler;

            @Override public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (mHandler != null) return true;
                        mHandler = new Handler();
                        vibro.run();
                        mHandler.postDelayed(mAction, 500);
                        Log.e("DOWN","DOWN");
                        break;
                    case MotionEvent.ACTION_UP:
                        if (mHandler == null) return true;
                        mHandler.removeCallbacks(mAction);
                        Log.e("UP","UP");
                        mHandler = null;
                        break;
                }
                return false;
            }

            Runnable mAction = new Runnable() {
                @Override public void run() {
                    System.out.println("Performing action...");
                    try {
                        consumerIrManager.transmit(frequency, vplus);
                    }
                    catch(UnsupportedOperationException e)
                    {

                    }
                    mHandler.postDelayed(this, delaysender);
                }
            };

            Runnable vibro = new Runnable() {
                @Override
                public void run() {
                    Log.e("Vibrate","VIBRO");
                    Log.e("BUT","WORK");
                    try {
                        if(state == true) {
                            v.vibrate(vibratemilsec);
                        }
                        consumerIrManager.transmit(frequency, vplus);
                    }
                    catch(UnsupportedOperationException e)
                    {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                errIr, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            };

        });

        volumeMinus.setOnTouchListener(new View.OnTouchListener() {

            private Handler mHandler;

            @Override public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (mHandler != null) return true;
                        mHandler = new Handler();
                        vibro.run();
                        mHandler.postDelayed(mAction, 500);
                        Log.e("DOWN","DOWN");
                        break;
                    case MotionEvent.ACTION_UP:
                        if (mHandler == null) return true;
                        mHandler.removeCallbacks(mAction);
                        Log.e("UP","UP");
                        mHandler = null;
                        break;
                }
                return false;
            }

            Runnable mAction = new Runnable() {
                @Override public void run() {
                    System.out.println("Performing action...");
                    try {
                        consumerIrManager.transmit(frequency, vminus);
                    }
                    catch(UnsupportedOperationException e)
                    {

                    }
                    mHandler.postDelayed(this, delaysender);
                }
            };

            Runnable vibro = new Runnable() {
                @Override
                public void run() {
                    Log.e("Vibrate","VIBRO");
                    Log.e("BUT","WORK");
                    try {
                        if(state == true) {
                            v.vibrate(vibratemilsec);
                        }
                        consumerIrManager.transmit(frequency, vminus);
                    }
                    catch(UnsupportedOperationException e)
                    {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                errIr, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            };

        });


    }
}

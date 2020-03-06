package com.example.chalaan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT =4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView img=findViewById(R.id.img);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
        img.startAnimation(animation);
        TextView t2 =findViewById(R.id.t2);
        Animation animation1= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.r);
        t2.startAnimation(animation1);
        TextView t3=findViewById(R.id.t3);
        Animation animation2= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.t);
        t3.startAnimation(animation2);
        TextView t4=findViewById(R.id.t4);
        Animation animation3= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.o);
        t4.startAnimation(animation3);
        Thread myThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(4000);
                    Intent intent = new Intent(getApplicationContext(), SlidingTab.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();

    }
}

package com.sveri.progressbarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.InterruptedIOException;

public class MainActivity extends AppCompatActivity {

    ProgressBar pbCircular, pbHorizontal;
    Button btnStart;
    int progressStatus = 0;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialisers();
        eventHanlder();
    }

    // initialisers
    protected void initialisers(){

        pbCircular = findViewById(R.id.progressBarCirclular);
        pbHorizontal = findViewById(R.id.progressBarHorizontal);
        btnStart = findViewById(R.id.buttonStart);
    }

    protected void eventHanlder(){
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                while(progressStatus < 100){
                                    progressStatus += 10;
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            pbHorizontal.setProgress(progressStatus);
                                            if (progressStatus == 100){
                                                pbHorizontal.setProgress(progressStatus);
                                                Toast.makeText(MainActivity.this, "Progress Complete",
                                                        Toast.LENGTH_SHORT).show();
                                            }


                                        }
                                    });
                                    try{
                                        Thread.sleep(2000);
                                    }catch (InterruptedException e){
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                ).start();
            }
        });
    }
}
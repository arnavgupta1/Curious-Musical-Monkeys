package com.example.curiousmusicalmonkeys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.media.MediaPlayer;
import android.app.Activity;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Make a test button
    private Button testButton,b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //testButton will correspond to testButton in activity_main.xml
        testButton = findViewById(R.id.testButton);
        b0 = findViewById(R.id.button0);
        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);
        b7 = findViewById(R.id.button7);
        b8 = findViewById(R.id.button8);
        b9 = findViewById(R.id.button9);
        b10 = findViewById(R.id.button10);
        b11 = findViewById(R.id.button11);
        b12 = findViewById(R.id.button12);
        b13 = findViewById(R.id.button13);
        b14 = findViewById(R.id.button14);
        b15 = findViewById(R.id.button15);
        b0.setOnTouchListener(new OnTouchListener(){
                @Override
                public boolean onTouch(View view, MotionEvent motionevent) {
                    MediaPlayer Sound  = MediaPlayer.create(this,R.raw.t0);
                    int action = motionevent.getAction();
                    if (action == MotionEvent.ACTION_DOWN) {
                        Sound.start();
                    } else if (action == MotionEvent.ACTION_UP) {
                        Sound.stop();
                    }//end else
                    return false;
                } //end onTouch
            }
        );
        testButton.setOnClickListener(this);








    }

   @Override
    public void onClick(View v) {
        MediaPlayer Sound  = MediaPlayer.create(this,R.raw.t0);
        Sound.start();
        ((Button) v).setText("Clicked");
    }

    //This will change the screen to a sound change menu
    public void changeSound(View view){
        Intent startNewActivity = new Intent(this, changeSoundActivity.class);
        startActivity(startNewActivity);
    }
}



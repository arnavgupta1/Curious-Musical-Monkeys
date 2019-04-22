package com.example.curiousmusicalmonkeys;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.media.*;
import android.media.SoundPool;
import android.app.Activity;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //private
    //Make a test button
    private Button testButton,b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15;
    private SoundPool lib;
    private int NumStreams = 1,k;
    private int bs0,bs1,bs2,bs3,bs4,bs5,bs6,bs7,bs8,bs9,bs10,bs11,bs12,bs13,bs14,bs15;
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
        if(Build.VERSION.SDK_INT >= 21){
            lib = new SoundPool.Builder().setMaxStreams(NumStreams).build();
        }else{
            lib = new SoundPool(NumStreams, AudioManager.STREAM_MUSIC,0);
        }
        bs0 = lib.load(this,R.raw.a,1);

        bs1 = lib.load(this,R.raw.b,1);
        bs2 = lib.load(this,R.raw.badumtss,1);
        bs3 = lib.load(this,R.raw.bounce,1);
        bs4 = lib.load(this,R.raw.d,1);
        bs5 = lib.load(this,R.raw.e,1);
        bs6 = lib.load(this,R.raw.f,1);
        bs7 = lib.load(this,R.raw.g,1);
        bs8 = lib.load(this,R.raw.hc,1);
        bs9 = lib.load(this,R.raw.loser,1);
        bs10 = lib.load(this,R.raw.mc,1);
        bs11 = lib.load(this,R.raw.meow,1);
        bs12 = lib.load(this,R.raw.pun,1);
        bs13 = lib.load(this,R.raw.scifi,1);
        bs14 = lib.load(this,R.raw.suspence,1);
        bs15 = lib.load(this,R.raw.t0,1);

        testButton.setOnClickListener(this);
        b0.setOnClickListener(new SondPlay(bs0,lib));
        b1.setOnClickListener(new SondPlay(bs1,lib));
        b2.setOnClickListener(new SondPlay(bs2,lib));
        b3.setOnClickListener(new SondPlay(bs3,lib));
        b4.setOnClickListener(new SondPlay(bs4,lib));
        b5.setOnClickListener(new SondPlay(bs5,lib));
        b6.setOnClickListener(new SondPlay(bs6,lib));
        b7.setOnClickListener(new SondPlay(bs7,lib));
        b8.setOnClickListener(new SondPlay(bs8,lib));
        b9.setOnClickListener(new SondPlay(bs9,lib));
        b10.setOnClickListener(new SondPlay(bs10,lib));
        b11.setOnClickListener(new SondPlay(bs11,lib));
        b12.setOnClickListener(new SondPlay(bs12,lib));
        b13.setOnClickListener(new SondPlay(bs13,lib));
        b14.setOnClickListener(new SondPlay(bs14,lib));
        b15.setOnClickListener(new SondPlay(bs15,lib));
        k = R.raw.a;











    }
    class SondPlay implements View.OnClickListener{
        private int sound;
        private SoundPool p;
        SondPlay(int a,SoundPool pa){
            super();
            sound = a;
            p = pa;
        }
        @Override
        public void onClick(View v) {
            soundPlayAction(sound,p);
        }

    }
    private void soundPlayAction(int a,SoundPool pa)
    {
        pa.play(a,1,1,0,0,1);
    }

   @Override
    public void onClick(View v) {
        ((Button) v).setText((k +"?"));
    }

    //This will change the screen to a sound change menu
    public void changeSound(View view){
        Intent startNewActivity = new Intent(this, changeSoundActivity.class);
        startActivity(startNewActivity);
    }
}



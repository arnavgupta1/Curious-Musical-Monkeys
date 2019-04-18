package com.example.curiousmusicalmonkeys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.media.MediaPlayer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Make a test button
    private Button testButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //testButton will correspond to testButton in activity_main.xml
        testButton = findViewById(R.id.testButton);

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



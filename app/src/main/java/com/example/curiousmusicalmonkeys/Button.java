package com.example.curiousmusicalmonkeys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.media.RingtoneManager;
import android.net.Uri;
import android.media.Ringtone;

public class Button {

    private boolean isOn;
    final private MediaPlayer sound;
    private int id;

    public boolean getIsOn(){
        return isOn;
    }

    public Button(int id, MediaPlayer sound){
        this.id = id;
        this.sound = sound;
    }

    public void onClick(){
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            sound.create(, notification);
            sound.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    private float pattern[8];
    private float soundTime;


    public int getID(){}

    public void setPattern(){}

    public String getSound(){}

    public MediaPlayer getSound(){}

    public void setSound(MediaPlayer mp){}

    public float getSoundTime(){}*/



}

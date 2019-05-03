/********************************
 This file was created to help show users how to use the app.
 It is in charge of keeping the map of the sounds to the various buttons
 This page was created on 04232018

 This app is a musical manipulation tool. It Allows the user to create a custom
 array of 16 distinct sounds which the can use to make music. This app was created
 by Tony Faller, Annamali Ganesh, Arnav Gupta and George Kent-Scheller as a final Project
 for EC327 at Boston University.
 v 05012019.3
 *********************************/
//import statements
package com.example.curiousmusicalmonkeys;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.media.MediaRecorder;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;


public class record extends AppCompatActivity {
    //Variables
    private TextView Explanation;// this holds the error screen
    private EditText inp;// this hold the inValue
    //Buttons
    private ImageButton Record, Stup;
    private Button Play;
    private String Pathsbaby = "";//this holds the path for the saved
    private MediaRecorder micDrop;// this creates the mediaRecorder obj
    private final int REQUEST_PERMISION_CODE = 1000;// this handles permissions.
    private MediaPlayer mediaPlayer;// this allows for the recording playback.
    private boolean isOn = false, hasSaved = false;// these stop crashes by assuring that there are no null obj
    private int timeRec = 2000;//This assigned the sound clip maximum length. This value must be < 6000.

    //Functions

    //onPageOpen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        //this makes sure the app has recording permission.
        if (!checkPermissionFromDevice()) requestPermision();

        //This links the various pages features with their corresponding
        Explanation = findViewById(R.id.dispText);
        inp = findViewById(R.id.editText);
        Record = findViewById(R.id.Rec);
        Stup = findViewById(R.id.StopRec);
        Play = findViewById(R.id.playButton);

        //this handles the record button call.
        Record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermissionFromDevice()) {//checks for recording permission
                    Pathsbaby = Environment.getExternalStorageDirectory().getAbsolutePath() +
                            "/" + UUID.randomUUID().toString() + "MM.3gp";// this creats the path for the file. These are then available to the user on their phones internal memory.
                    setupMediaRecorder();// this creates the Media Recorder obj.

                    try {// this attempt to record with the timer.
                        micDrop.prepare();// this sets up the mic
                        micDrop.start();// this starts recording
                        Timer timer = new Timer();// this creates a new timer obj
                        TimerTask end = new endRecs();// this creates a Task obj that will be run at the end of the timer
                        timer.schedule(end, timeRec + 200);// this starts the timer and give the time. it is longer than the time to record because the end of recording takes some time.
                    } catch (IOException e) {
                        e.printStackTrace();
                        endRec();// this ends the recording
                        isOn = false;// shows that the Media Recorder has been released.
                    }

                    Toast.makeText(record.this, "Recording", Toast.LENGTH_SHORT).show();// this prints "recording" to the screen.
                } else {
                    requestPermision();// getts permission
                }
            }
        });

        //this handles the Play button call.
        Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer = new MediaPlayer();// this creates a media player
                try {// this attempts to find the new recording and play it.
                    mediaPlayer.setDataSource(Pathsbaby);
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.start();
                Toast.makeText(record.this, "Playing... ", Toast.LENGTH_SHORT).show();
            }

        });

        //This handles the Stop button to allow for shorter clips.
        Stup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOn) {
                    endRec();// ends recording
                    isOn = false;// shows that recoding is off
                    hasSaved = true;// shows tha tther is a valid recording.
                }
            }
        });


    }

    // Timer object
    class endRecs extends TimerTask {
        public void run() {// ends the recording and sets various bool states.
            if (isOn) {
                endRec();
                isOn = false;
                hasSaved = true;
            }
        }
    }

    //Terminates the recording.
    private void endRec() {
        micDrop.stop();// ends recording saving it
        micDrop.release();// releases obj
        micDrop = null; // sets it to null to allow for a second press of the record button.
    }

    //Creates the recording environment.
    private void setupMediaRecorder() {
        micDrop = new MediaRecorder();// makes a new Media Rec obj
        micDrop.setAudioSource(MediaRecorder.AudioSource.MIC);//give input source
        micDrop.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);// give output file type.
        micDrop.setMaxDuration(timeRec);// give maximum recording duration
        micDrop.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);// gives encoding protocol.
        micDrop.setOutputFile(Pathsbaby);// assigns output path.
        isOn = true;// sets bool state
    }

    //Gets permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {// runs a method to attempt code request
            case REQUEST_PERMISION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT);// provides feedback
                } else {
                    Toast.makeText(this, "Permission Denined", Toast.LENGTH_SHORT);
                }
                break;
            }
        }
    }

    //ask app for permission
    private void requestPermision() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO
        }, REQUEST_PERMISION_CODE);// asks phone for permission.
    }

    // checks current permission state
    private boolean checkPermissionFromDevice() {
        int write_external_storage_res = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int record_audio = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        return write_external_storage_res == PackageManager.PERMISSION_GRANTED && record_audio == PackageManager.PERMISSION_GRANTED;
    }

    //same method as main
    private void updateSound(Intent i, int num) {
        if (getIntent().getExtras() != null) {

            Bundle bundle = getIntent().getExtras();
            if (bundle.getString("Id" + num) == null || bundle.getString("Id" + num).equals("")) {
                i.putExtra("Id" + num, "");
            } else {
                i.putExtra("Id" + num, bundle.getString("Id" + num));
            }
        } else {
            i.putExtra("Id" + num, "");
        }
    }

    //same method as main
    private void keepSounds(Intent startNewActivity) {
        updateSound(startNewActivity, 0);
        updateSound(startNewActivity, 1);
        updateSound(startNewActivity, 2);
        updateSound(startNewActivity, 3);
        updateSound(startNewActivity, 4);
        updateSound(startNewActivity, 5);
        updateSound(startNewActivity, 6);
        updateSound(startNewActivity, 7);
        updateSound(startNewActivity, 8);
        updateSound(startNewActivity, 9);
        updateSound(startNewActivity, 10);
        updateSound(startNewActivity, 11);
        updateSound(startNewActivity, 12);
        updateSound(startNewActivity, 13);
        updateSound(startNewActivity, 14);
        updateSound(startNewActivity, 15);
        startActivity(startNewActivity);
    }

    // this method adds the new path to the proto Database
    public void changeSound(View view) {

        Intent startNewActivity = new Intent(this, MainActivity.class);
        int num;// holds input from user
        try {// attempts to convert input to int
            String s = inp.getText().toString();
            num = Integer.parseInt(s) - 1;
        } catch (Exception e) {
            num = -9;// out of bounds value.
        }

        if (hasSaved && num >= 0 && num <= 15) {// checks to make sure number is valid.

            if (hasSaved) startNewActivity.putExtra("Id" + num, Pathsbaby);// adds new sound to id

            // same code from keepSounds but checks to make sure its not overwriting the chosen id.
            if (num != 0) updateSound(startNewActivity, 0);
            if (num != 1) updateSound(startNewActivity, 1);
            if (num != 2) updateSound(startNewActivity, 2);
            if (num != 3) updateSound(startNewActivity, 3);
            if (num != 4) updateSound(startNewActivity, 4);
            if (num != 5) updateSound(startNewActivity, 5);
            if (num != 6) updateSound(startNewActivity, 6);
            if (num != 7) updateSound(startNewActivity, 7);
            if (num != 8) updateSound(startNewActivity, 8);
            if (num != 9) updateSound(startNewActivity, 9);
            if (num != 10) updateSound(startNewActivity, 10);
            if (num != 11) updateSound(startNewActivity, 11);
            if (num != 12) updateSound(startNewActivity, 12);
            if (num != 13) updateSound(startNewActivity, 13);
            if (num != 14) updateSound(startNewActivity, 14);
            if (num != 15) updateSound(startNewActivity, 15);


            if (isOn) endRec();// makes sure to end recoding
            if (isOn) isOn = false;
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }// releases mediaPlayer

            startActivity(startNewActivity);// Page switch
        } else if (num == -9) {// if error code then it automatically exits to home page.
            keepSounds(startNewActivity);
            startActivity(startNewActivity);
        } else {
            Explanation.setText("Error Invalid input!");// give user another change to enter valid input.
        }
    }
}
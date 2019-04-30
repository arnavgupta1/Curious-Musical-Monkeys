package com.example.curiousmusicalmonkeys;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class record extends AppCompatActivity {
    ImageButton Record,Stup;
    String Pathsbaby = "";
    MediaRecorder micDrop;
    final int REQUEST_PERMISION_CODE = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        if(checkPermissionFromDevice()){}

        Record = findViewById(R.id.Rec);
        Stup = findViewById(R.id.StopRec);
        Record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
       Stup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private boolean checkPermissionFromDevice() {
        int write_external_storage_res = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int record_audio = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        return write_external_storage_res == PackageManager.PERMISSION_GRANTED && record_audio == PackageManager.PERMISSION_GRANTED;
    }
}

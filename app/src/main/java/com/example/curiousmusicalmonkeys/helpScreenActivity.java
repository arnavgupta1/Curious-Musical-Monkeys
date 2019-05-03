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

package com.example.curiousmusicalmonkeys;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class helpScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_screen);
    }

    // this method is used to keep Sound mapping. See MainAcitivity.java for full documentation
    public void updateSound(Intent i, int num) {
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

    // this method is used to keep Sound mapping. See MainAcitivity.java for full documentation
    public void keepSounds(Intent startNewActivity) {
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
    // this method switches to the main page.
    public void backToStart(View view) {
        Intent startNewActivity = new Intent(this, MainActivity.class);
        keepSounds(startNewActivity);
        startActivity(startNewActivity);
    }
}

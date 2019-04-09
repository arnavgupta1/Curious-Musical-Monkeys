package com.example.curiousmusicalmonkeys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
        ((Button) v).setText("Clicked");
    }
}



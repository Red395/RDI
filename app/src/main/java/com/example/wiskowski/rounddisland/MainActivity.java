package com.example.wiskowski.rounddisland;
import android.content.res.AssetManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
   // Private currentView = R.layout.activity_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  R.layout.activity_main.
        Button button = (Button) findViewById(R.id.NextButton);

        AssetManager am = getAssets();
        String[] files = {};

        try { files = am.list("Directory"); }
        catch (IOException e) {}

        final String[] fileList = files;

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                TextView text = (TextView)findViewById(R.id.NextText);
                //FileReaderMechanics test = new FileReaderMechanics();
                text.setText(fileList[0]);
            }
        });

    }
}

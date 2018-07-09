package com.example.wiskowski.rounddisland;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
   // Private currentView = R.layout.activity_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Integer temp = 0;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  R.layout.activity_main.
        Button button = (Button) findViewById(R.id.NextButton);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                TextView text = (TextView)findViewById(R.id.NextText);
                FileReaderMechanics test = new FileReaderMechanics();
                text.setText(test.getFiles()[0].getName());
            }
        });

    }
}

package com.example.wiskowski.rounddisland;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.security.PrivateKey;

public class MainActivity extends AppCompatActivity {
   // Private currentView = R.layout.activity_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  R.layout.activity_main.
        Button button = (Button) findViewById(R.id.NextButton);
        button.setOnClickListener(new View.OnClickListener(){
            Integer Position = 0;
            @Override
            public void onClick(View view){
                TextView text = (TextView)findViewById(R.id.NextText);
                String[] LocationNames = {"CityHall","TLF HQ", "Matt's House"};
                text.setText(LocationNames[Position]);
                if (Position >= LocationNames.length-1){
                    Position = 0;
                } else {
                    Position++;
                }

            }
        });

    }
}

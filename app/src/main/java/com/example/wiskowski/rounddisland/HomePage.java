package com.example.wiskowski.rounddisland;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        mContext = this;
        scanCodeButton();
        startGameButton();
    }

    private void scanCodeButton() {
        Button button = findViewById(R.id.ScanCodeButton);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // change "HomePage.class" to the desired class
                startActivity(new Intent(getApplicationContext(), QRReader.class));
            }
        });
    }

    // TODO: 7/12/2018   Change Directory.class to main game page
    private void startGameButton() {
        Button button = findViewById(R.id.StartGameButton);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Button button = (Button) view;
                startActivity(new Intent(getApplicationContext(), Directory.class)); // change to main game page
            }
        });
    }
}

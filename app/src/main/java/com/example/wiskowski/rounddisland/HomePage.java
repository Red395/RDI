package com.example.wiskowski.rounddisland;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        Context mContext = this;
    }

    private void scanCodeButton() {
        Button button = (Button) findViewById(R.id.ScanCodeButton);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Button button = (Button) view;
                // change "HomePage.class" to the desired class
                startActivity(new Intent(getApplicationContext(), HomePage.class));
            }
        });
    }

    private void startGameButton() {
        Button button = (Button) findViewById(R.id.StartGameButton);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Button button = (Button) view;
                startActivity(new Intent(getApplicationContext(), HomePage.class));
            }
        });
    }
}

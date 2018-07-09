package com.example.wiskowski.rounddisland;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        Button button = (Button) findViewById(R.id.NextButton);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                TextView text = (TextView)findViewById(R.id.NextText);
                FileReaderMechanics fmReader = new FileReaderMechanics(mContext);
                text.setText(fmReader.getFiles()[0]);
            }
        });

    }
}

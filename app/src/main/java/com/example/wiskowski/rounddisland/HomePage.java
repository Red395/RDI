package com.example.wiskowski.rounddisland;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.zxing.integration.android.IntentIntegrator;

import java.io.IOException;

public class HomePage extends AppCompatActivity {
    private IntentIntegrator qrScan;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // needed to check for permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, 1);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        // new scanner object
        qrScan = new IntentIntegrator(this);
        mContext = this;
        scanCodeButton();
        startGameButton();
    }

    private void scanCodeButton() {
        Button button = findViewById(R.id.ScanCodeButton);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // run the qrScanner
                qrScan.initiateScan();
            }
        });
    }

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        QRReader codeRunner = new QRReader(); // calls the QRReader class to deal with the result of the scan
        try {
            codeRunner.onActivityResult(requestCode, resultCode, data, this);
        } catch (IOException e){e.printStackTrace();};
    }
}

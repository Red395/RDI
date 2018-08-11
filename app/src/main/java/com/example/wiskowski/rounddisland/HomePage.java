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
import android.widget.Toast;

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
        directoryButton();
      //  DatabaseConnection dbc = new DatabaseConnection(this, null);
       // dbc.ClearDatabase();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        QRReader cr = new QRReader(); // calls the QRReader class to deal with the result of the scan
        try {
            Intent i = new Intent(cr.oar(requestCode, resultCode, data, mContext));
            if (i.resolveActivity(getPackageManager()) != null)
                startActivity(i);
        } catch (IOException e){e.printStackTrace();}
    }

    private void startGameButton() {
        Button button = findViewById(R.id.StartGameButton);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Button button = (Button) view;
                startActivity(new Intent(getApplicationContext(), HuntPage.class)); // change to main game page
            }
        });
    }

    private void restetDBButton(){
        Button button = findViewById(R.id.ResetDB);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Button button = (Button) view;
                DatabaseConnection dbc = new DatabaseConnection(mContext, null);
                dbc.ClearDatabase();

            }
        });
    }

    private void directoryButton(){
        Button button = findViewById(R.id.DirectoryButton);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Button button = (Button) view;
                startActivity(new Intent(getApplicationContext(), Directory.class)); // change to main game page
            }
        });
    }
}
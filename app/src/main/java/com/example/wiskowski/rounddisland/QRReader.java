package com.example.wiskowski.rounddisland;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QRReader extends AppCompatActivity implements View.OnClickListener {
    // view objects
    private Button ScanCodeButton;

    // qr reader
    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // checks to see if the app has camera permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, 1);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        ScanCodeButton = findViewById(R.id.ScanCodeButton);

        ScanCodeButton.setOnClickListener(this);

        qrScan = new IntentIntegrator(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "No QR Code Found", Toast.LENGTH_LONG).show();
            } else {
                try {
                    String testText = result.getContents();
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }

                // Code for calling LmkInformation here
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onClick(View view) {
        qrScan.initiateScan();
    }
}

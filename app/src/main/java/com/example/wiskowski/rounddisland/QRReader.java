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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class QRReader extends AppCompatActivity implements View.OnClickListener {
    // view objects
    private Button ScanCodeButton;
    private TextView TestTextUpdate;

    private String updateText;

    // qr reader
    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // checks to see if the app has camera permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, 1);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.scancode);

        ScanCodeButton = findViewById(R.id.ScanCodeButton);
        TestTextUpdate = findViewById(R.id.TestText);

        ScanCodeButton.setOnClickListener(this);

        //ImageView qrViewer = findViewById(R.id.qrCode);

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
                    JSONObject obj = new JSONObject(result.getContents());
                    updateText = result.getContents();

                } catch (JSONException e) {
                    e.printStackTrace();
                    updateText = result.getContents();
                }

                TestTextUpdate.setText(updateText);
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

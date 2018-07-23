package com.example.wiskowski.rounddisland;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QRReader extends AppCompatActivity {

    public void onActivityResult(int requestCode, int resultCode, Intent data, Context page) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(page, "No QR Code Found", Toast.LENGTH_LONG).show();
            } else {
                try {
                    String testText = result.getContents(); // tries to convert the result to a string
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }

                // Code for calling LmkInformation here
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}

package com.example.wiskowski.rounddisland;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class QRReader extends AppCompatActivity {

    public void onActivityResult(int requestCode, int resultCode, Intent data, Context page) throws IOException {
        String  returnText = "No QR Code Found";

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(page, returnText, Toast.LENGTH_LONG).show();
            } else {
                try {
                    String testText = result.getContents(); // tries to convert the result to a string
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }

                // Code for calling LmkInformation here
                File curFile = new File("/assets/Directory/" + returnText);
                BufferedReader curReader = new BufferedReader(new FileReader(curFile));

                String line;
                line = curReader.readLine();
                String Name = line;
                line = curReader.readLine();
                String PictureNames = line;

                Intent i = new Intent(getApplicationContext(), LmkInformation.class);
                i.putExtra("PICTURE_NAME", PictureNames);
                i.putExtra("LMK_NAME", Name);
                startActivity(i);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}

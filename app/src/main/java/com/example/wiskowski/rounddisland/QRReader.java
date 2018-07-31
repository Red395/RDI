package com.example.wiskowski.rounddisland;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class QRReader extends AppCompatActivity {

    public Intent onActivityResult(int requestCode, int resultCode, Intent data, Context page) throws IOException {
        String returnText = "No QR Code Found";

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(page, returnText, Toast.LENGTH_LONG).show();
            } else {
                try {
                    returnText = result.getContents(); // tries to convert the result to a string
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }

                try {
                    // Code for calling LmkInformation here
                    FileReaderMechanics fm = new FileReaderMechanics(page);
                    ArrayList<String> file = fm.getTextFileContents(returnText);

                    // Saves the landmark name and picture names
                    String Name = file.get(0);
                    String PictureNames = file.get(1);

                    // Call new LmkInformation class instance with the name and picture addresses
                    Intent i = new Intent(page, LmkInformation.class);
                    i.putExtra("PICTURE_NAME", PictureNames);
                    i.putExtra("LMK_NAME", Name);
                    return i;

                } catch (FileNotFoundException e) {
                    Toast.makeText(page, "Invalid QR Code",  Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
        return new Intent();
    }
}

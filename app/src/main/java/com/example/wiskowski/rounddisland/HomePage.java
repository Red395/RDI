package com.example.wiskowski.rounddisland;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.zxing.integration.android.IntentIntegrator;

import java.io.IOException;

public class HomePage extends AppCompatActivity {
    // BEFORE UPLOADING DO "gradlew clean" IN TERMINAL

    private IntentIntegrator qrScan;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // needed to check for permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        // use drawables
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.rdihorizontalwhite);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.sq);

        ImageView titleImage = (ImageView) findViewById(R.id.imageView3);
        titleImage.setImageResource(R.drawable.alexandra_battery);
        titleImage.getLayoutParams().height = Resources.getSystem().getDisplayMetrics().heightPixels / 3;

        // new scanner object
        qrScan = new IntentIntegrator(this);
        mContext = this;
        scanCodeButton();
        startGameButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.buttons, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Landmarks:
                startActivity(new Intent(getApplicationContext(), Directory.class));
                return true;
            case R.id.Home:
                startActivity(new Intent(getApplicationContext(), HomePage.class));
                return true;
            case R.id.Challenges:
                startActivity(new Intent(getApplicationContext(), HuntPage.class));
                return true;
            case R.id.Contact:
                startActivity(new Intent(getApplicationContext(), ContactUs.class));
                return true;
            case R.id.FAQ:
                startActivity(new Intent(getApplicationContext(), FAQ.class));
                return true;
            case R.id.TermsandConditions:
                startActivity(new Intent(getApplicationContext(), TermsandConditions.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        QRReader cr = new QRReader(); // calls the QRReader class to deal with the result of the scan
        try {
            Intent i = new Intent(cr.oar(requestCode, resultCode, data, mContext));
            if (i.resolveActivity(getPackageManager()) != null)
                startActivity(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                startActivity(new Intent(getApplicationContext(), HowtoPlay.class)); // change to main game page
            }
        });
    }
}
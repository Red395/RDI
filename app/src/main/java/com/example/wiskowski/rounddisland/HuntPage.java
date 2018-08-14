package com.example.wiskowski.rounddisland;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Debug;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Space;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class HuntPage extends AppCompatActivity {
    private IntentIntegrator qrScan;
    private boolean allCompleted=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.directory);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.rdihorizontalwhite);

        addTitle();
        createAllRows();
        createScanButton();


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
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void createAllRows(){
        FileReaderMechanics fmReader = new FileReaderMechanics(this);
        String[] allFileNames = fmReader.getFiles();
        PathGen pg = new PathGen(Calendar.getInstance(), allFileNames);
        ArrayList<String> FileStringRows= null;
        TableLayout tblLandmarksDisplay = findViewById(R.id.TblAllLandmarks);
        tblLandmarksDisplay.removeAllViews();
        DatabaseConnection dbc = new DatabaseConnection(this, null);
        allCompleted=true;

        for (String eachFileName : pg.getLocations()) {
            try {
                FileStringRows = fmReader.getTextFileContents(eachFileName);
                String date = dbc.getVisitDate(FileStringRows.get(0));
                Boolean isFound = false;
                Log.d("dateFound=", date);
                if (PathGen.isInTime(date)){
                   isFound=true;
                } else {
                    allCompleted=false;
                }


                createImageRow(tblLandmarksDisplay, FileStringRows.get(0), FileStringRows.get(1).split(","), eachFileName, isFound);
            }catch (Exception e){ Log.d("HuntPage:createAllRows",e.toString());}
        }
    }

    private void createImageRow(TableLayout tbl, final String Name, final String PictureNames[], final String FileName, final Boolean isFound){
        try {
            TableRow row = new TableRow(this);
            TextView rowText = new TextView(this);
            ImageView rowImg = new ImageView(this);
            ImageView checkImg = new ImageView(this);
            LinearLayout textLayout = new LinearLayout(this);
            LinearLayout imageTextLayout = new LinearLayout(this);
            LinearLayout checkLayout = new LinearLayout(this);
            Space checkHSpace = new Space(this);
            RelativeLayout rowLayout = new RelativeLayout(this);
            Space textHSpace = new Space(this);
            Space textVSpace = new Space(this);
            Space pictIndentSpace = new Space(this);
          //  final Button buttonToNextPage = new Button(this);

            RelativeLayout pictureLayout = new RelativeLayout(this);

            textLayout.getLayoutParams();

            rowText.setText(Name);
            int imgId = rowImg.getContext().getResources().getIdentifier(PictureNames[0], "drawable", rowImg.getContext().getPackageName());
            int checkImgId = rowImg.getContext().getResources().getIdentifier("check", "drawable", rowImg.getContext().getPackageName());
            rowImg.setImageResource(imgId);
            checkImg.setImageResource(checkImgId);
            textLayout.setOrientation(LinearLayout.VERTICAL);
            textLayout.addView(textVSpace);
            textLayout.addView(rowText);
            imageTextLayout.addView(pictIndentSpace);
            pictureLayout.addView(rowImg);

            checkLayout.addView(checkHSpace);
            checkLayout.addView(checkImg);
            if (isFound){
                pictureLayout.addView(checkLayout);
            }
            imageTextLayout.addView(pictureLayout);
            imageTextLayout.addView(textHSpace);
            imageTextLayout.addView(textLayout);
            rowLayout.addView(imageTextLayout);
           // rowLayout.addView(buttonToNextPage);

            row.addView(rowLayout);

            tbl.addView(row);

            rowImg.requestLayout();
            rowImg.getLayoutParams().height = 400;
            rowImg.getLayoutParams().width = 400;
            textHSpace.getLayoutParams().width=50;
            textVSpace.getLayoutParams().height=200;
            checkHSpace.getLayoutParams().width=50;
            pictIndentSpace.getLayoutParams().width=20;
           // buttonToNextPage.getBackground().setAlpha(0);
           // buttonToNextPage.getLayoutParams().height=400;
           // buttonToNextPage.getLayoutParams().width=row.getLayoutParams().width;

            checkImg.requestLayout();
            rowImg.requestLayout();
            checkImg.getLayoutParams().height=300;
            checkImg.getLayoutParams().width=300;
            checkHSpace.getLayoutParams().height=20;
            //rowText.getLayoutParams().width=row.getLayoutParams().width-100;

          /*  buttonToNextPage.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view){
                    Intent i = new Intent(getApplicationContext(), LmkInformation.class);
                    i.putExtra("PICTURE_NAME", PictureNames);

                    i.putExtra("LMK_NAME", Name);
                    i.putExtra("LMK_FILENAME", FileName);
                    startActivity(i);
                }
            });*/
        } catch (Exception e) {   }
    }

    private void createScanButton(){
        if(!allCompleted){
            qrScan = new IntentIntegrator(this);
            Button scanButton = new Button(this);
            scanButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    // run the qrScanner
                    qrScan.initiateScan();
                }
            });
            LinearLayout pageLayout = findViewById(R.id.pageLayout);
            scanButton.setText("Scan a Code");

            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);

            scanButton.setBackgroundResource(R.drawable.buttonripple);
            scanButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
            scanButton.setHeight(300);
            scanButton.setWidth(600);
            pageLayout.addView(scanButton);
            ScrollView sv = findViewById(R.id.scrollWindow);
            sv.getLayoutParams().height = sv.getHeight()-110;
        } else {
            DatabaseConnection dbc = new DatabaseConnection(this, null);

            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);

            Button scanButton = new Button(this);
            LinearLayout pageLayout = findViewById(R.id.pageLayout);
            scanButton.setText(dbc.getCode(PathGen.getCurrentWeek()));

            scanButton.setBackgroundResource(R.drawable.buttonripple);
            scanButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 36);
            scanButton.setLayoutParams(new TableLayout.LayoutParams(160,200));
            pageLayout.addView(scanButton);
            ScrollView sv = findViewById(R.id.scrollWindow);
            sv.getLayoutParams().height = sv.getHeight()-110;

            if(!dbc.hasShown(PathGen.getCurrentWeek())){
                KeyGen keyGenerator = new KeyGen();
                //dbc.addCode(keyGenerator.getKey(),PathGen.getCurrentWeek());
                //display congrads
                scanButton.setText(keyGenerator.getKey());

                Emailer em = new Emailer(this, EmailConfig.EMAIL, keyGenerator.getKey(), keyGenerator.getKey());
                em.checkConnection();
                if (em.getConnected()) {em.execute();}
                startActivity(new Intent(getApplicationContext(), WinPage.class));
            }
        }

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        QRReader cr = new QRReader(); // calls the QRReader class to deal with the result of the scan
        try {
            Intent i = new Intent(cr.oar(requestCode, resultCode, data, this));
            if (i.resolveActivity(getPackageManager()) != null)
                startActivity(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void addTitle(){
        TextView tv = findViewById(R.id.PageTitle);
        tv.setText("The Weekly Hunt");
        tv.setTextSize(36);
    }
}

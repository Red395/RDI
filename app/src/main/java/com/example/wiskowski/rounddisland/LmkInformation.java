package com.example.wiskowski.rounddisland;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;

public class LmkInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("LMK", "CREATED");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.lmk_information);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.rdihorizontalwhite);

        String PictureNames[] = getIntent().getStringArrayExtra("PICTURE_NAME");
        TextView HeaderText = findViewById(R.id.textView3);
        try {
            HeaderText.setText(getIntent().getStringExtra("LMK_NAME"));

        }catch(Exception e){}
        TextView DescriptionText = findViewById(R.id.Description);

        FileReaderMechanics fmReader = new FileReaderMechanics(this, "Descriptions");
        try {
            for (String eachLineOf : fmReader.getTextFileContents(getIntent().getStringExtra("LMK_FILENAME"))){
                DescriptionText.setText(DescriptionText.getText()+ eachLineOf + "\n");
            }
        }catch (IOException e){}

        for (String eachPictureName : PictureNames){
            addImgToCar(eachPictureName);
        }

        HorizontalScrollView cs = findViewById(R.id.cScrollView);
        cs.smoothScrollTo(cs.getLayoutParams().width-50,0);

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

    private void addImgToCar (String pictureName){

        LinearLayout lL = new LinearLayout(this);
        ImageView rowImg = new ImageView(this);
        LinearLayout hs = findViewById((R.id.carousel));
        lL.setOrientation(LinearLayout.HORIZONTAL);

        int imgId = rowImg.getContext().getResources().getIdentifier(pictureName, "drawable", rowImg.getContext().getPackageName());
        if (imgId == 0) {
            Log.d("Image ids", "Got " + pictureName);
        }
        rowImg.setImageResource(imgId);

        hs.addView(rowImg);

        rowImg.requestLayout();
        rowImg.getLayoutParams().width = Resources.getSystem().getDisplayMetrics().widthPixels;
        rowImg.getLayoutParams().height = 800;
        hs.getLayoutParams().height=rowImg.getLayoutParams().height;
    }
}
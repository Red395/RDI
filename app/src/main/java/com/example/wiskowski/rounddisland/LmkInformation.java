package com.example.wiskowski.rounddisland;

import android.graphics.Picture;
import android.os.Bundle;
import android.app.Activity;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

public class LmkInformation extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lmk_information);

        String PictureNames[] = getIntent().getStringExtra("PICTURE_NAME").split(",");
        TextView HeaderText = findViewById(R.id.textView3);
        HeaderText.setText(getIntent().getStringExtra("LMK_NAME"));

        FileReaderMechanics fmReader = new FileReaderMechanics(this, getIntent().getStringExtra("LMK_FILENAME"));

        for (String eachPictureName : PictureNames){
            addImgToCar(eachPictureName);
        }

        ImageView Picture = findViewById(R.id.imageView2);
        Picture.setImageResource(Picture.getContext().getResources().getIdentifier(PictureNames[0], "drawable", Picture.getContext().getPackageName()));
    }

    private void addImgToCar (String pictureName){
        HorizontalScrollView hs = findViewById((R.id.carousel));


    }
}

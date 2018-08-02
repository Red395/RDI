package com.example.wiskowski.rounddisland;

import android.graphics.Picture;
import android.os.Bundle;
import android.app.Activity;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LmkInformation extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lmk_information);

        String PictureNames[] = getIntent().getStringArrayExtra("PICTURE_NAME");
        TextView HeaderText = findViewById(R.id.textView3);
        HeaderText.setText(getIntent().getStringExtra("LMK_NAME"));

//        FileReaderMechanics fmReader = new FileReaderMechanics(this, getIntent().getStringExtra("LMK_FILENAME"));

        for (String eachPictureName : PictureNames){
            addImgToCar(eachPictureName);
        }
    }

    private void addImgToCar (String pictureName){

        TextView tx = findViewById(R.id.textView3);
        tx.setText(tx.getText() +", "+ pictureName);

        LinearLayout lL = new LinearLayout(this);
        ImageView rowImg = new ImageView(this);
        LinearLayout hs = findViewById((R.id.carousel));
        lL.setOrientation(LinearLayout.HORIZONTAL);

        int imgId = rowImg.getContext().getResources().getIdentifier(pictureName, "drawable", rowImg.getContext().getPackageName());
        rowImg.setImageResource(imgId);
        hs.addView(rowImg);

      //  rowImg.requestLayout();
      //  rowImg.getLayoutParams().height = 400;
      //  rowImg.requestLayout();
    }
}

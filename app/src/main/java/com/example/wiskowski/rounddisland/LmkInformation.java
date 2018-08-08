package com.example.wiskowski.rounddisland;

import android.content.res.Resources;
import android.graphics.Picture;
import android.os.Bundle;
import android.app.Activity;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;

public class LmkInformation extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lmk_information);

        String PictureNames[] = getIntent().getStringArrayExtra("PICTURE_NAME");
        TextView HeaderText = findViewById(R.id.textView3);
        HeaderText.setText(getIntent().getStringExtra("LMK_NAME"));

        TextView DescriptionText = findViewById(R.id.Description);

        FileReaderMechanics fmReader = new FileReaderMechanics(this, "Descriptions");
        try {
            for (String eachLineOf : fmReader.getTextFileContents(getIntent().getStringExtra("LMK_FILENAME"))){
                DescriptionText.setText(DescriptionText.getText()+ eachLineOf);
            }
        }catch (IOException e){}

        for (String eachPictureName : PictureNames){
            addImgToCar(eachPictureName);
        }
    }

    private void addImgToCar (String pictureName){

        LinearLayout lL = new LinearLayout(this);
        ImageView rowImg = new ImageView(this);
        LinearLayout hs = findViewById((R.id.carousel));
        lL.setOrientation(LinearLayout.HORIZONTAL);

        int imgId = rowImg.getContext().getResources().getIdentifier(pictureName, "drawable", rowImg.getContext().getPackageName());
        rowImg.setImageResource(imgId);

        hs.addView(rowImg);

        rowImg.requestLayout();
        rowImg.getLayoutParams().width =400;
      //  rowImg.getLayoutParams().height= ViewGroup.LayoutParams.MATCH_PARENT;
        rowImg.getLayoutParams().width = Resources.getSystem().getDisplayMetrics().widthPixels;
        hs.getLayoutParams().height=rowImg.getLayoutParams().height;
    }
}

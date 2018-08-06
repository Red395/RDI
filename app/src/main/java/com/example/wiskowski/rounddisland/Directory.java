package com.example.wiskowski.rounddisland;
import android.content.Context;
import android.content.Intent;
import android.graphics.Picture;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;
import java.util.ArrayList;

public class Directory extends AppCompatActivity {

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.directory);

        createAllRows();
    }

    private void createAllRows(){
        FileReaderMechanics fmReader = new FileReaderMechanics(this);
        String[] allFileNames = fmReader.getFiles();
        ArrayList<String> FileStringRows= null;
        TableLayout tblLandmarksDisplay = findViewById(R.id.TblAllLandmarks);
        tblLandmarksDisplay.removeAllViews();

        for (String eachFileName : allFileNames) {
            try {
                FileStringRows = fmReader.getTextFileContents(eachFileName);
                createImageRow(tblLandmarksDisplay, FileStringRows.get(0), FileStringRows.get(1).split(","), eachFileName);
            }catch (Exception e){}
        }
    }

    private void createImageRow(TableLayout tbl, final String Name, final String PictureNames[], final String FileName){
        try {
            TableRow row = new TableRow(this);
            TextView rowText = new TextView(this);
            ImageView rowImg = new ImageView(this);
            LinearLayout textLayout = new LinearLayout(this);
            LinearLayout imageTextLayout = new LinearLayout(this);
            RelativeLayout rowLayout = new RelativeLayout(this);
            Space textHSpace = new Space(this);
            Space textVSpace = new Space(this);
            Space pictIndentSpace = new Space(this);
            final Button buttonToNextPage = new Button(this);

            textLayout.getLayoutParams();


            rowText.setText(Name);
            int imgId = rowImg.getContext().getResources().getIdentifier(PictureNames[0], "drawable", rowImg.getContext().getPackageName());
            rowImg.setImageResource(imgId);
            textLayout.setOrientation(LinearLayout.VERTICAL);
            textLayout.addView(textVSpace);
            textLayout.addView(rowText);
            imageTextLayout.addView(pictIndentSpace);
            imageTextLayout.addView(rowImg);
            imageTextLayout.addView(textHSpace);
            imageTextLayout.addView(textLayout);
            rowLayout.addView(imageTextLayout);
            rowLayout.addView(buttonToNextPage);

            row.addView(rowLayout);

            tbl.addView(row);

            rowImg.requestLayout();
            rowImg.getLayoutParams().height = 400;
            rowImg.getLayoutParams().width = 400;
            textHSpace.getLayoutParams().width=50;
            textVSpace.getLayoutParams().height=200;
            pictIndentSpace.getLayoutParams().width=20;
            buttonToNextPage.getBackground().setAlpha(0);
            buttonToNextPage.getLayoutParams().height=400;
            buttonToNextPage.getLayoutParams().width=row.getLayoutParams().width;
            //rowText.getLayoutParams().width=row.getLayoutParams().width-100;

            buttonToNextPage.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view){
                    Intent i = new Intent(getApplicationContext(), LmkInformation.class);
                    i.putExtra("PICTURE_NAME", PictureNames);
                    i.putExtra("LMK_NAME", Name);
                    i.putExtra("LMK_FILENAME", FileName);
                    startActivity(i);
                }
            });
        } catch (Exception e) {   }
    }
}

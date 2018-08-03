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

        mContext = this;

        testButton();
        nextPageButton();
        createAllRows();
    }

    private void testButton() {
        Button button = (Button) findViewById(R.id.NextButton);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                TextView text = (TextView)findViewById(R.id.NextText);
                FileReaderMechanics fmReader = new FileReaderMechanics(mContext);
                createAllRows();
            }
        });
    }


    /** Code for the button that will open another page of the app
     */
    private void nextPageButton() {
        Button button = (Button) findViewById(R.id.nextPageButton);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Button button = (Button) view;
                // change "Page2Activity.class" to the desired class
                //startActivity(new Intent(getApplicationContext(), HomePage.class));
                finish();
            }
        });
    }


    private void createAllRows(){
        FileReaderMechanics fmReader = new FileReaderMechanics(mContext);
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
            final Button buttonToNextPage = new Button(this);

            textLayout.getLayoutParams();


            rowText.setText(Name + "\n|"+PictureNames[0]);
            int imgId = rowImg.getContext().getResources().getIdentifier(PictureNames[0], "drawable", rowImg.getContext().getPackageName());
            rowImg.setImageResource(imgId);
            textLayout.setOrientation(LinearLayout.VERTICAL);
            textLayout.addView(textVSpace);
            textLayout.addView(rowText);
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
            buttonToNextPage.getBackground().setAlpha(100);
            buttonToNextPage.getLayoutParams().height=400;
            buttonToNextPage.getLayoutParams().width=800;

            buttonToNextPage.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view){
                    Intent i = new Intent(getApplicationContext(), LmkInformation.class);
                    i.putExtra("PICTURE_NAME", PictureNames);
                    i.putExtra("LMK_NAME", FileName/*Name*/);
                    i.putExtra("LMK_FILENAME", FileName);
                    startActivity(i);
                }
            });
        } catch (Exception e) {   }
    }
}

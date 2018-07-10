package com.example.wiskowski.rounddisland;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        testButton();
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


    private void createAllRows(){
        FileReaderMechanics fmReader = new FileReaderMechanics(mContext);
        String[] allFileNames = fmReader.getFiles();
        ArrayList<String> FileStringRows= null;
        TableLayout tblLandmarksDisplay = findViewById(R.id.TblAllLandmarks);
        tblLandmarksDisplay.removeAllViews();

        for (String eachFileName : allFileNames) {
            try {
                FileStringRows = fmReader.getTextFileContents(eachFileName);
                createImageRow(tblLandmarksDisplay, FileStringRows.get(0), FileStringRows.get(1));
            }catch (Exception e){}
        }
    }

    private void createImageRow(TableLayout tbl,String Name, String PictureName){
        try {
            TableRow row = new TableRow(this);
            TextView rowText = new TextView(this);
            ImageView rowImg = new ImageView(this);
            rowText.setText(Name);
            int imgId = rowImg.getContext().getResources().getIdentifier(PictureName, "drawable", rowImg.getContext().getPackageName());
            rowImg.setImageResource(imgId);
            row.addView(rowText);
            row.addView(rowImg);
            tbl.addView(row);
            rowImg.requestLayout();
            rowImg.getLayoutParams().height = 400;
            rowImg.getLayoutParams().width = 400;
        } catch (Exception e) {   }
    }
}

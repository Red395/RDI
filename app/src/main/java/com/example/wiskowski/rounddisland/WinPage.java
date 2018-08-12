package com.example.wiskowski.rounddisland;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import pl.droidsonroids.gif.GifTextView;

public class WinPage extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.winpage);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.rdihorizontalwhite);
    }

    private void spawnFireworks() {
        GifTextView gif = new GifTextView(this);
        //gif.setX();
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
}

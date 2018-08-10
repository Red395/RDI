package com.example.wiskowski.rounddisland;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQuery;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DatabaseConnection extends  SQLiteOpenHelper{

    public DatabaseConnection(Context c, SQLiteDatabase.CursorFactory factory) {
        super(c, "tbl_DateVisited", factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS tbl_DateVisited (LmkName STRING PRIMARY KEY, VisitDate DATE)");
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

    public void addVisitDate(String LmkName){
        ContentValues dbValues = new ContentValues();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT OR REPLACE INTO tbl_DateVisited(LmkName, VisitDate) VALUES ('"+LmkName+"', '"+df.format(Calendar.getInstance().getTime())+"')");
    }

    public String getVisitDate(String LmkName){
        SQLiteDatabase db = this.getWritableDatabase();
        String returnDate ="";
        Cursor c = db.rawQuery("SELECT * FROM  tbl_DateVisited WHERE LmkName='"+LmkName+"'",null);
        //Cursor c = db.rawQuery("SELECT * FROM  tbl_DateVisited",null);
      //  Log.d("SQL:","SELECT * FROM  tbl_DateVisited WHERE LmkName='"+LmkName+"'");
      //  Log.d("compare of Names", "["+LmkName+"|");
      //  Log.d("getVisitDate:", "Size:"+c.getCount());
       //Log.d("firstOne?",c.getString(0));
        //c.moveToPosition(0);
        while(c.moveToNext()){
            Log.d("getVisitDate:", c.getString(0)+"/"+LmkName+"||"+c.getCount());
           returnDate= c.getString(1);
        }
        return returnDate;
    }



}

package com.example.wiskowski.rounddisland;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class DatabaseConnection extends  SQLiteOpenHelper{

    public DatabaseConnection(Context c, SQLiteDatabase.CursorFactory factory) {
        super(c, "tbl_DateVisited", factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS tbl_DateVisited (LmkName STRING PRIMARY KEY, VisitDate STRING)");
        db.execSQL("CREATE TABLE IF NOT EXISTS tbl_Completed (ChalDate STRING PRIMARY KEY, WeekCode STRING, HasBeenShown INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

    public void addVisitDate(String LmkName){
       // ContentValues dbValues = new ContentValues();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT OR REPLACE INTO tbl_DateVisited(LmkName, VisitDate) VALUES ('"+LmkName+"', '"+df.format(Calendar.getInstance().getTime())+"')");
        Log.d("enter:","INSERT OR REPLACE INTO tbl_DateVisited(LmkName, VisitDate) VALUES ('"+LmkName+"', '"+df.format(Calendar.getInstance().getTime())+"')");
        this.close();
    }

    public String getVisitDate(String LmkName){
        SQLiteDatabase db = this.getWritableDatabase();
        String returnDate = "";
        try {
            Cursor c = db.rawQuery("SELECT * FROM  tbl_DateVisited WHERE LmkName='" + LmkName + "'", null);
            while (c.moveToNext()) {
                returnDate = c.getString(1);
            }
        }catch (Exception e){
            Log.d("DBC.getVisitDate",e.toString());
        }
        this.close();
        return returnDate;
    }

    public void ClearDateVisitedDb(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS tbl_DateVisited");
        this.close();
    }
    public void addCode(String code, String chalDate){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT OR REPLACE INTO tbl_Completed (ChalDate, WeekCode, HasBeenShown) VALUES ('"+chalDate+"''"+code+"', 0)");
    }
    public boolean hasShown(String chalDate){
        int hasBeenShown = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            Cursor c = db.rawQuery("SELECT * FROM  tbl_Completed WHERE ChalDate='"+chalDate+"'", null);
            while (c.moveToNext()) {
                hasBeenShown = c.getInt(3);
            }
        }catch (Exception e){
            Log.d("DBC.hasShown",e.toString());}

        return hasBeenShown==1;
    }
    public String getCode(String chalDate){
        SQLiteDatabase db = this.getWritableDatabase();
        String challangeCode="";
        try {
            Cursor c = db.rawQuery("SELECT * FROM  tbl_Completed WHERE ChalDate='"+chalDate+"'", null);
            while (c.moveToNext()) {
                challangeCode = c.getString(2);
            }
        }catch (Exception e){
            Log.d("DBC.getCode",e.toString());}

        return challangeCode;
    }
}

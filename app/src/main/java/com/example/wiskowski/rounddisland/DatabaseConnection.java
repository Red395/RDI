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
            //"SELECT VisitDate FROM  tbl_DateVisited WHERE LmkName='" + LmkName + "'"
            Cursor c = db.rawQuery("SELECT * FROM  tbl_DateVisited WHERE LmkName='" + LmkName + "'", null);
            while (c.moveToNext()) {
                returnDate = c.getString(1);
            }
        }catch (Exception e){
            Log.d("DBC.getVisitDate",e.toString());}
        return returnDate;
    }

    public void ClearDatabase(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS tbl_DateVisited");
        this.close();
    }

}

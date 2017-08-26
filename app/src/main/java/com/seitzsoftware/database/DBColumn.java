package com.seitzsoftware.database;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ImageView;

import com.seitzsoftware.android.simpleandroidgdf.GameMainActivity;

/**
 * Created by vette on 6/11/2017.
 */

public class DBColumn {


    String ObjName;
    String ObjDBColumnName;
    String ObjDBColumnType;
    ImageView ObjImage;

    public ImageView getObjImage() {
        return ObjImage;
    }

    public void setObjImage(ImageView objImage) {
        ObjImage = objImage;
    }

    public DBColumn(String Name, String ColumnName, String ColumnType){
        setObjName(Name);
        setObjDBColumnName(ColumnName);
        setObjDBColumnType(ColumnType);
    }

    public String getObjName(){
        return ObjName;
    }


    public void setObjName(String objName) {
        ObjName = objName;
    }


    public String getObjDBColumnName() {
        return ObjDBColumnName;
    }

    public void setObjDBColumnName(String objDBColumnName) {
        ObjDBColumnName = objDBColumnName;
    }

    public String getObjDBColumnType() {
        return ObjDBColumnType;
    }

    public void setObjDBColumnType(String objDBColumnType) {
        ObjDBColumnType = objDBColumnType;
    }

    public int getItemAmount(String accountName) throws SQLException {
        MyDBHandler dbHandler = GameMainActivity.dbHandler;
        String dbString = "";
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        String query = "Select " + this.getObjDBColumnName() + " from " + "character" + " where " + "accountname" + " = '" + accountName + "'";

        //Cursor point to a location
        Cursor c = db.rawQuery(query, null);
        int value = 0;
        if(c.moveToNext()){
            value = c.getInt(0);
        }
        return value;

    }
}
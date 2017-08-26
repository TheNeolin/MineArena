package com.seitzsoftware.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.seitzsoftware.Player.Armor;
import com.seitzsoftware.Player.Bar;
import com.seitzsoftware.Player.Ore;
import com.seitzsoftware.android.simpleandroidgdf.GameMainActivity;

import java.util.Arrays;


    public class MyDBHandler extends SQLiteOpenHelper {

        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "character.db";
        public static final String TABLE_CHARACTER = "character";
        public static final String COLUMN_ID = "_id";

        public static final DBColumn COAL_BAR = new DBColumn("Coal Bar","coalbar","INT");
        public static final DBColumn COPPER_BAR = new DBColumn("Copper Bar","copperbar","INT");
        public static final DBColumn IRON_BAR = new DBColumn("Iron Bar","ironbar","INT");
        public static final DBColumn SILVER_BAR = new DBColumn("Silver Bar","silverbar","INT");
        public static final DBColumn GOLD_BAR = new DBColumn("Gold Bar","goldbar","INT");
        public static final DBColumn MITHRIL_BAR = new DBColumn("Mithril Bar","mithrilbar","INT");
        public static final DBColumn RUNE_BAR = new DBColumn("Rune Bar","runebar","INT");
        public static final DBColumn OBSIDIAN_BAR = new DBColumn("Obsidian Bar","obsidianbar","INT");
        public static final DBColumn DRAGON_BAR = new DBColumn("Dragon Bar","dragonbar","INT");
        public static final DBColumn ADAMANTITE_BAR = new DBColumn("Adamantite Bar","adamantitebar","INT");

        public static final String COLUMN_ACCOUNTNAME = "accountname";
        public static final String COLUMN_PASSWORD = "password";

        public String AccountName;

        public String getAccountName() {
            return AccountName;
        }

        String[] columnNames;

        public void setAccountName(String x){
            AccountName = x;
        }

        public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE " + TABLE_CHARACTER + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_ACCOUNTNAME + " TEXT, " +
                    COLUMN_PASSWORD + " TEXT " +

                    getOreColumnCreate() +

                    getBarColumnCreate() +

                    getArmorColumnCreate() +

                    ");";
            db.execSQL(query);
        }

        public String getOreColumnCreate(){
            String sqlText = "";
            for (Ore o : GameMainActivity.N.getOres()) {
                sqlText += "," + o.dBColumn.getObjDBColumnName() + " INT " ;
            }
            return sqlText;
        }

        public String getBarColumnCreate(){
            String sqlText = "";
            for (Bar b : GameMainActivity.N.getBars()) {
                sqlText += "," + b.dBColumn.getObjDBColumnName() + " INT " ;
            }
            return sqlText;
        }

        public String getArmorColumnCreate(){
            String sqlText = "";
            for (Armor a: GameMainActivity.N.getArmor()) {
                sqlText += "," + a.dBColumn.getObjDBColumnName() + " TEXT " ;
            }
            return sqlText;
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHARACTER);
            onCreate(db);
        }

        public void newCharacter(String accountName, String password){
            SQLiteDatabase mDataBase;
            mDataBase = getWritableDatabase();
            Cursor dbCursor = mDataBase.rawQuery("SELECT * FROM " + TABLE_CHARACTER + " LIMIT 1", null);
            columnNames = dbCursor.getColumnNames();
            System.out.println(Arrays.toString(columnNames));
            mDataBase.close();

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_ACCOUNTNAME,accountName);
            values.put(COLUMN_PASSWORD,password);

            for (String a : columnNames){
                if(a != columnNames[0] && a != columnNames[1] && a != columnNames[2]){
                    values.put(a,0);
                }
            }
            db.insert(TABLE_CHARACTER, null, values);
            db.close();
        }

        public void deleteCharacter(String character){
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("DELETE FROM " + TABLE_CHARACTER + " WHERE " + COLUMN_ACCOUNTNAME + "=\"" + character + "\";");
        }

        public String databaseToString(){
            String dbString = "";
            SQLiteDatabase db = getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_CHARACTER + " WHERE 1";

            //Cursor point to a location
            Cursor c = db.rawQuery(query, null);

            //Move to first row in results
            c.moveToFirst();

            while(!c.isAfterLast()){
                if(c.getString(c.getColumnIndex("accountname"))!=null){
                    dbString += c.getString(c.getColumnIndex("accountname"));
                    dbString += c.getString(c.getColumnIndex("coal_ore"));
                    dbString += "\n";
                }
                c.moveToNext();
            }
            db.close();
            return dbString;
        }

        public boolean DBHasObject(String TableName, String dbfield, String fieldValue) {
            SQLiteDatabase sqldb = getWritableDatabase();
            String Query = "Select * from " + TableName + " where " + dbfield + " = '" + fieldValue + "'";
            Cursor cursor = sqldb.rawQuery(Query, null);
            if(cursor.getCount() <= 0){
                cursor.close();
                return false;
            }
            cursor.close();
            sqldb.close();
            return true;
        }

        public int GetItemAmount(String accountName, String dbfield) throws SQLException {
            String dbString = "";
            SQLiteDatabase db = getReadableDatabase();
            String query = "Select " + dbfield + " from " + TABLE_CHARACTER + " where " + COLUMN_ACCOUNTNAME + " = '" + accountName + "'";

            //Cursor point to a location
            Cursor c = db.rawQuery(query, null);
            int value = 0;
            if(c.moveToNext()){
                value = c.getInt(0);
            }
            return value;

        }

        public String GetItemString(String accountName, String dbfield) throws SQLException {
            String dbString = "";
            SQLiteDatabase db = getReadableDatabase();
            String query = "Select " + dbfield + " from " + TABLE_CHARACTER + " where " + COLUMN_ACCOUNTNAME + " = '" + accountName + "'";

            //Cursor point to a location
            Cursor c = db.rawQuery(query, null);
            String value = null;
            if(c.moveToNext()){
                value = c.getString(0);
            }
            return value;

        }

        public void UpdateItemAmount(String accountName, String dbfield, int dbvalue) throws SQLException {

            SQLiteDatabase db = getWritableDatabase();

            int currentValue = GetItemAmount(accountName, dbfield);
            int currentValueInc = currentValue + dbvalue;

            //New Value for one column
            ContentValues values = new ContentValues();
            values.put(dbfield, currentValueInc);

            db.update(TABLE_CHARACTER, values,COLUMN_ACCOUNTNAME + " = ?", new String[]{String.valueOf(accountName)});
        }

        public void UpdateItemString(String accountName, String dbfield, String dbvalue) throws SQLException {

            SQLiteDatabase db = getWritableDatabase();

            //New Value for one column
            ContentValues values = new ContentValues();
            values.put(dbfield, dbvalue);

            db.update(TABLE_CHARACTER, values,COLUMN_ACCOUNTNAME + " = ?", new String[]{String.valueOf(accountName)});
        }

    }



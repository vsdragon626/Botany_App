package com.pocketbotanist.database;

//standard database imports
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class EntryTable {

  // Database table, we create them as strings here and add them to the database 
// via "super string" later
  public static final String TABLE_ENTRY = "entry";
  public static final String COLUMN_ID = "_id";
  public static final String COLUMN_CUSTOMID = "custom_id";
  public static final String COLUMN_SPECIES = "species";
  public static final String COLUMN_COMMONNAME = "common_name";
  public static final String COLUMN_DATE = "date";
  public static final String COLUMN_TIME = "time";
  public static final String COLUMN_PHOTOS = "photos";
  public static final String COLUMN_PHOTO = "photo";
  public static final String COLUMN_LONGITUDE = "longitude";
  public static final String COLUMN_LATITUDE = "latitude";
  public static final String COLUMN_PLANTNOTES = "plant_notes";
  public static final String COLUMN_HABITATNOTES = "habitat_notes";
  public static final String COLUMN_EXTRANOTES1 = "extra_notes_1";
  public static final String COLUMN_EXTRANOTES2 = "extra_notes_2";
  public static final String COLUMN_EXTRANOTES3 = "extra_notes_3";
  public static final String COLUMN_EXTRANOTES4 = "extra_notes_4";
  public static final String COLUMN_EXTRANOTES5 = "extra_notes_5";
  

  // Database creation SQL statement
  private static final String DATABASE_CREATE = "create table " 
      + TABLE_ENTRY
      + "(" 
      + COLUMN_ID + " integer primary key autoincrement, " 
      + COLUMN_CUSTOMID + " text not null, " 
      + COLUMN_SPECIES + " text not null, " 
      + COLUMN_COMMONNAME + " text not null, " 
      + COLUMN_DATE + " text not null, " 
      + COLUMN_TIME + " text not null, " 
      + COLUMN_PHOTOS + " text not null, " 
      + COLUMN_PHOTO + " text not null, " 
      + COLUMN_LONGITUDE + " text not null, " 
      + COLUMN_LATITUDE + " text not null, " 
      + COLUMN_PLANTNOTES + " text not null, " 
      + COLUMN_HABITATNOTES + " text not null, " 
      + COLUMN_EXTRANOTES1 + " text not null, " 
      + COLUMN_EXTRANOTES2 + " text not null, " 
      + COLUMN_EXTRANOTES3 + " text not null, " 
      + COLUMN_EXTRANOTES4 + " text not null, "
      + COLUMN_EXTRANOTES5
      + " text not null" 
      + ");";

  //if the database doesn't exist, we create it here
  public static void onCreate(SQLiteDatabase database) {
    database.execSQL(DATABASE_CREATE);
  }

  //if there's been a change in the database, we effectively create a new database
  //using the changes to the database schema
  //NOTE: THIS DESTROYS ALL OLD DATA!!!
  //CREATE NEW DATABASE VERSION ONLY WHEN ABOSLUTELY NECESARY!!
  public static void onUpgrade(SQLiteDatabase database, int oldVersion,
      int newVersion) {
    Log.w(EntryTable.class.getName(), "Upgrading database from version "
        + oldVersion + " to " + newVersion
        + ", which will destroy all old data");
    database.execSQL("DROP TABLE IF EXISTS " + TABLE_ENTRY);
    onCreate(database);
  }
} 

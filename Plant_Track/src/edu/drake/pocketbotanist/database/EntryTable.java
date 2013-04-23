package edu.drake.pocketbotanist.database;

//standard database imports
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class EntryTable {

  // Database table, we create them as strings here and add them to the database 
// via "super string" later
  public static final String TABLE_ENTRY = "entry";
  public static final String COLUMN_ID = "_id";
  public static final String COLUMN_CUSTOMID = "custom id";
  public static final String COLUMN_SPECIES = "species";
  public static final String COLUMN_COMMONNAME = "common name";
  public static final String COLUMN_DATE = "date";
  public static final String COLUMN_TIME = "time";
  public static final String COLUMN_PHOTOS = "photos";
  public static final String COLUMN_PHOTO = "photo";
  public static final String COLUMN_LONGITUDE = "longitude";
  public static final String COLUMN_LATITUDE = "latitude";
  public static final String COLUMN_PLANTNOTES = "plant notes";
  public static final String COLUMN_HABITATNOTES = "habitat notes";
  public static final String COLUMN_EXTRANOTES1 = "extra notes 1";
  public static final String COLUMN_EXTRANOTES2 = "extra notes 2";
  public static final String COLUMN_EXTRANOTES3 = "extra notes 3";
  public static final String COLUMN_EXTRANOTES4 = "extra notes 4";
  public static final String COLUMN_EXTRANOTES5 = "extra notes 5";
  

  // Database creation SQL statement
  private static final String DATABASE_CREATE = "create table " 
      + TABLE_ENTRY
      + "(" 
      + COLUMN_ID + " integer primary key autoincrement, " 
      + COLUMN_CUSTOMID + " custom id field, " 
      + COLUMN_SPECIES + " flower species," 
      + COLUMN_COMMONNAME + " flower common name," 
      + COLUMN_DATE + " date of entry creation," 
      + COLUMN_TIME + " time of entry creation," 
      + COLUMN_PHOTOS + " gallery location," 
      + COLUMN_PHOTO + " photo location," 
      + COLUMN_LONGITUDE + " longitude," 
      + COLUMN_LATITUDE + " latitude," 
      + COLUMN_PLANTNOTES + " notes about the plant," 
      + COLUMN_HABITATNOTES + " notes about the plant habitat," 
      + COLUMN_EXTRANOTES1 + " custom entry field 1," 
      + COLUMN_EXTRANOTES2 + " custom entry field 2," 
      + COLUMN_EXTRANOTES3 + " custom entry field 3," 
      + COLUMN_EXTRANOTES4 + " custom entry field 4,"
      + COLUMN_EXTRANOTES5
      + " custom entry field 5" 
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

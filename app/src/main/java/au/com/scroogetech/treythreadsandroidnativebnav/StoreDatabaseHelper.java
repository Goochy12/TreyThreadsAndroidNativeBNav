package au.com.scroogetech.treythreadsandroidnativebnav;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StoreDatabaseHelper extends SQLiteOpenHelper {

    public final static String DB_NAME = "stockDB.db";
    public final static int DB_Version = 1;
    String DB_PATH = null;
    //private SQLiteDatabase db;
    //private final Context context;
    //File dbFile;

    private final static String STOCK_TABLE = "stockitems";
    private final static String STOCK_PROP_TABLE = "stockProperties";

    private final static String STOCK_TABLE_CREATE = "CREATE TABLE " + STOCK_TABLE + " (" +  "_id" + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + "name" + " TEXT);";
    private final static String STOCK_PROP_TABLE_CREATE = "CREATE TABLE " + STOCK_PROP_TABLE + " (" +  "_id" + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + "id INTEGER, " +
            "size TEXT, colour TEXT)";

    public StoreDatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_Version);

        DB_PATH = "/data/data/" + context.getPackageName()+"/databases/";
        //this.context = context;
        //dbFile = new File(DB_PATH + DB_NAME);
//        SQLiteDatabase db = this.getWritableDatabase();
    }

    //my code

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(STOCK_TABLE_CREATE);
        db.execSQL(STOCK_PROP_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + STOCK_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + STOCK_PROP_TABLE);
        onCreate(db);
    }

    public int getItemCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from " + STOCK_TABLE;
        Log.i("PRODUCT", "getItemCount: ");
        Cursor cursor = db.rawQuery(query,null);
        Log.i("PRODUCT", "SHIT");

        return cursor.getCount();
    }

//
//    @Override
//    public synchronized SQLiteDatabase getWritableDatabase() {
//
//        if(!dbFile.exists()){
//            SQLiteDatabase db = super.getWritableDatabase();
//            copyDataBase(db.getPath());
//        }
//        return super.getWritableDatabase();
//    }
//
//    @Override
//    public synchronized SQLiteDatabase getReadableDatabase() {
//        if(!dbFile.exists()){
//            SQLiteDatabase db = super.getReadableDatabase();
//            copyDataBase(db.getPath());
//        }
//        return super.getReadableDatabase();
//    }
//
//    private void copyDataBase(String dbPath){
//        try{
//            String outFileName = DB_PATH + DB_NAME;
//            OutputStream myOutput = new FileOutputStream(outFileName);
//            InputStream myInput = context.getAssets().open(DB_NAME);
//
//            byte[] buffer = new byte[1024];
//            int length;
//            while ((length = myInput.read(buffer)) > 0)
//            {
//                myOutput.write(buffer, 0, length);
//            }
//            myInput.close();
//            myOutput.flush();
//            myOutput.close();
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//
//    }
//
//    //Open database
//    public void openDatabase() throws SQLException
//    {
//        String myPath = DB_PATH + DB_NAME;
//        db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
//    }
//
//    public synchronized void closeDataBase()throws SQLException
//    {
//        if(db != null)
//            db.close();
//        super.close();
//    }



}

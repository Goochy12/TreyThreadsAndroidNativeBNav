package au.com.scroogetech.treythreadsandroidnativebnav;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "realstockDB.db";
    private final static int DB_Version = 1;
    String DB_PATH = null;
    private SQLiteDatabase db;

    private final static String STOCK_TABLE = "stock";
    private final static String STOCK_PROP_TABLE = "stockProperties";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_Version);

        DB_PATH = "/data/data/" + context.getPackageName()+"/databases/";
//        SQLiteDatabase db = this.getWritableDatabase();
    }

    public int getItemCount(){
        String query = "SELECT NAME FROM " + STOCK_TABLE;
        Cursor cursor = db.rawQuery(query,null);

        return cursor.getCount();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}

package au.com.scroogetech.treythreadsandroidnativebnav;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class StockDBAccess {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static StockDBAccess instance;
    Cursor cursor = null;

    private StockDBAccess(Context context){
        this.openHelper = new StoreDatabaseHelper(context);

    }

    public static StockDBAccess getInstance(Context context){
        if (instance == null){
            instance = new StockDBAccess(context);
        }
        return instance;
    }

    public void open(){
        this.db = openHelper.getWritableDatabase();
    }

    public void close(){
        if (db != null){
            this.db.close();
        }
    }

    public String[] getItemName(){
        cursor = db.rawQuery("SELECT Name FROM stock", null);
        String[] itemNameArray = new String[cursor.getCount()];
        String itemName;
        int i = 0;
        Log.i("LOAD_DATA", "Gothere");
        while (cursor.moveToNext()){
            itemName = cursor.getString(cursor.getColumnIndex("Name"));
            Log.i("GET_ITEM", "getItemName: ");
            itemNameArray[i] = itemName;
            i++;
        }

        return itemNameArray;
    }
}

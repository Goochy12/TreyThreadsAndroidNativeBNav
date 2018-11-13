package au.com.scroogetech.treythreadsandroidnativebnav;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

public class HomeDatabaseHelper extends SQLiteOpenHelper {

    public final static String DB_NAME = "homeDB.db";
    public final static int DB_Version = 1;
    String DB_PATH = null;
    //private SQLiteDatabase db;
    //private final Context context;
    //File dbFile;

    private final static String HOME_TABLE = "homeitems";

    private final static String STOCK_TABLE_CREATE = "CREATE TABLE " + HOME_TABLE + " (" +  "_id" + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + "newstock TEXT, " +
            "sellingfast TEXT, socialmedia TEXT);";
    private final static String put = "INSERT INTO homeitems (newstock,sellingfast,socialmedia) values ('New Stock','Selling Fast','Social Media')";

    public HomeDatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_Version);

        DB_PATH = "/data/data/" + context.getPackageName()+"/databases/";

    }

    //my code

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(STOCK_TABLE_CREATE);
        db.execSQL(put);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + HOME_TABLE);
        onCreate(db);
    }

    public int getItemCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from " + HOME_TABLE;
        Log.i("PRODUCT", "getItemCount: ");
        Cursor cursor = db.rawQuery(query,null);
        Log.i("PRODUCT", "SHIT");

        return cursor.getCount();
    }

    public String[] getItemList(){
        String[] itemList;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from " + HOME_TABLE;
        Cursor cursor = db.rawQuery(query,null);

        itemList = new String[cursor.getCount()];
        int i = 0;

        if (cursor.moveToFirst()){
            while (cursor.moveToNext()){
                itemList[i] = cursor.getString(0);
                Log.i("PRODUCTS", ""+itemList[i]);
                i++;
            }
        }

        return itemList;
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

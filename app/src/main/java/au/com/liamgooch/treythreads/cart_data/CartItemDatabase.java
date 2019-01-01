package au.com.liamgooch.treythreads.cart_data;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

@Database(entities = {CartItem.class}, version = 1)
public abstract class CartItemDatabase extends RoomDatabase {
    public abstract CartItemDao cartItemDao();

    public static volatile CartItemDatabase INSTANCE;

    static CartItemDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (CartItemDatabase.class){
                if (INSTANCE == null){
                    //create db

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),CartItemDatabase.class,"cartdb").allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }
}

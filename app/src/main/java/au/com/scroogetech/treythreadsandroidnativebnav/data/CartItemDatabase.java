package au.com.scroogetech.treythreadsandroidnativebnav.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = (CartItem.class), version = 1)
public abstract class CartItemDatabase extends RoomDatabase {
    public abstract CartItemDao cartItemDao();

    public static volatile CartItemDatabase INSTANCE;

    static CartItemDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (CartItemDatabase.class){
                if (INSTANCE == null){
                    //create db

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),CartItemDatabase.class,"classdb").build();
                }
            }
        }
        return INSTANCE;
    }
}

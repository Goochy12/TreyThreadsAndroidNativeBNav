package au.com.scroogetech.treythreadsandroidnativebnav.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface CartItemDao {
    //sql queries here

    @Query("SELECT * FROM CART_TABLE")
    LiveData<List<CartItem>> getAllCartItems();
}

package au.com.scroogetech.treythreadsandroidnativebnav.cart_data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface CartItemDao {
    //sql queries here

    @Query("SELECT * FROM CART_TABLE")
    LiveData<List<CartItem>> getAllCartItems();

    @Query("DELETE FROM CART_TABLE WHERE itemID = :cartItemID")
    void deleteCartItem(int cartItemID);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCartItem(CartItem cartItem);

    @Query("DELETE FROM CART_TABLE")
    void deleteAllCartItems();

    //check same item
    @Query("SELECT * FROM CART_TABLE where itemName = :name and itemSize = :size and colour = :colour")
    CartItem getSameItem(String name, String size, String colour);

//    @Update(onConflict = OnConflictStrategy.REPLACE)
    @Query("UPDATE CART_TABLE SET quantity = :quan WHERE itemID = :id ")
    void updateQuantity(int id, int quan);
}

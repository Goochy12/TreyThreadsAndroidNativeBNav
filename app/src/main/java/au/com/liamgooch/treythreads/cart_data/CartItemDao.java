package au.com.liamgooch.treythreads.cart_data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

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
    @Query("SELECT * FROM CART_TABLE where productID = :productID")
    List<CartItem> getSameItem(String productID);

//    @Update(onConflict = OnConflictStrategy.REPLACE)
    @Query("UPDATE CART_TABLE SET quantity = :quan WHERE itemID = :id ")
    void updateQuantity(int id, int quan);

    @Query("SELECT COUNT(*) FROM cart_table")
    int getCount();
}

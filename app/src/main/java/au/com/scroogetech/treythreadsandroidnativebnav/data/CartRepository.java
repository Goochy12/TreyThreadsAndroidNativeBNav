package au.com.scroogetech.treythreadsandroidnativebnav.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class CartRepository {

    private CartItemDao mCartItemDao;
    private LiveData<List<CartItem>> mAllCartItems;
    //private List<CartItem> all;

    public CartRepository(Application application){
        CartItemDatabase db = CartItemDatabase.getDatabase(application);
        mCartItemDao = db.cartItemDao();
        mAllCartItems = mCartItemDao.getAllCartItems();
    }
}

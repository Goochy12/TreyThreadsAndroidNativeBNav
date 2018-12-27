package au.com.scroogetech.treythreadsandroidnativebnav;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import au.com.scroogetech.treythreadsandroidnativebnav.cart_data.CartItem;
import au.com.scroogetech.treythreadsandroidnativebnav.cart_data.CartRepository;

public class CartViewModel extends AndroidViewModel {

    private CartRepository cartRepository;
    private LiveData<List<CartItem>> allCartItems;

    public CartViewModel(Application application){
        super(application);

        cartRepository = new CartRepository(application);
        allCartItems = cartRepository.getAllCartItems();
    }

    public LiveData<List<CartItem>> getAllItems(){return allCartItems;}

    public void insert(CartItem cartItem){cartRepository.insertCartItem(cartItem);}

    public void deleteAll(){cartRepository.deleteAllCartItems();}

    public void deleteItem(CartItem cartItem){
        cartRepository.deleteCartItem(cartItem);}

//    public List<CartItem> getSameItem(CartItem cartItem){
//        return cartRepository.getSameItem(cartItem);
//    }

    public void updateQuantity(CartItem cartItem, int quantity){
        cartRepository.updateQuantity(cartItem, quantity);
    }
}

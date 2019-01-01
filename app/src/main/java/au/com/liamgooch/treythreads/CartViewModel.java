package au.com.liamgooch.treythreads;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import au.com.liamgooch.treythreads.cart_data.CartItem;
import au.com.liamgooch.treythreads.cart_data.CartRepository;

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

    public List<CartItem> getSameItem(String productID){
        return cartRepository.getSameItem(productID);
    }

    public void updateQuantity(CartItem cartItem, int quantity){
        cartRepository.updateQuantity(cartItem, quantity);
    }
}

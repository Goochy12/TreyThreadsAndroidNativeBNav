package au.com.scroogetech.treythreadsandroidnativebnav;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import au.com.scroogetech.treythreadsandroidnativebnav.data.CartItem;
import au.com.scroogetech.treythreadsandroidnativebnav.data.CartRepository;

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

    public void deleteItem(CartItem cartItem){cartRepository.deleteCartItem(cartItem);}
}

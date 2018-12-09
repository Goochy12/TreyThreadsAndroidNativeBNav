package au.com.scroogetech.treythreadsandroidnativebnav.cart_data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

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

    public LiveData<List<CartItem>> getAllCartItems(){return mAllCartItems;}
    public void insertCartItem(CartItem cartItem){new insertAsyncTask(mCartItemDao).execute(cartItem);}
    public void deleteAllCartItems(){new deleteAllAsyncTask(mCartItemDao).execute();}
    public void deleteCartItem(CartItem cartItem){new deleteItemAsyncTask(mCartItemDao).execute();}

    private static class insertAsyncTask extends AsyncTask<CartItem, Void, Void>{
        private CartItemDao mAsyncCartDao;

        insertAsyncTask(CartItemDao cartItemDao){mAsyncCartDao = cartItemDao;}

        @Override
        protected Void doInBackground(final CartItem... params){
            CartItem newCartItem = new CartItem(params[0].getItemName(),params[0].getItemColour(),params[0].getItemPrice());
            mAsyncCartDao.insertCartItem(newCartItem);

            return null;
        }
    }

    private static class deleteAllAsyncTask extends AsyncTask<CartItem, Void, Void>{
        private CartItemDao mAsyncCartDao;

        deleteAllAsyncTask(CartItemDao cartItemDao){mAsyncCartDao = cartItemDao;}

        @Override
        protected Void doInBackground(final CartItem... params){
            mAsyncCartDao.deleteAllCartItems();

            return null;
        }
    }

    private static class deleteItemAsyncTask extends AsyncTask<CartItem, Void, Void>{
        private CartItemDao mAsyncCartDao;

        deleteItemAsyncTask(CartItemDao cartItemDao){mAsyncCartDao = cartItemDao;}

        @Override
        protected Void doInBackground(final CartItem... params){
            mAsyncCartDao.deleteCartItem(params[0].getItemID());

            return null;
        }
    }
}
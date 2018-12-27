package au.com.scroogetech.treythreadsandroidnativebnav.cart_data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
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
//    public void insertCartItem(CartItem cartItem){mCartItemDao.insertCartItem(cartItem);}
    public void insertCartItem(CartItem cartItem){new insertAsyncTask(mCartItemDao).execute(cartItem);}
    public void deleteAllCartItems(){new deleteAllAsyncTask(mCartItemDao).execute();}
    public void deleteCartItem(CartItem cartItem){new deleteItemAsyncTask(mCartItemDao).execute(cartItem);}

//    public List<CartItem> getSameItem(CartItem cartItem){
//        return mCartItemDao.getSameItem(cartItem.getItemName(),cartItem.getItemSize(),cartItem.getColour());
//    }

    public void updateQuantity(CartItem cartItem, int quantity){
//        mCartItemDao.updateQuantity(cartItem.getItemID(),quantity);
        new updateQuantityAsyncTask(mCartItemDao,quantity).execute(cartItem);
//        mCartItemDao.updateQuantity(cartItem.getItemID(),quan);
    }

    private static class insertAsyncTask extends AsyncTask<CartItem, Void, Void>{
        private CartItemDao mAsyncCartDao;

        insertAsyncTask(CartItemDao cartItemDao){mAsyncCartDao = cartItemDao;}

        @Override
        protected Void doInBackground(final CartItem... params){
            CartItem newCartItem = new CartItem(params[0].getItemName(),params[0].getItemSize(),params[0].getItemPrice(),params[0].getItemPath(),
                    params[0].getQuantity(), params[0].getMaxQuantity(), params[0].getColour(),params[0].getProductID());
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

    private static class updateQuantityAsyncTask extends AsyncTask<CartItem, Void, Void>{
        private CartItemDao mAsyncCartDao;
        private int quantityAsync;

        updateQuantityAsyncTask(CartItemDao cartItemDao, int quantity){
            mAsyncCartDao = cartItemDao;
            this.quantityAsync = quantity;
        }

        @Override
        protected Void doInBackground(final CartItem... params){
            mAsyncCartDao.updateQuantity(params[0].getItemID(),quantityAsync);

            return null;
        }
    }
}

package au.com.scroogetech.treythreadsandroidnativebnav;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import au.com.scroogetech.treythreadsandroidnativebnav.data.CartItem;
import au.com.scroogetech.treythreadsandroidnativebnav.data.CartItemDatabase;


public class CartFragment extends Fragment {

    private RecyclerView cartRecycler;
    private cartRecAdpt cartRecyclerAdapter;
    private RecyclerView.LayoutManager cartRecyclerLayoutManager;

    private TextView cartEmptyTextView;

    private CartItemDatabase db;
    private CartViewModel cartViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstaceState){
        super.onViewCreated(view, savedInstaceState);

        //get the recycler
        cartRecycler = (RecyclerView) view.findViewById(R.id.cartRecyclerView);
        //cartRecycler.setHasFixedSize(true);

        //use a linear layout
        cartRecyclerLayoutManager = new LinearLayoutManager(getActivity());
        cartRecycler.setLayoutManager(cartRecyclerLayoutManager);

        cartRecyclerAdapter = new cartRecAdpt(this.getActivity());
        cartRecycler.setAdapter(cartRecyclerAdapter);

        //String[] productList = loadData();
        //String[] productList = {"New Stock", "Selling Fast", "Social Media"};




        cartEmptyTextView = (TextView) view.findViewById(R.id.cartEmptyMessage);
        cartViewModel = ViewModelProviders.of((FragmentActivity) this.getContext()).get(CartViewModel.class);
        cartViewModel.getAllItems().observe(this, new Observer<List<CartItem>>() {
            @Override
            public void onChanged(@Nullable List<CartItem> cartItems) {
                cartRecyclerAdapter.setItems(cartItems);
                if (cartItems.size() > 0){
                    cartEmptyTextView.setVisibility(View.INVISIBLE);
                }else{
                    cartEmptyTextView.setVisibility(View.VISIBLE);
                }
            }
        });


//        cartDatabaseHelper dbHelper = new cartDatabaseHelper(getActivity());
////        dbHelper.openDatabase();
////        dbHelper.getReadableDatabase();
//        int products = dbHelper.getProductCount();
//        productList = dbHelper.getProducts();
//        productList = formatNames(productList);
//
//        String[] productImagePath = dbHelper.getProductImagePath();


        //specify adapter


//        if (recLayoutState != null){
//            Log.i(TAG, "onViewCreated: ");
//            cartRecyclerLayoutManager.onRecartInstanceState(recLayoutState);
//        }
    }

}

package au.com.scroogetech.treythreadsandroidnativebnav;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class CartFragment extends Fragment {

    private RecyclerView cartRecycler;
    private RecyclerView.Adapter cartRecyclerAdapter;
    private RecyclerView.LayoutManager cartRecyclerLayoutManager;

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

        //String[] productList = loadData();
        //String[] productList = {"New Stock", "Selling Fast", "Social Media"};
        String[] productList = {"1","2","3","4","5","6","7","8","9","10"};


//        cartDatabaseHelper dbHelper = new cartDatabaseHelper(getActivity());
////        dbHelper.openDatabase();
////        dbHelper.getReadableDatabase();
//        int products = dbHelper.getProductCount();
//        productList = dbHelper.getProducts();
//        productList = formatNames(productList);
//
//        String[] productImagePath = dbHelper.getProductImagePath();


        //specify adapter
        cartRecyclerAdapter = new cartRecAdpt(productList.length, productList, this.getActivity());
        cartRecycler.setAdapter(cartRecyclerAdapter);

//        if (recLayoutState != null){
//            Log.i(TAG, "onViewCreated: ");
//            cartRecyclerLayoutManager.onRecartInstanceState(recLayoutState);
//        }
    }

}

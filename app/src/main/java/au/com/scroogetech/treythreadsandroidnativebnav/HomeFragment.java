package au.com.scroogetech.treythreadsandroidnativebnav;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class HomeFragment extends Fragment {

    private RecyclerView homeRecycler;
    private RecyclerView.Adapter homeRecyclerAdapter;
    private RecyclerView.LayoutManager homeRecyclerLayoutManager;

    private Parcelable recLayoutState;
    private Bundle recBundle;
    private static String LIST_STATE = "LIST_STATE";
    private static String TAG = "LIST_STATE";

    public HomeFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return  inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstaceState){
        super.onViewCreated(view, savedInstaceState);

        //get the recycler
        homeRecycler = (RecyclerView) view.findViewById(R.id.homeRecyclerView);
        //homeRecycler.setHasFixedSize(true);

        //use a linear layout
        homeRecyclerLayoutManager = new LinearLayoutManager(getActivity());
        homeRecycler.setLayoutManager(homeRecyclerLayoutManager);

        //String[] productList = loadData();
        String[] productList = {"New Stock", "Selling Fast", "Social Media"};


        HomeDatabaseHelper dbHelper = new HomeDatabaseHelper(getActivity());
//        dbHelper.openDatabase();
//        dbHelper.getReadableDatabase();

        int products = dbHelper.getItemCount();
        Log.i("PRODUCTS", "onViewCreated: " + products);
        productList = dbHelper.getItemList();
        for (int i = 0; i < productList.length;i++){
            Log.i("PRODUCTS", ""+productList[i]);
        }

        //String[] productList = {"1","2","3","4","5","6","7","8","9","10"};
        //specify adapter
        homeRecyclerAdapter = new homeRecAdpt(productList);
        homeRecycler.setAdapter(homeRecyclerAdapter);

//        if (recLayoutState != null){
//            Log.i(TAG, "onViewCreated: ");
//            homeRecyclerLayoutManager.onRestoreInstanceState(recLayoutState);
//        }
    }

    public void /*String[]*/ loadData(){
        //URL dbURL = new URL();

//        StoreDatabaseHelper dbh = new StoreDatabaseHelper(this);
//
//
//        return databaseAccess.getItemName();
    }
}

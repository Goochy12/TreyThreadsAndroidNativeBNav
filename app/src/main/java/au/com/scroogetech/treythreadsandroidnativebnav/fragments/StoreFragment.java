package au.com.scroogetech.treythreadsandroidnativebnav.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import au.com.scroogetech.treythreadsandroidnativebnav.R;
import au.com.scroogetech.treythreadsandroidnativebnav.StoreDatabaseHelper;
import au.com.scroogetech.treythreadsandroidnativebnav.recycler_adapters.storeRecAdpt;


public class StoreFragment extends Fragment {

    private RecyclerView storeRecycler;
    private RecyclerView.Adapter storeRecyclerAdapter;
    private RecyclerView.LayoutManager storeRecyclerLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_store, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstaceState){
        super.onViewCreated(view, savedInstaceState);

        //get the recycler
        storeRecycler = (RecyclerView) view.findViewById(R.id.storeRecyclerView);
        //storeRecycler.setHasFixedSize(true);

        //use a linear layout
        storeRecyclerLayoutManager = new LinearLayoutManager(getActivity());
        storeRecycler.setLayoutManager(storeRecyclerLayoutManager);

        //String[] productList = loadData();
        //String[] productList = {"New Stock", "Selling Fast", "Social Media"};
        String[] productList = {"1","2","3","4","5","6","7","8","9","10"};


        StoreDatabaseHelper dbHelper = new StoreDatabaseHelper(getActivity());
//        dbHelper.openDatabase();
//        dbHelper.getReadableDatabase();
        int products = dbHelper.getProductCount();
        productList = dbHelper.getProducts();
        productList = formatNames(productList);

        String[] productImagePath = dbHelper.getProductImagePath();


        //specify adapter
        storeRecyclerAdapter = new storeRecAdpt(products, productList, productImagePath, this.getActivity());
        storeRecycler.setAdapter(storeRecyclerAdapter);

//        if (recLayoutState != null){
//            Log.i(TAG, "onViewCreated: ");
//            storeRecyclerLayoutManager.onRestoreInstanceState(recLayoutState);
//        }
    }

    public void getNumberOfItems(){

    }

    public void connectToDataBase(){

    }

    private String[] formatNames(String[] productList){
        String[] newList = productList; // new String[productList.length];
        for (int i = 0; i < productList.length;i++){
            newList[i] = productList[i];
        }


        for (int i = 0; i < newList.length;i++){
            newList[i] = newList[i].toLowerCase();
            newList[i] = newList[i].substring(0,1).toUpperCase() + newList[i].substring(1);
            for (int j = 0; j < newList[i].length();j++){
                if (newList[i].substring(j,j+1).equals("_")){
                    newList[i] = newList[i].substring(0,j) + " " + newList[i].substring(j+1,j+2).toUpperCase() + newList[i].substring(j+2);
                }
            }
        }
        return newList;
    }

    private String[] formatPath(String[] path){
        String[] newPath = path;
        for (int i = 0; i < newPath.length; i++){
            newPath[i] = "goochystesting.tk/" + newPath[i];
        }

        return newPath;
    }

}

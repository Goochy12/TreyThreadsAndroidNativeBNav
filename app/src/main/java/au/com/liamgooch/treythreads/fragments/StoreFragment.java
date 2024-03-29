package au.com.liamgooch.treythreads.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import au.com.liamgooch.treythreads.R;
import au.com.liamgooch.treythreads.recycler_adapters.storeRecAdpt;


public class StoreFragment extends Fragment {

    private RecyclerView storeRecycler;
    private RecyclerView.Adapter storeRecyclerAdapter;
    private RecyclerView.LayoutManager storeRecyclerLayoutManager;

    private ArrayList<ArrayList<String>> stockList = new ArrayList<>();
    private ArrayList<ArrayList<String>> stockProperties = new ArrayList<>();
    private ArrayList<ArrayList<String>> stockQuantities = new ArrayList<>();

    private ProgressBar storeFragmentProgressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_store, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstaceState){
        super.onViewCreated(view, savedInstaceState);

        storeFragmentProgressBar = (ProgressBar) getActivity().findViewById(R.id.storeFragmentProgressBar);
        storeFragmentProgressBar.setVisibility(View.VISIBLE);
        storeFragmentProgressBar.animate();

        //get the recycler
        storeRecycler = (RecyclerView) view.findViewById(R.id.storeRecyclerView);
        //storeRecycler.setHasFixedSize(true);

        //use a linear layout
        storeRecyclerLayoutManager = new LinearLayoutManager(getActivity());
        storeRecycler.setLayoutManager(storeRecyclerLayoutManager);

        //specify adapter
        storeRecyclerAdapter = new storeRecAdpt(this.getActivity(), stockList, stockProperties, stockQuantities, storeFragmentProgressBar);
        storeRecycler.setAdapter(storeRecyclerAdapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference stockRef = database.getReference("stock");

        stockRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                stockList.clear();
                stockProperties.clear();

                storeFragmentProgressBar.setVisibility(View.VISIBLE);
                storeFragmentProgressBar.animate();

                for(DataSnapshot categorySnapShot : dataSnapshot.getChildren()){

                    for (DataSnapshot typeSnapShot : categorySnapShot.getChildren()){
                        ArrayList<String> type = new ArrayList<>();

                        boolean error = false;

                        try{
                            String id = typeSnapShot.child("id").getValue().toString();
                            String name = typeSnapShot.getKey();
                            String price = typeSnapShot.child("price").getValue().toString();

                            type.add(id);
                            type.add(formatName(name));
                            type.add(price);

                        }catch (NullPointerException e){
                            error = true;
                        }

                        if (!error){
                            stockList.add(type);

                            for (DataSnapshot eachColour : typeSnapShot.getChildren()){
                                ArrayList<String> specifics = new ArrayList<>();
                                if (eachColour.child("colourID").exists()) {

                                    try{
                                        String productID = eachColour.child("productID").getValue().toString();
                                        String colourID = eachColour.child("colourID").getValue().toString();
                                        String colour = eachColour.getKey();
                                        String front_path = eachColour.child("image_front").getValue(String.class);
//                                      String back_path = typeSnapShot.child("image_back").getValue().toString();

                                        specifics.add(productID);
                                        specifics.add(colourID);
                                        specifics.add(formatName(colour));
                                        specifics.add(front_path);
//                                      specifics.add(back_path);

                                        stockProperties.add(specifics);

                                    }catch (NullPointerException e){

                                        specifics.add("ERROR");
                                        stockProperties.add(specifics);
                                    }

                                }
                            }
                        //end of error check
                        }

                    }
                }
                storeRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference quantityRef = database.getReference("stock_quantities");
        quantityRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                stockQuantities.clear();

                for(DataSnapshot productIDSnapshot : dataSnapshot.getChildren()) {
                    storeFragmentProgressBar.setVisibility(View.VISIBLE);
                    storeFragmentProgressBar.animate();

                    try {
                        String productID = productIDSnapshot.getKey();
                        String quantity = productIDSnapshot.getValue().toString();

                        ArrayList<String> productQuan = new ArrayList<>();
                        productQuan.add(productID);
                        productQuan.add(quantity);
                        stockQuantities.add(productQuan);
                    }catch (NullPointerException e){

                    }

                }
                storeRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private String formatName(String name){
        String newName = name;

        newName = newName.toLowerCase();
        newName = newName.substring(0,1).toUpperCase() + newName.substring(1);
        for (int j = 0; j < newName.length();j++){
            if (newName.substring(j,j+1).equals("_")){
                newName = newName.substring(0,j) + " " + newName.substring(j+1,j+2).toUpperCase() + newName.substring(j+2);
            }
        }
        return newName;
    }

    private String[] formatPath(String[] path){
        String[] newPath = path;
        for (int i = 0; i < newPath.length; i++){
            newPath[i] = "goochystesting.tk/" + newPath[i];
        }

        return newPath;
    }

}

package au.com.scroogetech.treythreadsandroidnativebnav.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import au.com.scroogetech.treythreadsandroidnativebnav.HomeDatabaseHelper;
import au.com.scroogetech.treythreadsandroidnativebnav.R;
import au.com.scroogetech.treythreadsandroidnativebnav.recycler_adapters.homeRecAdpt;


public class HomeFragment extends Fragment {

    private RecyclerView homeRecycler;
    private RecyclerView.Adapter homeRecyclerAdapter;
    private RecyclerView.LayoutManager homeRecyclerLayoutManager;

    private Parcelable recLayoutState;
    private Bundle recBundle;
    private static String LIST_STATE = "LIST_STATE";
    private static String TAG = "LIST_STATE";

    private ArrayList<String> names = new ArrayList<>();

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

        //String[] productList2 = {"1","2","3","4","5","6","7","8","9","10"};
//specify adapter
        homeRecyclerAdapter = new homeRecAdpt(names);
        homeRecycler.setAdapter(homeRecyclerAdapter);



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("home");
        //
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String value = dataSnapshot.getValue(String.class);
                names.add(value);
                homeRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                names.remove(value);
                homeRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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

    public void talkToDB(){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("home");

        //List<String> sA = new ArrayList<>();
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                for (DataSnapshot ds : dataSnapshot.getChildren()){
//                    sA.add(ds.getValue().toString());
//                }

                Log.i("ONCLOCK", "onChildAdded: ");
                String value = dataSnapshot.getValue(String.class);
                names.add(value);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // create child in home object
        // assign values to child

    }
}

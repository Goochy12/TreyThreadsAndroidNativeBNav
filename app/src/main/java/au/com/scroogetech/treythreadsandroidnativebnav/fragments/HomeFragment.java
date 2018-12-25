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

import java.util.ArrayList;

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

    private ArrayList<ArrayList<String>> homeItems = new ArrayList<>();

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

//specify adapter
        homeRecyclerAdapter = new homeRecAdpt(homeItems, getActivity());
        homeRecycler.setAdapter(homeRecyclerAdapter);



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("home");
        //

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot categorySnapShot : dataSnapshot.getChildren()){

                    for (DataSnapshot itemSnapShot : categorySnapShot.getChildren()){
                        ArrayList<String> item = new ArrayList<>();

                        String title = itemSnapShot.child("title").getValue().toString();
                        String message = itemSnapShot.child("message").getValue().toString();
                        String link = itemSnapShot.child("link").getValue().toString();
                        String image = itemSnapShot.child("image").getValue().toString();
                        String internal = itemSnapShot.child("internal").getValue().toString();
                        String clickable = itemSnapShot.child("clickable").getValue().toString();

                        item.add(title);
                        item.add(message);
                        item.add(link);
                        item.add(image);
                        item.add(internal);
                        item.add(clickable);

                        homeItems.add(item);
                    }
                }
                homeRecyclerAdapter.notifyDataSetChanged();
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

        // create child in home object
        // assign values to child

    }

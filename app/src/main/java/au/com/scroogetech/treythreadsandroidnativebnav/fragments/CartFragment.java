package au.com.scroogetech.treythreadsandroidnativebnav.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import au.com.scroogetech.treythreadsandroidnativebnav.CartViewModel;
import au.com.scroogetech.treythreadsandroidnativebnav.R;
import au.com.scroogetech.treythreadsandroidnativebnav.recycler_adapters.cartRecAdpt;
import au.com.scroogetech.treythreadsandroidnativebnav.cart_data.CartItem;
import au.com.scroogetech.treythreadsandroidnativebnav.cart_data.CartItemDatabase;


public class CartFragment extends Fragment {

    private RecyclerView cartRecycler;
    private cartRecAdpt cartRecyclerAdapter;
    private RecyclerView.LayoutManager cartRecyclerLayoutManager;

    private TextView cartEmptyTextView;
    private Button checkoutButton;

    private ArrayList<ArrayList<String>> stockQuantities = new ArrayList<>();

    private CartItemDatabase db;
    private CartViewModel cartViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstaceState){
        super.onViewCreated(view, savedInstaceState);

        //get the recycler
        cartRecycler = (RecyclerView) view.findViewById(R.id.cartRecyclerView);
        //cartRecycler.setHasFixedSize(true);

        //use a linear layout
        cartRecyclerLayoutManager = new LinearLayoutManager(getActivity());
        cartRecycler.setLayoutManager(cartRecyclerLayoutManager);

        cartRecyclerAdapter = new cartRecAdpt(this.getActivity(), stockQuantities);
        cartRecycler.setAdapter(cartRecyclerAdapter);

        cartEmptyTextView = (TextView) view.findViewById(R.id.cartEmptyMessage);
        checkoutButton = (Button) view.findViewById(R.id.checkoutButton);
        checkoutButton.setBackgroundColor(getResources().getColor(android.R.color.black));

        cartViewModel = ViewModelProviders.of((FragmentActivity) this.getContext()).get(CartViewModel.class);
        cartViewModel.getAllItems().observe(this, new Observer<List<CartItem>>() {
            @Override
            public void onChanged(@Nullable List<CartItem> cartItems) {
                cartRecyclerAdapter.setItems(cartItems);
                if (cartItems.size() > 0){
                    cartEmptyTextView.setVisibility(View.INVISIBLE);
                    checkoutButton.setVisibility(View.VISIBLE);
                }else{
                    cartEmptyTextView.setVisibility(View.VISIBLE);
                    checkoutButton.setVisibility(View.INVISIBLE);
                }
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference stockQuantitiesRef = database.getReference("stock_quantities");
        stockQuantitiesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                stockQuantities.clear();
                for (DataSnapshot eachProductID : dataSnapshot.getChildren()){
                    ArrayList<String> item = new ArrayList<>();
                    String id = eachProductID.getKey();
                    String quantity = eachProductID.getValue().toString();

                    item.add(id);
                    item.add(quantity);

                    stockQuantities.add(item);
                }
                cartRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

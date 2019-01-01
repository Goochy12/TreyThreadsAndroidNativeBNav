package au.com.liamgooch.treythreads.fragments;

import android.app.Activity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.braintreepayments.api.dropin.DropInActivity;
import com.braintreepayments.api.dropin.DropInResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import au.com.liamgooch.treythreads.CartViewModel;
import au.com.liamgooch.treythreads.PaymentProcessing;
import au.com.liamgooch.treythreads.R;
import au.com.liamgooch.treythreads.recycler_adapters.cartRecAdpt;
import au.com.liamgooch.treythreads.cart_data.CartItem;
import au.com.liamgooch.treythreads.cart_data.CartItemDatabase;


public class CartFragment extends Fragment {

    private RecyclerView cartRecycler;
    private cartRecAdpt cartRecyclerAdapter;
    private RecyclerView.LayoutManager cartRecyclerLayoutManager;

    private TextView cartEmptyTextView;
    private Button checkoutButton;

    private ArrayList<ArrayList<String>> stockQuantities = new ArrayList<>();

    private CartItemDatabase db;
    private CartViewModel cartViewModel;

    public PaymentProcessing paymentProcessing;
    private static final int REQUEST_CODE = 1234;

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

        cartEmptyTextView = (TextView) view.findViewById(R.id.cartEmptyMessage);
        checkoutButton = (Button) view.findViewById(R.id.checkoutButton);
        checkoutButton.setBackgroundColor(getResources().getColor(android.R.color.black));

        //use a linear layout
        cartRecyclerLayoutManager = new LinearLayoutManager(getActivity());
        cartRecycler.setLayoutManager(cartRecyclerLayoutManager);

        cartRecyclerAdapter = new cartRecAdpt(this.getActivity(), stockQuantities, getActivity().findViewById(R.id.homeFragmentConstraintLayout));
        cartRecycler.setAdapter(cartRecyclerAdapter);

        cartViewModel = ViewModelProviders.of((FragmentActivity) this.getContext()).get(CartViewModel.class);
        cartViewModel.getAllItems().observe(this, new Observer<List<CartItem>>() {
            @Override
            public void onChanged(@Nullable List<CartItem> cartItems) {
                cartRecyclerAdapter.setItems(cartItems);
                if (cartItems.size() > 0){
                    cartEmptyTextView.setVisibility(View.INVISIBLE);
                    checkoutButton.setVisibility(View.VISIBLE);


                    checkoutButton.setText("Checkout - $" + cartRecyclerAdapter.getRunningTotal());
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

        //PAYMENT PROCESSING
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupPaymentProcessing();
            }
        });

    }
    
    
    
    
    
    
    
    
    
    
    

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                DropInResult result = data.getParcelableExtra(DropInResult.EXTRA_DROP_IN_RESULT);
                // use the result to update your UI and send the payment method nonce to your server
            } else if (resultCode == Activity.RESULT_CANCELED) {
                // the user canceled
            } else {
                // handle errors here, an exception may be available in
                Exception error = (Exception) data.getSerializableExtra(DropInActivity.EXTRA_ERROR);
            }
        }
    }

    public void setupPaymentProcessing(){
        PaymentProcessing paymentProcessing = new PaymentProcessing(getActivity());
        paymentProcessing.setupBraintreeAndStartExpressCheckout(String.valueOf(cartRecyclerAdapter.getRunningTotal()));
    }


}

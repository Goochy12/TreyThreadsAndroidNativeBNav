package au.com.liamgooch.treythreads.recycler_adapters;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import au.com.liamgooch.treythreads.CartViewModel;
import au.com.liamgooch.treythreads.R;
import au.com.liamgooch.treythreads.cart_data.CartItem;

public class cartRecAdpt extends RecyclerView.Adapter<cartRecAdpt.cartViewHolder> {



    private List<CartItem> cartItems = Collections.emptyList();
    private CartViewModel cartViewModel;
    private Context context;
    private ArrayList<ArrayList<String>> stockQuantities = new ArrayList<>();

    private View snackBarView;

    private int runningTotal = 0;

    //constructor
    public cartRecAdpt(Context context, ArrayList<ArrayList<String>> stockQuantities, View snackBarView){
        this.context = context;
        this.stockQuantities = stockQuantities;
        this.snackBarView = snackBarView;
    }

    //create views
    @NonNull
    @Override
    public cartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        //create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_cards,parent,false);

        cartViewHolder hVH = new cartViewHolder(v);
        cartViewModel = ViewModelProviders.of((FragmentActivity) this.context).get(CartViewModel.class);

        return hVH;
    }

    @Override
    public void onBindViewHolder(@NonNull final cartViewHolder holder, final int position){
        holder.cartCardProgressBar.setVisibility(View.VISIBLE);
        holder.cartCardProgressBar.animate();

        holder.itemText.setText(cartItems.get(position).getItemName());
        holder.itemSize.setText(cartItems.get(position).getItemSize());
        holder.itemColour.setText(cartItems.get(position).getColour());

        //quantity spinner
        final ArrayList<String> quantity = new ArrayList<>();

        //find product max quantity
        ArrayList<String> product = new ArrayList<>();
        int p = 0;
        while (product.isEmpty()){
            if (cartItems.get(position).getProductID().equals(stockQuantities.get(p).get(0))){
                product.add(stockQuantities.get(p).get(0));
                product.add(stockQuantities.get(p).get(1));
            }else{
                p++;
            }
        }

        //add max quantity
        int maxQuan = 0;
//        Log.i("OHERE", "onBindViewHolder: " + cartItems.get(position).getMaxQuantity());
        while (maxQuan < 5 && maxQuan < Integer.parseInt(product.get(1))){
            quantity.add(Integer.toString(maxQuan+1));
            maxQuan++;
        }


        final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(context,android.R.layout.simple_spinner_item,quantity);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        holder.itemQuantity.setAdapter(spinnerAdapter);

        Toast notifyQuantityChanged = Toast.makeText(context, "Quantity updated...", Toast.LENGTH_SHORT);
        //set selected quantity
        int selection = cartItems.get(position).getQuantity() - 1;
        if (maxQuan == 0){
            cartViewModel.deleteItem(cartItems.get(position));
            notifyQuantityChanged.show();
        }else if (selection >= maxQuan){
            selection = maxQuan - 1;

            //NOTIFY QUANTITY CHANGED
            cartViewModel.updateQuantity(cartItems.get(position), selection + 1);
            notifyQuantityChanged.show();
        }
        holder.itemQuantity.setSelection(selection);

        final int[] firstSelection = new int[1];
        final int[] secondSelection = new int[1];


        firstSelection[0] = holder.itemQuantity.getSelectedItemPosition();

        holder.itemQuantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int spinnerPosition, long id) {
                secondSelection[0] = holder.itemQuantity.getSelectedItemPosition();

                if (firstSelection[0] != secondSelection[0]){
                    cartViewModel.updateQuantity(cartItems.get(position),holder.itemQuantity.getSelectedItemPosition() + 1);
                    firstSelection[0] = secondSelection[0];

                    setPrice(holder, position);
                }
//

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        setPrice(holder,position);

        //image
        if(cartItems.get(position).getItemPath() != null && !cartItems.get(position).getItemPath().isEmpty()){
            Picasso.get().load(cartItems.get(position).getItemPath()).into(holder.itemImage);
        }

        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartViewModel.deleteItem(cartItems.get(position));
                notifyDataSetChanged();


            }
        });
    }

    public static Drawable LoadImageFromWeb(String url){
        try{
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src Name");
            return d;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public int getItemCount(){
        return cartItems.size();
    }

    public void setItems(List<CartItem> cartItems){
        this.cartItems = cartItems;
        notifyDataSetChanged();
    }

    public List<CartItem> getUpdatedTasks(){return this.cartItems;}

    //create the view holder
    public static class cartViewHolder extends RecyclerView.ViewHolder{
        public View itemView;
        public ImageView itemImage;
        public TextView itemText;
        public TextView itemPrice;
        public TextView itemSize;
        public TextView itemColour;
        public ImageView removeButton;
        public Spinner itemQuantity;
        public ProgressBar cartCardProgressBar;


        public cartViewHolder(View itemView){
            super(itemView);
            this.itemView = itemView;
            itemText = (TextView) itemView.findViewById(R.id.cartCardHeading);
            itemPrice = (TextView) itemView.findViewById(R.id.cartCardPrice);
            itemSize = (TextView) itemView.findViewById(R.id.cartCardSize);
            itemColour = (TextView) itemView.findViewById(R.id.cartColour);
            itemImage = (ImageView) itemView.findViewById(R.id.cartCardImage);
            itemQuantity = (Spinner) itemView.findViewById(R.id.cartCardSpinner);
            removeButton = (ImageView) itemView.findViewById(R.id.removeFromCartButton);
            cartCardProgressBar = (ProgressBar) itemView.findViewById(R.id.cartCardProgressBar);
        }
    }

    public void setPrice(cartViewHolder holder, int position){
        int price = Integer.parseInt(cartItems.get(position).getItemPrice()) * cartItems.get(position).getQuantity();
        holder.itemPrice.setText("$" + (price));
    }

    public int getRunningTotal(){
        int total = 0;
        for (int i = 0; i < cartItems.size(); i++){
            total += (Integer.parseInt(cartItems.get(i).getItemPrice()) * cartItems.get(i).getQuantity());
        }
        this.runningTotal = total;
        return this.runningTotal;
    }

    public boolean validateCart(){
        boolean valid = true;

        for (int i = 0; i < cartItems.size(); i++){
            if (cartItems.get(i).getQuantity() <= 0){
                valid = false;
                return valid;
            }
        }

        return valid;
    }

}

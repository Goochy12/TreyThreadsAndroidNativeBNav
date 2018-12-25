package au.com.scroogetech.treythreadsandroidnativebnav.recycler_adapters;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import au.com.scroogetech.treythreadsandroidnativebnav.CartViewModel;
import au.com.scroogetech.treythreadsandroidnativebnav.R;
import au.com.scroogetech.treythreadsandroidnativebnav.cart_data.CartItem;

public class cartRecAdpt extends RecyclerView.Adapter<cartRecAdpt.cartViewHolder> {



    private List<CartItem> cartItems = Collections.emptyList();
    private CartViewModel cartViewModel;
    private Context context;
    private Boolean first = true;

    //constructor
    public cartRecAdpt(Context context){
        this.context = context;
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

        holder.itemText.setText(cartItems.get(position).getItemName());
        holder.itemPrice.setText("$" + cartItems.get(position).getItemPrice().toString());
        holder.itemSize.setText(cartItems.get(position).getItemSize());
        holder.itemColour.setText(cartItems.get(position).getColour());

        //quantity spinner
        ArrayList<String> quantity = new ArrayList<>();

        //add max quantity
        int i = 0;
//        Log.i("OHERE", "onBindViewHolder: " + cartItems.get(position).getMaxQuantity());
        while (i < 5 && i < cartItems.get(position).getMaxQuantity()){
            quantity.add(Integer.toString(i+1));
            i++;
        }


        final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(context,android.R.layout.simple_spinner_item,quantity);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        holder.itemQuantity.setAdapter(spinnerAdapter);
        //set selected quantity
        holder.itemQuantity.setSelection(cartItems.get(position).getQuantity() - 1);

        holder.itemQuantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int spinnerPosition, long id) {
//                cartViewModel.updateQuantity(cartItems.get(position), holder.itemQuantity.getSelectedItemPosition() + 1);
                //updateQuan(position, holder.itemQuantity.getSelectedItemPosition() + 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


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

    public void hello(){

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
        }
    }

    public void updateQuan(int position, int quantity){
        cartViewModel.updateQuantity(cartItems.get(position), quantity);
    }

}

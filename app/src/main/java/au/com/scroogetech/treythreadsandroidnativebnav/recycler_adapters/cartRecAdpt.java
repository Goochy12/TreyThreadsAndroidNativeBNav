package au.com.scroogetech.treythreadsandroidnativebnav.recycler_adapters;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import au.com.scroogetech.treythreadsandroidnativebnav.CartViewModel;
import au.com.scroogetech.treythreadsandroidnativebnav.R;
import au.com.scroogetech.treythreadsandroidnativebnav.cart_data.CartItem;

public class cartRecAdpt extends RecyclerView.Adapter<cartRecAdpt.cartViewHolder> {



    private List<CartItem> cartItems = Collections.emptyList();
    private CartViewModel cartViewModel;
    private Context context;

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
    public void onBindViewHolder(@NonNull cartViewHolder holder, int position){
        //holder.itemText.setText(productList[position]);

//        new DownloadImageFromInternet((ImageView) findViewById(R.id.image_view))
//                .execute("https://pbs.twimg.com/profile_images/630285593268752384/iD1MkFQ0.png");
//        if(productImagePath[position] != null && !productImagePath[position].isEmpty()){
//            Picasso.get().load("http://goochystesting.tk/" + productImagePath[position]).into(holder.itemImage);
//        }
        //holder.itemImage.setImageDrawable(LoadImageFromWeb("http://goochystesting.tk/" + productImagePath[position]));

//        Bitmap bitmap = null;
//        try {
//            bitmap = BitmapFactory.decodeStream((InputStream)new URL(productImagePath[position]).getContent());
//            holder.itemImage.setImageBitmap(bitmap);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //holder.itemImage.setImageURI(Uri.parse(productImagePath[position]));

        holder.itemText.setText(cartItems.get(position).getItemName());
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


        public cartViewHolder(View itemView){
            super(itemView);
            this.itemView = itemView;
            itemText = (TextView) itemView.findViewById(R.id.cartCardHeading);
            itemImage = (ImageView) itemView.findViewById(R.id.cartCardImage);
        }
    }

}

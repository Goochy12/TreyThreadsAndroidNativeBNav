package au.com.scroogetech.treythreadsandroidnativebnav.recycler_adapters;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;

import au.com.scroogetech.treythreadsandroidnativebnav.CartViewModel;
import au.com.scroogetech.treythreadsandroidnativebnav.R;
import au.com.scroogetech.treythreadsandroidnativebnav.cart_data.CartItem;

public class storeRecAdpt extends RecyclerView.Adapter<storeRecAdpt.storeViewHolder> {

    private int products;
    private String[] productList;
    private String[] productImagePath;
    private Context context;

    private CartViewModel cartViewModel;

    //constructor
    public storeRecAdpt(int products, String[] productList, String[] productImagePath, Context context){
        this.products = products;
        this.productList = productList;
        this.productImagePath = productImagePath;
        this.context = context;
    }

    //create views
    @NonNull
    @Override
    public storeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        //create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_cards,parent,false);

        storeViewHolder hVH = new storeViewHolder(v);

        return hVH;
    }

    @Override
    public void onBindViewHolder(@NonNull storeViewHolder holder, final int position){
        holder.itemText.setText(productList[position]);

//        new DownloadImageFromInternet((ImageView) findViewById(R.id.image_view))
//                .execute("https://pbs.twimg.com/profile_images/630285593268752384/iD1MkFQ0.png");
        if(productImagePath[position] != null && !productImagePath[position].isEmpty()){
            Picasso.get().load("http://goochystesting.tk/" + productImagePath[position]).into(holder.itemImage);
        }
        //holder.itemImage.setImageDrawable(LoadImageFromWeb("http://goochystesting.tk/" + productImagePath[position]));

//        Bitmap bitmap = null;
//        try {
//            bitmap = BitmapFactory.decodeStream((InputStream)new URL(productImagePath[position]).getContent());
//            holder.itemImage.setImageBitmap(bitmap);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //holder.itemImage.setImageURI(Uri.parse(productImagePath[position]));

        cartViewModel = ViewModelProviders.of((FragmentActivity) context).get(CartViewModel.class);
        holder.addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartItem cartItem = new CartItem(productList[position],"","NULL");
                cartViewModel.insert(cartItem);
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
        return products;
    }





    //create the view holder
    public static class storeViewHolder extends RecyclerView.ViewHolder{
        public View itemView;
        public ImageView itemImage;
        public TextView itemText;
        public Button addToCartButton;


        public storeViewHolder(View itemView){
            super(itemView);
            this.itemView = itemView;
            itemText = (TextView) itemView.findViewById(R.id.storeCardHeading);
            itemImage = (ImageView) itemView.findViewById(R.id.storeCardImage);
            addToCartButton = (Button) itemView.findViewById(R.id.addToCartButton);
        }
    }

}

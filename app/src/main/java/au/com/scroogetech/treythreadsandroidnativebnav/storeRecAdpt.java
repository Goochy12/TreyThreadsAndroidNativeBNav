package au.com.scroogetech.treythreadsandroidnativebnav;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class storeRecAdpt extends RecyclerView.Adapter<storeRecAdpt.storeViewHolder> {

    private int products;
    private String[] productList;
    private String[] productImagePath;

    //constructor
    public storeRecAdpt(int products, String[] productList, String[] productImagePath){
        this.products = products;
        this.productList = productList;
        this.productImagePath = productImagePath;
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
    public void onBindViewHolder(@NonNull storeViewHolder holder, int position){
        holder.itemText.setText(productList[position]);

        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream((InputStream)new URL(productImagePath[position]).getContent());
            holder.itemImage.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //holder.itemImage.setImageURI(Uri.parse(productImagePath[position]));
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


        public storeViewHolder(View itemView){
            super(itemView);
            this.itemView = itemView;
            itemText = (TextView) itemView.findViewById(R.id.storeCardHeading);
            itemImage = (ImageView) itemView.findViewById(R.id.storeCardImage);
        }
    }

}

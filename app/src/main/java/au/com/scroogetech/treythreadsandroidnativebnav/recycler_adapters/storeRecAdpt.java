package au.com.scroogetech.treythreadsandroidnativebnav.recycler_adapters;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import au.com.scroogetech.treythreadsandroidnativebnav.CartViewModel;
import au.com.scroogetech.treythreadsandroidnativebnav.R;
import au.com.scroogetech.treythreadsandroidnativebnav.cart_data.CartItem;

public class storeRecAdpt extends RecyclerView.Adapter<storeRecAdpt.storeViewHolder> {

    private int products;
    private String[] productList;
    private String[] productImagePath;
    private Context context;

    private ArrayList<ArrayList<String>> stockList = new ArrayList<>();

    private CartViewModel cartViewModel;

    //constructor
    public storeRecAdpt(Context context, ArrayList<ArrayList<String>> stockList){
        this.products = products;
        this.productList = productList;
        this.productImagePath = productImagePath;
        this.context = context;

        this.stockList = stockList;
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
        //SET NAME
        holder.itemText.setText(stockList.get(position).get(0));
//        holder.itemText.setText(productList[position]);

//        new DownloadImageFromInternet((ImageView) findViewById(R.id.image_view))
//                .execute("https://pbs.twimg.com/profile_images/630285593268752384/iD1MkFQ0.png");

        //SET IMAGE
        //productImagePath[position] != null && !productImagePath[position].isEmpty()
        if(stockList.get(position).get(1) != null && !stockList.get(position).get(1).isEmpty()){
            Picasso.get().load(stockList.get(position).get(1)).into(holder.itemImage);
        }

        //SET PRICE
        holder.itemPrice.setText("$"+stockList.get(position).get(2));

        //SET SIZES
        ArrayList<String> sizes = formatSizes(stockList.get(position).get(3));

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(context,android.R.layout.simple_spinner_item,sizes);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        holder.sizeList.setAdapter(spinnerAdapter);

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
                CartItem cartItem;
                //CartItem cartItem = cartViewModel.getSameItem("StockName","M");
//                if (cartItem != null){
//                    //cartViewModel
//                }
//                else {
                    cartItem = new CartItem(stockList.get(position).get(0),"M","NULL","NULL",1);
                    cartViewModel.insert(cartItem);
//                }
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
        return stockList.size();
    }


    //create the view holder
    public static class storeViewHolder extends RecyclerView.ViewHolder{
        public View itemView;
        public ImageView itemImage;
        public TextView itemText;
        public TextView itemPrice;
        public Spinner sizeList;
        public Button addToCartButton;


        public storeViewHolder(View itemView){
            super(itemView);
            this.itemView = itemView;
            itemText = (TextView) itemView.findViewById(R.id.storeCardHeading);
            itemImage = (ImageView) itemView.findViewById(R.id.storeCardImage);
            itemPrice = (TextView) itemView.findViewById(R.id.storeCardPrice);
            sizeList = (Spinner) itemView.findViewById(R.id.storeCardSpinner);
            addToCartButton = (Button) itemView.findViewById(R.id.addToCartButton);
        }
    }


    public ArrayList<String> formatSizes(String sizes){
        ArrayList<String> sizeList = new ArrayList<>();

        for (int i = 0; i < sizes.length(); i++){
            if (sizes.substring(i,i+1).equals("S")){
                sizeList.add("S");
            }
            if (sizes.substring(i,i+1).equals("M")){
                sizeList.add("M");
            }
            if (i+2 <= sizes.length() && sizes.substring(i,i+2).equals("L,")){
                sizeList.add("L");
            }
            if (i+2 <= sizes.length() && sizes.substring(i,i+2).equals("XL")){
                sizeList.add("XL");
            }
        }

        return sizeList;
    }

}

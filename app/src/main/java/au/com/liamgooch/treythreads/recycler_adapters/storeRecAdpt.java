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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import au.com.liamgooch.treythreads.CartViewModel;
import au.com.liamgooch.treythreads.R;
import au.com.liamgooch.treythreads.cart_data.CartItem;

public class storeRecAdpt extends RecyclerView.Adapter<storeRecAdpt.storeViewHolder> {

    private Context context;

    private ArrayList<ArrayList<String>> stockList = new ArrayList<>();
    private ArrayList<ArrayList<String>> stockProperties = new ArrayList<>();
    private ArrayList<ArrayList<String>> stockQuantities = new ArrayList<>();
    private int colourSpinnerPos;


    private CartViewModel cartViewModel;

    //constructor
    public storeRecAdpt(Context context, ArrayList<ArrayList<String>> stockList, ArrayList<ArrayList<String>> stockProperties, ArrayList<ArrayList<String>> stockQuantities){

        this.context = context;

        this.stockList = stockList;
        this.stockProperties = stockProperties;
        this.stockQuantities = stockQuantities;
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
    public void onBindViewHolder(@NonNull final storeViewHolder holder, final int position){

        String name;
        String price;
        String id;

        final ArrayList<String> path = new ArrayList<>();
        final ArrayList<String> productIDList = new ArrayList<>();

        id = stockList.get(position).get(0);
        name = stockList.get(position).get(1);
        price = stockList.get(position).get(2);



        ArrayList<String> colourList = new ArrayList<>();

        final ArrayList<ArrayList<Integer>> quantityList = new ArrayList<>();


        //get colours
        for(int i = 0; i < stockProperties.size(); i++){
            if (stockProperties.get(i).get(1).equals(id)){
                colourList.add(stockProperties.get(i).get(2));
//        Log.i("OHERE", "onBindViewHolder: " + name + ", " + stockProperties.get(i).get(1) + ", " + stockProperties.get(i).get(3));

                //get quantities of sizes
//                ArrayList<Integer> tempSizes = new ArrayList<>();
//                tempSizes.add(Integer.parseInt(stockProperties.get(i).get(3)));
//                tempSizes.add(Integer.parseInt(stockProperties.get(i).get(4)));
//                tempSizes.add(Integer.parseInt(stockProperties.get(i).get(5)));
//                tempSizes.add(Integer.parseInt(stockProperties.get(i).get(6)));
//                quantityList.add(tempSizes);
//                Log.i("OHERE", "S: " + tempSizes.get(0));
//                Log.i("OHERE", "M: " + tempSizes.get(1));
//                Log.i("OHERE", "L: " + tempSizes.get(2));
//                Log.i("OHERE", "XL: " + tempSizes.get(3));
//                Log.i("OHERE", "XL: ");

                path.add(stockProperties.get(i).get(3));
                productIDList.add(stockProperties.get(i).get(0));

            }
        }

        String s;
        String m;
        String l;
        String xl;
        ArrayList<Integer> tempSizes = new ArrayList<>();
        ArrayList<String> finished = new ArrayList<>();
        for (int i = 0; i < productIDList.size(); i++){
            int j = 0;

            tempSizes = new ArrayList<>();
            tempSizes.add(0);
            tempSizes.add(0);
            tempSizes.add(0);
            tempSizes.add(0);
            finished = new ArrayList<>();

            while (finished.size() < 4 && j < stockQuantities.size()){
                s = productIDList.get(i) + "-s";
                m = productIDList.get(i) + "-m";
                l = productIDList.get(i) + "-l";
                xl = productIDList.get(i) + "-xl";


                if (s.equals(stockQuantities.get(j).get(0))){
                    tempSizes.set(0,Integer.parseInt(stockQuantities.get(j).get(1)));
                    finished.add("s");
                }
                if (m.equals(stockQuantities.get(j).get(0))){
                    tempSizes.set(1,Integer.parseInt(stockQuantities.get(j).get(1)));
                    finished.add("m");
                }
                if (l.equals(stockQuantities.get(j).get(0))){
                    tempSizes.set(2,Integer.parseInt(stockQuantities.get(j).get(1)));
                    finished.add("l");
                }
                if (xl.equals(stockQuantities.get(j).get(0))){
                    tempSizes.set(3,Integer.parseInt(stockQuantities.get(j).get(1)));
                    finished.add("xl");
                }
                j++;
            }
//            Log.i("OHERE", "S: " + tempSizes.get(0));
//            Log.i("OHERE", "M: " + tempSizes.get(1));
//            Log.i("OHERE", "L: " + tempSizes.get(2));
//            Log.i("OHERE", "XL: " + tempSizes.get(3));
            quantityList.add(tempSizes);
        }

        //GET SIZES AVAILABLE
        ArrayList<String> sizeList = getSizeList(quantityList.get(0));
        if (sizeList.size() == 0){
            holder.addToCartButton.setEnabled(false);
            holder.sizeList.setEnabled(false);
        }


//        path = stockList.get(position).get(1);

        //SET NAME
        holder.itemText.setText(name);


        //SET PRICE
        holder.itemPrice.setText("$"+price);

        //SET SIZES

        final ArrayAdapter<String> sizeSpinnerAdapter = new ArrayAdapter<>(context,android.R.layout.simple_spinner_item,sizeList);
        sizeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        holder.sizeList.setAdapter(sizeSpinnerAdapter);


        //SET COLOURS
        final ArrayAdapter<String> colourSpinnerAdapter = new ArrayAdapter<>(context,android.R.layout.simple_spinner_item,colourList);
        colourSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        holder.colourList.setAdapter(colourSpinnerAdapter);
        //colour listener
        holder.colourList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int spinnerPosition, long id) {
                colourSpinnerPos = spinnerPosition;

                //set image
                setImage(path.get(spinnerPosition),holder);

                //updates sizes
                ArrayList<String> updatedSizeList = getSizeList(quantityList.get(spinnerPosition));
                if (updatedSizeList.size() == 0){
                    holder.addToCartButton.setEnabled(false);
                    holder.sizeList.setEnabled(false);
                }else{
                    holder.addToCartButton.setEnabled(true);
                    holder.sizeList.setEnabled(true);
                }

                setSpinnerAdapter(holder,updatedSizeList);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //SET IMAGE
        //productImagePath[position] != null && !productImagePath[position].isEmpty()
//        if(stockProperties.get(position).get(6) != null && !stockProperties.get(position).get(6).isEmpty()){
//            Picasso.get().load(path.get()).into(holder.itemImage);
//        }


        //Add to cart button
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
                String name;
                String price;
                String pa;
                String colour;
                String productID;

                name = stockList.get(position).get(1);
                pa = path.get(colourSpinnerPos);
                price = stockList.get(position).get(2);
                colour = getSelectedColour(holder);
                productID = productIDList.get(holder.colourList.getSelectedItemPosition()) + "-" + getSelectedSize(holder).toLowerCase();

                cartItem = new CartItem(name,getSelectedSize(holder),price,pa,1, colour, productID);

                List<CartItem> sameItems = cartViewModel.getSameItem(productID);
                if (sameItems.size() <= 0){
                    cartViewModel.insert(cartItem);
                }else{
                    cartViewModel.updateQuantity(sameItems.get(0),sameItems.get(0).getQuantity() + 1);
                }

//                Log.i("OHERE", "onClick: ");
//                    List<CartItem> sameItem = cartViewModel.getSameItem(cartItem);
//                Log.i("OHERE", "onClick1: ");
//                if (sameItem == null){
//                    Log.i("OHERE", "onClick2: ");
//                    cartViewModel.insert(cartItem);
//                }else {
//                    Log.i("OHERE", "onClick3: ");
//                    cartViewModel.updateQuantity(sameItem.get(0), sameItem.get(0).getQuantity() + 1);
//                    }
////                }
            }
        });
//
//        String checkID = productIDList.get(holder.colourList.getSelectedItemPosition()) + "-" + getSelectedSize(holder).toLowerCase();
//        List<CartItem> testItems = cartViewModel.getSameItem(checkID);
//        int maxQuantity = quantityList.get(colourSpinnerPos).get(getMaxQuantPos(holder));
//
//        if (testItems.size() > 0 && testItems.get(0).getQuantity() + 1 >= maxQuantity){
//            holder.addToCartButton.setEnabled(false);
//            holder.sizeList.setEnabled(false);
//        }else{
//            holder.addToCartButton.setEnabled(true);
//            holder.sizeList.setEnabled(true);
//        }
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
        public Spinner colourList;
        public Button addToCartButton;


        public storeViewHolder(View itemView){
            super(itemView);
            this.itemView = itemView;
            itemText = (TextView) itemView.findViewById(R.id.storeCardHeading);
            itemImage = (ImageView) itemView.findViewById(R.id.storeCardImage);
            itemPrice = (TextView) itemView.findViewById(R.id.storeCardPrice);
            sizeList = (Spinner) itemView.findViewById(R.id.storeSizeSpinner);
            colourList = (Spinner) itemView.findViewById(R.id.storeColourSpinner);
            addToCartButton = (Button) itemView.findViewById(R.id.addToCartButton);
        }
    }

    public String getSelectedSize(storeViewHolder holder){
        return holder.sizeList.getSelectedItem().toString();
    }

    public String getSelectedColour(storeViewHolder holder){
        return holder.colourList.getSelectedItem().toString();
//        return (int) holder.colourList.getSelectedItem();
    }

    public int getMaxQuantPos(storeViewHolder holder){
        String size = holder.sizeList.getSelectedItem().toString();
        int pos = 0;

        if (size.equals("S")){
            pos = 0;
        }
        if (size.equals("M")){
            pos = 1;
        }
        if (size.equals("L")){
            pos = 2;
        }
        if (size.equals("XL")){
            pos = 3;
        }

        return pos;
    }

    public void setImage(String path, storeViewHolder holder){
        if(path != null && !path.isEmpty()){
            Picasso.get().load(path).into(holder.itemImage);
        }
    }

    public ArrayList<String> getSizeList(ArrayList<Integer> quantitiesList){
        ArrayList<String> sizeList = new ArrayList<>();

        if (quantitiesList.get(0) > 0){
            sizeList.add("S");
        }
        if (quantitiesList.get(1) > 0){
            sizeList.add("M");
        }
        if (quantitiesList.get(2) > 0){
            sizeList.add("L");
        }
        if (quantitiesList.get(3) > 0){
            sizeList.add("XL");
        }

        return sizeList;
    }

    public void setSpinnerAdapter(storeViewHolder holder, ArrayList<String> sizeList){
        final ArrayAdapter<String> sizeSpinnerAdapter = new ArrayAdapter<>(context,android.R.layout.simple_spinner_item,sizeList);
        sizeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        holder.sizeList.setAdapter(sizeSpinnerAdapter);
    }

}

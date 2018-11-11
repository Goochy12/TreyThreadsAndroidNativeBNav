package au.com.scroogetech.treythreadsandroidnativebnav;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class homeRecAdpt extends RecyclerView.Adapter<homeRecAdpt.HomeViewHolder> {

    private String[] homeData;

    //constructor
    public homeRecAdpt(String[] homeData){
        this.homeData = homeData;
    }

    //create views
    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        //create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_cards,parent,false);

        HomeViewHolder hVH = new HomeViewHolder(v);

        return hVH;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position){
        holder.itemText.setText(homeData[position]);
    }

    @Override
    public int getItemCount(){
        return homeData.length;
    }





    //create the view holder
    public static class HomeViewHolder extends RecyclerView.ViewHolder{
        public View itemView;
        public ImageView itemImage;
        public TextView itemText;


        public HomeViewHolder(View itemView){
            super(itemView);
            this.itemView = itemView;
            itemText = (TextView) itemView.findViewById(R.id.homeCardHeading);
            itemImage = (ImageView) itemView.findViewById(R.id.homeCardImage);
        }
    }

}

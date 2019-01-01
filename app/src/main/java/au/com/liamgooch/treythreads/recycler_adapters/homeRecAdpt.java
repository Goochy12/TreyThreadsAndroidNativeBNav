package au.com.liamgooch.treythreads.recycler_adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import au.com.liamgooch.treythreads.R;

public class homeRecAdpt extends RecyclerView.Adapter<homeRecAdpt.HomeViewHolder> {

    private ArrayList<ArrayList<String>> homeData = new ArrayList<>();

    private Context context;

    private ProgressBar homeProgressBar;

    //constructor
    public homeRecAdpt(ArrayList<ArrayList<String>> homeData, Context context, ProgressBar homeProgressBar){
        this.homeData = homeData;
        this.context = context;
        this.homeProgressBar = homeProgressBar;
    }

    //create views
    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        //create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_cards,parent,false);

        HomeViewHolder hVH = new HomeViewHolder(v);

        homeProgressBar.setVisibility(View.GONE);

        return hVH;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position){
        holder.homeCardProgressBar.setVisibility(View.VISIBLE);
        holder.homeCardProgressBar.animate();

        String title = homeData.get(position).get(0);
        String message = homeData.get(position).get(1);
        final String link = homeData.get(position).get(2);
        String image = homeData.get(position).get(3);
        final String internal = homeData.get(position).get(4);
        String clickable = homeData.get(position).get(5);


        holder.itemTitle.setText(title);
        holder.itemMessage.setText(message);

        //Log.i("ONCLICK", "onBindViewHolder: " + image);
        if(image != null && !image.isEmpty()){
            Picasso.get().load(image).into(holder.itemImage);
        }
//        holder.homeCardProgressBar.setVisibility(View.GONE);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardClicked(internal, link);
            }
        });
    }

    @Override
    public int getItemCount(){
        return homeData.size();
    }





    //create the view holder
    public static class HomeViewHolder extends RecyclerView.ViewHolder{
        public View itemView;
        public ImageView itemImage;
        public TextView itemTitle;
        public TextView itemMessage;
        public ProgressBar homeCardProgressBar;

        public HomeViewHolder(View itemView){
            super(itemView);
            this.itemView = itemView;
            itemTitle = (TextView) itemView.findViewById(R.id.homeCardHeading);
            itemMessage = (TextView) itemView.findViewById(R.id.homeCardInfo);
            itemImage = (ImageView) itemView.findViewById(R.id.homeCardImage);
            homeCardProgressBar = (ProgressBar) itemView.findViewById(R.id.homeCardProgressBar);
        }
    }

    private void cardClicked(String internal, String link){
            if (internal.equals("1")){

            }else{
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                context.startActivity(browserIntent);
            }
        }
    }


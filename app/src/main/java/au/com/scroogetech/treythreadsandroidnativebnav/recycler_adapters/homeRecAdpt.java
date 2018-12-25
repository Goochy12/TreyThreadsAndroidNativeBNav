package au.com.scroogetech.treythreadsandroidnativebnav.recycler_adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import au.com.scroogetech.treythreadsandroidnativebnav.Home;
import au.com.scroogetech.treythreadsandroidnativebnav.R;

public class homeRecAdpt extends RecyclerView.Adapter<homeRecAdpt.HomeViewHolder> {

    private ArrayList<ArrayList<String>> homeData = new ArrayList<>();

    private String title;
    private String message;
    private String link;
    private String image;
    private String internal;
    private String clickable;
    private Context context;

    //constructor
    public homeRecAdpt(ArrayList<ArrayList<String>> homeData, Context context){
        this.homeData = homeData;
        this.context = context;
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
        title = homeData.get(position).get(0);
        message = homeData.get(position).get(1);
        link = homeData.get(position).get(2);
        image = homeData.get(position).get(3);
        internal = homeData.get(position).get(4);
        clickable = homeData.get(position).get(5);


        holder.itemTitle.setText(title);
        holder.itemMessage.setText(message);

        //Log.i("ONCLICK", "onBindViewHolder: " + image);
        if(image != null && !image.isEmpty()){
            Picasso.get().load(image).into(holder.itemImage);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardClicked(link);
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

        public HomeViewHolder(View itemView){
            super(itemView);
            this.itemView = itemView;
            itemTitle = (TextView) itemView.findViewById(R.id.homeCardHeading);
            itemMessage = (TextView) itemView.findViewById(R.id.homeCardInfo);
            itemImage = (ImageView) itemView.findViewById(R.id.homeCardImage);
        }
    }

    private void cardClicked(String link){
            if (internal.equals("1")){

            }else{
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                context.startActivity(browserIntent);
            }
        }
    }


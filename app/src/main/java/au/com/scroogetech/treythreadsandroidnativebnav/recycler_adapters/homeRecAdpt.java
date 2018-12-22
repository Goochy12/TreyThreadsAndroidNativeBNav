package au.com.scroogetech.treythreadsandroidnativebnav.recycler_adapters;

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

import java.util.ArrayList;

import au.com.scroogetech.treythreadsandroidnativebnav.Home;
import au.com.scroogetech.treythreadsandroidnativebnav.R;

public class homeRecAdpt extends RecyclerView.Adapter<homeRecAdpt.HomeViewHolder> {

    private ArrayList<String> homeData = new ArrayList<>();

    //constructor
    public homeRecAdpt(ArrayList<String> homeData){
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
        holder.itemText.setText(homeData.get(position));
    }

    @Override
    public int getItemCount(){
        return homeData.size();
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

}

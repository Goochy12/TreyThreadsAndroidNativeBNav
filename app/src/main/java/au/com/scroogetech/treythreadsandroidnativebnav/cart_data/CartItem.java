package au.com.scroogetech.treythreadsandroidnativebnav.cart_data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "cart_table")
public class CartItem {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int itemID;

    private String itemName;
    //private String[] itemSizeList;
    private String itemPrice;
    private String itemColour;

    public CartItem(String itemName, String itemColour, String itemPrice){
        this.itemName = itemName;
        this.itemColour = itemColour;
        //this.itemSizeList = itemSizeList;
        this.itemPrice = itemPrice;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

//    public String[] getItemSizeList() {
//        return itemSizeList;
//    }
//
//    public void setItemSizeList(String[] itemSizeList) {
//        this.itemSizeList = itemSizeList;
//    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemColour() {
        return itemColour;
    }

    public void setItemColour(String itemColour) {
        this.itemColour = itemColour;
    }
}

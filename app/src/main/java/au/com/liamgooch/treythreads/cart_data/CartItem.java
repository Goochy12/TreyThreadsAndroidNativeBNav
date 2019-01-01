package au.com.liamgooch.treythreads.cart_data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "cart_table")
public class CartItem {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int itemID;

    private String itemName;
    private String itemSize;
    private String itemPrice;
    private String itemPath;
    private int quantity;
    private String colour;
    private String productID;

    public CartItem(String itemName, String itemSize, String itemPrice, String itemPath, int quantity, String colour, String productID){
        this.itemName = itemName;
        this.itemSize = itemSize;
        //this.itemSizeList = itemSizeList;
        this.itemPrice = itemPrice;
        this.itemPath = itemPath;
        this.quantity = quantity;

        this.colour = colour;
        this.productID = productID;
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

    public String getItemSize() {
        return itemSize;
    }

    public void setItemSize(String itemSize) {
        this.itemSize = itemSize;
    }

    public String getItemPath() {
        return itemPath;
    }

    public void setItemPath(String itemPath) {
        this.itemPath = itemPath;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }
}

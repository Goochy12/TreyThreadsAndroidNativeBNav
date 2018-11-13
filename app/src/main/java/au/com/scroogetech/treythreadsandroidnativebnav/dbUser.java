package au.com.scroogetech.treythreadsandroidnativebnav;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class dbUser {
    @PrimaryKey
    public int uid;

    @ColumnInfo //(id = "id")
    public int stockID;

    @ColumnInfo
    public String stockName;
}

package com.lesson.spaceminer.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class SpaceObj implements Parcelable {

    @SerializedName("id")
    private String id;

    @SerializedName("product_id")
    private String productID;

    @SerializedName("status")
    private String status;

    public SpaceObj(String id, String productID, String status) {
        this.id = id;
        this.productID = productID;
        this.status = status;
    }

    protected SpaceObj(Parcel in) {
        id = in.readString();
        productID = in.readString();
        status = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(productID);
        dest.writeString(status);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SpaceObj> CREATOR = new Creator<SpaceObj>() {
        @Override
        public SpaceObj createFromParcel(Parcel in) {
            return new SpaceObj(in);
        }

        @Override
        public SpaceObj[] newArray(int size) {
            return new SpaceObj[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String orderID) {
        this.productID = productID;
    }

}

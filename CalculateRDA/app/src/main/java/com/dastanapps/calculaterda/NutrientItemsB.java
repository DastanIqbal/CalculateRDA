package com.dastanapps.calculaterda;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by IQBAL-MEBELKART on 8/20/2016.
 */

public class NutrientItemsB implements Parcelable {

    public String nutrients;
    public String rda_lower;
    public String rda_upper;
    public String quantity_in_gms;
    public String calories_in_kcal;
    public String lower_calories_in_gram;
    public String upper_calories_in_gram;

    public NutrientItemsB(){

    }

    public NutrientItemsB(Parcel in) {
        nutrients = in.readString();
        rda_lower = in.readString();
        rda_upper = in.readString();
        quantity_in_gms = in.readString();
        calories_in_kcal = in.readString();
        lower_calories_in_gram = in.readString();
        upper_calories_in_gram = in.readString();
    }

    public static final Creator<NutrientItemsB> CREATOR = new Creator<NutrientItemsB>() {
        @Override
        public NutrientItemsB createFromParcel(Parcel in) {
            return new NutrientItemsB(in);
        }

        @Override
        public NutrientItemsB[] newArray(int size) {
            return new NutrientItemsB[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nutrients);
        parcel.writeString(rda_lower);
        parcel.writeString(rda_upper);
        parcel.writeString(quantity_in_gms);
        parcel.writeString(calories_in_kcal);
        parcel.writeString(lower_calories_in_gram);
        parcel.writeString(upper_calories_in_gram);
    }
}

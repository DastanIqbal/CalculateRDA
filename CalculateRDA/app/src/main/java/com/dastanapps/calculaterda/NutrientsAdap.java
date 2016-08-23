package com.dastanapps.calculaterda;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dastanapps.dastanLib.utils.FontUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * *@author : Dastan Iqbal
 *
 * @email : iqbal.ahmed@mebelkart.com
 */

public class NutrientsAdap extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = NutrientsAdap.class.getSimpleName();
    private ArrayList<NutrientItemsB> itemlistArray;
    private Context ctxt;

    public NutrientsAdap(Context ctxt, ArrayList<NutrientItemsB> itemList) {
        this.ctxt = ctxt;
        this.itemlistArray = itemList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctxt).inflate(R.layout.item_nutrients, parent, false);
        RecyclerView.ViewHolder vh = new ItemViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holdr, int position) {
        NutrientItemsB itemListItemB = getItem(position);
        ItemViewHolder holder = ((ItemViewHolder) holdr);
        holder.tv_nutrients.setText(itemListItemB.nutrients);
        holder.tv_rda_lower.setText(itemListItemB.rda_lower + " kcal");
        holder.tv_rda_upper.setText(itemListItemB.rda_upper + " kcal");
        holder.tv_caloriesperqunatity.setText(itemListItemB.calories_in_kcal + " kcal per " + itemListItemB.quantity_in_gms + " gms");
        holder.tv_calories_lower.setText(itemListItemB.lower_calories_in_gram + " gms");
        holder.tv_calories_upper.setText(itemListItemB.upper_calories_in_gram + " gms");
    }

    public NutrientItemsB getItem(int pos) {
        return itemlistArray.get(pos);
    }

    @Override
    public int getItemCount() {
        return itemlistArray.size();
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = ItemViewHolder.class.getSimpleName();

        @Bind(R.id.tv_nutrients)
        public TextView tv_nutrients;
        @Bind(R.id.tv_rda_lower)
        public TextView tv_rda_lower;
        @Bind(R.id.tv_rda_upper)
        public TextView tv_rda_upper;
        @Bind(R.id.tv_caloriesperqunatity)
        public TextView tv_caloriesperqunatity;
        @Bind(R.id.tv_claories_lower)
        public TextView tv_calories_lower;
        @Bind(R.id.tv_caloires_upper)
        public TextView tv_calories_upper;

        public ItemViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            FontUtils.setRobotoMedium(tv_nutrients);
            FontUtils.setRobotoRegular(tv_rda_lower, tv_rda_upper, tv_caloriesperqunatity,
                    tv_calories_lower, tv_calories_upper);
        }
    }
}

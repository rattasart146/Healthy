package com.example.rattasartpc.healthy.Weight;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.rattasartpc.healthy.R;

import java.util.ArrayList;
import java.util.List;

public class WeightAssetAdapter extends ArrayAdapter <WeightAsset>{
    TextView status;
    List<WeightAsset> weightAssets = new ArrayList<>();
    Context context;

    public WeightAssetAdapter (@NonNull Context context, int resource, @NonNull List<WeightAsset> objects) {
        super(context, resource, objects);
        this.context = context;
        this.weightAssets = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View weightItem = LayoutInflater.from(context).inflate(
                R.layout.fragment_weight_asset,
                parent,
                false
        );
        TextView weight = (TextView)weightItem.findViewById(R.id.weight_item_weight);
        TextView date = weightItem.findViewById(R.id.weight_item_date);
        status = weightItem.findViewById(R.id.weight_item_status);

        weight.setText(weightAssets.get(position).getWeight()+" Kg.");
        date.setText(weightAssets.get(position).getDate());

        status.setText(weightAssets.get(position).getStatus());
        statusColor(weightAssets.get(position).getStatus());

        return weightItem;
    }

    private void statusColor(String statusStr){
        if(statusStr.equals("Up")){
            status.setTextColor(Color.parseColor("#FB3353"));
        }else if(statusStr.equals("Down")){
            status.setTextColor(Color.parseColor("#33000000"));
        }else{
            status.setTextColor(Color.parseColor("#33000000"));

        }
    }
}

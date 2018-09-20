package com.example.rattasartpc.healthy.Weight;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.rattasartpc.healthy.R;

import java.util.ArrayList;

public class WeightActivity extends Fragment{

    private String userId;
    private ArrayList<WeightAsset> weightAsset = new ArrayList<WeightAsset>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight, container, false);
    }

//    ListView menuList = getView().findViewById(R.id.menu_list_view);
//    final ArrayAdapter<String> menuAdapter = new ArrayAdapter<String>(
//            getActivity(),
//            android.R.layout.simple_list_item_1,
//            weightAsset
//    );

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}

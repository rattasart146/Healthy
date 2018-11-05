package com.example.rattasartpc.healthy.Sleep;

import android.content.SyncStatusObserver;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.rattasartpc.healthy.MenuActivity;
import com.example.rattasartpc.healthy.R;
import com.example.rattasartpc.healthy.Utils.DBAide;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class SleepActivity extends Fragment{

    private ArrayList<sleepAsset> sleeps = new ArrayList<>();
    private DBAide dbAide;
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sleep, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initBackButton();
        initAddButton();
        initSleepHistory();

    }

    void initSleepHistory(){
        dbAide = new DBAide(getContext());
        mAuth = FirebaseAuth.getInstance();
        sleeps = dbAide.getSleepList(mAuth.getCurrentUser().getUid());
        ListView _sleepList = getView().findViewById(R.id.sleep_list);
        sleepAssetAdapter _sleepAdapter = new sleepAssetAdapter(getActivity(), R.layout.fragment_sleep_asset, sleeps);

        _sleepList.setAdapter(_sleepAdapter);
        _sleepList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                sleepAsset _clickedSleepItem = (sleepAsset) parent.getItemAtPosition(position);
                Bundle bundle = new Bundle();
                bundle.putString("id", Integer.toString(_clickedSleepItem.getId()));
                bundle.putString("wakeUpTime", _clickedSleepItem.getWakeUpTime());
                bundle.putString("sleepTime", _clickedSleepItem.getSleepTime());
                bundle.putString("date", _clickedSleepItem.getDate());

                sleepForm sleepFormFragment = new sleepForm();
                sleepFormFragment.setArguments(bundle);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, sleepFormFragment).commit();
                Log.d("Sleep", "GO TO SLEEP FORM");

            }
        });
    }

    void initBackButton(){
        Button _backBtn = getView().findViewById(R.id.sleep_back);
        _backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new MenuActivity())
                        .commit();
            }
        });
    }

    void initAddButton(){
        Button _addBtn = getView().findViewById(R.id.sleep_add);
        _addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new sleepForm())
                        .commit();
            }
        });
    }
}
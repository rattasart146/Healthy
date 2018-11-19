package com.example.rattasartpc.healthy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.rattasartpc.healthy.Post.PostFragment;
import com.example.rattasartpc.healthy.Sleep.SleepFragment;
import com.example.rattasartpc.healthy.Weight.WeightFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MenuFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    FirebaseUser _user = FirebaseAuth.getInstance().getCurrentUser();
    ArrayList<String> menu = new ArrayList<>();

    public MenuFragment(){

        menu.add("BMI");
        menu.add("Sleep");
        menu.add("Weight");
        menu.add("Post");
        menu.add("Log out");

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (_user.isEmailVerified()){
            ListView menuList = getView().findViewById(R.id.menu_list_view);
            final ArrayAdapter<String> menuAdapter = new ArrayAdapter<String>(
                    getActivity(),
                    android.R.layout.simple_list_item_1,
                    menu
            );

            menuList.setAdapter(menuAdapter);

            menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (menu.get(position).equals("BMI")){
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new BMIFragment()).addToBackStack(null).commit();
                        Log.d("MENU", "Select" + menu.get(position));
                    }
                    if (menu.get(position).equals("Sleep")){
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new SleepFragment()).addToBackStack(null).commit();
                        Log.d("MENU", "Select" + menu.get(position));
                    }
                    if (menu.get(position).equals("Weight")){
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new WeightFragment()).addToBackStack(null).commit();
                        Log.d("MENU", "Select" + menu.get(position));
                    }
                    if (menu.get(position).equals("Post")){
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new PostFragment()).addToBackStack("Menu").commit();
                        Log.d("MENU", "Select" + menu.get(position));
                    }
                    if (menu.get(position).equals("Log out")){
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new LogoutFragment()).addToBackStack(null).commit();
                        Log.d("MENU", "Select" + menu.get(position));
                    }
//                menu.add("new Value");
//                menuAdapter.notifyDataSetChanged();
                }
            });

        }
        else{
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new LogoutFragment()).addToBackStack(null).commit();
        }

    }
}

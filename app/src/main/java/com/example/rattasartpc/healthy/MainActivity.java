package com.example.rattasartpc.healthy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rattasartpc.healthy.Weight.WeightActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setContentView(R.layout.fragment_login);
        if (savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_view, new WeightActivity()) // new fragment will be replaced instead of main_view
                    .commit();
        }
    }
}

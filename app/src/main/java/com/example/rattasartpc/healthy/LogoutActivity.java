package com.example.rattasartpc.healthy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

public class LogoutActivity extends Fragment{
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view,new LoginActivity()).addToBackStack(null).commit();
    }
}

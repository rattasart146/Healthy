package com.example.rattasartpc.healthy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Fragment{

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button buttonLogin = (Button) getView().findViewById(R.id.button_login);
        TextView textRegister = (TextView) getView().findViewById(R.id.register);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView userName = (TextView) getView().findViewById(R.id.user_id);
                TextView password = (TextView) getView().findViewById(R.id.password);
                String userNameStr = userName.getText().toString();
                String passwordStr = password.getText().toString();

                if(userNameStr.isEmpty() || passwordStr.isEmpty()){
                    Toast.makeText(getActivity(),"กรุณาระบุข้อมูลให้ครบถ้วน", Toast.LENGTH_SHORT).show();
                    Log.i("LOGIN","USER OR PASSWORD IS EMPTY");
                }
                else if(userNameStr.equals("admin") == passwordStr.equals("admin")){
                    Toast.makeText(getActivity(),"User or Password ไม่ถูกต้อง", Toast.LENGTH_SHORT).show();
                    Log.i("LOGIN","INVALID USER OR PASSWORD");
                }
                else {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new BMIActivity()).addToBackStack(null).commit();
                    Log.i("LOGIN","GOTO BMI");
                }

            }
        });
        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new RegisterActivity()).addToBackStack(null).commit();
                Log.i("Login","GOTO REGISTER");
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
}

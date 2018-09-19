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

public class RegisterActivity extends Fragment{
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button buttonRegisterDone = getView().findViewById(R.id.button_register);
        buttonRegisterDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView emailRegister = (TextView) getView().findViewById(R.id.register_email);
                TextView passwordRegister = (TextView) getView().findViewById(R.id.register_password);
                TextView rePasswordRegister = (TextView) getView().findViewById(R.id.register_re_password);

                String emailRegisterStr = emailRegister.getText().toString();
                String passwordRegisterStr = passwordRegister.getText().toString();
                String rePasswordRegisterStr = rePasswordRegister.getText().toString();

                if(emailRegisterStr.isEmpty() || passwordRegisterStr.isEmpty() || rePasswordRegisterStr.isEmpty()){
                    Toast.makeText(getActivity(),"กรุณาระบุข้อมูลให้ครบถ้วน", Toast.LENGTH_SHORT).show();
                    Log.i("REGISTER","FIELD NAME IS EMPTY");
                }
                else if(emailRegisterStr.equals("admin")){
                    Toast.makeText(getActivity(),"User นี้มีอยู่ในระบบแล้ว", Toast.LENGTH_SHORT).show();
                    Log.i("REGISTER","USER ALREADY EXIST");
                }
                else{
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new LoginActivity()).addToBackStack(null).commit();
                    Log.i("REGISTER","GOTO LOGIN");
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }
}

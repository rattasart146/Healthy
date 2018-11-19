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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment {

    private FirebaseUser _user = FirebaseAuth.getInstance().getCurrentUser();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_login, container, false);

    }


    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button loginButton = (Button) getView().findViewById(R.id.button_login);

        //Check Loged In
        if(_user != null){
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view,new MenuFragment()).addToBackStack(null).commit();
        }

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                TextView userTextField = (TextView) getView().findViewById(R.id.user_id);
                TextView passwordTextField = (TextView) getView().findViewById(R.id.password);

                String userTextFieldStr = userTextField.getText().toString();
                String passwordTextFieldStr = passwordTextField.getText().toString();

                if(userTextFieldStr.isEmpty() || passwordTextFieldStr.isEmpty()){
                    Log.i("LOGIN","USER OR PASSWORD IS EMPTY");
                    Toast.makeText(getActivity(), "กรุณาระบุ Username หรือ Password",Toast.LENGTH_SHORT).show();
                }else if(userTextField.equals("admin") && passwordTextField.equals("admin")){
                    Log.i("LOGIN","INVALID USER OR PASSWORD");
                    Toast.makeText(getActivity(), "Username และ Password ไม่ถูกต้อง",Toast.LENGTH_SHORT).show();

                }else{
                    final FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    mAuth.signInWithEmailAndPassword(userTextFieldStr, passwordTextFieldStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            _user = FirebaseAuth.getInstance().getCurrentUser();
                            if(task.isSuccessful()){
                                if(_user.isEmailVerified()){
                                    Log.i("LOGIN", "Complete");
                                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new MenuFragment()).addToBackStack(null).commit();
                                }else{
                                    Toast.makeText(getActivity(), "กรุณายืนยัน E-Mail", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(getActivity(), "Loading", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(getActivity(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    });


                }
            }

        });

        TextView signUpButton = (TextView) getView().findViewById(R.id.register);
        signUpButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new RegisterFragment()).addToBackStack(null).commit();
            }
        });

    }

}
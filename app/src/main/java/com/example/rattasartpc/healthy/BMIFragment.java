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

public class BMIFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bmi, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button buttonBMICalculate = getView().findViewById(R.id.button_bmi_calculate);
        Button backButton = getView().findViewById(R.id.bmi_back);

        buttonBMICalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView weightBMI = (TextView) getView().findViewById(R.id.weight);
                TextView heightBMI = (TextView) getView().findViewById(R.id.height);
                String weightBmiStr = weightBMI.getText().toString();
                String heightBmiStr = heightBMI.getText().toString();

                TextView resultCalBmi = (TextView)getView().findViewById(R.id.result_text_bmi);
                TextView textBMIView = (TextView)getView().findViewById(R.id.bmi_view);
                if(weightBmiStr.isEmpty() || heightBmiStr.isEmpty()){
                    Toast.makeText(getActivity(),"กรุณาระบุข้อมูลให้ครบถ้วน", Toast.LENGTH_SHORT).show();
                    Log.i("BMI","Feild is empty");
                }
                else {
                    Float weightBmiInt = Float.parseFloat(weightBMI.getText().toString());
                    Float heightBmiInt = Float.parseFloat(heightBMI.getText().toString())/100;
                    float result = weightBmiInt / (heightBmiInt * heightBmiInt);
                    resultCalBmi.setText("" + result);
                    textBMIView.setText("Your BMI");
                    Log.i("BMI","BMI is value");
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("BACK", "Back to menu");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new MenuFragment()).addToBackStack(null).commit();
            }
        });
    }
}

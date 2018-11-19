package com.example.rattasartpc.healthy.Weight;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.rattasartpc.healthy.MenuFragment;
import com.example.rattasartpc.healthy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Comparator;

public class WeightFragment extends Fragment {
    private String uid;
    private FirebaseUser _user = FirebaseAuth.getInstance().getCurrentUser();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ArrayList<Weight> weight = new ArrayList<Weight>();

    private DocumentSnapshot doc;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);Button backButton = getView().findViewById(R.id.weight_back);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("BACK", "Back to menu");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new MenuFragment()).addToBackStack(null).commit();
            }
        });

        this.uid = _user.getUid();
        final ListView weightList = (ListView) getView().findViewById(R.id.weight_list);
        final WeightAdapter weightAdapter =  new WeightAdapter(
                getActivity(),
                R.layout.fragment_weight_asset,
                weight
        );

        Button addWeightButton = (Button) getView().findViewById(R.id.weight_add);
        addWeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new WeightForm()).addToBackStack(null).commit();
            }
        });
        weightList.setAdapter(weightAdapter);
        weightAdapter.clear();


        db.collection("myfitness").document(uid).collection("weight").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        weight.add(document.toObject(Weight.class));
                }

                }

                weight = checkStatus(weight);




                weightAdapter.sort(new Comparator<Weight>() {
                    @Override
                    public int compare(Weight o1, Weight o2) {
                        return o2.getDate().compareTo(o1.getDate());
                    }
                });

                updateStatusToFirestore(weight);
                weightAdapter.notifyDataSetChanged();
            }
        });

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight,container,false);
    }

    private ArrayList<Weight> checkStatus(ArrayList<Weight> weight) {
        this.uid = _user.getUid();

        for (int i = 1; i < weight.size(); i++) {
            if (weight.get(i - 1).getWeight() > weight.get(i).getWeight()) {
                weight.get(i).setStatus("ลง");
            }else if(weight.get(i - 1).getWeight() == weight.get(i).getWeight()){
                weight.get(i).setStatus("");
            } else {
                weight.get(i).setStatus("ขึ้น");
            }
        }
        return weight;
    }

    private void  updateStatusToFirestore(ArrayList<Weight> weight){
        uid = _user.getUid().toString();
        this.weight = weight;
        int index = 0;
        for(Weight items: this.weight){
            db.collection("myfitness").document(uid).collection("weight").document(items.getDate()).set(this.weight.get(index++));
        }

    }

}
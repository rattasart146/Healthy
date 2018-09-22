package com.example.rattasartpc.healthy.Weight;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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

public class WeightActivity extends Fragment {
    private String uid;
    private FirebaseUser _user = FirebaseAuth.getInstance().getCurrentUser();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ArrayList<WeightAsset> weightAsset = new ArrayList<WeightAsset>();

    private DocumentSnapshot doc;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.uid = _user.getUid();
        final ListView weightList = (ListView) getView().findViewById(R.id.weight_list);
        final WeightAssetAdapter weightAssetAdapter =  new WeightAssetAdapter(
                getActivity(),
                R.layout.fragment_weight_asset,
                weightAsset
        );

        Button addWeightButton = (Button) getView().findViewById(R.id.weight_add);
        addWeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new WeightForm()).addToBackStack(null).commit();
            }
        });
        weightList.setAdapter(weightAssetAdapter);
        weightAssetAdapter.clear();


        db.collection("myfitness").document(uid).collection("weight").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        weightAsset.add(document.toObject(WeightAsset.class));
                }

                }

                weightAsset = checkStatus(weightAsset);




                weightAssetAdapter.sort(new Comparator<WeightAsset>() {
                    @Override
                    public int compare(WeightAsset o1, WeightAsset o2) {
                        return o2.getDate().compareTo(o1.getDate());
                    }
                });

                updateStatusToFirestore(weightAsset);
                weightAssetAdapter.notifyDataSetChanged();
            }
        });

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight,container,false);
    }

    private ArrayList<WeightAsset> checkStatus(ArrayList<WeightAsset> weightAsset) {
        this.uid = _user.getUid();

        for (int i = 1; i < weightAsset.size(); i++) {
            if (weightAsset.get(i - 1).getWeight() > weightAsset.get(i).getWeight()) {
                weightAsset.get(i).setStatus("ลง");
            }else if(weightAsset.get(i - 1).getWeight() == weightAsset.get(i).getWeight()){
                weightAsset.get(i).setStatus("");
            } else {
                weightAsset.get(i).setStatus("ขึ้น");
            }
        }
        return weightAsset;
    }

    private void  updateStatusToFirestore(ArrayList<WeightAsset> weightAsset){
        uid = _user.getUid().toString();
        this.weightAsset = weightAsset;
        int index = 0;
        for(WeightAsset items: this.weightAsset ){
            db.collection("myfitness").document(uid).collection("weight").document(items.getDate()).set(this.weightAsset.get(index++));
        }

    }

}
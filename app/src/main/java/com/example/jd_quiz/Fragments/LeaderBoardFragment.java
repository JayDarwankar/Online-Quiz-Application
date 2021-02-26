package com.example.jd_quiz.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jd_quiz.LeaderboardAdapter;
import com.example.jd_quiz.R;
import com.example.jd_quiz.databinding.FragmentLeaderBoardBinding;
import com.example.jd_quiz.user;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class LeaderBoardFragment extends Fragment {



    public LeaderBoardFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    FragmentLeaderBoardBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentLeaderBoardBinding.inflate(inflater,container,false);
        FirebaseFirestore database=FirebaseFirestore.getInstance();

        ArrayList<user> users=new ArrayList<>();
        LeaderboardAdapter adapter=new LeaderboardAdapter(getContext(),users);

        binding.recycleView.setAdapter(adapter);
        binding.recycleView.setLayoutManager(new LinearLayoutManager(getContext()));

        database.collection("users")
                .orderBy("coins", Query.Direction.DESCENDING).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(DocumentSnapshot snapshot: queryDocumentSnapshots){
                    user user=snapshot.toObject(user.class);
                    users.add(user);
                }
                adapter.notifyDataSetChanged();
            }
        });
        return binding.getRoot();
    }
}
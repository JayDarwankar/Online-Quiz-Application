package com.example.jd_quiz.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jd_quiz.R;
import com.example.jd_quiz.databinding.FragmentWalletBinding;
import com.example.jd_quiz.user;
import com.example.jd_quiz.withdrawl;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;


public class walletFragment extends Fragment {



    public walletFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    FragmentWalletBinding binding;
    FirebaseFirestore database;
    user user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentWalletBinding.inflate(inflater,container,false);
        database=FirebaseFirestore.getInstance();
        database.collection("users")
                .document(FirebaseAuth.getInstance().getUid())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                user=documentSnapshot.toObject(user.class);
                binding.currentCoins.setText(String.valueOf(user.getCoins()));


            }
        });

      binding.sendRequest.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if(user.getCoins()>50000){
                  String uid=FirebaseAuth.getInstance().getUid();
                  String paypal=binding.paypalEmailBox.getText().toString();

                  withdrawl withdrawl=new withdrawl(uid,paypal,user.getName());
                  database.collection("withdraws")
                          .document(uid)
                          .set(withdrawl).addOnSuccessListener(new OnSuccessListener<Void>() {
                      @Override
                      public void onSuccess(Void aVoid) {
                          Toast.makeText(getContext(),"Request send Successfully",Toast.LENGTH_LONG).show();
                      }
                  });

              }else {
                  Toast.makeText(getContext(),"You need more than 50000 coins to withdrawl",Toast.LENGTH_LONG).show();
              }
          }
      });

        return binding.getRoot();
    }
}
package com.example.jd_quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.jd_quiz.SpinWheel.LuckyWheelView;
import com.example.jd_quiz.SpinWheel.model.LuckyItem;
import com.example.jd_quiz.databinding.ActivitySpinnerWheelBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class spinnerWheel extends AppCompatActivity {
    ActivitySpinnerWheelBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySpinnerWheelBinding.inflate(getLayoutInflater());
        getSupportActionBar().hide();

        setContentView(binding.getRoot());

        List<LuckyItem> data = new ArrayList<>();

        LuckyItem luckyItem1 = new LuckyItem();
        luckyItem1.topText = "5";
        luckyItem1.secondaryText = "COINS";
        luckyItem1.textColor = Color.parseColor("#212121");
        luckyItem1.color = Color.parseColor("#eceff1");
        data.add(luckyItem1);

        LuckyItem luckyItem2 = new LuckyItem();
        luckyItem2.topText = "10";
        luckyItem2.secondaryText = "COINS";
        luckyItem2.color = Color.parseColor("#00cf00");
        luckyItem2.textColor = Color.parseColor("#ffffff");
        data.add(luckyItem2);

        LuckyItem luckyItem3 = new LuckyItem();
        luckyItem3.topText = "15";
        luckyItem3.secondaryText = "COINS";
        luckyItem3.textColor = Color.parseColor("#212121");
        luckyItem3.color = Color.parseColor("#eceff1");
        data.add(luckyItem3);

        LuckyItem luckyItem4 = new LuckyItem();
        luckyItem4.topText = "20";
        luckyItem4.secondaryText = "COINS";
        luckyItem4.color = Color.parseColor("#7f00d9");
        luckyItem4.textColor = Color.parseColor("#ffffff");
        data.add(luckyItem4);

        LuckyItem luckyItem5 = new LuckyItem();
        luckyItem5.topText = "25";
        luckyItem5.secondaryText = "COINS";
        luckyItem5.textColor = Color.parseColor("#212121");
        luckyItem5.color = Color.parseColor("#eceff1");
        data.add(luckyItem5);

        LuckyItem luckyItem6 = new LuckyItem();
        luckyItem6.topText = "30";
        luckyItem6.secondaryText = "COINS";
        luckyItem6.color = Color.parseColor("#dc0000");
        luckyItem6.textColor = Color.parseColor("#ffffff");
        data.add(luckyItem6);

        LuckyItem luckyItem7 = new LuckyItem();
        luckyItem7.topText = "35";
        luckyItem7.secondaryText = "COINS";
        luckyItem7.textColor = Color.parseColor("#212121");
        luckyItem7.color = Color.parseColor("#eceff1");
        data.add(luckyItem7);

        LuckyItem luckyItem8 = new LuckyItem();
        luckyItem8.topText = "0";
        luckyItem8.secondaryText = "COINS";
        luckyItem8.color = Color.parseColor("#008bff");
        luckyItem8.textColor = Color.parseColor("#ffffff");
        data.add(luckyItem8);


        binding.wheelView.setData(data);
        binding.wheelView.setRound(5);
        final int[] round = {1};

        if(round[0] <2){
            binding.spinBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    round[0]++;
                    Random r=new Random();
                    int randomNumber=r.nextInt(8);
                    binding.wheelView.startLuckyWheelWithTargetIndex(randomNumber);
                }
            });
        }


        binding.wheelView.setLuckyRoundItemSelectedListener(new LuckyWheelView.LuckyRoundItemSelectedListener() {
            @Override
            public void LuckyRoundItemSelected(int index) {
                updateCoins(index);
            }
        });

    }
    void updateCoins(int index){
        long cash=0;
        switch (index){
            case 0:
                cash=5;
                break;
            case 1:
                cash=10;
                break;
            case 2:
                cash=15;
                break;
            case 3:
                cash=20;
                break;
            case 4:
                cash=25;
                break;
            case 5:
                cash=30;
                break;
            case 6:
                cash=35;
                break;
            case 7:
                cash=0;
                break;

        }
        FirebaseFirestore database=FirebaseFirestore.getInstance();
        database
                .collection("users")
                .document(FirebaseAuth.getInstance().getUid())
                .update("coins", FieldValue.increment(cash)).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(spinnerWheel.this,"Coins Updated successfully",Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

}
package com.example.jd_quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.jd_quiz.databinding.ActivityResultBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

public class result extends AppCompatActivity {
    ActivityResultBinding binding;
    int points=20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityResultBinding.inflate(getLayoutInflater());
        getSupportActionBar().hide();
        setContentView(binding.getRoot());
        ProgressDialog dialog=new ProgressDialog(this);
        dialog.setMessage("Restarting Quiz....");



        binding.RestartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                Intent intent = new Intent(result.this,QuizActivity.class);
                startActivity(intent);

            }
        });
        dialog.dismiss();


        int correctAnswer=getIntent().getIntExtra("correct",0);
        int totalQuestion=getIntent().getIntExtra("total",0);

        long earnedCoins= correctAnswer * points;

        binding.score.setText(String.format("%d/%d",correctAnswer,totalQuestion));
        binding.coins.setText(String.valueOf(earnedCoins));

        FirebaseFirestore database=FirebaseFirestore.getInstance();
        database.collection("users")
                .document(FirebaseAuth.getInstance().getUid())
                .update("coins", FieldValue.increment(earnedCoins));



    }
}
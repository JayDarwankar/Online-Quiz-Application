package com.example.jd_quiz.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jd_quiz.CategoryAdapter;
import com.example.jd_quiz.CategoryModel;
import com.example.jd_quiz.R;
import com.example.jd_quiz.databinding.FragmentHomeBinding;
import com.example.jd_quiz.spinnerWheel;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class HomeFragment extends Fragment {



    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    FragmentHomeBinding binding;
    FirebaseFirestore database;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentHomeBinding.inflate(inflater,container,false);
        database= FirebaseFirestore.getInstance();


        final ArrayList<CategoryModel> categories=new ArrayList<>();
        // categories.add(new CategoryModel("","Mathematics","https://cdn.dribbble.com/users/2552641/screenshots/6549959/icon_challenge_originals_edu2_1x.jpg"));
        //categories.add(new CategoryModel("","Physics","https://cdn.dribbble.com/users/2552641/screenshots/6549959/icon_challenge_originals_edu2_1x.jpg"));
        //categories.add(new CategoryModel("","computer","https://cdn.dribbble.com/users/2552641/screenshots/6549959/icon_challenge_originals_edu2_1x.jpg"));
        //categories.add(new CategoryModel("","Geography","https://cdn.dribbble.com/users/2552641/screenshots/6549959/icon_challenge_originals_edu2_1x.jpg"));
        //categories.add(new CategoryModel("","History","https://cdn.dribbble.com/users/2552641/screenshots/6549959/icon_challenge_originals_edu2_1x.jpg"));
        //categories.add(new CategoryModel("","Chemistry","https://cdn.dribbble.com/users/2552641/screenshots/6549959/icon_challenge_originals_edu2_1x.jpg"));
        final CategoryAdapter adapter=new CategoryAdapter(getContext(),categories);

        database.collection("categories")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        categories.clear();
                        for(DocumentSnapshot snapshot: value.getDocuments()){
                            CategoryModel model=snapshot.toObject(CategoryModel.class);
                            model.setCategoryId(snapshot.getId());
                            categories.add(model);
                        }
                        adapter.notifyDataSetChanged();

                    }
                });
        binding.categoryList.setLayoutManager(new GridLayoutManager(getContext(),2) );
        binding.categoryList.setAdapter(adapter);

        binding.spin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getContext(), spinnerWheel.class);
                startActivity(intent);
            }
        });

        binding.inviteFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createLink();
            }
        });



        // Inflate the layout for this fragment
        return binding.getRoot();
    }
    public void createLink(){
        Log.e("main","create link");
        /*JdQuiz.page.link*/
        DynamicLink dynamicLink = FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLink(Uri.parse("https://www.blueappsoftware.com/"))
                .setDomainUriPrefix("https://JdQuiz.page.link")
                // Open links with this app on Android
                .setAndroidParameters(new DynamicLink.AndroidParameters.Builder().build())
                // Open links with com.example.ios on iOS
                .setIosParameters(new DynamicLink.IosParameters.Builder("com.example.ios").build())
                .buildDynamicLink();

        Uri dynamicLinkUri = dynamicLink.getUri();


    }
}
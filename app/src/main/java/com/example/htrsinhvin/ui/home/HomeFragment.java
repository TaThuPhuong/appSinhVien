package com.example.htrsinhvin.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.htrsinhvin.FACEBOOK.PH18428_Main_FB;
import com.example.htrsinhvin.MAP.PH18428_Main_MAP;
import com.example.htrsinhvin.NEWS.PH18428_Main_NEWS;
import com.example.htrsinhvin.QLSV.Main_QLSV;
import com.example.htrsinhvin.R;
import com.example.htrsinhvin.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    ImageView qlsv,map,news,fb;

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        qlsv = root.findViewById(R.id.qlsv);
        map = root.findViewById(R.id.map);
        news = root.findViewById(R.id.news);
        fb = root.findViewById(R.id.fb);

        qlsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(root.getContext(), Main_QLSV.class);
                startActivity(i);
            }
        });

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(root.getContext(), PH18428_Main_MAP.class);
                startActivity(i);
            }
        });

        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(root.getContext(), PH18428_Main_NEWS.class);
                startActivity(i);
            }
        });

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(root.getContext(), PH18428_Main_FB.class);
                startActivity(i);
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
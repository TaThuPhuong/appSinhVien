package com.example.htrsinhvin.ui.gT;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.htrsinhvin.databinding.FragmentGtBinding;


public class GtFragment extends Fragment {

    private FragmentGtBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GtViewModel galleryViewModel =
                new ViewModelProvider(this).get(GtViewModel.class);

        binding = FragmentGtBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package com.example.sdkunicocheck.ui.customizations.customizations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sdkunicocheck.databinding.FragmentCustomizationsBinding;
import com.example.sdkunicocheck.useful.MyAcessoBio;

public class CustomizationsFragment extends Fragment {

    private FragmentCustomizationsBinding binding;
    ImageView imgDocuments;
    Button btnTakePicture;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCustomizationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        MyAcessoBio myAcessoBio = new MyAcessoBio(getContext());

        imgDocuments = binding.imgDocuments;
        btnTakePicture = binding.btnTakePictureCnh;
        btnTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAcessoBio.takePitureCustomCamera(imgDocuments);
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
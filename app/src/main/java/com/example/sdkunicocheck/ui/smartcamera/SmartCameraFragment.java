package com.example.sdkunicocheck.ui.smartcamera;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.sdkunicocheck.databinding.FragmentSmartCameraBinding;
import com.example.sdkunicocheck.useful.MyAcessoBio;

public class SmartCameraFragment extends Fragment {

    private FragmentSmartCameraBinding binding;
    ImageView imgDocuments;
    Button btnTakePicture;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSmartCameraBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        MyAcessoBio myAcessoBio = new MyAcessoBio(getContext());

        imgDocuments = binding.imgDocuments;
        btnTakePicture = binding.btnTakePictureCnh;
        btnTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAcessoBio.takePitureSmartCamera(imgDocuments);
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
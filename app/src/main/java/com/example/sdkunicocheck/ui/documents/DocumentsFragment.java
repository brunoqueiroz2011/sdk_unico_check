package com.example.sdkunicocheck.ui.documents;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.acesso.acessobio_android.onboarding.types.DocumentType;
import com.example.sdkunicocheck.databinding.FragmentDocumentsBinding;
import com.example.sdkunicocheck.useful.MyAcessoBio;

public class DocumentsFragment extends Fragment {

    private FragmentDocumentsBinding binding;

    ImageView imgDocuments;
    Button btnTakePictureCNH;
    Button btnTakePictureRGFront;
    Button btnTakePictureRGBack;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDocumentsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        MyAcessoBio myAcessoBio = new MyAcessoBio(getContext());

        imgDocuments = binding.imgDocuments;
        btnTakePictureCNH = binding.btnTakePictureCnh;
        btnTakePictureCNH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAcessoBio.takePitureDocument(DocumentType.CNH, imgDocuments);
            }
        });

        btnTakePictureRGFront = binding.btnTakePictureRgFront;
        btnTakePictureRGFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAcessoBio.takePitureDocument(DocumentType.RG_FRENTE, imgDocuments);
            }
        });

        btnTakePictureRGBack = binding.btnTakePictureRgBack;
        btnTakePictureRGBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAcessoBio.takePitureDocument(DocumentType.RG_VERSO, imgDocuments);
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
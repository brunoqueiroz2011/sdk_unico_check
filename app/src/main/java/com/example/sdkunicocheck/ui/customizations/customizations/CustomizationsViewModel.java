package com.example.sdkunicocheck.ui.customizations.customizations;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CustomizationsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CustomizationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is  Take a Picture Customization Camera  fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
package com.example.sdkunicocheck.ui.normalcamera;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NormalCameraViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NormalCameraViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is take a picture normal camera fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
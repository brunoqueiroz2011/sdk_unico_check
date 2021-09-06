package com.example.sdkunicocheck.ui.smartcamera;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SmartCameraViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SmartCameraViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Take a Picture Smart Camera fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
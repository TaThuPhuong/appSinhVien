package com.example.htrsinhvin.ui.gT;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GtViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public GtViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gT fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
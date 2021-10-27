package com.example.finalprojectmobile.ui.clienti;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ClientiViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ClientiViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Client fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
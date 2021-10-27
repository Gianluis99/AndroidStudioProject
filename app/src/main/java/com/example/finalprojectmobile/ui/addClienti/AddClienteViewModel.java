package com.example.finalprojectmobile.ui.addClienti;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddClienteViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public AddClienteViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Add Client fragment");

    }

    public LiveData<String> getText() {
        return mText;
    }
}


package com.example.finalprojectmobile.ui.prenotazioni;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PrenotazioneViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PrenotazioneViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is homeee fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
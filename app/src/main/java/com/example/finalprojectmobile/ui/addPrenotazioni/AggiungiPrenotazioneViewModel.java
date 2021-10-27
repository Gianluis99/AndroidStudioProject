package com.example.finalprojectmobile.ui.addPrenotazioni;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AggiungiPrenotazioneViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AggiungiPrenotazioneViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is AggiungiPrenotazioneViewModel fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}




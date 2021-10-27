package com.example.finalprojectmobile.ui.addPrenotazioni;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.finalprojectmobile.Database.DatabaseDate;
import com.example.finalprojectmobile.Database.DatabaseHandler;
import com.example.finalprojectmobile.R;
import com.example.finalprojectmobile.databinding.FragmentAggiungiprenotazioneBinding;

public class AddPrenotazioneFragment extends Fragment {

    private FragmentAggiungiprenotazioneBinding binding;
    private AggiungiPrenotazioneViewModel aggiungiPrenotazioneViewModel;

    private EditText id;
    private EditText ora;
    private EditText giorno;
    private EditText idCliente;
    private Button aggiungi;
    private DatabaseHandler db;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        aggiungiPrenotazioneViewModel =
                new ViewModelProvider(this).get(AggiungiPrenotazioneViewModel.class);

        binding = FragmentAggiungiprenotazioneBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        id=root.findViewById(R.id.textAddPrenotazioneId);
        ora=root.findViewById(R.id.textAddPrenotazioneOra);
        giorno=root.findViewById(R.id.editTextDatePrenotazione);
        idCliente=root.findViewById(R.id.editTextClienteIdPre);
        aggiungi=root.findViewById(R.id.addPrenotazioneButton);
        db=new DatabaseHandler(this.getContext());


        aggiungiPrenotazioneViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });

        aggiungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= DatabaseDate.getInstance().getEmail();
                if(db.checkPrenotazione(email,id.getText().toString())){
                    Toast.makeText(getContext(), "Id Prenotazione già esistente!", Toast.LENGTH_SHORT).show();
                    return;
                }
                db.insertNewPrenotazione(email,id.getText().toString(),giorno.getText().toString(),
                        ora.getText().toString(),idCliente.getText().toString());
                    Toast.makeText(getContext(), "Prenotazione inserita con successo!", Toast.LENGTH_SHORT).show();

                    clearAll();


            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void clearAll(){
        id.setText("");
        ora.setText("");
        giorno.setText("");
        idCliente.setText("");


    }
}
package com.example.finalprojectmobile.ui.prenotazioni;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.finalprojectmobile.Database.DatabaseDate;
import com.example.finalprojectmobile.Database.DatabaseHandler;
import com.example.finalprojectmobile.EditPrenotazione;
import com.example.finalprojectmobile.Model.Prenotazione;
import com.example.finalprojectmobile.R;
import com.example.finalprojectmobile.databinding.FragmentPrenotazioneBinding;

import java.util.ArrayList;

public class PrenotazioneFragment extends Fragment {

   private PrenotazioneViewModel prenotazioneViewModel;
   private FragmentPrenotazioneBinding binding;
   private ListView listViewPrenotazioni;
   private DatabaseHandler db;
   private ArrayAdapter adapter;

   public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       prenotazioneViewModel =
                new ViewModelProvider(this).get(PrenotazioneViewModel.class);

        binding = FragmentPrenotazioneBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

       db=new DatabaseHandler(this.getContext());
       listViewPrenotazioni=root.findViewById(R.id.listViewPrenotazioni);

       prenotazioneViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            showPrenotazioni();

            }
        });


       binding.listViewPrenotazioni.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
               Prenotazione prenotazioneSelezionata= (Prenotazione) listViewPrenotazioni.getItemAtPosition(i);
                DatabaseDate.getInstance().setPrenotazione(prenotazioneSelezionata);

               Intent changeActivity = new Intent(getContext(), EditPrenotazione.class);
               startActivity(changeActivity);
           }
       });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void showPrenotazioni(){
        ArrayList<Prenotazione> prenotazioni=db.getPrenotazioni(DatabaseDate.getInstance().getEmail());
        adapter=new ArrayAdapter(this.getContext(), android.R.layout.simple_expandable_list_item_1, prenotazioni);
        listViewPrenotazioni.setAdapter(adapter);

    }
}
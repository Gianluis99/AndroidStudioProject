package com.example.finalprojectmobile.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.finalprojectmobile.Database.DatabaseDate;
import com.example.finalprojectmobile.Database.DatabaseHandler;
import com.example.finalprojectmobile.Model.Cliente;
import com.example.finalprojectmobile.Model.Prenotazione;
import com.example.finalprojectmobile.R;
import com.example.finalprojectmobile.databinding.FragmentHomeBinding;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private DatabaseHandler db;

    private TextView numclienti;
    private TextView numPrenotazioni;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        db=new DatabaseHandler(this.getContext());
        numclienti=(TextView) root.findViewById(R.id.textNumClienti);
        numPrenotazioni=(TextView) root.findViewById(R.id.textNumPrenotazioni);

        numclienti.setText(Integer.toString(getNumUtenti()));
        numPrenotazioni.setText(Integer.toString(getNumPrenotazioni()));


        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);

            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private int getNumUtenti()
    {
        ArrayList<Cliente> clienti=db.getClienti(DatabaseDate.getInstance().getEmail());
        int numCLienti=clienti.size();

        return numCLienti;
    }

    private int getNumPrenotazioni()
    {
        ArrayList<Prenotazione> prenotazioni=db.getPrenotazioni(DatabaseDate.getInstance().getEmail());
        int numP=prenotazioni.size();

        return numP;
    }
}
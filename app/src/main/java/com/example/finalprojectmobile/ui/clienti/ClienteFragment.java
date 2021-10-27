package com.example.finalprojectmobile.ui.clienti;

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
import com.example.finalprojectmobile.EditCliente;
import com.example.finalprojectmobile.Model.Cliente;
import com.example.finalprojectmobile.R;
import com.example.finalprojectmobile.databinding.FragmentClienteBinding;

import java.util.ArrayList;

public class ClienteFragment extends Fragment {

    private ClientiViewModel clientiViewModel;
    private FragmentClienteBinding binding;
    private ListView listViewClienti;
    private DatabaseHandler db;
    private ArrayAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        clientiViewModel =
                new ViewModelProvider(this).get(ClientiViewModel.class);

        binding = FragmentClienteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        listViewClienti=root.findViewById(R.id.listViewClienti);
        db=new DatabaseHandler(this.getContext());


        clientiViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                showClienti();

            }
        });


        binding.listViewClienti.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Cliente clienteSelezionato= (Cliente) listViewClienti.getItemAtPosition(i);
                DatabaseDate.getInstance().setCliente(clienteSelezionato);

                Intent changeActivity = new Intent(getContext(), EditCliente.class);
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
    private void showClienti(){
        ArrayList<Cliente> clienti= db.getClienti(DatabaseDate.getInstance().getEmail());
        adapter=new ArrayAdapter(this.getContext(), android.R.layout.simple_expandable_list_item_1, clienti);
        listViewClienti.setAdapter(adapter);

    }
}
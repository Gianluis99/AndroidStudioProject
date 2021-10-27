package com.example.finalprojectmobile.ui.addClienti;

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
import com.example.finalprojectmobile.databinding.FragmentAggiungiclienteBinding;

public class AddClienteFragment extends Fragment {

    private FragmentAggiungiclienteBinding binding;
    private AddClienteViewModel  addClienteViewModel;

    private EditText id;
    private EditText nome;
    private EditText cognome;
    private EditText eta;
    private Button aggiungiCliente;
    private DatabaseHandler db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addClienteViewModel =
                new ViewModelProvider(this).get(AddClienteViewModel.class);

        binding = FragmentAggiungiclienteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        id=(EditText)root.findViewById(R.id.editTextAddClienteID);
        nome=(EditText)root.findViewById(R.id.editTextAddClienteNome);
        cognome=(EditText)root.findViewById(R.id.editTextAddClienteCognome);
        eta=(EditText)root.findViewById(R.id.editTextAddClienteEta);

        aggiungiCliente=root.findViewById(R.id.aggiungiClienteButton);
        db=new DatabaseHandler(this.getContext());

        addClienteViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });

        aggiungiCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String email= DatabaseDate.getInstance().getEmail();
            if(db.checkCliente(email,id.getText().toString())){
                Toast.makeText(getContext(), "Id Cliente già inserito!", Toast.LENGTH_SHORT).show();
                return;
            }

           db.insertNewCliente(email,id.getText().toString(),nome.getText().toString(),
                    cognome.getText().toString(),eta.getText().toString());
               Toast.makeText(getContext(), "Cliente inserito con successo!", Toast.LENGTH_SHORT).show();

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
        nome.setText("");
        cognome.setText("");
        eta.setText("");
    }

}
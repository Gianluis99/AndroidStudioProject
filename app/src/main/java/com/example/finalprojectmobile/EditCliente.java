package com.example.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalprojectmobile.Database.DatabaseDate;
import com.example.finalprojectmobile.Database.DatabaseHandler;

public class EditCliente extends AppCompatActivity {

    private TextView idCliente;
    private EditText nomeCliente;
    private EditText cognomeCliente;
    private EditText etaCliente;
    private Button editCliente;
    private Button deleteCliente;

    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cliente);

        idCliente=(TextView) findViewById(R.id.textViewIdCliente);
        nomeCliente=(EditText) findViewById(R.id.editNomeCliente);
        cognomeCliente=(EditText) findViewById(R.id.editCognomeCliente);
        etaCliente=(EditText) findViewById(R.id.editEtaCliente);
        editCliente=(Button) findViewById(R.id.editClienteButton);
        deleteCliente=(Button) findViewById(R.id.deleteClienteButton);
        db=new DatabaseHandler(this);

        idCliente.setText(DatabaseDate.getInstance().getCliente().getId());
        nomeCliente.setText(DatabaseDate.getInstance().getCliente().getNome());
        cognomeCliente.setText(DatabaseDate.getInstance().getCliente().getCognome());
        etaCliente.setText(DatabaseDate.getInstance().getCliente().getEta());


        deleteCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfermDelete();
            }
        });

        editCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfermEdit();
            }
        });

    }


    private void showConfermEdit(){
        AlertDialog dialog = new AlertDialog.Builder(this).setTitle("Attenzione!")
                .setMessage("Sei sicuro di voler modificare il cliente selezionato?")
                .setPositiveButton("Si", null)
                .setNegativeButton("Cancella",null).show();

        Button positiveButton=dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!db.editCliente(DatabaseDate.getInstance().getEmail(),
                        DatabaseDate.getInstance().getCliente().getId(),nomeCliente.getText().toString(),
                        cognomeCliente.getText().toString(),etaCliente.getText().toString())) {

                    Toast.makeText(getBaseContext(), "Cliente Modificato con successo!", Toast.LENGTH_SHORT).show();
                    Intent changeActivity=new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(changeActivity);
                }

            }
        });
    }

    private void showConfermDelete(){
        AlertDialog dialog = new AlertDialog.Builder(this).setTitle("Attenzione!")
                .setMessage("Sei sicuro di voler eliminare il cliente selezionato?")
                .setPositiveButton("Si", null)
                .setNegativeButton("Cancella",null).show();

        Button positiveButton=dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!db.deleteCliente(DatabaseDate.getInstance().getEmail(),
                        DatabaseDate.getInstance().getCliente().getId().toString())) {

                    Toast.makeText(getBaseContext(), "Cliente Eliminato con successo!", Toast.LENGTH_SHORT).show();
                    Intent changeActivity=new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(changeActivity);
                }

            }
        });
    }

}
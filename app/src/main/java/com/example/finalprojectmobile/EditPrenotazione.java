package com.example.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalprojectmobile.Database.DatabaseDate;
import com.example.finalprojectmobile.Database.DatabaseHandler;

public class EditPrenotazione extends AppCompatActivity {

    private TextView idPrenotazione;
    private TextView giornoPrenotazione;
    private TextView oraPrenotazione;
    private TextView idClientePrenotazione;

    private Button deleteButton;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_prenotazione);
        db=new DatabaseHandler(this);

        idPrenotazione=(TextView) findViewById(R.id.textIdPrenotazione);
        giornoPrenotazione=(TextView) findViewById(R.id.textGiornoPrenotazione);
        oraPrenotazione=(TextView) findViewById(R.id.textOraPrenotazione);
        idClientePrenotazione=(TextView) findViewById(R.id.textIdClientePrenotazione);

        deleteButton=(Button )findViewById(R.id.deletePrenotazione);


        idPrenotazione.setText(DatabaseDate.getInstance().getPrenotazione().getId());
        giornoPrenotazione.setText(DatabaseDate.getInstance().getPrenotazione().getGiorno());
        oraPrenotazione.setText(DatabaseDate.getInstance().getPrenotazione().getOra());
        idClientePrenotazione.setText(DatabaseDate.getInstance().getPrenotazione().getClienteId());



        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogConferm();
            }
        });



    }

    //creiamo il popUp di conferma e se l'utente inserisce si allora la prenotazione verrà eliminata
    private void showDialogConferm(){
        AlertDialog dialog = new AlertDialog.Builder(this).setTitle("Attenzione!")
                .setMessage("Sei sicuro di voler eliminare la prenotazione?")
                .setPositiveButton("Si", null)
                .setNegativeButton("Cancella",null).show();

        Button positiveButton=dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!db.deletePrenotazione(DatabaseDate.getInstance().getEmail(),
                        DatabaseDate.getInstance().getPrenotazione().getId())) {

                    Toast.makeText(getBaseContext(), "Prenotazione Eliminata con successo!", Toast.LENGTH_SHORT).show();
                    Intent changeActivity=new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(changeActivity);
                }

            }
        });
    }

}
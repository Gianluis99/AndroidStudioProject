package com.example.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalprojectmobile.Database.DatabaseHandler;

public class RegisterActivity extends AppCompatActivity {

    EditText email2, password2;
    Button register2;
    DatabaseHandler dh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email2 = (EditText) findViewById(R.id.textEmail2);
        password2 = (EditText) findViewById(R.id.textPassword2);
        register2 = (Button) findViewById(R.id.accediButton2);
        dh=new DatabaseHandler(this);

        register2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String em = email2.getText().toString();
                String pass = password2.getText().toString();

                if (em.equals("") || pass.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Inserisci tutti i valori!", Toast.LENGTH_LONG).show();

                } else {
                    boolean check =dh.checkEmail(em);
                    if (!check) {
                        boolean ins = dh.insertNewUsers(em, pass);
                        if (!ins) {
                            Intent changeActivity = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(changeActivity);
                            Toast.makeText(RegisterActivity.this, "Registrazione avvenuta con successo!", Toast.LENGTH_LONG).show();

                        } else
                            Toast.makeText(RegisterActivity.this, "Non è stato possibile creare l'account!", Toast.LENGTH_LONG).show();

                    } else
                        Toast.makeText(RegisterActivity.this, "Email gia esistente!", Toast.LENGTH_LONG).show();

                }


            }
        });


    }

}
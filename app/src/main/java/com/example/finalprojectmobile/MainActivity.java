package com.example.finalprojectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalprojectmobile.Database.DatabaseHandler;


public class MainActivity extends AppCompatActivity {

    EditText email, password;
    Button login;
    Button goToRegister;
    DatabaseHandler dh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email=(EditText) findViewById(R.id.textEmail);
        password=(EditText) findViewById(R.id.textPassword);
        login=(Button) findViewById(R.id.accediButton);
        goToRegister=(Button) findViewById(R.id.registratiButton);
        dh=new DatabaseHandler(this);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em= email.getText().toString();
                String pass= password.getText().toString();

                if(em.equals("") ||pass.equals("")) {
                    Toast.makeText(MainActivity.this, "Inserisci tutti i valori", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    boolean  check=dh.checkUsers(em,pass);
                    if(check) {
                        Toast.makeText(MainActivity.this, "Login effettuato!", Toast.LENGTH_SHORT).show();
                        Intent changeActivity = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(changeActivity);
                    }
                    else
                        Toast.makeText(MainActivity.this, "Email o password errati!", Toast.LENGTH_SHORT).show();

                }
            }

        });

        goToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changeActivity=new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(changeActivity);

            }

        });

    }
}
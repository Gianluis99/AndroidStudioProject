package com.example.finalprojectmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SendEmail extends AppCompatActivity {

    private EditText oggetto;
    private EditText messaggio;
    private EditText email;
    private Button sendMessageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_send_email);

        oggetto=(EditText) findViewById(R.id.editObjectText);
        messaggio=(EditText)findViewById(R.id.editMessagetext);
        email=(EditText)findViewById(R.id.editTextEmail);
        sendMessageButton=(Button)findViewById(R.id.sendMessageButton);


        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

    }

    private void sendEmail(){
        String  emailToSender=email.getText().toString();
        String subjectEmail=oggetto.getText().toString();
        String messaggioEmail=messaggio.getText().toString();

        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,emailToSender);
        intent.putExtra(Intent.EXTRA_SUBJECT,subjectEmail);
        intent.putExtra(Intent.EXTRA_EMAIL,messaggioEmail);
        intent.setType("message/rfc822");

        startActivity(Intent.createChooser(intent,"Scegli con quale app proseguire:"));


    }
}
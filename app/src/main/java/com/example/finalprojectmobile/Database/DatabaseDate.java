package com.example.finalprojectmobile.Database;

import com.example.finalprojectmobile.Model.Cliente;
import com.example.finalprojectmobile.Model.Prenotazione;

public class DatabaseDate {

    private String email=null;
    private Prenotazione prenotazione;
    private Cliente cliente;

    private static DatabaseDate instance=null;

    private DatabaseDate(){
    }

    public static DatabaseDate getInstance(){
        if(instance==null)
            instance=new DatabaseDate();
        return instance;
    }


    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setPrenotazione(Prenotazione prenotazione) {
        this.prenotazione = prenotazione;
    }


    public Prenotazione getPrenotazione() {
        return prenotazione;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}

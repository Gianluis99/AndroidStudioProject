package com.example.finalprojectmobile.Model;

import java.util.Objects;

public class Cliente {
    private String nome;
    private String cognome;
    private String eta;
    private String id;

    public Cliente(String nome, String cognome, String eta,String id) {
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
        this.id=id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ID Cliente=" +id+
                ", Nome=" + nome +
                ", Cognome=" + cognome+
                ", Eta=" + eta ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(nome, cliente.nome) && Objects.equals(cognome, cliente.cognome) && Objects.equals(eta, cliente.eta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, cognome, eta);
    }
}

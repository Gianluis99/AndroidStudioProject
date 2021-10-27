package com.example.finalprojectmobile.Model;

    import java.util.Objects;

public class Prenotazione {
    private  String ora;
    private String giorno;
    private String  idCliente;
    private String id;

    public Prenotazione(String id, String ora, String giorno, String  idCliente) {
        this.ora = ora;
        this.giorno = giorno;
        this.idCliente = idCliente;
        this.id=id;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public String getGiorno() {
        return giorno;
    }

    public void setGiorno(String giorno) {
        this.giorno = giorno;
    }

    public String getClienteId() {
        return idCliente;
    }

    public void setCliente(Cliente cliente) {
        this.idCliente = idCliente;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ID Prenotazione=" +id+
                ", Ora=" + ora +
                ", Giorno=" + giorno +
                ", Id Cliente=" + idCliente ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prenotazione that = (Prenotazione) o;
        return Objects.equals(ora, that.ora) && Objects.equals(giorno, that.giorno) && Objects.equals(idCliente, that.idCliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ora, giorno, idCliente);
    }
}

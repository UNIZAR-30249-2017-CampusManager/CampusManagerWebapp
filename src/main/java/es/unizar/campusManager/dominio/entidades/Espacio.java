package es.unizar.campusManager.dominio.entidades;

import es.unizar.campusManager.dominio.objetosValor.InformacionEspacio;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Espacio extends Entidad {

    @Id
    private String id;
    private InformacionEspacio informacionEspacio;
    private boolean esReservable;

    public Espacio(){}

    public Espacio(InformacionEspacio informacionEspacio, boolean esReservable) {
        super();
        this.id = super.getId();
        this.informacionEspacio = informacionEspacio;
        this.esReservable = esReservable;
    }

    public String getId() {
        return id;
    }

    public InformacionEspacio getInformacionEspacio() {
        return informacionEspacio;
    }

    public boolean isReservable() {
        return esReservable;
    }

    public void setReservable(boolean nuevo) {
        this.esReservable = nuevo;
    }

    @Override
    public String toString() {
        return "{\"_class\":\"Espacio\", " +
                "\"id\":" + (id == null ? "null" : "\"" + id + "\"") + ", " +
                "\"informacionEspacio\":" + (informacionEspacio == null ? "null" : informacionEspacio) + ", " +
                "\"esReservable\":\"" + esReservable + "\"" +
                "}";
    }
}

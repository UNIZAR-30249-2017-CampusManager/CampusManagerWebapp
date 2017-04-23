package es.unizar.campusManager.dominio.objetosValor;

import javax.persistence.Embeddable;

@Embeddable
public class Espacio {

    private final String idUtc;
    private final String nombreEspacio;
    private final boolean esReservable;
    private final Ubicacion ubicacion;

    public Espacio(){
        this.idUtc = "";
        this.nombreEspacio = "";
        this.esReservable = false;
        this.ubicacion = new Ubicacion();
    }

    public Espacio(String idUtc, String nombreEspacio, boolean esReservable, Ubicacion ubicacion) {
        this.idUtc = idUtc;
        this.nombreEspacio = nombreEspacio;
        this.esReservable = esReservable;
        this.ubicacion = ubicacion;
    }

    public String getIdUtc() {
        return idUtc;
    }

    public String getNombreEspacio() {
        return nombreEspacio;
    }

    public boolean isEsReservable() {
        return esReservable;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    @Override
    public String toString() {
        return "{\"_class\":\"Espacio\", " +
                "\"idUtc\":" + (idUtc == null ? "null" : "\"" + idUtc + "\"") + ", " +
                "\"nombreEspacio\":" + (nombreEspacio == null ? "null" : "\"" + nombreEspacio + "\"") + ", " +
                "\"esReservable\":\"" + esReservable + "\"" + ", " +
                "\"ubicacion\":" + (ubicacion == null ? "null" : ubicacion) +
                "}";
    }
}

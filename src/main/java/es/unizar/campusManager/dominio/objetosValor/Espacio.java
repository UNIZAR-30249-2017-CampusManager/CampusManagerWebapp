package es.unizar.campusManager.dominio.objetosValor;

import javax.persistence.Embeddable;

@Embeddable
public class Espacio {

    private final String idUtc;
    private final String nombreEspacio;
    private final Ubicacion ubicacion;

    public Espacio(){
        this.idUtc = "";
        this.nombreEspacio = "";
        this.ubicacion = new Ubicacion();
    }

    public Espacio(String idUtc, String nombreEspacio, Ubicacion ubicacion) {
        this.idUtc = idUtc;
        this.nombreEspacio = nombreEspacio;
        this.ubicacion = ubicacion;
    }

    public String getIdUtc() {
        return idUtc;
    }

    public String getNombreEspacio() {
        return nombreEspacio;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    @Override
    public String toString() {
        return "{\"_class\":\"Espacio\", " +
                "\"idUtc\":" + (idUtc == null ? "null" : "\"" + idUtc + "\"") + ", " +
                "\"nombreEspacio\":" + (nombreEspacio == null ? "null" : "\"" + nombreEspacio + "\"") + ", " +
                "\"ubicacion\":" + (ubicacion == null ? "null" : ubicacion) +
                "}";
    }
}

package es.unizar.campusManager.dominio.objetosValor;

public class Espacio {

    private final String idUtc;
    private final String nombre;
    private final boolean esReservable;
    private final Ubicacion ubicacion;

    public Espacio(String idUtc, String nombre, boolean esReservable, Ubicacion ubicacion) {
        this.idUtc = idUtc;
        this.nombre = nombre;
        this.esReservable = esReservable;
        this.ubicacion = ubicacion;
    }

    public String getIdUtc() {
        return idUtc;
    }

    public String getNombre() {
        return nombre;
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
                "\"nombre\":" + (nombre == null ? "null" : "\"" + nombre + "\"") + ", " +
                "\"esReservable\":\"" + esReservable + "\"" + ", " +
                "\"ubicacion\":" + (ubicacion == null ? "null" : ubicacion) +
                "}";
    }
}

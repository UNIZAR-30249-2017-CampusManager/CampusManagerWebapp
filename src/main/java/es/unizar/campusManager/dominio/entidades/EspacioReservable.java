package es.unizar.campusManager.dominio.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EspacioReservable extends Entidad {

    @Id
    private String id;
    private String nombreEspacio;
    private String nombreEdificio;
    private Integer planta;

    public EspacioReservable(){}

    public EspacioReservable(String nombreEspacio, String nombreEdificio, Integer planta) {
        super();
        this.id = super.getId();
        this.nombreEspacio = nombreEspacio;
        this.nombreEdificio = nombreEdificio;
        this.planta = planta;
    }

    public String getId() {
        return id;
    }

    public String getNombreEspacio() {
        return nombreEspacio;
    }

    public String getNombreEdificio() {
        return nombreEdificio;
    }

    public Integer getPlanta() {
        return planta;
    }

    @Override
    public String toString() {
        return "{\"_class\":\"EspacioReservable\", " +
                "\"id\":" + (id == null ? "null" : "\"" + id + "\"") + ", " +
                "\"nombreEspacio\":" + (nombreEspacio == null ? "null" : "\"" + nombreEspacio + "\"") + ", " +
                "\"nombreEdificio\":" + (nombreEdificio == null ? "null" : "\"" + nombreEdificio + "\"") + ", " +
                "\"planta\":" + (planta == null ? "null" : "\"" + planta + "\"") +
                "}";
    }
}

package es.unizar.campusManager.dominio.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Reserva extends Entidad {

    @Id
    private String id;
    private String emailProfesor;
    private String fecha;
    private String hora;

    @OneToOne
    private EspacioReservable espacioReservable;

    public Reserva() {
    }

    public Reserva(String emailProfesor, String fecha, String hora, EspacioReservable espacioReservable) {
        super();
        this.id = super.getId();
        this.emailProfesor = emailProfesor;
        this.fecha = fecha;
        this.hora = hora;
        this.espacioReservable = espacioReservable;
    }

    public String getId() {
        return id;
    }

    public String getEmailProfesor() {
        return emailProfesor;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public EspacioReservable getEspacioReservable() {
        return espacioReservable;
    }

    @Override
    public String toString() {
        return "{\"_class\":\"Reserva\", " +
                "\"id\":" + (id == null ? "null" : "\"" + id + "\"") + ", " +
                "\"emailProfesor\":" + (emailProfesor == null ? "null" : "\"" + emailProfesor + "\"") + ", " +
                "\"fecha\":" + (fecha == null ? "null" : "\"" + fecha + "\"") + ", " +
                "\"hora\":" + (hora == null ? "null" : "\"" + hora + "\"") + ", " +
                "\"espacioReservable\":" + (espacioReservable == null ? "null" : espacioReservable) +
                "}";
    }
}

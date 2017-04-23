package es.unizar.campusManager.infraestructura.DTO;

public class ReservaDTO {

    private String emailProfesor;
    private String fecha;
    private String hora;
    private String idEspacioReservable;

    public ReservaDTO(){}

    public ReservaDTO(String emailProfesor, String fecha, String hora, String idEspacioReservable) {
        this.emailProfesor = emailProfesor;
        this.fecha = fecha;
        this.hora = hora;
        this.idEspacioReservable = idEspacioReservable;
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

    public String getIdEspacioReservable() {
        return idEspacioReservable;
    }
}

package es.unizar.campusManager.infraestructura.springDataEntities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class IncidenciaJPA {

    @Id
    private String id;

    private String nombre;
    private String descripcion;
    private String emailTrabajador;
    private String fecha;
    private String estado;
    private Integer grupo;
    private String idUtc;
    private String nombreEspacio;
    private boolean esReservable;
    private Integer planta;
    private String nombreEdificio;
    private String x;
    private String y;
    private String sistemaReferenciaCoordenadas;

    public IncidenciaJPA() {
    }

    public IncidenciaJPA(String id, String nombre, String descripcion, String emailTrabajador, String fecha,
                         String estado, Integer grupo, String idUtc, String nombreEspacio, boolean esReservable,
                         Integer planta, String nombreEdificio, String x, String y,
                         String sistemaReferenciaCoordenadas) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.emailTrabajador = emailTrabajador;
        this.fecha = fecha;
        this.estado = estado;
        this.grupo = grupo;
        this.idUtc = idUtc;
        this.nombreEspacio = nombreEspacio;
        this.esReservable = esReservable;
        this.planta = planta;
        this.nombreEdificio = nombreEdificio;
        this.x = x;
        this.y = y;
        this.sistemaReferenciaCoordenadas = sistemaReferenciaCoordenadas;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getEmailTrabajador() {
        return emailTrabajador;
    }

    public String getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    public Integer getGrupo(){
        return grupo;
    }

    public String getIdUtc() {
        return idUtc;
    }

    public String getNombreEspacio() {
        return nombreEspacio;
    }

    public boolean getEsReservable(){
        return esReservable;
    }

    public Integer getPlanta() {
        return planta;
    }

    public String getNombreEdificio() {
        return nombreEdificio;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    public String getSistemaReferenciaCoordenadas() {
        return sistemaReferenciaCoordenadas;
    }
}

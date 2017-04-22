package es.unizar.campusManager.dominio.entidades;

import es.unizar.campusManager.dominio.objetosValor.Espacio;

public class Incidencia extends Entidad {

    private String id;
    private String nombre;
    private String descripcion;
    private String emailTrabajador;
    private String fecha;
    private String estado;
    private Integer grupo;
    private Espacio espacio;

    public Incidencia(String id, String nombre, String descripcion, String emailTrabajador, String fecha,
                      String estado, Integer grupo, Espacio espacio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.emailTrabajador = emailTrabajador;
        this.fecha = fecha;
        this.estado = estado;
        this.grupo = grupo;
        this.espacio = espacio;
    }

    public Incidencia(String nombre, String descripcion, String fecha, Espacio espacio) {
        super();
        this.id = super.getId();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.espacio = espacio;
        this.grupo = null;
        this.emailTrabajador = null;
        this.estado = "Sin asignar";
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

    public Integer getGrupo() {
        return grupo;
    }

    public Espacio getEspacio() {
        return espacio;
    }

    public void setEmailTrabajador(String emailTrabajador) {
        this.emailTrabajador = emailTrabajador;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setGrupo(Integer grupo) {
        this.grupo = grupo;
    }
}

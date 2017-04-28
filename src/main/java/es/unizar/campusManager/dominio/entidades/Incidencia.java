package es.unizar.campusManager.dominio.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Incidencia extends Entidad {

    @Id
    private String id;
    private String nombre;
    private String descripcion;
    private String emailTrabajador;
    private String fecha;
    private String estado;
    private Integer grupo;

    @OneToOne
    private Espacio espacio;

    public Incidencia(){}

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

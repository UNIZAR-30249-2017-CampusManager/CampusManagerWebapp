package es.unizar.campusManager.infraestructura.DTO;

public class IncidenciaDTO {

    private String id;
    private String nombre;
    private String descripcion;
    private String nombreEspacio;
    private String nombreEdificio;
    private String estado;
    private String emailTrabajador;
    private String fecha;
    private Integer grupo;
    private String fechaLimite;

    public IncidenciaDTO(){}

    public IncidenciaDTO(String id, String nombre, String descripcion, String nombreEspacio, String nombreEdificio, String estado, String emailTrabajador, String fecha,
                         Integer grupo, String fechaLimite) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nombreEspacio = nombreEspacio;
        this.nombreEdificio = nombreEdificio;
        this.estado = estado;
        this.emailTrabajador = emailTrabajador;
        this.fecha = fecha;
        this.grupo = grupo;
        this.fechaLimite = fechaLimite;
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

    public String getNombreEspacio() {
        return nombreEspacio;
    }

    public String getNombreEdificio() {
        return nombreEdificio;
    }

    public String getEstado() {
        return estado;
    }

    public String getEmailTrabajador() {
        return emailTrabajador;
    }

    public String getFecha() {
        return fecha;
    }

    public Integer getGrupo(){
        return grupo;
    }

    public String getFechaLimite() {
        return fechaLimite;
    }

    @Override
    public String toString() {
        return "{\"_class\":\"IncidenciaDTO\", " +
                "\"id\":" + (id == null ? "null" : "\"" + id + "\"") + ", " +
                "\"nombre\":" + (nombre == null ? "null" : "\"" + nombre + "\"") + ", " +
                "\"descripcion\":" + (descripcion == null ? "null" : "\"" + descripcion + "\"") + ", " +
                "\"nombreEspacio\":" + (nombreEspacio == null ? "null" : "\"" + nombreEspacio + "\"") + ", " +
                "\"nombreEdificio\":" + (nombreEdificio == null ? "null" : "\"" + nombreEdificio + "\"") + ", " +
                "\"estado\":" + (estado == null ? "null" : "\"" + estado + "\"") + ", " +
                "\"emailTrabajador\":" + (emailTrabajador == null ? "null" : "\"" + emailTrabajador + "\"") + ", " +
                "\"fecha\":" + (fecha == null ? "null" : "\"" + fecha + "\"") + ", " +
                "\"grupo\":" + (grupo == null ? "null" : "\"" + grupo + "\"") + ", " +
                "\"fechaLimite\":" + (fechaLimite == null ? "null" : "\"" + fechaLimite + "\"") +
                "}";
    }
}

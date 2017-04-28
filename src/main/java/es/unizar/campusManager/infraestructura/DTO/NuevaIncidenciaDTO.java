package es.unizar.campusManager.infraestructura.DTO;

public class NuevaIncidenciaDTO {

    private String nombre;
    private String descripcion;
    private String idUtc;

    public NuevaIncidenciaDTO(){}

    public NuevaIncidenciaDTO(String nombre, String descripcion, String idUtc) {
        this.idUtc = idUtc;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getIdUtc() {
        return idUtc;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

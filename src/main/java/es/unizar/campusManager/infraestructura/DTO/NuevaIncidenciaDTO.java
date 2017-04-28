package es.unizar.campusManager.infraestructura.DTO;

public class NuevaIncidenciaDTO {

    private String nombre;
    private String descripcion;
    private String idEspacio;

    public NuevaIncidenciaDTO(){}

    public NuevaIncidenciaDTO(String nombre, String descripcion, String idEspacio) {
        this.idEspacio = idEspacio;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getIdEspacio() {
        return idEspacio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

package es.unizar.campusManager.infraestructura.DTO;

public class NuevaIncidenciaDTO {

    private String x;
    private String y;
    private String nombreEdificio;
    private Integer planta;
    private String idUtc;
    private String nombreEspacio;
    private String nombre;
    private String descripcion;

    public NuevaIncidenciaDTO(String x, String y, String nombreEdificio, Integer planta, String idUtc, String nombreEspacio, String nombre, String descripcion) {
        this.x = x;
        this.y = y;
        this.nombreEdificio = nombreEdificio;
        this.planta = planta;
        this.idUtc = idUtc;
        this.nombreEspacio = nombreEspacio;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    public String getNombreEdificio() {
        return nombreEdificio;
    }

    public Integer getPlanta() {
        return planta;
    }

    public String getIdUtc() {
        return idUtc;
    }

    public String getNombreEspacio() {
        return nombreEspacio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

package es.unizar.campusManager.dominio.objetosValor;


public class Ubicacion {

    private final Integer planta;
    private final String nombreEdificio;
    private final Coordenadas coordenadas;

    public Ubicacion(Integer planta, String nombreEdificio, Coordenadas coordenadas) {
        this.planta = planta;
        this.nombreEdificio = nombreEdificio;
        this.coordenadas = coordenadas;
    }

    public Integer getPlanta() {
        return planta;
    }

    public String getNombreEdificio() {
        return nombreEdificio;
    }

    public Coordenadas getCoordenadas() {
        return coordenadas;
    }

    @Override
    public String toString() {
        return "{\"_class\":\"Ubicacion\", " +
                "\"planta\":" + (planta == null ? "null" : "\"" + planta + "\"") + ", " +
                "\"nombreEdificio\":" + (nombreEdificio == null ? "null" : "\"" + nombreEdificio + "\"") + ", " +
                "\"coordenadas\":" + (coordenadas == null ? "null" : coordenadas) +
                "}";
    }
}

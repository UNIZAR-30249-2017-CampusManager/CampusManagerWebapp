package es.unizar.campusManager.dominio.objetosValor;

import javax.persistence.Embeddable;

@Embeddable
public class InformacionEspacio {

    private final String idEspacio;
    private final String nombreEspacio;
    private final String edifico;

    public InformacionEspacio() {
        this.idEspacio = "";
        this.nombreEspacio = "";
        this.edifico = "";
    }

    public InformacionEspacio(String idEspacio, String nombreEspacio, String edifico) {
        this.idEspacio = idEspacio;
        this.nombreEspacio = nombreEspacio;
        this.edifico = edifico;
    }

    public String getIdEspacio() {
        return idEspacio;
    }

    public String getNombreEspacio() {
        return nombreEspacio;
    }

    public String getEdifico() {
        return edifico;
    }

    @Override
    public String toString() {
        return "{\"_class\":\"InformacionEspacio\", " +
                "\"idEspacio\":" + (idEspacio == null ? "null" : "\"" + idEspacio + "\"") + ", " +
                "\"nombreEspacio\":" + (nombreEspacio == null ? "null" : "\"" + nombreEspacio + "\"") + ", " +
                "\"edifico\":" + (edifico == null ? "null" : "\"" + edifico + "\"") +
                "}";
    }
}

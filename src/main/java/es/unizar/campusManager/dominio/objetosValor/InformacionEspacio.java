package es.unizar.campusManager.dominio.objetosValor;

import javax.persistence.Embeddable;

@Embeddable
public class InformacionEspacio {

    private final String idUtc;
    private final String nombreEspacio;
    private final String edifico;

    public InformacionEspacio() {
        this.idUtc = "";
        this.nombreEspacio = "";
        this.edifico = "";
    }

    public InformacionEspacio(String idUtc, String nombreEspacio, String edifico) {
        this.idUtc = idUtc;
        this.nombreEspacio = nombreEspacio;
        this.edifico = edifico;
    }

    public String getIdUtc() {
        return idUtc;
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
                "\"idUtc\":" + (idUtc == null ? "null" : "\"" + idUtc + "\"") + ", " +
                "\"nombreEspacio\":" + (nombreEspacio == null ? "null" : "\"" + nombreEspacio + "\"") + ", " +
                "\"edifico\":" + (edifico == null ? "null" : "\"" + edifico + "\"") +
                "}";
    }
}

package es.unizar.campusManager.dominio.objetosValor;

import javax.persistence.Embeddable;

@Embeddable
public class MenuCafeteria {

    private final String lunes;
    private final String martes;
    private final String miercoles;
    private final String jueves;
    private final String viernes;

    public MenuCafeteria(){
        this.lunes = "";
        this.martes = "";
        this.miercoles = "";
        this.jueves = "";
        this.viernes = "";
    }

    public MenuCafeteria(String lunes, String martes, String miercoles, String jueves, String viernes) {
        this.lunes = lunes;
        this.martes = martes;
        this.miercoles = miercoles;
        this.jueves = jueves;
        this.viernes = viernes;
    }

    public String getLunes() {
        return lunes;
    }

    public String getMartes() {
        return martes;
    }

    public String getMiercoles() {
        return miercoles;
    }

    public String getJueves() {
        return jueves;
    }

    public String getViernes() {
        return viernes;
    }

    @Override
    public String toString() {
        return "{\"_class\":\"MenuCafeteria\", " +
                "\"lunes\":" + (lunes == null ? "null" : "\"" + lunes + "\"") + ", " +
                "\"martes\":" + (martes == null ? "null" : "\"" + martes + "\"") + ", " +
                "\"miercoles\":" + (miercoles == null ? "null" : "\"" + miercoles + "\"") + ", " +
                "\"jueves\":" + (jueves == null ? "null" : "\"" + jueves + "\"") + ", " +
                "\"viernes\":" + (viernes == null ? "null" : "\"" + viernes + "\"") +
                "}";
    }
}

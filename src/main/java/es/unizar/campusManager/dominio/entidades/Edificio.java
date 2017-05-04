package es.unizar.campusManager.dominio.entidades;

import es.unizar.campusManager.dominio.objetosValor.MenuCafeteria;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.List;

@Entity
public class Edificio extends Entidad {

    @Id
    private String id;

    private String nombre;
    private String horaApertura;
    private String horaCierre;
    @ElementCollection(targetClass=String.class, fetch = FetchType.EAGER)
    private List<String> mesesCerrado;
    private MenuCafeteria menuCafeteria;


    public Edificio(){}

    public Edificio(String nombre, String horaApertura, String horaCierre, List<String> mesesCerrado,
                    MenuCafeteria menuCafeteria) {
        super();
        this.id = super.getId();
        this.nombre = nombre;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
        this.mesesCerrado = mesesCerrado;
        this.menuCafeteria = menuCafeteria;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getHoraApertura() {
        return horaApertura;
    }

    public String getHoraCierre() {
        return horaCierre;
    }

    public List<String> getMesesCerrado() {
        return mesesCerrado;
    }

    public MenuCafeteria getMenuCafeteria() {
        return menuCafeteria;
    }

    public void setHoraApertura(String horaApertura) {
        this.horaApertura = horaApertura;
    }

    public void setHoraCierre(String horaCierre) {
        this.horaCierre = horaCierre;
    }

    public void setMesesCerrado(List<String> mesesCerrado) {
        this.mesesCerrado = mesesCerrado;
    }

    public void setMenuCafeteria(MenuCafeteria menuCafeteria) {
        this.menuCafeteria = menuCafeteria;
    }

    @Override
    public String toString() {
        return "{\"_class\":\"Edificio\", " +
                "\"id\":" + (id == null ? "null" : "\"" + id + "\"") + ", " +
                "\"nombre\":" + (nombre == null ? "null" : "\"" + nombre + "\"") + ", " +
                "\"horaApertura\":" + (horaApertura == null ? "null" : "\"" + horaApertura + "\"") + ", " +
                "\"horaCierre\":" + (horaCierre == null ? "null" : "\"" + horaCierre + "\"") + ", " +
                "\"mesesCerrado\":" + (mesesCerrado == null ? "null" : Arrays.toString(mesesCerrado.toArray())) + ", " +
                "\"menuCafeteria\":" + (menuCafeteria == null ? "null" : menuCafeteria) +
                "}";
    }
}

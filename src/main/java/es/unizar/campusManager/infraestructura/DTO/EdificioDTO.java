package es.unizar.campusManager.infraestructura.DTO;

import es.unizar.campusManager.dominio.objetosValor.MenuCafeteria;

import java.util.List;

public class EdificioDTO {

    private String horaApertura;
    private String horaCierre;
    private List<String> mesesCerrado;
    private MenuCafeteria menuCafeteria;

    public EdificioDTO() {
    }

    public EdificioDTO(String horaApertura, String horaCierre, List<String> mesesCerrado, MenuCafeteria menuCafeteria) {
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
        this.mesesCerrado = mesesCerrado;
        this.menuCafeteria = menuCafeteria;
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
}

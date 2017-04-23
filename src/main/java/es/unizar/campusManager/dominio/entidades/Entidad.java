package es.unizar.campusManager.dominio.entidades;

import java.util.UUID;

public class Entidad {

    private String id;

    public Entidad(){
        this.id = UUID.randomUUID().toString();
    }

    public String getId(){
        return this.id;
    }

}

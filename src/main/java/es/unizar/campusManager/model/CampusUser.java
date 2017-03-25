package es.unizar.campusManager.model;

import javax.persistence.*;

@Entity
public class CampusUser {

    @Id
    private String email;

    private String password;

    private String name;

    private String surname;

    private String role;

    private static final String ADMIN = "ADMIN";
    private static final String WORKER = "WORKER";
    private static final String PROFFESOR = "PROFFESOR";


    public CampusUser() {
    }

    public CampusUser(String email, String password, String name,String surname,String role) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.password = password;
        role = role.toUpperCase();
        if(!role.equals(ADMIN) & !role.equals(WORKER) & !role.equals(PROFFESOR)){
            System.err.println("El rol introducido no existe");
        }
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String toString() {
        return "{" +
                "\"name\":\"" + name + '\"' +
                ", \"surname\":\"" + surname + '\"' +
                ", \"email\":\"" + email + '\"' +
                ", \"password\":\"" + password + '\"' +
                ", \"role\":\"" + getRole() + '\"' +
                '}';
    }

    public String getRole(){
        return role;
    }

    public boolean isAdmin(){
        return role.equals(ADMIN);
    }
    public boolean isWorker(){
        return role.equals(WORKER);
    }
    public boolean isProffesor(){
        return role.equals(PROFFESOR);
    }

}

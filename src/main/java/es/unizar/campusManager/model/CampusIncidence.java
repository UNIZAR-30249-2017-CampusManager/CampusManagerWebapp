package es.unizar.campusManager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class CampusIncidence {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String place;
    private String status;
    private String workerEmail;
    private Date fecha;

    private static final String UNASSIGNED = "UNASSIGNED";
    private static final String ASSIGNED = "ASSIGNED";
    private static final String INPROGRESS = "INPROGRESS";
    private static final String INVALID = "INVALID";
    private static final String FINALIZED = "FINALIZED";

    public CampusIncidence() {

    }

    public CampusIncidence(String name, String description, String place) {
        this.name = name;
        this.description = description;
        this.place = place;
        status = UNASSIGNED;
        fecha = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWorkerEmail() {
        return workerEmail;
    }

    public void setWorkerEmail(String workerEmail) {
        this.workerEmail = workerEmail;
    }


    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        return "{" +
                "\"name\":\"" + name + '\"' +
                ", \"description\":\"" + description + '\"' +
                ", \"place\":\"" + place + '\"' +
                ", \"status\":\"" + status + '\"' +
                ", \"workerEmail\":\"" + workerEmail + '\"' +
                ", \"fecha\":\"" + sdf.format(fecha) + '\"' +
                '}';
    }
}

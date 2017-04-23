package es.unizar.campusManager.infraestructura.repository;

import es.unizar.campusManager.dominio.entidades.Reserva;
import es.unizar.campusManager.dominio.repository.ReservaRepository;
import es.unizar.campusManager.infraestructura.springData.ReservaRepositorySpring;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ReservaRepositoryImp implements ReservaRepository {

    @Autowired
    private ReservaRepositorySpring reservaRepositorySpring;


    @Override
    public List<Reserva> findByDate(String date) {
        return reservaRepositorySpring.findByFecha(date);
    }

    @Override
    public boolean save(Reserva reserva) {
        Reserva reservaResult = reservaRepositorySpring.save(reserva);

        return reservaResult != null;
    }

    @Override
    public boolean delete(Reserva reserva) {
        reservaRepositorySpring.delete(reserva);

        return (reservaRepositorySpring.findOne(reserva.getId()) == null);
    }
}

package es.unizar.campusManager.infraestructura;

import es.unizar.campusManager.infraestructura.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    TrabajadorRepositoryImp trabajadorRepositoryImp(){
        return new TrabajadorRepositoryImp();
    }

    @Bean
    AdminRepositoryImp adminRepositoryImp(){
        return new AdminRepositoryImp();
    }

    @Bean
    IncidenciaRepositoryImp incidenciaRepositoryImp(){
        return new IncidenciaRepositoryImp();
    }

    @Bean
    EdificioRepositoryImp edificioRepositoryImp() {
        return new EdificioRepositoryImp();
    }

    @Bean
    EspacioReservableRepositoryImp espacioReservableRepositoryImp (){
        return new EspacioReservableRepositoryImp();
    }

    @Bean
    ReservaRepositoryImp reservaRepositoryImp(){
        return new ReservaRepositoryImp();
    }
}

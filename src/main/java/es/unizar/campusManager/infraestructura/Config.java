package es.unizar.campusManager.infraestructura;

import es.unizar.campusManager.infraestructura.repository.AdminRepositoryImp;
import es.unizar.campusManager.infraestructura.repository.EdificioRepositoryImp;
import es.unizar.campusManager.infraestructura.repository.IncidenciaRepositoryImp;
import es.unizar.campusManager.infraestructura.repository.TrabajadorRepositoryImp;
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
}

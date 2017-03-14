package es.unizar.campusManager;

import es.unizar.campusManager.model.CampusUser;
import es.unizar.campusManager.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CampusManagerWebappApplication  implements CommandLineRunner {

	@Autowired
	private UserRepository u;

	public static void main(String[] args) {
		SpringApplication.run(CampusManagerWebappApplication.class, args);
	}

	@Override
	public void run(String... args){

	}
}

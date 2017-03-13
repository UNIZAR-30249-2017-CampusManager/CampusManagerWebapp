package es.unizar.campusManager;

import com.sun.org.apache.xpath.internal.SourceTree;
import es.unizar.campusManager.model.User;
import es.unizar.campusManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

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

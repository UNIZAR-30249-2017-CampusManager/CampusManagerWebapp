package es.unizar.campusManager;

import es.unizar.campusManager.dominio.entidades.Espacio;
import es.unizar.campusManager.dominio.objetosValor.InformacionEspacio;
import es.unizar.campusManager.infraestructura.repository.EspacioRepositoryImp;
import es.unizar.campusManager.infraestructura.springData.EspacioRepositorySpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@SpringBootApplication
public class CampusManagerWebappApplication  implements CommandLineRunner {

	@Autowired
	private EspacioRepositorySpring espacioRepositorySpring;

	public static void main(String[] args) {
		SpringApplication.run(CampusManagerWebappApplication.class, args);
	}

	@Override
	public void run(String... args){
		File f = new File("datos.txt");
		try {
			Scanner s = new Scanner(f);

			espacioRepositorySpring.deleteAll();
			s.nextLine();
			while(s.hasNextLine()){
				String line = s.nextLine();
				line = line.replaceAll("\"","");

				String[] campos = line.split(",");

				//campos[1] = idEdificio, campos[2] = idUtc, campos[3] = idCentro

				String idUtc = campos[2];
				String nombreEspacio = campos[3];
				nombreEspacio = nombreEspacio.trim().replaceAll(" +", " ");

				if(!nombreEspacio.equals("")){
					String edificio;

					if(campos[1].equals("CRE.1065.")){
						edificio = "Torres Quevedo";
					} else if(campos[1].equals("CRE.1200.")){
						edificio = "Ada Byron";
					} else {
						edificio = "Betancourt";
					}


					System.out.println(idUtc + " " + nombreEspacio + " " + edificio);

					espacioRepositorySpring.save(new Espacio(new InformacionEspacio(idUtc,nombreEspacio,edificio),false));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}

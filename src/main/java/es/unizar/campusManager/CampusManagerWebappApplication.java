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

		//Descomentar si se quiere cargar datos de espacios nuevamente

//		File f = new File("datos.txt");
//		try {
//			Scanner s = new Scanner(f);
//			s.nextLine();
//			while(s.hasNextLine()){
//				String line = s.nextLine();
//				line = line.replaceAll("\"","");
//
//				String[] campos = line.split(",");
//
//				//campos[1] = idEdificio, campos[2] = idUtc, campos[3] = idCentro
//
//				String idEspacio = campos[0];
//				String nombreEspacio = campos[3];
//				nombreEspacio = nombreEspacio.trim().replaceAll(" +", " ");
//
//				if(!nombreEspacio.equals("")){
//					String edificio;
//
//					switch (campos[1]) {
//						case "CRE.1065.":
//							edificio = "Torres Quevedo";
//							break;
//						case "CRE.1200.":
//							edificio = "Ada Byron";
//							break;
//						default:
//							edificio = "Betancourt";
//							break;
//					}
//
//
//					System.out.println(idEspacio + " " + nombreEspacio + " " + edificio);
//
//					espacioRepositorySpring.save(new Espacio(new InformacionEspacio(idEspacio,nombreEspacio,edificio),false));
//				}
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
	}
}

package uniandes.edu.co.demo;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import uniandes.edu.co.demo.modelo.Bar;
import uniandes.edu.co.demo.modelo.Usuario;
import uniandes.edu.co.demo.repository.BarRepository;
import uniandes.edu.co.demo.repository.UsuarioRepository;
import uniandes.edu.co.demo.repository.BarRepository.RespuestaGrupo;

@ComponentScan({"uniandes.edu.co.demo.repository"})
@SpringBootApplication
public class DemoApplication  implements CommandLineRunner{

	@Autowired
	private UsuarioRepository usuarioRepository;


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception{
		
		boolean continuar = true;
		Scanner scanner = new Scanner(System.in);

		while(continuar){
			System.out.println("----------------");
			System.out.println("1. Crear usuario");
			System.out.println("2. Crear oficina");
			System.out.println("3. Crear punto de atención");
			System.out.println("4. Borrar punto de atención");
			System.out.println("5. Crear cuenta");
			System.out.println("6. Cambiar estado de cuenta a cerrada o desactivada");
			System.out.println("7. Registrar operación sobre cuenta");
			System.out.println("8. Consultar las cuentas");
			System.out.println("9. Extracto bancario para una cuenta");
			System.out.println("0. salir");
			
			System.out.println("\nIngresar opción: ");
			int opcion = Integer.parseInt(scanner.nextLine());
			if(opcion == 0){
				continuar = false;
				System.out.println("EXIT");
			}

			else if(opcion ==1 ){
				this.crearUsuario();
			}
			else if(opcion ==2 ){
				this.crearOficina();
			}
		}
		scanner.close();
		/*
		List<Usuario> res = usuarioRepository.buscarPorNumDoc(562);
		List<Usuario> ans = usuarioRepository.encontrarTodos();

		if(res.isEmpty()){
			System.out.println("no hay usuarios por ese id");
		}
		else{
			for(Usuario b: res){
				System.out.println(b);
			}
		}
		System.out.println("todos: "+ans.size());
		


		 
		//QUERIES
		List<Bar> res = barRepository.buscarPorId(60);

		for(Bar b: res){
			System.out.println(b);
		}
		
		//AGGREGATIONS
		List<RespuestaGrupo> res2 = barRepository.darBaresPorCiudad();

		for(RespuestaGrupo r: res2){
			System.out.println(r.getCiudad());
			System.out.println(r.getCantidad());
		}

		//INSERT / UPDATE
		barRepository.save(new Bar(101, "Bar de prueba","Cali","Alto",2));

		//Update
		barRepository.aniadirBebidaABar(101, "Bebida de prueba 2", "aperitivo", 10, "diurno", 10);
		*/
	}

	public void crearUsuario(){
		System.out.println("todos los usuarios:");
		List<Usuario> ans = usuarioRepository.encontrarTodos();
		System.out.println("todos: "+ans.size());
	}

	public void crearOficina(){
		System.out.println("aalgo");
	}


}

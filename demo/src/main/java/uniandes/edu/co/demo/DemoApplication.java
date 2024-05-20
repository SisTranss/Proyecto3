package uniandes.edu.co.demo;

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import uniandes.edu.co.demo.modelo.Bar;
import uniandes.edu.co.demo.modelo.Oficina;
import uniandes.edu.co.demo.modelo.Usuario;
import uniandes.edu.co.demo.modelo.Usuario2;
import uniandes.edu.co.demo.modelo.Cuenta;
import uniandes.edu.co.demo.modelo.OperacionCuenta;
import uniandes.edu.co.demo.repository.BarRepository;
import uniandes.edu.co.demo.repository.OficinaRepository;
import uniandes.edu.co.demo.repository.UsuarioRepository;
import uniandes.edu.co.demo.repository.BarRepository.RespuestaGrupo;
import uniandes.edu.co.demo.repository.Usuario2Repository;
import uniandes.edu.co.demo.repository.CuentaRepository;

@SpringBootApplication
@ComponentScan({"uniandes.edu.co.demo"})
@EnableMongoRepositories(basePackages = "uniandes.edu.co.demo.repository")
public class DemoApplication  implements CommandLineRunner{

	/*
	@Autowired
	private UsuarioRepository usuarioRepository;
*/
	@Autowired
	private Usuario2Repository usuario2Repository;
/*
	@Autowired
	private CuentaRepository cuentaRepository;

	private OficinaRepository oficinaRepository;
	 */

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	/*

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
			else if(opcion ==3 ){
				this.crearUsuario2();
			}
			else if(opcion ==4){
				this.crearCuenta(123);
			}
			else{
				System.out.println("Opción no válida");
			}
		}
		scanner.close();
		
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
		
	}

	public void crearUsuario(){
		System.out.println("todos los usuarios:");
		List<Usuario> ans = usuarioRepository.encontrarTodos();
		System.out.println("todos: "+ans.size());
	}

	public void crearOficina(){
		List<Oficina> oficinas = oficinaRepository.findAll();
		System.out.println("numero de oficinas: " + oficinas.size());
	}
	*/

	public void crearUsuario2(){
		System.out.println("USUARIOS:");
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		usuario2Repository.save(new Usuario2("1", "nombre", 123,  "email", 123, "nacionalidad", "direccion", "ciudad", "departamento", 1, 1, cuentas));
		List<Usuario2> ans = usuario2Repository.findAll();
		System.out.println("todos: "+ans.size());
	}

	public void crearCuenta(int num_doc_cliente){
		System.out.println("USUARIOS:");
		List<OperacionCuenta> operacionesCuenta = new ArrayList<OperacionCuenta>();
		long millis=System.currentTimeMillis();
        Date hoy = new Date(millis);

		usuario2Repository.aniadirCuentaAUsuario(num_doc_cliente, 1, 100.0, hoy, "activa","ahorros", operacionesCuenta);

	}

	public void filtarCuenta(){
		String tipo = "ahorros";
		Double saldo_min = 100.0;
		Double saldo_max = 1000.0;
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2022);
		calendar.set(Calendar.MONTH, Calendar.JANUARY); // Note: January is 0, not 1.
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Date date = calendar.getTime();

		Calendar ultima = Calendar.getInstance();
		ultima.set(Calendar.YEAR, 2022);
		ultima.set(Calendar.MONTH, Calendar.JANUARY); // Note: January is 0, not 1.
		ultima.set(Calendar.DAY_OF_MONTH, 1);
		Date fecha_u_transaccion = ultima.getTime();

		int num_doc = 123;
	}

	@Override
	public void run(String... args) throws Exception {
		crearCuenta(123);
	}



}

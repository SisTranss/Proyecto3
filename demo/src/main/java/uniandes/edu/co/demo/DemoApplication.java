package uniandes.edu.co.demo;

import java.util.List;

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
		


		/* 
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


}

package uniandes.edu.co.demo.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import uniandes.edu.co.demo.modelo.Oficina;

public interface OficinaRepository extends MongoRepository<Oficina, String>{
    
    @Query("{nombre:?0}")
    List<Oficina> darOficinaPorNombre(String nombre);
    
}

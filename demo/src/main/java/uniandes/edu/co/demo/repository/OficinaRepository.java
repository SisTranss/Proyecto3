package uniandes.edu.co.demo.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import uniandes.edu.co.demo.modelo.Oficina;

public interface OficinaRepository extends MongoRepository<Oficina, String>{
    
}

package uniandes.edu.co.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import uniandes.edu.co.demo.modelo.PuntoAtencion;

public interface PuntoAtencionRepository  extends MongoRepository<PuntoAtencion, String>{
    
}

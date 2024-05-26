package uniandes.edu.co.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import uniandes.edu.co.demo.modelo.PuntoAtencion;

public interface PuntoAtencionRepository  extends MongoRepository<PuntoAtencion, String>{
    
    @Query("{id: ?0}")
    PuntoAtencion buscarPorId(int id);

    @Query("{tipoPunto:{$in:[\"personalizado\",\"automatico\"]}}")
    List<PuntoAtencion> puntosAtCajeroYPersonalizado();
}

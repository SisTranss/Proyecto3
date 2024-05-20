package uniandes.edu.co.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.demo.modelo.Bar;
import uniandes.edu.co.demo.modelo.Cuenta;
import uniandes.edu.co.demo.modelo.Usuario2;
import uniandes.edu.co.demo.modelo.OperacionCuenta;

public interface Usuario2Repository extends MongoRepository<Usuario2, String>{
    
    @Query("{num_doc: ?0}")
    List<Usuario2> buscarPorNumDoc(int id);

    @Query("{num_doc: ?0}")
    @Update("{$push:{cuentas:{numero_cuenta:?1, saldo:?2, ultima_transaccion:?3, estado:?4, tipo:?5, operaciones_cuenta:?6}}}")
    void aniadirCuentaAUsuario(int num_doc, int numero_cuenta, Double saldo, Date ultima_transaccion, String estado, String tipo, List<OperacionCuenta> operaciones_cuenta);


    @Query("{'cuentas.numero_cuenta': ?0}")
    List<Usuario2> buscarPorNum_cuenta(int id);


    
    

}

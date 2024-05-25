package uniandes.edu.co.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.demo.modelo.Usuario2;
import uniandes.edu.co.demo.modelo.Cuenta;
import uniandes.edu.co.demo.modelo.OperacionCuenta;

public interface Usuario2Repository extends MongoRepository<Usuario2, String>{
    
    @Query("{num_doc: ?0}")
    Usuario2 buscarPorNumDoc(int num_doc);

    @Query("{num_doc: ?0}")
    @Update("{$push:{cuentas:{numero_cuenta:?1, saldo:?2, ultima_transaccion:?3, estado:?4, tipo:?5, operaciones_cuenta:?6}}}")
    void aniadirCuentaAUsuario(int num_doc, int numero_cuenta, Double saldo, Date ultima_transaccion, String estado, String tipo, List<OperacionCuenta> operaciones_cuenta);

    @Query("{'cuentas.numero_cuenta': ?0}")
    List<Usuario2> buscarPorNum_cuenta(int id);

    @Query("{num_doc: ?0}")
    @Aggregation(pipeline = {"{ $unwind: '$cuentas' },{ $project: { _id: 0, cuentas: 1 } }"})
    List<Cuenta> darCuentasUser(int num_doc);

    @Aggregation(pipeline = {"{ $unwind: '$cuentas' },{ $project: { _id: 0, cuentas: 1 } }"})
    List<Cuenta> darCuentas();
    
    @Query("{num_doc: ?0}")
    @Aggregation(pipeline={"{$unwind:'$cuentas'}", 
    "{$match: {'cuentas.numero_cuenta':?1}}","{$project:{'cuentas':1, '_id':false}}",
    "{$unwind: '$cuentas.operaciones_cuenta'}"," {$project:{op_cuenta: '$cuentas.operaciones_cuenta'}}",
    "{$match:{'op_cuenta.fecha_operacion':{$gte:?2,$lte:?3}}}",
    "{$project:{tipo: '$op_cuenta.tipo', fecha_operacion: '$op_cuenta.fecha_operacion', monto_pago: '$op_cuenta.monto_pago', cuenta_destino:'$op_cuenta.cuenta_destino'}}"})
    List<OperacionCuenta> darOperacionCuentaMes(int numDoc,  int numeroCuenta, Date fechaInicio, Date fechaFin );

    @Aggregation(pipeline={"{$unwind: '$cuentas'}","{$project:{'cuentas':1, '_id':false}}",
    "{$unwind: '$cuentas.operaciones_cuenta'}",
    "{$match:{'cuentas.operaciones_cuenta.fecha_operacion':{$gte:?0,$lte:?1}}}",
    "{$project:{op_cuenta: '$cuentas.operaciones_cuenta'}}", 
    "{$project:{tipo: '$op_cuenta.tipo', fecha_operacion: '$op_cuenta.fecha_operacion', monto_pago: '$op_cuenta.monto_pago', cuenta_destino:'$op_cuenta.cuenta_destino'}}"})
    List<OperacionCuenta> getAllOperacionesCuentaMes(Date fechaInic, Date fechaFin);

    @Query("{num_doc: ?0}")
    @Aggregation(pipeline={"{$unwind:'$cuentas'}", 
    "{$match: {'cuentas.numero_cuenta':?1}}","{$project:{'cuentas':1, '_id':false}}",
    "{$unwind: '$cuentas.operaciones_cuenta'}"," {$project:{op_cuenta: '$cuentas.operaciones_cuenta'}}",
    "{$match:{'op_cuenta.fecha_operacion':{$lte:?2}}}",
    "{$project:{tipo: '$op_cuenta.tipo', fecha_operacion: '$op_cuenta.fecha_operacion', monto_pago: '$op_cuenta.monto_pago', cuenta_destino:'$op_cuenta.cuenta_destino'}}"})
    List<OperacionCuenta> getOperacionesCuentaUserAntesDelMes(int numDoc, int numeroCuenta, Date fechaInicial);
    

    @Aggregation(pipeline={"{$unwind:'$cuentas'}", 
    "{$project:{'cuentas':1, '_id':false}}",
    "{$unwind: '$cuentas.operaciones_cuenta'}"," {$project:{op_cuenta: '$cuentas.operaciones_cuenta'}}",
    "{$match:{'op_cuenta.fecha_operacion':{$lte:?0}}}",
    "{$project:{tipo: '$op_cuenta.tipo', fecha_operacion: '$op_cuenta.fecha_operacion', monto_pago: '$op_cuenta.monto_pago', cuenta_destino:'$op_cuenta.cuenta_destino'}}"})
    List<OperacionCuenta> getAllOperacionesCuentaAntesDelMes(Date fechaInicial);
}

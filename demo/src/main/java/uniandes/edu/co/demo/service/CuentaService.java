package uniandes.edu.co.demo.service;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import uniandes.edu.co.demo.modelo.Cuenta;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CuentaService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Cuenta> findCuentas(List<Cuenta> cuentas,String tipo, Double minSaldo, Double maxSaldo, LocalDateTime fechaUltimaTransaccion, Integer num_doc) {
        List<Cuenta> cuentasFiltradas = new ArrayList<>();
        for (Cuenta cuenta : cuentas) {
            LocalDateTime fechaCuenta = cuenta.getUltima_transaccion().toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime();
            if ((tipo == null || tipo.equals("") || cuenta.getTipo().equals(tipo)) &&
            (minSaldo == null || cuenta.getSaldo() >= minSaldo) &&
            (maxSaldo == null || cuenta.getSaldo() <= maxSaldo) &&
            (fechaUltimaTransaccion == null || fechaUltimaTransaccion.isAfter(fechaCuenta)) &&
            (num_doc == null || cuenta.getNum_doc_cliente() == num_doc)) {
            cuentasFiltradas.add(cuenta);
            }
        }

        return cuentasFiltradas;
    }

    public List<Cuenta> findWithFilters(String tipo, Double minSaldo, Double maxSaldo, Date fechaUltimaTransaccion, Integer num_doc) {
        List<AggregationOperation> operations = new ArrayList<>();
    
        operations.add(Aggregation.unwind("cuentas"));
    
        if (tipo != null) {
            operations.add(Aggregation.match(Criteria.where("cuentas.tipo").is(tipo)));
        }
    
        if (minSaldo != null) {
            operations.add(Aggregation.match(Criteria.where("cuentas.saldo").gte(minSaldo)));
        }
    
        if (maxSaldo != null) {
            operations.add(Aggregation.match(Criteria.where("cuentas.saldo").lte(maxSaldo)));
        }
    
        if (fechaUltimaTransaccion != null) {
            operations.add(Aggregation.match(Criteria.where("cuentas.ultima_transaccion").lte(fechaUltimaTransaccion)));
        }
    
        if (num_doc != null) {
            operations.add(Aggregation.match(Criteria.where("cuentas.num_doc_cliente").is(num_doc)));
        }
    
        Aggregation aggregation = Aggregation.newAggregation(operations);
        AggregationResults<Cuenta> results = mongoTemplate.aggregate(aggregation, "usuarios2", Cuenta.class);
    
        System.out.println(results.getMappedResults());
        return results.getMappedResults();
    }
}

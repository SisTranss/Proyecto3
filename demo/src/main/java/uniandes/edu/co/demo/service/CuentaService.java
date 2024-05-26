package uniandes.edu.co.demo.service;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import uniandes.edu.co.demo.modelo.Cuenta;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CuentaService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Cuenta> findWithFilters(String tipo, Double minSaldo, Double maxSaldo, Date fechaUltimaTransaccion, Integer num_doc) {

        List<AggregationOperation> operations = new ArrayList<>();

        operations.add(Aggregation.unwind("cuentas"));
        operations.add(Aggregation.project().andExclude("_id").andInclude("cuentas"));

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

    operations.add(Aggregation.project().andExclude("cuentas.operaciones_cuenta"));

    Aggregation aggregation = Aggregation.newAggregation(operations);

    return mongoTemplate.aggregate(aggregation, "usuarios2", Cuenta.class).getMappedResults();
    }
}

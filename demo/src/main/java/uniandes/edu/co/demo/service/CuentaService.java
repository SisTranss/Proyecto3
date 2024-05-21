package uniandes.edu.co.demo.service;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import uniandes.edu.co.demo.modelo.Cuenta;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;
import java.util.List;

@Service
public class CuentaService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Cuenta> findWithFiltersAndGroupBy(String tipo, Double minSaldo, Double maxSaldo, Date fechaCreacionInicio, Date fechaCreacionFin, Date fechaUltimaTransaccionInicio, Date fechaUltimaTransaccionFin, String cliente, String groupByField) {
        MatchOperation matchStage = Aggregation.match(
            new Criteria().orOperator(
                Criteria.where("cuentas.tipo").is(tipo),
                Criteria.where("cuentas.saldo").gte(minSaldo).lte(maxSaldo),
                Criteria.where("cuentas.fechaCreacion").gte(fechaCreacionInicio).lte(fechaCreacionFin),
                Criteria.where("cuentas.ultimaTransaccion").gte(fechaUltimaTransaccionInicio).lte(fechaUltimaTransaccionFin),
                Criteria.where("cliente").is(cliente)
            )
        );

        GroupOperation groupStage = Aggregation.group(groupByField);

        Aggregation aggregation = Aggregation.newAggregation(matchStage, groupStage);

        return mongoTemplate.aggregate(aggregation, "usuario", Cuenta.class).getMappedResults();
    }

    public List<Cuenta> findWithFilters(String tipo, Double minSaldo, Double maxSaldo, Date fechaCreacionInicio, Date fechaCreacionFin, Date fechaUltimaTransaccionInicio, Date fechaUltimaTransaccionFin, String cliente) {
        Query query = new Query();

        if (tipo != null) {
            query.addCriteria(Criteria.where("tipo").is(tipo));
        }
        if (minSaldo != null && maxSaldo != null) {
            query.addCriteria(Criteria.where("saldo").gte(minSaldo).lte(maxSaldo));
        }
        if (fechaCreacionInicio != null && fechaCreacionFin != null) {
            query.addCriteria(Criteria.where("fechaCreacion").gte(fechaCreacionInicio).lte(fechaCreacionFin));
        }
        if (fechaUltimaTransaccionInicio != null && fechaUltimaTransaccionFin != null) {
            query.addCriteria(Criteria.where("fechaUltimaTransaccion").gte(fechaUltimaTransaccionInicio).lte(fechaUltimaTransaccionFin));
        }
        if (cliente != null) {
            query.addCriteria(Criteria.where("cliente").is(cliente));
        }

        return mongoTemplate.find(query, Cuenta.class);
    }
}

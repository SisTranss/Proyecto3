package uniandes.edu.co.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uniandes.edu.co.demo.modelo.OperacionCuenta;
import uniandes.edu.co.demo.repository.Usuario2Repository;

@Service
public class OperacionCuentaService {

    @Autowired
    private Usuario2Repository usuario2Repository;

    public List<OperacionCuenta> darOperacionesCuentaUserMes(int numDoc, int numeroCuenta, Date fechaInicio, Date fechaFin) {
        List<OperacionCuenta> operacionesCuentaMes = usuario2Repository.darOperacionCuentaMes(numDoc, numeroCuenta,fechaInicio, fechaFin);
        List<OperacionCuenta> allOperacionesCuentaMes = usuario2Repository.getAllOperacionesCuentaMes(fechaInicio, fechaFin);

        for(OperacionCuenta op: allOperacionesCuentaMes){
            if(op.getTipo().equals("transaccion")&&op.getCuenta_destino()==numeroCuenta){
                operacionesCuentaMes.add(op);
            }
        }

        return operacionesCuentaMes;
    }

    public List<Integer> calcularExtractoBancario(int numDoc, int numeroCuenta, Date fechaInicio,
            Date fechaFin) {
        // 1. calcular neto del mes
        List<OperacionCuenta> operacionesCuentaMes = usuario2Repository.darOperacionCuentaMes(numDoc, numeroCuenta,
                fechaInicio, fechaFin);
        int dineroInMes = 0;
        int dineroOutMes = 0;

        for (OperacionCuenta opCuenta : operacionesCuentaMes) {
            if (opCuenta.getTipo().equals("consignar")) {
                dineroInMes += opCuenta.getMonto_pago();
            } else if (opCuenta.getTipo().equals("retirar")) {
                dineroOutMes += opCuenta.getMonto_pago();
            } else {
                dineroOutMes += opCuenta.getMonto_pago();
            }
        }

        List<OperacionCuenta> allOperacionesCuentasMes = usuario2Repository.getAllOperacionesCuentaMes(fechaInicio,
                fechaFin);
        for (OperacionCuenta opCuenta : allOperacionesCuentasMes) {
            if (opCuenta.getTipo().equals("transaccion") && opCuenta.getCuenta_destino() == numeroCuenta) {
                dineroInMes += opCuenta.getMonto_pago();
            }
        }

        int netoMes = dineroInMes - dineroOutMes;

        // 2. calcular dinero total que ingresó
        int dineroInicioMes = 0;
        // todo el dinero in y todo el dinero out de la cuenta
        List<OperacionCuenta> opCuentaUserAntesDelMes = usuario2Repository.getOperacionesCuentaUserAntesDelMes(numDoc,
                numeroCuenta, fechaInicio);
        for (OperacionCuenta op : opCuentaUserAntesDelMes) {
            if (op.getTipo().equals("consignar")) {
                dineroInicioMes += op.getMonto_pago();
            } else if (op.getTipo().equals("retirar")) {
                dineroInicioMes -= op.getMonto_pago();
            }
            else{
                dineroInicioMes -= op.getMonto_pago();
            }
        }

        List<OperacionCuenta> allOpCuentaAntesDelMes = usuario2Repository.getAllOperacionesCuentaAntesDelMes(fechaInicio);
        for(OperacionCuenta op: allOpCuentaAntesDelMes){
            if(op.getTipo().equals("transaccion")&&op.getCuenta_destino()==numeroCuenta){
                dineroInicioMes += op.getMonto_pago();
            }
        }

        int dineroFinMes = dineroInicioMes + netoMes;

        List<Integer> rta = new ArrayList<>();
        rta.add(dineroInicioMes);
        rta.add(dineroFinMes);

        return rta;
    }

}

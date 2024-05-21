package uniandes.edu.co.demo.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import uniandes.edu.co.demo.modelo.Cuenta;
import uniandes.edu.co.demo.modelo.Usuario2;
import uniandes.edu.co.demo.repository.Usuario2Repository;

@Controller
public class ExtractoBancarioController {

    @Autowired
    private Usuario2Repository usuarioRepository;

    @GetMapping("/extractoBancario2")
    public String extractoBancario(Model model, @Param("numDocUsuario") Integer numDocUsuario, @Param("idCuenta") Integer idCuenta, @Param("mes") Integer mes) {
        if (idCuenta == null || mes == null || numDocUsuario == null) {
        } else {
            Date startDate = getStartDateOfMonth(mes, 2024);
            Date endDate = getEndDateOfMonth(mes, 2024);

            //dar operaciones que se hicieron sobre la cuenta en el mes seleccionado

            model.addAttribute("inicioMes", startDate);
            model.addAttribute("finMes", endDate);
        }

        return "extractoBancario2";
    }
    /*
     * @GetMapping("/extractoBancario2")
     * public String extractoBancario(Model model, @Param("idCuenta")Integer
     * idCuenta, @Param("mes")Integer mes) {
     * 
     * if(idCuenta==null || mes==null){;
     * // model.addAttribute("operacionesCuentas",
     * operacionCuentaRepository.darOperaciones());
     * }
     * 
     *
     * }
     * model.addAttribute("operacionesCuentas",
     * operacionCuentaRepository.darOperacionesMes(idCuenta, fechaInic, fechaFin));
     * model.addAttribute("transacciones",
     * transaccionesRepository.darTransaccionesMes(idCuenta, fechaInic, fechaFin));
     * 
     * 
     * Integer dineroInTransaccionesMes;
     * if(transaccionesRepository.darDineroInMes(idCuenta, fechaInic, fechaFin) ==
     * null){
     * dineroInTransaccionesMes = 0;
     * }
     * else{
     * dineroInTransaccionesMes = transaccionesRepository.darDineroInMes(idCuenta,
     * fechaInic, fechaFin);
     * }
     * 
     * Integer dineroOutTransaccionesMes;
     * if(transaccionesRepository.darDineroOutMes(idCuenta, fechaInic, fechaFin) ==
     * null){
     * dineroOutTransaccionesMes = 0;
     * }
     * else{
     * dineroOutTransaccionesMes = transaccionesRepository.darDineroOutMes(idCuenta,
     * fechaInic, fechaFin);
     * }
     * 
     * Integer dineroInOPCuentas;
     * if(operacionCuentaRepository.darDineroInMesOPCuenta(idCuenta, fechaInic,
     * fechaFin) == null){
     * dineroInOPCuentas = 0;
     * }
     * else{
     * dineroInOPCuentas =
     * operacionCuentaRepository.darDineroInMesOPCuenta(idCuenta, fechaInic,
     * fechaFin);
     * }
     * 
     * Integer dineroOutOPCuentas;
     * if(operacionCuentaRepository.darDineroOutMesOPCuenta(idCuenta, fechaInic,
     * fechaFin) == null){
     * dineroOutOPCuentas = 0;
     * }
     * else{
     * dineroOutOPCuentas =
     * operacionCuentaRepository.darDineroOutMesOPCuenta(idCuenta, fechaInic,
     * fechaFin);
     * }
     * 
     * int dineroNetoMes = dineroInTransaccionesMes -dineroOutTransaccionesMes +
     * dineroInOPCuentas - dineroOutOPCuentas;
     * System.out.println(dineroNetoMes);
     * 
     * Integer dineroInRestanteOPCuenta;
     * if(operacionCuentaRepository.dineroInOPCuenta(idCuenta, fechaFin)==null){
     * dineroInRestanteOPCuenta = 0;
     * }
     * else{
     * dineroInRestanteOPCuenta =
     * operacionCuentaRepository.dineroInOPCuenta(idCuenta, fechaFin);
     * }
     * 
     * Integer dineroOutRestanteOPCuenta;
     * if(operacionCuentaRepository.dineroOutOPCuenta(idCuenta, fechaFin)==null){
     * dineroOutRestanteOPCuenta = 0;
     * }
     * else{
     * dineroOutRestanteOPCuenta =
     * operacionCuentaRepository.dineroOutOPCuenta(idCuenta, fechaFin);
     * }
     * 
     * Integer dineroInRestanteTransaccion;
     * if(transaccionesRepository.dineroRestanteIn(idCuenta, fechaFin)==null){
     * dineroInRestanteTransaccion = 0;
     * }
     * else{
     * dineroInRestanteTransaccion =
     * transaccionesRepository.dineroRestanteIn(idCuenta, fechaFin);
     * }
     * 
     * Integer dineroOutRestanteTransaccion;
     * if(transaccionesRepository.dineroRestanteOut(idCuenta, fechaFin)==null){
     * dineroOutRestanteTransaccion = 0;
     * }
     * else{
     * dineroOutRestanteTransaccion =
     * transaccionesRepository.dineroRestanteOut(idCuenta, fechaFin);
     * }
     * 
     * int dineroNetoAfterMes = dineroInRestanteOPCuenta+dineroInRestanteTransaccion
     * -dineroOutRestanteOPCuenta-dineroOutRestanteTransaccion;
     * 
     * int dineroFinMes = cuentaRepository.darDineroActual(idCuenta) +
     * dineroNetoAfterMes*(-1);
     * int dineroInicioMes = dineroFinMes + dineroNetoMes*(-1);
     * 
     * model.addAttribute("inicioMes", dineroInicioMes);
     * model.addAttribute("finMes", dineroFinMes);
     * 
     * }
     * return "extractoBancario2";
     * }
     */

    public Date getStartDateOfMonth(int month, int year) {
        LocalDate localDate = LocalDate.of(year, month, 1);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public Date getEndDateOfMonth(int month, int year) {
        LocalDate localDate = LocalDate.of(year, month, 1)
                .with(TemporalAdjusters.lastDayOfMonth());
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

}

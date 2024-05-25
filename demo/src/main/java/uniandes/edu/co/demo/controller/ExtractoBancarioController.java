package uniandes.edu.co.demo.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import uniandes.edu.co.demo.modelo.OperacionCuenta;
import uniandes.edu.co.demo.service.OperacionCuentaService;

@Controller
public class ExtractoBancarioController {

    @Autowired
    private OperacionCuentaService opCuentaService;

    @GetMapping("/extractoBancario2")
    public String extractoBancario(Model model, @Param("numDocUsuario") Integer numDocUsuario, @Param("idCuenta") Integer idCuenta, @Param("mes") Integer mes) {
        if (idCuenta == null || mes == null || numDocUsuario == null) {
        } else {
            Date startDate = getStartDateOfMonth(mes, 2024);
            Date endDate = getEndDateOfMonth(mes, 2024);

            //dar operaciones que se hicieron sobre la cuenta en el mes seleccionado
            List<OperacionCuenta> operacionesMes = opCuentaService.darOperacionesCuentaUserMes(numDocUsuario, idCuenta, startDate, endDate);
            model.addAttribute("operacionesCuentas", operacionesMes);

            //dar el dinero del inicio del mes y el dinero del final del mes
            List<Integer> dineroMes = opCuentaService.calcularExtractoBancario(numDocUsuario, idCuenta, startDate, endDate);
            model.addAttribute("inicioMes", dineroMes.get(0));
            model.addAttribute("finMes", dineroMes.get(1));
        }

        return "extractoBancario2";
    }
    
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

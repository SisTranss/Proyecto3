package uniandes.edu.co.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uniandes.edu.co.demo.modelo.Cuenta;
import uniandes.edu.co.demo.modelo.OperacionCuenta;
import uniandes.edu.co.demo.modelo.PuntoAtencion;
import uniandes.edu.co.demo.modelo.Usuario2;
import uniandes.edu.co.demo.repository.PuntoAtencionRepository;
import uniandes.edu.co.demo.repository.Usuario2Repository;

import org.springframework.transaction.annotation.Transactional;


@Controller
public class OperacionCuentasController {

    @Autowired
    private Usuario2Repository usuarioRepository;

    @Autowired
    private PuntoAtencionRepository puntoAtencionRepository;

    
    @GetMapping("/consignar/new")
    public String consignarForm(Model model) {
        model.addAttribute("operacion_cuenta", new OperacionCuenta());
        model.addAttribute("puntosAtencion", puntoAtencionRepository.findAll());
        return "operacionConsignarNueva";
    }

    @GetMapping("/retirar/new")
    public String retirarForm(Model model) {
        model.addAttribute("operacion_cuenta", new OperacionCuenta());
        model.addAttribute("puntosAtencion", puntoAtencionRepository.puntosAtCajeroYPersonalizado());
        return "operacionRetirarNueva";
    }

    @GetMapping("/transferir/new")
    public String transaccionForm(Model model) {
        model.addAttribute("operacion_cuenta", new OperacionCuenta());
        model.addAttribute("puntosAtencion", puntoAtencionRepository.findAll());
        return "transaccionNueva";
    }
    


    @PostMapping("/retirar/new/save")
    @Transactional
    public String retirarDinero(@RequestParam("numero_cuenta") int numero_cuenta, Model model, @ModelAttribute OperacionCuenta operacion_cuenta,@ModelAttribute("puntoAtencion") int idPuntoAtencion) {

        Usuario2 usuario = usuarioRepository.buscarPorNum_cuenta(numero_cuenta).get(0);
        Usuario2 usuarioModificado;
        List<Cuenta> c = usuario.getCuentas();
        for (Cuenta cuenta : c) {
                if (cuenta.getNumero_cuenta() == numero_cuenta && cuenta.getSaldo() >= operacion_cuenta.getMonto_pago() && cuenta.getEstado().equals("activa")) {
                    cuenta.setSaldo(cuenta.getSaldo() - operacion_cuenta.getMonto_pago());
                    List<OperacionCuenta> operacionesCuenta = cuenta.getOperaciones_cuenta();
                    operacionesCuenta.add(new OperacionCuenta(operacion_cuenta.getTipo(), operacion_cuenta.getFecha_operacion(), operacion_cuenta.getMonto_pago(), 0));
                    usuarioModificado = usuario;
                    usuarioRepository.delete(usuario);
                    usuarioRepository.save(usuarioModificado);

                    PuntoAtencion puntoAt = puntoAtencionRepository.buscarPorId(idPuntoAtencion);
                    puntoAt.getOperaciones().add(numero_cuenta);
                    puntoAtencionRepository.save(puntoAt);
                    return "redirect:/cuentas";

                }
        }
        return "redirect:/error";
        
    }
    
    @PostMapping("/consignar/new/save")
    public String consignarDinero(@RequestParam("numero_cuenta") int numero_cuenta,  Model model, @ModelAttribute OperacionCuenta operacion_cuenta,@ModelAttribute("puntoAtencion") int idPuntoAtencion) {
        Usuario2 usuario = usuarioRepository.buscarPorNum_cuenta(numero_cuenta).get(0);
        Usuario2 usuarioModificado;
        List<Cuenta> c = usuario.getCuentas();
        
        for (Cuenta cuenta : c) {

                if (cuenta.getNumero_cuenta() == numero_cuenta && cuenta.getEstado().equals("activa")) {

                    cuenta.setSaldo(cuenta.getSaldo() + operacion_cuenta.getMonto_pago());
                    List<OperacionCuenta> operacionesCuenta = cuenta.getOperaciones_cuenta();
                    operacionesCuenta.add(new OperacionCuenta(operacion_cuenta.getTipo(), operacion_cuenta.getFecha_operacion(), operacion_cuenta.getMonto_pago(), 0));
                    System.out.print(usuario);
                    usuarioModificado = usuario;
                    usuarioRepository.delete(usuario);
                    usuarioRepository.save(usuarioModificado);

                    PuntoAtencion puntoAt = puntoAtencionRepository.buscarPorId(idPuntoAtencion);
                    puntoAt.getOperaciones().add(numero_cuenta);
                    puntoAtencionRepository.save(puntoAt);

                    return "redirect:/cuentas";

                
                }
            }
        return "redirect:/error";
        
    }

    @PostMapping("/transferir/new/save")
    public String transaccion(@RequestParam("numero_cuenta") int numero_cuenta, Model model, @ModelAttribute OperacionCuenta operacion_cuenta, @ModelAttribute("puntoAtencion") int idPuntoAtencion) {
        Usuario2 usuarioOrigen = usuarioRepository.buscarPorNum_cuenta(numero_cuenta).get(0);
        Usuario2 usuarioOrigenModificado;
        
       
        List<Cuenta> cOrigen = usuarioOrigen.getCuentas();

        for (Cuenta cuenta : cOrigen) {
            if (cuenta.getNumero_cuenta() == numero_cuenta  && cuenta.getSaldo() >= operacion_cuenta.getMonto_pago() && cuenta.getEstado().equals("activa") ) {
                cuenta.setSaldo(cuenta.getSaldo() - operacion_cuenta.getMonto_pago());

                List<OperacionCuenta> operacionesCuenta = cuenta.getOperaciones_cuenta();
                operacionesCuenta.add(new OperacionCuenta("transaccion", operacion_cuenta.getFecha_operacion(), operacion_cuenta.getMonto_pago(), operacion_cuenta.getCuenta_destino()));
                
                usuarioOrigenModificado = usuarioOrigen;
                usuarioRepository.delete(usuarioOrigen);
                usuarioRepository.save(usuarioOrigenModificado);

                Usuario2 usuarioDestino = usuarioRepository.buscarPorNum_cuenta(operacion_cuenta.getCuenta_destino()).get(0);
                Usuario2 usuarioDestinoModificado;
                List<Cuenta> cDestino = usuarioDestino.getCuentas();

                for(Cuenta cuentaD : cDestino ){
                    if(cuentaD.getNumero_cuenta() == operacion_cuenta.getCuenta_destino()){

                        cuentaD.setSaldo(cuentaD.getSaldo() + operacion_cuenta.getMonto_pago());
                        usuarioDestinoModificado = usuarioDestino;
                        usuarioRepository.delete(usuarioDestino);
                        usuarioRepository.save(usuarioDestinoModificado);
                    }
                }

                PuntoAtencion puntoAt = puntoAtencionRepository.buscarPorId(idPuntoAtencion);
                puntoAt.getOperaciones().add(numero_cuenta);
                puntoAtencionRepository.save(puntoAt);

                return "redirect:/cuentas";
            
            }
    
        }

        return "error";
        
    }

    public void modificarSaldoCuentaConsignar(Usuario2 usuario, Cuenta cuenta, Float monto_pago) {

        
        
    }

    public void modificarSaldoCuentaRetirar(Usuario2 usuario, Cuenta cuenta, Float monto_pago) {
        cuenta.setSaldo(cuenta.getSaldo() - monto_pago);
        List<OperacionCuenta> operacionesCuenta = new ArrayList<OperacionCuenta>();
        cuenta.setOperaciones_cuenta(operacionesCuenta);

        
        
    }
}

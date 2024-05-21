package uniandes.edu.co.demo.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mongodb.client.MongoCollection;

import uniandes.edu.co.demo.repository.CuentaRepository;
import uniandes.edu.co.demo.repository.Usuario2Repository;
import uniandes.edu.co.demo.modelo.Cuenta;
import uniandes.edu.co.demo.modelo.Oficina;
import uniandes.edu.co.demo.modelo.OperacionCuenta;
import uniandes.edu.co.demo.modelo.Usuario2;

import org.springframework.ui.Model;
import org.bson.Document;

@Controller
public class CuentaController {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private Usuario2Repository usuarioRepository;

    @GetMapping("/cuentas")
    public String cuentas(Model model) {

        List<Usuario2> ans = usuarioRepository.findAll();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        for (Usuario2 u : ans) {
            List<Cuenta> c = u.getCuentas();
            for (Cuenta cuenta : c) {
                cuentas.add(cuenta);
                cuenta.setNum_doc_cliente(u.getNum_doc());
            }
        }
        model.addAttribute("cuentas", cuentas);
        return "cuentas";
    }

    @GetMapping("/cuentas/new")
    public String cuentaNueva(Model model) {
        model.addAttribute("cuenta", new Cuenta());
        return "cuentaNueva";
    }

    @PostMapping("cuentas/new/save")
    public String cuentaGuardar(@ModelAttribute Cuenta cuenta, int num_doc_cliente, Model model) { 
        List<OperacionCuenta> operacionesCuenta = new ArrayList<OperacionCuenta>();
        cuenta.setOperaciones_cuentas(operacionesCuenta);
        long millis=System.currentTimeMillis();
        Date hoy = new Date(millis);
        cuenta.setNumero_cuenta(32);

        List<Usuario2> ans = usuarioRepository.findAll();
        for (Usuario2 u : ans) {
            if (u.getNum_doc() == num_doc_cliente) {
                usuarioRepository.aniadirCuentaAUsuario(num_doc_cliente, cuenta.getNumero_cuenta(), cuenta.getSaldo(), hoy, "activa", cuenta.getTipo(), cuenta.getOperaciones_cuentas());
                return "redirect:/cuentas";
            }
        }
        return "error";

        
        
    }

    @GetMapping("/cuentas/{numero_cuenta}/cambiar-estado/save")
    public String actualizarCuentaClienteGuardar(@PathVariable("numero_cuenta") int numero_cuenta, @RequestParam("num_doc_cliente") int num_doc_cliente, @RequestParam("nuevo_estado") String nuevo_estado, Model model) {
        Usuario2 usuario = usuarioRepository.buscarPorNumDoc(num_doc_cliente).get(0);
        List<Cuenta> c = usuario.getCuentas();
        for (Cuenta cuenta : c) {
            if (cuenta.getNumero_cuenta() == numero_cuenta) {
                if(cuenta.getEstado().equals("activa") && cuenta.getSaldo() == 0){
                    modificarCuentaPorNumeroCliente(num_doc_cliente, numero_cuenta, nuevo_estado);   
                    return "redirect:/cuentas";
                }
            }
        }
        return "error";
        
    }

    public void modificarCuentaPorNumeroCliente(int num_doc_cliente, int numero_cuenta, String nuevo_estado) {
        List<Usuario2> ans = usuarioRepository.findAll();
        Usuario2 usuarioModificado;
        for (Usuario2 u : ans) {
            List<Cuenta> c = u.getCuentas();
            for (Cuenta cuenta : c) {
                if (cuenta.getNumero_cuenta() == numero_cuenta) {
                    cuenta.setEstado(nuevo_estado);
                    usuarioModificado = u;
                    usuarioRepository.delete(u);
                    usuarioRepository.save(usuarioModificado);

                }
            }
        }
    }

}
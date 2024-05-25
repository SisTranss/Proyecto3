package uniandes.edu.co.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import uniandes.edu.co.demo.modelo.Oficina;
import uniandes.edu.co.demo.modelo.Usuario2;
import uniandes.edu.co.demo.repository.OficinaRepository;
import uniandes.edu.co.demo.repository.Usuario2Repository;

@Controller
public class OficinaController {

    @Autowired
    OficinaRepository oficinaRepository;

    @Autowired
    Usuario2Repository usuarioRepository;

    @GetMapping("/oficinas")
    public String oficinas(Model model) {
        model.addAttribute("oficinas", oficinaRepository.findAll());
        return "oficinas";
    }

    @GetMapping("/oficinas/new")
    public String oficinasForm(Model model) {
        model.addAttribute("oficina", new Oficina());
        return "oficinaNueva";
    }

    @PostMapping("oficinas/new/save")
    public String oficinaGuardar(@ModelAttribute Oficina oficina) {
        // validar que el nombre sea diferente
        List<Oficina> of = oficinaRepository.darOficinaPorNombre(oficina.getNombre());
        Usuario2 usuarioGerente = usuarioRepository.buscarPorNumDoc(oficina.getGerente_oficina());
       
        if (!of.isEmpty()) {
            return "redirect:/error";
        } else {
            // validar que el gerente exista
            if (usuarioGerente.getTipo_empleado() == 2) {
                List<Integer> puntosAtRelacionados = new ArrayList<>();
                oficina.setPuntos_atencion(puntosAtRelacionados);
                oficinaRepository.save(oficina);
            }
            return "redirect:/oficinas";
        }

    }

}

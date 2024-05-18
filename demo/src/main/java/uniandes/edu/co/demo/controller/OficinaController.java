package uniandes.edu.co.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import uniandes.edu.co.demo.modelo.Oficina;
import uniandes.edu.co.demo.repository.OficinaRepository;

@Controller
public class OficinaController {

    @Autowired
    OficinaRepository oficinaRepository;

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
    
}

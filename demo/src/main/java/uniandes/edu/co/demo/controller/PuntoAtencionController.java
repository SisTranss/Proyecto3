package uniandes.edu.co.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import uniandes.edu.co.demo.repository.PuntoAtencionRepository;

import org.springframework.ui.Model;

@Controller
public class PuntoAtencionController {

    @Autowired
    PuntoAtencionRepository puntoAtencionRepository;

    @GetMapping("/puntosAtencion")
    public String oficinas(Model model) {
        model.addAttribute("puntosAtencion", puntoAtencionRepository.findAll());
        return "puntosAtencion";
    }
    
    
}

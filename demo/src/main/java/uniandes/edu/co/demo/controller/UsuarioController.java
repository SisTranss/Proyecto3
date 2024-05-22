package uniandes.edu.co.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import uniandes.edu.co.demo.repository.Usuario2Repository;

@Controller
public class UsuarioController {
    
    @Autowired
    Usuario2Repository usuarioRepository;

    @GetMapping("/usuarios")
    public String usuarios(Model model){
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "usuarios";
    } 
}

package uniandes.edu.co.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.demo.modelo.Oficina;
import uniandes.edu.co.demo.modelo.PuntoAtencion;
import uniandes.edu.co.demo.repository.OficinaRepository;
import uniandes.edu.co.demo.repository.PuntoAtencionRepository;

import org.springframework.ui.Model;

@Controller
public class PuntoAtencionController {

    @Autowired
    PuntoAtencionRepository puntoAtencionRepository;

    @Autowired
    OficinaRepository oficinaRepository;

    @GetMapping("/puntosAtencion")
    public String oficinas(Model model) {
        model.addAttribute("puntosAtencion", puntoAtencionRepository.findAll());
        return "puntosAtencion";
    }

     @GetMapping("/puntosAtencion/new")
    public String puntosAtencionForm(Model model) {
        model.addAttribute("puntoAtencion", new PuntoAtencion());
        model.addAttribute("oficinas", oficinaRepository.findAll());
        return "puntoAtencionNuevo";
    }

    @PostMapping("/puntosAtencion/new/save")
    public String puntoAtencionGuardar(@ModelAttribute PuntoAtencion puntoAtencion, @ModelAttribute("oficina") String nombreOficina) {
        List<Integer> operaciones = new ArrayList<>();
        puntoAtencion.setOperaciones(operaciones);
        if(puntoAtencion.getTipo_punto() != "digital"){
            Oficina oficina = oficinaRepository.darOficinaPorNombre(nombreOficina);
            oficina.getPuntos_atencion().add(puntoAtencion.getId());
            oficinaRepository.save(oficina);
        }
        
        puntoAtencionRepository.save(puntoAtencion);
        return "redirect:/puntosAtencion";
    }

    @GetMapping("/puntosAtencion/{id}/delete")
    public String puntoAtencionEliminar(@PathVariable("id") int id){
        //validar que no haya ninguan operacion
        PuntoAtencion puntoDetelete = puntoAtencionRepository.buscarPorId(id);
        puntoAtencionRepository.delete(puntoDetelete);
        return "redirect:/puntosAtencion";
    }
    
    
}

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
        PuntoAtencion puntoAt = puntoAtencionRepository.buscarPorId(puntoAtencion.getId());
        //validar que el id no sea igual
        if(puntoAt != null){
            return "redirect:/error";
        }
        List<Integer> operaciones = new ArrayList<>();
        puntoAtencion.setOperaciones(operaciones);
        if(puntoAtencion.getTipo_punto() != "digital"){
            List<Oficina> oficina = oficinaRepository.darOficinaPorNombre(nombreOficina);
            oficina.get(0).getPuntos_atencion().add(puntoAtencion.getId());
            oficinaRepository.save(oficina.get(0));
        }
        
        puntoAtencionRepository.save(puntoAtencion);
        return "redirect:/puntosAtencion";
    }

    @GetMapping("/puntosAtencion/{id}/delete")
    public String puntoAtencionEliminar(@PathVariable("id") int id){
        //validar que no haya ninguan operacion
        PuntoAtencion puntoAt = puntoAtencionRepository.buscarPorId(id);
        if(puntoAt.getOperaciones().size() == 0){
            PuntoAtencion puntoDetelete = puntoAtencionRepository.buscarPorId(id);
            puntoAtencionRepository.delete(puntoDetelete);

            //eliminar de la oficina asociada
            List<Oficina> oficinas = oficinaRepository.findAll();

            for(Oficina office: oficinas){
                List<Integer> puntosAt = office.getPuntos_atencion();
                for(Integer puntoAten: puntosAt){
                    if(puntoAten == id){
                        office.getPuntos_atencion().remove((Integer)id);
                        oficinaRepository.save(office);
                        break;
                    }
                }
            }
        }

        return "redirect:/puntosAtencion";
    }
    
    
}


package col.com.jager.web;

import col.com.jager.domain.Persona;
import col.com.jager.servicio.PersonaServicio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@Slf4j
public class ControladorInicio {
    
    @Autowired
    private PersonaServicio personaServicio;
    @GetMapping("/")                          //Peticion Get
    public String inicio(Model model , @AuthenticationPrincipal User user) { //La clase Model permite agregar información a nuestra vista
        var personas = personaServicio.listarPersonas();
        log.info("ejecuando el controlador spring MVC");
        log.info("Usuario que hizo login" + user);
        model.addAttribute("personas", personas);
        return "index";
    }
    
    @GetMapping("/agregar")
    public String agregar(Persona persona){
        return "modificar";
    }
    
    @PostMapping("/guardar")
    public String guardar(@Valid Persona persona, Errors errores){
        if (errores.hasErrors()){
            return "modificar";
        }
        personaServicio.guardar(persona);
        return "redirect:/";
    }
    
    @GetMapping("/editar/{idPersona}")
    public String editar(Persona persona, Model model){
       persona = personaServicio.encontrarPersona(persona);
       model.addAttribute("persona", persona);
       return "modificar";     
    }

    @GetMapping("/eliminar")
    public String eliminar(Persona persona){
        personaServicio.eliminar(persona);
        return "redirect:/";
    }
}


 /* var saldoTotal = 0D;
        for(var p: personas){
            saldoTotal += p.getSaldo();
        }
        model.addAttribute("saldoTotal", saldoTotal);
        model.addAttribute("totalClientes", personas.size());*/
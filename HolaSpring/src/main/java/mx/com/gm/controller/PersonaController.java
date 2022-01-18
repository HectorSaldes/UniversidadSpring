package mx.com.gm.controller;

import lombok.extern.slf4j.Slf4j;
import mx.com.gm.entity.Persona;
import mx.com.gm.service.PersonaImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
public class PersonaController {

    // * Con esto instanciamos o inyectamos la clase service
    @Autowired
    private PersonaImplementation service;

    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal User user) {
        List<Persona> personas = service.allPersonas();
        log.info("Ejecutando un controlador Spring MVC");
        log.info("Usuario que hizo login " + user);
        model.addAttribute("personas", personas);
        return "index";
    }

    @GetMapping("/agregar")
    public String agregar(Persona persona) {
        return "modificar";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Persona persona, Errors errors) {
        if (errors.hasErrors()) {
            return "modificar";
        }
        service.savePersona(persona);
        return "redirect:/";
    }

    @GetMapping("/editar/{idPersona}")
    public String editar(Persona persona, Model model) {
        persona = service.findOnePersona(persona);
        model.addAttribute("persona", persona);
        return "modificar";
    }

    @GetMapping("/eliminar")
    public String eliminar(Persona persona) {
        service.deletePersona(persona);
        return "redirect:/";
    }
}

package mx.com.gm.controller;

import lombok.extern.slf4j.Slf4j;
import mx.com.gm.entity.Persona;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@Slf4j
public class ControladorInicio {

    /* Importanto desde application.properties
        @Value("${index.saludo}")
        private String saludo;
     */

    @GetMapping("/thymeleaf")
    public String inicio(Model model) {
        String mensaje = "Adios Mundo desde Thymeleaf";
        Persona persona = new Persona();
        persona.setNombre("Hector");
        persona.setApellido("Saldaña");
        persona.setEmail("asd@g.com");
        persona.setTelefono("65448");

        Persona persona2 = new Persona();
        persona2.setNombre("Grecia");
        persona2.setApellido("Saldaña");
        persona2.setEmail("grecia@g.com");
        persona2.setTelefono("8798454");

        List<Persona> personas = Arrays.asList(persona, persona2);

        log.info("Ejecutando un controlador Spring MVC");

        model.addAttribute("mensaje", mensaje);
        // model.addAttribute("persona", persona);
        model.addAttribute("personas", personas);

        return "index";
    }
}



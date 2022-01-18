package mx.com.gm.service;

import mx.com.gm.entity.Persona;
import mx.com.gm.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// * Importamos la etiqueta Service para que sea reconocible y pueda ser inyectada
@Service
public class PersonaImplementation implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    @Transactional(readOnly = true) // ? Solo nos interesa que lea la información
    public List<Persona> allPersonas() {
        return (List<Persona>) personaRepository.findAll();
    }

    @Override
    @Transactional // ? Aquí se encarga de los commits o rollbacks si hubiesen para guardar los datos
    public void savePersona(Persona persona) {
        personaRepository.save(persona);
    }

    @Override
    @Transactional // ? Aquí se encarga de los commits o rollbacks si hubiesen para eliminar los datos
    public void deletePersona(Persona persona) {
        personaRepository.delete(persona);
    }

    @Override
    @Transactional(readOnly = true) // ? Solo nos interesa que lea la información
    public Persona findOnePersona(Persona persona) {
        // * Para evitar el optional, existe un metodo .orElse que si no encuentra el objeto, retorne algo más
        return personaRepository.findById(persona.getIdPersona()).orElse(null);
    }
}

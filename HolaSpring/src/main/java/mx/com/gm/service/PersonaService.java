package mx.com.gm.service;

import mx.com.gm.entity.Persona;

import java.util.List;

// * Creamos una interfaz donde usaremos los métodos
public interface PersonaService {
    List<Persona> allPersonas();
    void savePersona(Persona persona);
    void deletePersona(Persona persona);
    Persona findOnePersona(Persona persona);
}

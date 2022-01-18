package mx.com.gm.repository;

import mx.com.gm.entity.Persona;
import org.springframework.data.repository.CrudRepository;

// ! Ya no es necesario esta etiqueta cuando extendemos de CrudRepository
// @Repository
                                            // * Tipo de objeto, Tipo de Id del objeto
public interface PersonaRepository extends CrudRepository<Persona, Long> {

}

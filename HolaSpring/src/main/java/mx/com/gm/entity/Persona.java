package mx.com.gm.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data // ! Lombok setters y getters
@Entity // ? Una entitdad
@Table(name = "persona") // * Será una tabla y el nombre debe ser el mismo de la tabla en la base de datos

public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id // * Identificador ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) // * Manera en la que se ira incrementando e identificando
    private Long idPersona;

    @NotEmpty // * Se usa para cadenas, si usamos NotNull, solo revisa si no es objeto, ya que NotEmpty es recomentable para cadenas
    private String nombre;

    @NotEmpty
    private String apellido;

    @NotEmpty
    @Email // * Valida emails
    private String email;

    private String telefono;
}

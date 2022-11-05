package co.edu.uniquindio.proyecto.entidades;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.List;

/**
 * Esta clase define la entidad administrador
 *
 * @author: Daniel Ceballos, Angy Tabares
 */
@Entity
@ToString(callSuper = true)
@NoArgsConstructor
@Getter
@Setter
public class Administrador extends Persona implements Serializable {
    //Campos o atributos de la clase
    @OneToMany(mappedBy = "administrador")
    @JsonIgnore
    private List<Moderador> moderadoresCreados;


    @Builder
    public Administrador(@Email String email, String nickname, String password, String nombre) {
        super(email, nickname, password, nombre);
    }


}

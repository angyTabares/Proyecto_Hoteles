package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * Esta clase define la entidad moderador de la base de datos
 *
 * @author: Daniel Ceballos, Angy Tabares
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Moderador extends Persona implements Serializable {
    //Campos o atributos de la clase

    @ManyToOne
    @JoinColumn(nullable = false)
    private Administrador administrador;

    @OneToMany(mappedBy = "moderador")
    @JsonIgnore
    private List<Lugar> lugaresAutorizados;


    @Builder
    public Moderador(@Email String email, String nickname, String password, String nombre, Administrador administrador) {
        super(email, nickname, password, nombre);
        this.administrador = administrador;
    }

}

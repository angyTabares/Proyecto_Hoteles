package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.List;

/**
 * Esta clase define la entidad Usuario de la base de datos
 *
 * @author: Daniel Ceballos, Angy Tabares
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Usuario extends Persona implements Serializable {
    //Campos o atributos de la clase
    @Column(name = "latitud")
    private float latitud;
    @Column(name = "longitud")
    private float longitud;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Lugar> lugares;

    @ManyToOne
    private Ciudad ciudadUsuario;


    @ManyToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Lugar> lugaresFavoritos;

    @JsonIgnore
    @OneToMany(mappedBy = "usuarioComentario")
    @ToString.Exclude
    private List<Comentario> comentarios;


    @Builder
    public Usuario(String email, String nickname, String password, String nombre, Ciudad ciudadUsuario) {
        super(email, nickname, password, nombre);
        this.ciudadUsuario = ciudadUsuario;
    }


}

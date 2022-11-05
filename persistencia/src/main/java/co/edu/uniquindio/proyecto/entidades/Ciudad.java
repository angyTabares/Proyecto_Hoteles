package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Esta clase define la entidad ciudad de la base de datos
 *
 * @author: Daniel Ceballos, Angy Tabares
 */
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Ciudad implements Serializable {

    //Campos o atributos de la clase
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    @EqualsAndHashCode.Include
    private int codigo;

    @Column(name = "nombre", length = 200, nullable = false)
    private String nombre;

    @JsonIgnore
    @OneToMany(mappedBy = "ciudadUsuario")
    @ToString.Exclude
    private List<Usuario> usuarios;

    @JsonIgnore
    @OneToMany(mappedBy = "ciudadLugar")
    @ToString.Exclude
    private List<Lugar> lugares;

    @Builder
    public Ciudad(String nombre) {

        this.nombre = nombre;
    }


}

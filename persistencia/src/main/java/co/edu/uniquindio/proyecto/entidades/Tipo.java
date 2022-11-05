package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Esta clase define la entidad Tipo de lugar de la base de datos
 *
 * @author: Daniel Ceballos, Angy Tabares
 */
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Tipo implements Serializable {
    //Campos o atributos de la clase
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private int id;
    @Column(name = "nombre", length = 200, nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "tipo")
    @ToString.Exclude
    @JsonIgnore
    private List<Lugar> lugares;


    @Builder
    public Tipo(String nombre) {
        this.nombre = nombre;
    }

}

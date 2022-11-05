package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Esta clase define la entidad Persona que es una superclase
 *
 * @author: Daniel Ceballos, Angy Tabares
 */
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Persona implements Serializable {
    //Campos o atributos de la clase
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private int id;


    @Column(name = "email", nullable = false, unique = true)
    @NotBlank
    @Email
    @Size(max = 255)
    private String email;

    @Column(name = "nickname", nullable = false, unique = true)
    @NotBlank
    @Size(max = 255)
    private String nickname;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(max = 255)
    private String password;

    @Column(name = "nombre", nullable = false)
    @NotBlank
    @Size(max = 255)
    private String nombre;


    public Persona(@Email String email, String nickname, String password, String nombre) {

        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.nombre = nombre;
    }

    
}

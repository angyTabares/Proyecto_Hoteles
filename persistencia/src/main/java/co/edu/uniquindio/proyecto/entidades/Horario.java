package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

/**
 * Esta clase define la entidad Horario de la base de datos
 *
 * @author: Daniel Ceballos, Angy Tabares
 */
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Horario implements Serializable {
    //Campos o atributos de la clase
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private int id;


    @Column(name = "hora_apertura", nullable = false)

    private LocalTime horaApertura;


    @Column(name = "hora_cierre", nullable = false)

    private LocalTime horaCierre;

    @Column(name = "dia_semana", nullable = false)

    private String dia;

    @ManyToOne
    private Lugar horarioLugar;


}

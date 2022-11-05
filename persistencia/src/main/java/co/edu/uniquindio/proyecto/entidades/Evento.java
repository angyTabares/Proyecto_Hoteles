package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Esta clase define la entidad evento de la base de datos
 *
 * @author: Daniel Ceballos, Angy Tabares
 */
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Evento implements Serializable {
    //Campos o atributos de la clase
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "codigo")
    private int codigo;
    @Column(name = "nombre", length = 200, nullable = false)
    private String nombre;
    @Column(name = "descripcion", length = 1000, nullable = false)
    private String descripcion;
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha", nullable = false)
    private Date fecha;
    @Temporal(TemporalType.TIME)
    @Column(name = "hora", nullable = false)
    private Date hora;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Lugar eventoLugar;


    @Builder
    public Evento(String nombre, String descripcion, Date fecha, Lugar eventoLugar, Date hora) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.hora = hora;
        this.eventoLugar = eventoLugar;
    }

  
}

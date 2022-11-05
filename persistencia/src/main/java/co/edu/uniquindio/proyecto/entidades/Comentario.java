package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Date;

/**
 * Esta clase define la entidad comentario de la base de datos
 *
 * @author: Daniel Ceballos, Angy Tabares
 */
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Comentario implements Serializable {
    //Campos o atributos de la clase
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private int id;

    @Column(name = "comentario", length = 1000)
    @NotBlank
    private String comentario;

    @Positive
    @Column(name = "calificacion", nullable = false)
    @Positive
    @Max(5)
    @Min(1)
    private int calificacion;

    @Column(name = "respuesta", length = 1000)
    private String respuesta;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuarioComentario;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Lugar lugarComentario;


    @Builder
    public Comentario(String comentario, int calificacion, Lugar lugarComentario, Usuario usuarioComentario) {
        this.comentario = comentario;
        this.calificacion = calificacion;
        this.usuarioComentario = usuarioComentario;
        this.lugarComentario = lugarComentario;
    }

    public Comentario(@NotBlank String comentario, @Positive @Positive @Max(5) @Min(1) int calificacion, String respuesta, Date fecha, Usuario usuarioComentario, Lugar lugarComentario) {
        this.comentario = comentario;
        this.calificacion = calificacion;
        this.respuesta = respuesta;
        this.fecha = fecha;
        this.usuarioComentario = usuarioComentario;
        this.lugarComentario = lugarComentario;
    }
}

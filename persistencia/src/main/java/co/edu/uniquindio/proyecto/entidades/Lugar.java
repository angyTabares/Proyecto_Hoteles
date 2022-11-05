package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.ZoneId;
import java.util.*;

/**
 * Esta clase define la entidad lugar de la base de datos
 *
 * @author: Daniel Ceballos, Angy Tabares
 */
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Lugar implements Serializable {
    //Campos o atributos de la clase
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "codigo")
    private int codigo;

    @Column(name = "descripcion", length = 1000, nullable = false)
    @NotBlank
    @Size(max = 1000)
    private String descripcion;

    @Column(name = "nombre", length = 200, nullable = false)
    @NotBlank
    @Size(max = 200)
    private String nombre;

    @Column(name = "latitud", nullable = false)
    private Float latitud;

    @Column(name = "longitud", nullable = false)
    private Float longitud;

    @Column(name = "fecha_creacion", nullable = false)
    private Date fecha_creacion;

    @Column(name = "fecha_aprobacion")
    private Date fecha_aprobacion;

    @ElementCollection
    @JoinColumn(nullable = false)
    @ToString.Exclude
    @Column(name = "url_imagen")
    private List<String> imagenes = new ArrayList<>();


    @Column(name = "estado")
    private boolean estado;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Ciudad ciudadLugar;

    @ManyToOne()
    @JoinColumn(nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Tipo tipo;

    @OneToMany(mappedBy = "eventoLugar")
    @ToString.Exclude
    @JsonIgnore
    private List<Evento> eventos;

    @ElementCollection
    @JoinColumn(nullable = false)
    @Column(name = "numero_telefonos")
    @ToString.Exclude
    private List<String> telefonos;

    @OneToMany(mappedBy = "horarioLugar")
    @JsonIgnore
    private List<Horario> horarios;

    @ManyToMany(mappedBy = "lugaresFavoritos")
    @ToString.Exclude
    @JsonIgnore
    private List<Usuario> usuariosFavoritos;

    @ManyToOne
    private Moderador moderador;

    @OneToMany(mappedBy = "lugarComentario")
    @ToString.Exclude
    @JsonIgnore
    private List<Comentario> comentarios;


    @Builder
    public Lugar(@NotBlank @Size(max = 1000) String descripcion, @Size(max = 200) String nombre, Float latitud, Float longitud, List<String> imagenes, Ciudad ciudadLugar, @NotBlank Usuario usuario, @NotBlank Tipo tipo, List<Horario> horarios) {
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.imagenes = imagenes;
        this.ciudadLugar = ciudadLugar;
        this.usuario = usuario;
        this.tipo = tipo;
        this.horarios = horarios;
    }

    public Lugar(@NotBlank @Size(max = 1000) String descripcion, @NotBlank @Size(max = 200) String nombre, Ciudad ciudadLugar, Usuario usuario, Tipo tipo, Moderador moderador) {
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.ciudadLugar = ciudadLugar;
        this.usuario = usuario;
        this.tipo = tipo;
        this.moderador = moderador;

    }

    public String getImagenPrincipal() {
        if (!imagenes.isEmpty()) return imagenes.get(0);
        return "defaultLugarImg.svg";

    }


}

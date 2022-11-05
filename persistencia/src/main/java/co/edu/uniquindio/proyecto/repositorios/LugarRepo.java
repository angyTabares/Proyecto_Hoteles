package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.dto.ComentariosLugarDTO;
import co.edu.uniquindio.proyecto.dto.LugaresPorUsuarioDTO;
import co.edu.uniquindio.proyecto.dto.NumeroLugaresPorCategoriaDTO;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Horario;
import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Esta interface define el deposito de datos de lugar
 *
 * @author: Daniel Ceballos, Angy Tabares
 */
@Repository
public interface LugarRepo extends JpaRepository<Lugar, Integer> {

    @Query("select l.tipo.nombre from Lugar l where l.codigo = :codigo")
    String obtenerTipoLugar(Integer codigo);

    @Query("select l.nombre,l.latitud, l.longitud from Lugar l where l.codigo = :codigo")
    List<Object[]> obtenerInfo(Integer codigo);

    @Query("select new co.edu.uniquindio.proyecto.dto.ComentariosLugarDTO(l, c)  from Lugar  l  left join l.comentarios c ")
    List<ComentariosLugarDTO> obtenerComentariosLugares();

    @Query("select l.nombre, l.descripcion, l.ciudadLugar.nombre, l.tipo.nombre from Lugar l where l.moderador.email= :emailModerador")
    List<Object[]> obtenerLugaresModerador(String emailModerador);

    @Query("select count(c) from Lugar l join l.comentarios c where l.codigo= :codigo")
    int obtenerCantidadComentarios(Integer codigo);

    @Query("select new co.edu.uniquindio.proyecto.dto.NumeroLugaresPorCategoriaDTO(l.tipo.nombre,count(l))  from Lugar l group by l.tipo")
    List<NumeroLugaresPorCategoriaDTO> obtenerCantidadLugaresPorCategoria();

    @Query("select l from Lugar l where l.horarios is empty ")
    List<Lugar> obtenerLugaresSinHorarios();

    @Query("select l.ciudadLugar.nombre, count(l) from Lugar l group by l.ciudadLugar")
    List<Object[]> obtenerCantidadLugaresPorCiudad();

    //  @Query("select l from Lugar l join l.horarios h where h.diaSemana and  :horaActual between h.horaApertura and h.horaCierre ")
    //  List<Lugar> obtenerLugaresAbiertos(String diaSemana, Date horaActual);

    @Query("select l.tipo.nombre,count(l) as total from Lugar l where l.estado=true group by l.tipo order by total desc ")
    List<Object[]> obtenerTipoLugarPopular();

    @Query("select avg (c.calificacion) from Lugar l join l.comentarios c where l.codigo = :codigo")
    Integer obtenerCalificacionPromedio(Integer codigo);


    @Query("select l.tipo.nombre,avg (c.calificacion) as total  from Lugar l join l.comentarios c where l.ciudadLugar.codigo= :codigo group by l order by total desc")
    List<Object[]> obtenerLugarCalificacionMasAltaPorCiudad(Integer codigo);


    @Query("select l,avg (c.calificacion) as total  from Lugar l join l.comentarios c where l.ciudadLugar.codigo= :codigo group by l order by total desc")
    List<Object[]> obtenerLugaresCalificacionPorCiudad(Integer codigo);


    @Query("select l.ciudadLugar.nombre ,count(l)  from Lugar l  where l.estado=false group by l.ciudadLugar")
    List<Object[]> obtenerCantidadLugaresNoAprobadosPorCiudad();

    @Query("select l.tipo.nombre,count(l)  from Lugar l join l.horarios h where h.dia= :diaSemana and  :horaActual between h.horaApertura and h.horaCierre group by l.tipo")
    List<Object[]> obtenerCantidadLugaresAbiertosPorCategoria(String diaSemana, Date horaActual);

    /**
     * Query que permite traer los lugares creados por un usuario especifico
     */
    @Query("select l from Lugar l where l.usuario.email = :emailUsuario")
    List<Lugar> obtenerLugaresCreadosPorUsuario(String emailUsuario);

    /**
     * Query que permite traer un listado de todos los lugares y la información del usuario que los creó
     */
    @Query("select new co.edu.uniquindio.proyecto.dto.LugaresPorUsuarioDTO(u,l) from Usuario u left join u.lugares l")
    List<LugaresPorUsuarioDTO> obtenerListaLugaresEinformacionUsuarioCreador();

    /**
     * Query que permite traer un listado los comentarios de un lugar especifico
     */
    @Query("select c from Comentario c where c.lugarComentario.codigo = :id_lugar")
    List<Comentario> obtenerComentariosPorLugar(int id_lugar);

    /**
     * Query que permite traer un listado los comentarios sin respuesta de un usuario que ha creado lugares
     */
    @Query("select c from Lugar l join l.comentarios c where l.usuario.id=:id_usuario and c.respuesta is null")
    List<Object> obtenerComentarioSinRespuestaPorUsuario(int id_usuario);

    /**
     * Query que permite traer un listado con la cantidad de comentarios de 1,2,3,4,5 estrellas por lugar
     */
    @Query("select c.calificacion, count(c.calificacion)from Lugar l join l.comentarios c where l.codigo=:codigo_lugar group by c.calificacion")
    List<Object[]> obtenerCantComentariosPorCalificacionLugar(int codigo_lugar);

    @Query("select l from Lugar l ")
    List<Object[]> ensayo(int ciudad);

    Optional<Lugar> findByNombre(String nombre);

    Optional<Lugar> findByCodigo(int codigo);

    //lugares que esten aprobados por algun moderador
    @Query("select l from Lugar l where l.nombre like concat('%', :nombre, '%') or l.tipo.nombre like concat('%', :nombre, '%') ")
    List<Lugar> buscarLugares(String nombre);

    @Query("select h from Horario h where h.horarioLugar.codigo  = :id_lugar")
    List<Horario> obtenerHorariosPorLugar(int id_lugar);

    @Query("select l from Lugar l where  l.moderador is null ")
    List<Lugar> obtenerLugaresPendientes();

    @Query("select l from Lugar l where  l.estado=true")
    List<Lugar> obtenerLugaresAprobados();

    @Query("select l from Lugar l where (l.nombre like concat('%', :nombre, '%') or l.tipo.nombre  like concat('%', :nombre, '%') ) and l.estado=true")
    List<Lugar> obtenerLugaresAprobadosPorNombreOTipo(String nombre);

    @Query("select l from Lugar l where l.moderador.email= :emailModerador and l.estado=true ")
    List<Lugar> obtenerLugaresAprobadosModerador(String emailModerador);

    @Query("select l from Lugar l where l.moderador.email= :emailModerador and l.estado=false ")
    List<Lugar> obtenerLugaresDenegadosModerador(String emailModerador);


}

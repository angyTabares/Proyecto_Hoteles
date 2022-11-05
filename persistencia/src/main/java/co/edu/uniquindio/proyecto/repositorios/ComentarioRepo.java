package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Esta interface define el deposito de datos de comentario
 *
 * @author: Daniel Ceballos, Angy Tabares
 */
@Repository
public interface ComentarioRepo extends JpaRepository<Comentario, Integer> {

    @Query("select c from Comentario c where c.calificacion >?1")
    List<Comentario> obtenerListaPorCalificacion(int calificacion);

    @Query("select distinct c.usuarioComentario from Comentario c where c.lugarComentario.codigo = :id ")
    List<Usuario> usuariosComentarios(Integer id);

    @Query("select c.lugarComentario,c.calificacion,count(c.calificacion) as total from Comentario c where c.lugarComentario.codigo= :codigo group by c.calificacion ")
    List<Object[]> cantidadComentariosDeUnLugarEspecifico(Integer codigo);

    @Query("delete from Comentario c where c.lugarComentario.codigo =:codigo")
    void eliminarComentariosLugar(int codigo);
}

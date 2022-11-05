package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Esta interface define el deposito de datos de usuario
 *
 * @author: Daniel Ceballos, Angy Tabares
 */
@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {

    @Query("select u from Usuario u")
    List<Usuario> obtenerUsuarios();

    @Query("select u from Usuario u")
    List<Usuario> obtenerUsuarios(Pageable pageable);

    @Query("select u from Usuario u")
    List<Usuario> obtenerUsuariosAlfabeticamente(Sort sort);

    @Query("select u from Usuario u")
    List<Usuario> obtenerUsuarios(Sort pageable);

    @Query("select u from Usuario u where u.email= ?1 and u.nombre= ?2")
    Usuario obtenerUsuario(String email, String nombre);

    //se infiere la consulta y no necesita el query
    // Usuario findByEmailorNombre(String email, String nombre );

    void deleteAllByNombre(String nombre);

    List<Usuario> findByNombre(String nombre);

    @Query("select f from Usuario u, IN(u.lugaresFavoritos)f where u.id= :id")
    List<Lugar> obtenerLugaresFavoritos(Integer id);

    //metodo que obtiene el email del usuario y los lugares publicados incluye aquellos que no han publicado
    @Query("select u.email , l from Usuario u left join u.lugares l")
    List<Object[]> obtenerLugaresPublicadosUsuarios();

    @Query("select u from Usuario u order by u.nombre")
    List<Usuario> obtenerListaUsuarioAlfabeticamente();

    @Query("select u from Usuario u where u.email like '%gmail.%'")
    List<Usuario> obtenerUsuariosDeGmail();

    @Query("select u from Usuario u where u.email like concat('%',:dominio, '%') ")
    List<Usuario> obtenerUsuariosDeDominio(String dominio);

    Optional<Usuario> findByNickname(String nickname);

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findById(int id);


}

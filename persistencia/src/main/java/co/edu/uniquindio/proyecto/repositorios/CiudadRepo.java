package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Esta interface define el deposito de datos de ciudad
 *
 * @author: Daniel Ceballos, Angy Tabares
 */
@Repository
public interface CiudadRepo extends JpaRepository<Ciudad, Integer> {

    @Query("select u from Ciudad c join c.usuarios u where c.nombre = :nombreCiudad")
    List<Usuario> obtenerListaUsuarios(String nombreCiudad);

    @Query("select c.nombre, u from Ciudad c left join c.usuarios u")
    List<Object[]> obtenerListaUsuariosJoinLeft();

    /**
     * Query que devuelve una lista de la cantidad de lugares no aprobados por cada ciudad
     */

    @Query("select c.nombre,count(l)  from Ciudad c LEFT JOIN c.lugares l ON l.estado=false group by  c")
    List<Object[]> obtenerListaLugares();


    Optional<Ciudad> findByCodigo(int codigo);
    //  Optional<Ciudad>  findByEmail(String email);


}

package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.entidades.Moderador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Esta interface define el deposito de datos de moderador
 * @author: Daniel Ceballos, Angy Tabares
 */
@Repository
public interface ModeradorRepo extends JpaRepository<Moderador,Integer> {

    Optional<Moderador> findByNickname(String nickname);
    Optional<Moderador> findByEmail(String email);
    Optional<Moderador> findById(Integer id);

   // @Query("select m.lugaresAutorizados from Moderador m  where m.email= :emailModerador ")
   // List<Lugar> obtenerLugaresModerador(String emailModerador);
}

package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Moderador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Esta interface define el deposito de datos de administrador
 * @author: Daniel Ceballos, Angy Tabares
 */
@Repository
public interface AdministradorRepo extends JpaRepository<Administrador,Integer> {

    Optional<Administrador> findByNickname(String nickname);
    Optional<Administrador> findByEmail(String email);
    Optional<Administrador> findById(Integer id);

}

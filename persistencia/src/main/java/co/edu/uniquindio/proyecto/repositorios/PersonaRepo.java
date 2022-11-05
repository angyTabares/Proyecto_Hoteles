package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepo extends JpaRepository<Persona, Integer> {

    Optional<Persona> findByEmailAndPassword(String email, String password);

    Optional<Persona> findByEmailAndNickname(String email, String nickname);


}

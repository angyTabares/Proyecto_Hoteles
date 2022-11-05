package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Evento;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Esta interface define el deposito de datos de evento
 * @author: Daniel Ceballos, Angy Tabares
 */
@Repository
public interface EventoRepo extends JpaRepository<Evento,Integer> {



}

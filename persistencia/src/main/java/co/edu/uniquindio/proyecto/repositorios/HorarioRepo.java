package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Horario;
import co.edu.uniquindio.proyecto.entidades.Lugar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Esta interface define el deposito de datos de horario
 *
 * @author: Daniel Ceballos, Angy Tabares
 */
@Repository
public interface HorarioRepo extends JpaRepository<Horario, Integer> {

    @Query("Delete from Horario h where h.horarioLugar.codigo =:id")
    void eliminarHorariosLugar(int id);
}

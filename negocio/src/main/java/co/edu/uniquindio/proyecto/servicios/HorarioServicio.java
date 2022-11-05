package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Horario;
import co.edu.uniquindio.proyecto.entidades.Lugar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HorarioServicio {

    Horario crearHorario(Horario horario) throws Exception;

    Horario actualizarHorario(Horario horario) throws Exception;

    void eliminarHorariosLugar(int id) throws Exception;
}

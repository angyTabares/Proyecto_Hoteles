package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Horario;
import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.repositorios.HorarioRepo;
import co.edu.uniquindio.proyecto.repositorios.LugarRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HorarioServicioImpl implements HorarioServicio {

    private HorarioRepo horarioRepo;
    private LugarRepo lugarRepo;

    public HorarioServicioImpl(HorarioRepo horarioRepo, LugarRepo lugarRepo) {
        this.horarioRepo = horarioRepo;
        this.lugarRepo = lugarRepo;
    }

    @Override
    public Horario crearHorario(Horario horario) throws Exception {

        if (horario.getDia().isEmpty()) {
            throw new Exception("El dia es obligatorio");
        }
        if (horario.getHoraApertura() == null) {
            throw new Exception("La hora de apertura es obligatoria");
        }
        if (horario.getHoraCierre() == null) {
            throw new Exception("La hora de cierre es obligatoria");
        }

        return horarioRepo.save(horario);
    }

    @Override
    public Horario actualizarHorario(Horario horario) throws Exception {


        return horarioRepo.save(horario);
    }

    @Override
    public void eliminarHorariosLugar(int id) throws Exception {

        Optional<Lugar> lugar = lugarRepo.findByCodigo(id);
        if (lugar.isEmpty()) {
            throw new Exception("El lugar no existe");
        }

        horarioRepo.eliminarHorariosLugar(id);

    }
}

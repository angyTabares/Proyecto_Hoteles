package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.LugaresNoAutDTO;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Tipo;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CiudadServicioImpl implements CiudadServicio {

    private final CiudadRepo ciudadRepo;

    public CiudadServicioImpl(CiudadRepo ciudadRepo) {
        this.ciudadRepo = ciudadRepo;
    }

    @Override
    public Ciudad registrarCiudad(Ciudad ciudad) throws Exception {
        return ciudadRepo.save(ciudad);
    }

    @Override
    public Ciudad obtenerCiudad(int id) throws Exception {
        Optional<Ciudad> ciudad = ciudadRepo.findByCodigo(id);
        if (ciudad.isEmpty()) {
            throw new Exception("No existe una ciudad con el id dado");
        }

        return ciudad.get();

    }

    @Override
    public List<Ciudad> listarCiudades() {
        return ciudadRepo.findAll();
    }

    @Override
    public List<LugaresNoAutDTO> listaLugaresNoAutPorCiudad() {

        List<LugaresNoAutDTO> lugaresNoAutDTOS = new ArrayList<>();
        List<Object[]> listaObjetos = ciudadRepo.obtenerListaLugares();
        for (Object[] o : listaObjetos) {
            LugaresNoAutDTO l = new LugaresNoAutDTO((String) o[0], (Long) o[1]);
            lugaresNoAutDTOS.add(l);
        }

        return lugaresNoAutDTOS;
    }

}

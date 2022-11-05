package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.LugaresNoAutDTO;
import co.edu.uniquindio.proyecto.entidades.Ciudad;

import java.util.List;

public interface CiudadServicio {
    Ciudad registrarCiudad(Ciudad ciudad) throws Exception;

    Ciudad obtenerCiudad(int id) throws Exception;

    List<Ciudad> listarCiudades();

    List<LugaresNoAutDTO> listaLugaresNoAutPorCiudad();
}

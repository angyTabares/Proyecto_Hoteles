package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Tipo;

import java.util.List;

public interface TipoServicio {

    Tipo registrarTipoLugar(Tipo t) throws Exception;
    void eliminarTipoLugar(Tipo t) throws Exception;
    Tipo actualizarTipoLugar(Tipo t) throws Exception;
    Tipo obtenerTipoLugar(int id) throws Exception;
    List<Tipo> listarTiposLugares() ;


}

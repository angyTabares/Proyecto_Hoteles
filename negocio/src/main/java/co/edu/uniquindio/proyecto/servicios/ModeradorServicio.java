package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Moderador;

import java.util.List;

public interface ModeradorServicio {

    Moderador registrarModerador(Moderador m)throws Exception;
    void eliminarModerador(Moderador m) throws Exception;
    Moderador actualizarModerador(Moderador m) throws  Exception;
    Moderador obtenerModerador(int id) throws Exception;
    List<Moderador> listarModeradores() throws Exception;
}

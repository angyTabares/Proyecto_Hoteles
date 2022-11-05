package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Evento;

import java.util.List;

public interface EventoServicio {
    Evento obtenerEvento(int codigo) throws Exception;
    Evento crearEvento(Evento evento) throws  Exception;
    List<Evento> listarEventos() throws  Exception;
    Evento actualizarEvento(Evento evento) throws Exception;
    void eliminarEvento(Evento evento) throws Exception;
}

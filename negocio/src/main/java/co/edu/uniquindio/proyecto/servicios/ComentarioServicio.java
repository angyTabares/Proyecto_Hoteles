package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import com.sun.jdi.event.ExceptionEvent;

import java.util.List;

public interface ComentarioServicio {
    Comentario obtenerComentario(int codigo) throws Exception;

    Comentario crearComentario(Comentario comentario) throws Exception;

    void eliminarComentario(Comentario comentario) throws Exception;

    List<Comentario> listarComentarios() throws Exception;

    Comentario actualizarComentario(Comentario comentario) throws Exception;

    void eliminarComentariosLugar(int codigo) throws Exception;

}


package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Persona;
import org.aspectj.weaver.patterns.PerObject;

public interface PersonaServicio {

    Persona login(String email, String password) throws Exception;

    Persona recuperarPassword(String email, String nickname) throws Exception;

    Persona actualizarDatos(Persona p) throws Exception;
}

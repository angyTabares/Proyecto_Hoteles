package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Administrador;

import java.util.List;

public interface AdministradorServicio {

    Administrador registrarAdmin(Administrador admin) throws Exception;

    void eliminarAdmin(Administrador admin) throws Exception;

    Administrador actualizarAdmin(Administrador admin) throws Exception;

    Administrador obtenerAdmin(int codigo) throws Exception;

    List<Administrador> listarAdmins() throws Exception;

    boolean existenAdmins() throws Exception;
}

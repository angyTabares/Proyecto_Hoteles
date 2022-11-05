package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Usuario;

import java.util.List;

public interface UsuarioServicio {
    Usuario registrarUsuario(Usuario u) throws Exception;
    void eliminarUsuario(Usuario u) throws Exception;
    Usuario actualizarUsuario(String email) throws Exception;
    Usuario obtenerUsuario(Integer id) throws Exception;
    List<Usuario> listarUsuario() throws Exception;
    Usuario actualizarUsuarioCompleto(Usuario u) throws Exception;
}

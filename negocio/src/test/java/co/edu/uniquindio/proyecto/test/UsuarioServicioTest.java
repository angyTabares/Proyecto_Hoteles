package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;


@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class UsuarioServicioTest {
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private CiudadServicio ciudadServicio;

    @Test
    public void registrarUsuarioTest() {
        try {
            Ciudad ciudad = new Ciudad("Armenia");
            ciudadServicio.registrarCiudad(ciudad);

            Usuario usuario = new Usuario("daniel@gmail.com", "Daniel", "1234 ", "Danielxd", ciudad);
            Usuario usuarioRegistrado = usuarioServicio.registrarUsuario(usuario);

            System.out.println(usuarioRegistrado);
            Assertions.assertNotNull(usuarioRegistrado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql({"classpath:ciudad.sql", "classpath:usuario.sql"})
    public void eliminarUsuarioTest() {
        try {
            Usuario usuario = usuarioServicio.obtenerUsuario(1);
            usuarioServicio.eliminarUsuario(usuario);

            Usuario usuarioEliminado = usuarioServicio.obtenerUsuario(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql({"classpath:ciudad.sql", "classpath:usuario.sql"})
    public void actualizarUsuarioTest() {
        try {
            Usuario usuario = usuarioServicio.obtenerUsuario(1);
            System.out.println("Usuario antes del update: " + usuario);
            usuario.setNombre("Daniel Ceballos");
            Usuario usuarioActualizado = usuarioServicio.actualizarUsuario(usuario.getEmail());
            System.out.println("Usuario despues del update: " + usuarioActualizado);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @Sql({"classpath:ciudad.sql", "classpath:usuario.sql"})
    public void listarUsuarioTest() {
        try {
            for (Usuario user : usuarioServicio.listarUsuario()) {
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ComentarioServicio;
import co.edu.uniquindio.proyecto.servicios.LugarServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class ComentarioServicioTest {
    @Autowired
    private LugarServicio lugarServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private ComentarioServicio comentarioServicio;

    @Test
    @Sql({"classpath:ciudad.sql", "classpath:tipo.sql", "classpath:usuario.sql", "classpath:administrador.sql", "classpath:moderador.sql", "classpath:lugar.sql", "classpath:comentario.sql"})
    public void crearComentarioTest() {
        try {
            Lugar lugar = lugarServicio.obtenerLugar(1);
            Usuario usuario = usuarioServicio.obtenerUsuario(1);


            Date fechaActual = new Date();
            String coment = "Excelente el servicio recibido";
            int cal = 4;
            Comentario comentario = new Comentario(coment, cal, null, fechaActual, usuario, lugar);
            Comentario comentarioGuardado = comentarioServicio.crearComentario(comentario);
            System.out.println(comentarioGuardado);
            Assertions.assertNotNull(comentarioGuardado);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @Sql({"classpath:ciudad.sql", "classpath:tipo.sql", "classpath:usuario.sql", "classpath:administrador.sql", "classpath:moderador.sql", "classpath:lugar.sql", "classpath:comentario.sql"})

    public void eliminarComentarioTest() {
        try {
            Comentario comentario = comentarioServicio.obtenerComentario(1);
            comentarioServicio.eliminarComentario(comentario);

            Comentario comentarioEliminado = comentarioServicio.obtenerComentario(1);
            Assertions.assertNull(comentarioEliminado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql({"classpath:ciudad.sql", "classpath:tipo.sql", "classpath:usuario.sql", "classpath:administrador.sql", "classpath:moderador.sql", "classpath:lugar.sql", "classpath:comentario.sql"})
    public void actualizarComentarioTest() {
        try {
            Comentario comentario = comentarioServicio.obtenerComentario(1);
            System.out.println("Comentario antes del update: " + comentario);
            comentario.setCalificacion(2);
            comentario.setComentario("Pesima comida");
            Comentario comentarioActualizado = comentarioServicio.actualizarComentario(comentario);
            System.out.println("Comentario despues del update: " + comentarioActualizado);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    @Sql({"classpath:ciudad.sql", "classpath:tipo.sql", "classpath:usuario.sql", "classpath:administrador.sql", "classpath:moderador.sql", "classpath:lugar.sql", "classpath:comentario.sql"})
    public void listarComentariosTest() {
        try {
            List<Comentario> lista = comentarioServicio.listarComentarios();
            for (Comentario c : lista) {
                System.out.println(c.getComentario());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

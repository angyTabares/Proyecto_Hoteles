package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Moderador;
import co.edu.uniquindio.proyecto.servicios.AdministradorServicio;
import co.edu.uniquindio.proyecto.servicios.ModeradorServicio;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@SpringBootTest(classes = NegocioApplication.class)
@Transactional

public class ModeradorServicioTest {

    @Autowired
    private ModeradorServicio moderadorServicio;
    @Autowired
    private AdministradorServicio administradorServicio;

    @Test
    public void registrarModeradorTest() {
        try {

            Administrador admin = new Administrador("CamilaAdmin@gmail.com", "admin321", "147852", "Camila");
            administradorServicio.registrarAdmin(admin);

            Moderador moderador = new Moderador("SebastianMod@gmail.com", "moderador3", "123456", "Sebastian", admin);
            Moderador moderadorGuardado = moderadorServicio.registrarModerador(moderador);

            System.out.println(moderadorGuardado);
            Assertions.assertNotNull(moderadorGuardado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql({"classpath:ciudad.sql", "classpath:usuario.sql", "classpath:administrador.sql", "classpath:moderador.sql"})
    public void eliminarModeradorTest() {
        try {
            Moderador moderador = moderadorServicio.obtenerModerador(1);
            moderadorServicio.eliminarModerador(moderador);

            Moderador moderadorEliminado = moderadorServicio.obtenerModerador(1);
            Assertions.assertNull(moderadorEliminado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql({"classpath:ciudad.sql", "classpath:usuario.sql", "classpath:administrador.sql", "classpath:moderador.sql"})
    public void actualizarModeradorTest() {
        try {
            Moderador moderador = moderadorServicio.obtenerModerador(1);
            System.out.println("moderador antes del update: " + moderador);

            moderador.setNombre("Juan");

            Moderador moderadorActualizado = moderadorServicio.actualizarModerador(moderador);
            System.out.println("moderador antes del update: " + moderadorActualizado);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql({"classpath:ciudad.sql", "classpath:usuario.sql", "classpath:administrador.sql", "classpath:moderador.sql"})
    public void listarModeradoresTest() {
        try {
            List<Moderador> lista = moderadorServicio.listarModeradores();

            for (Moderador m : lista) {
                System.out.println(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

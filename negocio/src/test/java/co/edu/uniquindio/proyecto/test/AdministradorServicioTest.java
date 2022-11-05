package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.servicios.AdministradorServicio;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.stylesheets.LinkStyle;


import java.util.List;


@SpringBootTest(classes = NegocioApplication.class)
@Transactional

public class AdministradorServicioTest {

    @Autowired
    private AdministradorServicio administradorServicio;

    @Test
    public void registrarAdminTest() {
        try {
            Administrador admin = new Administrador("CamilaAdmin@gmail.com", "admin321", "147852", "Camila");
            Administrador adminGuardado = administradorServicio.registrarAdmin(admin);

            System.out.println(adminGuardado);
            Assertions.assertNotNull(adminGuardado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql({"classpath:ciudad.sql", "classpath:usuario.sql", "classpath:administrador.sql"})
    public void eliminarAdminTest() {
        try {
            Administrador admin = administradorServicio.obtenerAdmin(1);
            administradorServicio.eliminarAdmin(admin);

            Administrador adminEliminado = administradorServicio.obtenerAdmin(1);
            Assertions.assertNull(adminEliminado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql({"classpath:ciudad.sql", "classpath:usuario.sql", "classpath:administrador.sql"})
    public void actualizarAdminTest() {
        try {
            Administrador admin = administradorServicio.obtenerAdmin(1);
            System.out.println("Admin antes del update: " + admin);

            admin.setNombre("Angy Paola");

            Administrador adminActualizado = administradorServicio.actualizarAdmin(admin);
            System.out.println("Admin despues del update: " + adminActualizado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql({"classpath:ciudad.sql", "classpath:usuario.sql", "classpath:administrador.sql"})
    public void listarAdminsTest() {
        try {
            List<Administrador> lista = administradorServicio.listarAdmins();

            for (Administrador l : lista) {
                System.out.println(l);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

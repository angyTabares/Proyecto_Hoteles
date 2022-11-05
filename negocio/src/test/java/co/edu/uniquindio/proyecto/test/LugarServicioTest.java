package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;

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

public class LugarServicioTest {

    @Autowired
    private LugarServicio lugarServicio;
    @Autowired
    private CiudadServicio ciudadServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private TipoServicio tipoServicio;
    @Autowired
    private AdministradorServicio administradorServicio;
    @Autowired
    private ModeradorServicio moderadorServicio;

    @Test
    public void registrarLugarTest() {
        try {

            Ciudad ciudad = new Ciudad("Cali");
            ciudadServicio.registrarCiudad(ciudad);

            Usuario usuario = new Usuario("angy@gmail.com", "angy123", "147852 ", "Angy", ciudad);
            usuarioServicio.registrarUsuario(usuario);

            Tipo tipo = new Tipo("disco");
            tipoServicio.registrarTipoLugar(tipo);

            Administrador administrador = new Administrador("CamilaAdmin@gmail.com", "admin321", "147852", "Camila");
            administradorServicio.registrarAdmin(administrador);

            Moderador moderador = new Moderador("SebastianMod@gmail.com", "moderador3", "123456", "Sebastian", administrador);
            moderadorServicio.registrarModerador(moderador);

            Lugar lugarNuevo = new Lugar("la mejor disco de salsa", "swing salsero", ciudad, usuario, tipo, moderador);

            Lugar lugarGuardado = lugarServicio.crearLugar(lugarNuevo);
            System.out.println(lugarGuardado);
            Assertions.assertNotNull(lugarGuardado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql({"classpath:ciudad.sql", "classpath:tipo.sql", "classpath:usuario.sql", "classpath:administrador.sql", "classpath:moderador.sql", "classpath:lugar.sql"})
    public void eliminarLugarTest() {
        try {
            Lugar lugar = lugarServicio.obtenerLugar(1);
            lugarServicio.eliminarLugar(lugar);

            Lugar lugarEliminado = lugarServicio.obtenerLugar(1);

            Assertions.assertNull(lugarEliminado);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @Sql({"classpath:ciudad.sql", "classpath:tipo.sql", "classpath:usuario.sql", "classpath:administrador.sql", "classpath:moderador.sql", "classpath:lugar.sql"})
    public void actualizarLugarTest() {

        try {
            Lugar lugar = lugarServicio.obtenerLugar(1);

            System.out.println("Lugar antes del update: " + lugar);
            lugar.setDescripcion("come al lado de la mejor vista");
            Lugar lugarActualizado = lugarServicio.actualizarLugar(lugar);
            System.out.println("Lugar despues del update: " + lugarActualizado);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql({"classpath:ciudad.sql", "classpath:tipo.sql", "classpath:usuario.sql", "classpath:administrador.sql", "classpath:moderador.sql", "classpath:lugar.sql"})
    public void listarLugarTest() {
        try {
            List<Lugar> lista = lugarServicio.listarLugares();

            for (Lugar l : lista) {
                System.out.println(l);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
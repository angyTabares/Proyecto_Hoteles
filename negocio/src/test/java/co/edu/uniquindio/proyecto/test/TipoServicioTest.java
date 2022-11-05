package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.entidades.Tipo;
import co.edu.uniquindio.proyecto.servicios.TipoServicio;

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

public class TipoServicioTest {

    @Autowired
    private TipoServicio tipoServicio;

    @Test
    public void registrarTipoLugarTest() {
        try {
            Tipo tipoLugar = new Tipo("Restaurante");
            Tipo tipoLugarGuardado = tipoServicio.registrarTipoLugar(tipoLugar);
            System.out.println(tipoLugarGuardado);
            Assertions.assertNotNull(tipoLugarGuardado);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql({"classpath:tipo.sql"})
    public void eliminarTipoLugarTest() {
        try {
            Tipo tipoLugar = tipoServicio.obtenerTipoLugar(1);
            tipoServicio.eliminarTipoLugar(tipoLugar);

            Tipo tipoLugarEliminado = tipoServicio.obtenerTipoLugar(1);
            Assertions.assertNull(tipoLugarEliminado);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql({"classpath:tipo.sql"})
    public void actualizarTipoLugarTest() {
        try {
            Tipo tipoLugar = tipoServicio.obtenerTipoLugar(1);
            System.out.println("tipo lugar antes del update: " + tipoLugar);

            tipoLugar.setNombre("bar");
            Tipo tipoLugarActualizado = tipoServicio.actualizarTipoLugar(tipoLugar);
            System.out.println("tipo lugar despues del update: " + tipoLugarActualizado);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql({"classpath:tipo.sql"})
    public void listarTipoLugaresTest() {
        try {
            List<Tipo> lista = tipoServicio.listarTiposLugares();

            for (Tipo t : lista) {
                System.out.println(t);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

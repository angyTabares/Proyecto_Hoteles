package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Evento;
import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.servicios.EventoServicio;
import co.edu.uniquindio.proyecto.servicios.LugarServicio;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@SpringBootTest(classes = NegocioApplication.class)
@Transactional

public class EventoServicioTest {
    @Autowired
    private EventoServicio eventoServicio;
    @Autowired
    private LugarServicio lugarServicio;


    @Test
    @Sql({"classpath:ciudad.sql", "classpath:tipo.sql", "classpath:usuario.sql", "classpath:administrador.sql", "classpath:moderador.sql", "classpath:lugar.sql", "classpath:evento.sql"})
    public void crearEventoTest() {
        try {
            Lugar lugar = lugarServicio.obtenerLugar(1);

            String fecha = "05/08/2021";
            String hora = "19:00:00";

            Date horaEvento = new SimpleDateFormat("HH:mm:ss").parse(hora);
            Date fechaEvento = new SimpleDateFormat("YYYY/DD/MM").parse(fecha);
            Evento evento = new Evento("Gran inauguración", "Bienvenidos todos", fechaEvento, lugar, horaEvento);
            System.out.println("El evento es: " + evento.getNombre());
            Evento eventoGuardado = eventoServicio.crearEvento(evento);
            System.out.println("El evento guardado es: " + eventoGuardado);
            Assertions.assertNotNull(eventoGuardado);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql({"classpath:ciudad.sql", "classpath:tipo.sql", "classpath:usuario.sql", "classpath:administrador.sql", "classpath:moderador.sql", "classpath:lugar.sql", "classpath:evento.sql"})
    public void listarEventostest() {
        try {
            List<Evento> lista = eventoServicio.listarEventos();
            for (Evento evento : lista) {
                System.out.println(evento.getDescripcion());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql({"classpath:ciudad.sql", "classpath:tipo.sql", "classpath:usuario.sql", "classpath:administrador.sql", "classpath:moderador.sql", "classpath:lugar.sql", "classpath:evento.sql"})
    public void eliminarEventoTest() {
        try {

            Evento evento = eventoServicio.obtenerEvento(1);
            eventoServicio.eliminarEvento(evento);
            Evento eventoEliminado = eventoServicio.obtenerEvento(1);
            Assertions.assertNull(eventoEliminado);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql({"classpath:ciudad.sql", "classpath:tipo.sql", "classpath:usuario.sql", "classpath:administrador.sql", "classpath:moderador.sql", "classpath:lugar.sql", "classpath:evento.sql"})
    public void actualizarEventoTest() {
        try {

            String nuevaDescripcion = "Madrugada de cafe";
            String fechaCadena = "05/08/2021";
            Date fecha = new SimpleDateFormat("yyyy/dd/MM").parse(fechaCadena);
            System.out.println(fecha);

            Evento evento = eventoServicio.obtenerEvento(1);
            System.out.println("El evento antes del update" + evento);
            evento.setFecha(fecha);
            evento.setDescripcion(nuevaDescripcion);
            Evento eventoActualizado = eventoServicio.actualizarEvento(evento);
            System.out.println("El evento despues del update" + evento);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

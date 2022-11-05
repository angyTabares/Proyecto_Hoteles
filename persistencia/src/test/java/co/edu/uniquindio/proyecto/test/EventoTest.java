package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Evento;
import co.edu.uniquindio.proyecto.repositorios.EventoRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

/**
 * Esta clase permite testear la entidad evento
 * @author: Daniel Ceballos, Angy Tabares
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EventoTest {

    @Autowired
    private EventoRepo eventoRepo;

    /**
     * metodo para verificar si se ha agregado un evento correctamente
     * se añaden a la anotacion  sql los archivos necesarios para este test
     */
    @Test
    @Sql({"classpath:ciudad.sql","classpath:tipo.sql","classpath:usuario.sql","classpath:administrador.sql","classpath:moderador.sql","classpath:lugar.sql", "classpath:eventos.sql"})
    public void registrarEventoTest(){
        Evento eventoNuevo=eventoRepo.getOne(1);
        Evento eventoGuardado=eventoRepo.save(eventoNuevo);
        Assertions.assertNotNull(eventoGuardado);
    }

    /**
     * metodo para verificar si se ha eliminado un evento correctamente
     * se añaden a la anotacion  sql los archivos necesarios para este test
     */
    @Test
    @Sql({"classpath:ciudad.sql","classpath:tipo.sql","classpath:usuario.sql","classpath:administrador.sql","classpath:moderador.sql","classpath:lugar.sql", "classpath:eventos.sql"})
    public void eliminarEventoTest(){
        eventoRepo.delete(eventoRepo.getOne(2));
        Evento buscado=eventoRepo.findById(2).orElse(null);
        Assertions.assertNull(buscado);
    }

    /**
     * metodo para verificar si se ha actualizado un evento correctamente
     * se añaden a la anotacion  sql los archivos necesarios para este test
     */
    @Test
    @Sql({"classpath:ciudad.sql","classpath:tipo.sql","classpath:usuario.sql","classpath:administrador.sql","classpath:moderador.sql","classpath:lugar.sql", "classpath:eventos.sql"})
    public void actualizarEventoTest(){
        Evento registrado=eventoRepo.getOne(2);

        registrado.setNombre("tarde de cafe");

        eventoRepo.save(registrado);
        Evento buscado=eventoRepo.findById(2).orElse(null);
        Assertions.assertEquals("tarde de cafe",buscado.getNombre());
    }

    /**
     * metodo para listar los eventos
     * se añaden a la anotacion  sql los archivos necesarios para este test
     */
    @Test
    @Sql({"classpath:ciudad.sql","classpath:tipo.sql","classpath:usuario.sql","classpath:administrador.sql","classpath:moderador.sql","classpath:lugar.sql", "classpath:eventos.sql"})
    public void listarEventosTest(){
        List<Evento> lista = eventoRepo.findAll();
        System.out.println("Listado de eventos"+"\n"+lista);
    }
}

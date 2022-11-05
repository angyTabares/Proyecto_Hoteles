package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.repositorios.AdministradorRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

/**
 * Esta clase permite testear la entidad administrador
 * @author: Daniel Ceballos, Angy Tabares
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AdministradorTest {
    @Autowired
    private AdministradorRepo administradorRepo;

    /**
     * metodo para verificar si se ha registrado un administrador correctamente
     * se añaden a la anotacion  sql los archivos necesarios para este test
     */
    @Test
    @Sql({"classpath:ciudad.sql","classpath:usuario.sql","classpath:administrador.sql"})
    public void registrarAdministradorTest(){
        Administrador administradorNuevo=administradorRepo.getOne(1);
        Administrador administradorGuardado=administradorRepo.save(administradorNuevo);
        Assertions.assertNotNull(administradorGuardado);
    }

    /**
     * metodo para verificar si se ha eliminado un administrador correctamente
     * se añaden a la anotacion  sql los archivos necesarios para este test
     */
    @Test
    @Sql({"classpath:ciudad.sql","classpath:usuario.sql","classpath:administrador.sql"})
    public void eliminarAdministradorTest(){
        administradorRepo.delete(administradorRepo.getOne(2));
        Administrador buscado=administradorRepo.findById(2).orElse(null);
        Assertions.assertNull(buscado);
    }

    /**
     * metodo para verificar si se ha actualizado un administrador correctamente
     * se añaden a la anotacion  sql los archivos necesarios para este test
     */
    @Test
    @Sql({"classpath:ciudad.sql","classpath:usuario.sql","classpath:administrador.sql"})
    public void actualizarAdministradorTest(){
        Administrador registrado=administradorRepo.getOne(1);

        registrado.setNombre("Paola");

        administradorRepo.save(registrado);
        Administrador buscado=administradorRepo.findById(1).orElse(null);
        Assertions.assertEquals("Paola",buscado.getNombre());
    }

    /**
     * metodo para listar los administradores
     * se añaden a la anotacion  sql los archivos necesarios para este test
     */
    @Test
    @Sql({"classpath:ciudad.sql","classpath:usuario.sql","classpath:administrador.sql"})
    public void listarAdministradorTest(){
        List<Administrador> lista = administradorRepo.findAll();
        System.out.println("Listado de Administradores"+"\n"+lista);
    }





}

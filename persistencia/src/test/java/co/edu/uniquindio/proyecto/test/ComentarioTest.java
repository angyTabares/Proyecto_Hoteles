package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Moderador;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

/**
 * Esta clase permite testear la entidad comentario
 * @author: Daniel Ceballos, Angy Tabares
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ComentarioTest {
    @Autowired
    private ComentarioRepo comentarioRepo;

    /**
     * metodo para verificar si se ha agregado una comentario correctamente
     * se añaden a la anotacion  sql los archivos necesarios para este test
     */
    @Test
    @Sql({"classpath:ciudad.sql","classpath:tipo.sql","classpath:usuario.sql","classpath:administrador.sql","classpath:moderador.sql","classpath:lugar.sql","classpath:comentario.sql"})
    public void agregarComentarioTest(){
        Comentario comentarioNuevo=comentarioRepo.getOne(1);
        Comentario comentarioGuardado=comentarioRepo.save(comentarioNuevo);
        System.out.println(comentarioGuardado);
        Assertions.assertNotNull(comentarioGuardado);
    }

    /**
     * metodo para verificar si se ha eliminado un comentario correctamente
     * se añaden a la anotacion  sql los archivos necesarios para este test
     */
    @Test
    @Sql({"classpath:ciudad.sql","classpath:tipo.sql","classpath:usuario.sql","classpath:administrador.sql","classpath:moderador.sql","classpath:lugar.sql","classpath:comentario.sql"})
    public  void eliminarComentarioTest(){
        comentarioRepo.delete(comentarioRepo.getOne(2));
        Comentario buscado=comentarioRepo.findById(2).orElse(null);
        Assertions.assertNull(buscado);
    }

    /**
     * metodo para verificar si se ha actualizado un comentario correctamente
     * se añaden a la anotacion  sql los archivos necesarios para este test
     */
    @Test
    @Sql({"classpath:ciudad.sql","classpath:tipo.sql","classpath:usuario.sql","classpath:administrador.sql","classpath:moderador.sql","classpath:lugar.sql","classpath:comentario.sql"})
    public void actualizarComentarioTest(){
        Comentario registrado=comentarioRepo.getOne(1);
        registrado.setRespuesta("Gracias por comentar");
        comentarioRepo.save(registrado);
        System.out.println(registrado.getRespuesta());
        Comentario buscado=comentarioRepo.findById(1).orElse(null);
        Assertions.assertEquals("Gracias por comentar",buscado.getRespuesta());
    }

    /**
     * metodo para listar los comentarios
     * se añaden a la anotacion  sql los archivos necesarios para este test
     */
    @Test
    @Sql({"classpath:ciudad.sql","classpath:tipo.sql","classpath:usuario.sql","classpath:administrador.sql","classpath:moderador.sql","classpath:lugar.sql","classpath:comentario.sql"})
    public void listarComentarioTest(){
        List<Comentario> lista = comentarioRepo.findAll();
        System.out.println("Listado de comentarios"+"\n"+ lista);
    }

    @Test
    @Sql({"classpath:ciudad.sql","classpath:tipo.sql","classpath:usuario.sql","classpath:administrador.sql","classpath:moderador.sql","classpath:lugar.sql","classpath:comentario.sql"})
    public void obtenerUsuariosComentariosTest(){

        List<Usuario> lugaresComentarios=comentarioRepo.usuariosComentarios(1);

        for (Usuario l: lugaresComentarios) {
            System.out.println(l);
        }

    }

    @Test
    @Sql({"classpath:ciudad.sql","classpath:tipo.sql","classpath:usuario.sql","classpath:administrador.sql","classpath:moderador.sql","classpath:lugar.sql","classpath:comentario.sql"})
    public void cantidadComentariosDeUnLugarEspecificoTest(){

        List<Object[]> c=comentarioRepo.cantidadComentariosDeUnLugarEspecifico(1);

        for (Object[] l: c) {
            System.out.println(l[0]+" "+l[1]+" "+l[2]);
        }

    }
}

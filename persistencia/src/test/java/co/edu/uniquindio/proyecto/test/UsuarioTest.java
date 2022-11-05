package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

/**
 * Esta clase permite testear la entidad usuario
 * @author: Daniel Ceballos, Angy Tabares
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest {
    @Autowired
    private UsuarioRepo usuarioRepo;

    /**
     * metodo para verificar si se ha agregado un usuario correctamente
     * se añaden a la anotacion  sql los archivos necesarios para este test
     */
    @Test
    @Sql({"classpath:ciudad.sql","classpath:usuario.sql"})
    public void registrarUsuarioTest(){
        Usuario usuarioNuevo=usuarioRepo.getOne(1);
        Usuario usuarioGuardado=usuarioRepo.save(usuarioNuevo);
        Assertions.assertNotNull(usuarioGuardado);
    }

    /**
     * metodo para verificar si se ha eliminado un usuario correctamente
     * se añaden a la anotacion  sql los archivos necesarios para este test
     */
    @Test
    @Sql({"classpath:ciudad.sql","classpath:usuario.sql"})
    public void eliminarUsuarioTest(){
        usuarioRepo.delete(usuarioRepo.getOne(2));
        Usuario buscado=usuarioRepo.findById(2).orElse(null);
        Assertions.assertNull(buscado);
    }

    /**
     * metodo para verificar si se ha actualizado un usuario correctamente
     * se añaden a la anotacion  sql los archivos necesarios para este test
     */
    @Test
    @Sql({"classpath:ciudad.sql","classpath:usuario.sql"})
    public void actualizarUsuarioTest(){
        Usuario registrado=usuarioRepo.getOne(2);
        registrado.setNombre("Paola");
        usuarioRepo.save(registrado);
        Usuario buscado=usuarioRepo.findById(2).orElse(null);
        Assertions.assertEquals("Paola",buscado.getNombre());
    }

    /**
     * metodo para listar los usuarios
     * se añaden a la anotacion  sql los archivos necesarios para este test
     */
    @Test
    @Sql({"classpath:ciudad.sql","classpath:usuario.sql"})
    public void listarUsuarioTest(){
        List<Usuario> lista = usuarioRepo.findAll();

        System.out.println("Listado de usuarios"+"\n"+ lista);
    }

    @Test
    @Sql({"classpath:ciudad.sql","classpath:usuario.sql"})
    public void listarUsuarioDadoUnNombreTest(){
        List<Usuario> lista = usuarioRepo.findByNombre("Angy");

        System.out.println("Listado de usuarios"+"\n"+ lista);
    }

    @Test
    @Sql({"classpath:ciudad.sql","classpath:usuario.sql"})
    public void listarUsuarioPaginableTest(){

        List<Usuario> lista = usuarioRepo.obtenerUsuarios(PageRequest.of(0,2));

        System.out.println("Listado de usuarios"+"\n"+ lista);
    }

    @Test
    @Sql({"classpath:ciudad.sql","classpath:tipo.sql","classpath:administrador.sql","classpath:moderador.sql","classpath:usuario.sql","classpath:lugar.sql","classpath:favorito.sql"})
    public void obtenerLugaresFavoritosTest(){

        List<Lugar> lugares= usuarioRepo.obtenerLugaresFavoritos(2);

        for (Lugar l: lugares) {
            System.out.println(l);
        }

    }

    @Test
    @Sql({"classpath:ciudad.sql","classpath:tipo.sql","classpath:administrador.sql","classpath:moderador.sql","classpath:usuario.sql","classpath:lugar.sql","classpath:favorito.sql"})
    public void obtenerUsuariosYLugaresPublicadosTest(){

        List<Object[]> lugaresUsuario= usuarioRepo.obtenerLugaresPublicadosUsuarios();

        for (Object[] l: lugaresUsuario) {
            System.out.println(l[0]+""+l[1]);
        }

    }

    @Test
    @Sql({"classpath:ciudad.sql","classpath:usuario.sql"})
    public void listarUsuarioAlfabeticamenteTest(){

        List<Usuario> lista = usuarioRepo.obtenerUsuariosAlfabeticamente(Sort.by("nombre"));

        System.out.println("Listado de usuarios"+"\n"+ lista);
    }

}

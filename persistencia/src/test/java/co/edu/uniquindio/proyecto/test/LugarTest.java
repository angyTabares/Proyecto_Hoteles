package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.ComentariosLugarDTO;
import co.edu.uniquindio.proyecto.dto.LugaresPorUsuarioDTO;
import co.edu.uniquindio.proyecto.dto.NumeroLugaresPorCategoriaDTO;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.entidades.Moderador;
import co.edu.uniquindio.proyecto.repositorios.LugarRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Esta clase permite testear la entidad lugar
 *
 * @author: Daniel Ceballos, Angy Tabares
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LugarTest {
    @Autowired
    private LugarRepo lugarRepo;

    /**
     * metodo para verificar si se ha agregado un lugar correctamente
     * se añaden a la anotacion  sql los archivos necesarios para este test
     */
    @Test
    @Sql({"classpath:ciudad.sql", "classpath:tipo.sql", "classpath:usuario.sql", "classpath:administrador.sql", "classpath:moderador.sql", "classpath:lugar.sql"})
    public void registrarLugarTest() {
        Lugar lugarNuevo = lugarRepo.getOne(1);
        Lugar lugarGuardado = lugarRepo.save(lugarNuevo);
        Assertions.assertNotNull(lugarGuardado);
    }

    /**
     * metodo para verificar si se ha eliminado un lugar correctamente
     * se añaden a la anotacion  sql los archivos necesarios para este test
     */
    @Test
    @Sql({"classpath:ciudad.sql", "classpath:tipo.sql", "classpath:usuario.sql", "classpath:administrador.sql", "classpath:moderador.sql", "classpath:lugar.sql"})
    public void eliminarLugarTest() {
        lugarRepo.delete(lugarRepo.getOne(2));
        Lugar buscado = lugarRepo.findById(2).orElse(null);
        Assertions.assertNull(buscado);
    }

    /**
     * metodo para verificar si se ha actualizado un lugar correctamente
     * se añaden a la anotacion  sql los archivos necesarios para este test
     */
    @Test
    @Sql({"classpath:ciudad.sql", "classpath:tipo.sql", "classpath:usuario.sql", "classpath:administrador.sql", "classpath:moderador.sql", "classpath:lugar.sql"})
    public void actualizarLugarTest() {
        Lugar registrado = lugarRepo.getOne(2);

        registrado.setNombre("Bar React");

        lugarRepo.save(registrado);
        Lugar buscado = lugarRepo.findById(2).orElse(null);
        Assertions.assertEquals("Bar React", buscado.getNombre());
    }

    /**
     * metodo para listar los lugares
     * se añaden a la anotacion  sql los archivos necesarios para este test
     */
    @Test
    @Sql({"classpath:ciudad.sql", "classpath:tipo.sql", "classpath:usuario.sql", "classpath:administrador.sql", "classpath:moderador.sql", "classpath:lugar.sql"})
    public void listarLugarTest() {
        List<Lugar> lista = lugarRepo.findAll();

        System.out.println("Listado de lugares" + "\n" + lista);
    }

    @Test
    @Sql({"classpath:ciudad.sql", "classpath:tipo.sql", "classpath:usuario.sql", "classpath:administrador.sql", "classpath:moderador.sql", "classpath:lugar.sql"})
    public void obtenerTipoLugarTest() {

        String nombreTipoLugar = lugarRepo.obtenerTipoLugar(1);
        System.out.println(nombreTipoLugar);
    }

    @Test
    @Sql({"classpath:ciudad.sql", "classpath:tipo.sql", "classpath:usuario.sql", "classpath:administrador.sql", "classpath:moderador.sql", "classpath:lugar.sql"})
    public void obtenerInfoTest() {

        List<Object[]> infoLugar = lugarRepo.obtenerInfo(1);

        for (int i = 0; i < infoLugar.get(0).length; i++) {

            System.out.println(infoLugar.get(0)[i]);
        }

    }

    @Test
    @Sql({"classpath:ciudad.sql", "classpath:tipo.sql", "classpath:usuario.sql", "classpath:administrador.sql", "classpath:moderador.sql", "classpath:lugar.sql", "classpath:comentario.sql"})
    public void obtenerComentariosLugaresTest() {

        List<ComentariosLugarDTO> lugaresComentarios = lugarRepo.obtenerComentariosLugares();
        for (ComentariosLugarDTO l : lugaresComentarios) {
            System.out.println(l.getLugar().getNombre() + " " + l.getComentario().getComentario());
        }

    }

    @Test
    @Sql({"classpath:ciudad.sql", "classpath:tipo.sql", "classpath:usuario.sql", "classpath:administrador.sql", "classpath:moderador.sql", "classpath:lugar.sql", "classpath:comentario.sql"})
    public void obtenerCantidadComentariosLugarTest() {

        int cantidad = lugarRepo.obtenerCantidadComentarios(1);

        System.out.println(cantidad);
    }

    @Test
    @Sql({"classpath:ciudad.sql", "classpath:tipo.sql", "classpath:usuario.sql", "classpath:administrador.sql", "classpath:moderador.sql", "classpath:lugar.sql", "classpath:comentario.sql"})
    public void obtenerCantidadLugaresPorCategoriaTest() {

        List<NumeroLugaresPorCategoriaDTO> cantLugares = lugarRepo.obtenerCantidadLugaresPorCategoria();

        for (NumeroLugaresPorCategoriaDTO l : cantLugares) {
            System.out.println(l);
        }
    }

    @Test
    @Sql({"classpath:ciudad.sql", "classpath:tipo.sql", "classpath:usuario.sql", "classpath:administrador.sql", "classpath:moderador.sql", "classpath:lugar.sql", "classpath:comentario.sql", "classpath:horario.sql"})
    public void obtenerLugaresSinHorariosTest() {

        List<Lugar> lugares = lugarRepo.obtenerLugaresSinHorarios();

        for (Lugar l : lugares) {
            System.out.println(l);
        }
    }

  /*  @Test
    @Sql({"classpath:ciudad.sql","classpath:tipo.sql","classpath:usuario.sql","classpath:administrador.sql","classpath:moderador.sql","classpath:lugar.sql","classpath:comentario.sql"})
    public void obtenerModeradorConMasLugaresAprobadosTest(){
        List<Object[]> lista = lugarRepo.obtenerModeradorConMasAprobados();

        for (Object[] l: lista) {
            System.out.println(l[0]+""+l[1]);
        }
    }*/

    @Test
    @Sql({"classpath:ciudad.sql", "classpath:tipo.sql", "classpath:usuario.sql", "classpath:administrador.sql", "classpath:moderador.sql", "classpath:lugar.sql", "classpath:comentario.sql"})
    public void obtenerLugarCalificacionMasAltaPorCiudadTest() {
        List<Object[]> lista = lugarRepo.obtenerLugarCalificacionMasAltaPorCiudad(1);

        for (Object[] l : lista) {
            System.out.println(l[0] + "" + l[1]);
        }
    }

    @Test
    @Sql({"classpath:ciudad.sql", "classpath:tipo.sql", "classpath:usuario.sql", "classpath:administrador.sql", "classpath:moderador.sql", "classpath:lugar.sql", "classpath:comentario.sql"})
    public void obtenerCalificacionPromedioTest() {
        float promedio = lugarRepo.obtenerCalificacionPromedio(1);

        System.out.println(promedio);
    }

    @Test
    @Sql({"classpath:ciudad.sql", "classpath:tipo.sql", "classpath:usuario.sql", "classpath:administrador.sql", "classpath:moderador.sql", "classpath:lugar.sql", "classpath:comentario.sql"})
    public void obtenerCantidadLugaresNoAprobadosPorCiudadTest() {

        List<Object[]> lista = lugarRepo.obtenerCantidadLugaresNoAprobadosPorCiudad();
        for (Object[] l : lista) {
            System.out.println(l[0] + " " + l[1]);
        }

    }

//    @Test
//    @Sql({"classpath:ciudad.sql", "classpath:tipo.sql", "classpath:usuario.sql", "classpath:administrador.sql", "classpath:moderador.sql", "classpath:horario.sql", "classpath:lugar.sql", "classpath:comentario.sql"})
//    public void obtenerCantidadLugaresAbiertosPorCategoriaTest() {
//
//        String horaNueva = "08:00:00";
//
//        try {
//            Date hora = new SimpleDateFormat("HH:mm:ss").parse(horaNueva);
//
//            List<Object[]> lista = lugarRepo.obtenerCantidadLugaresAbiertosPorCategoria("martes", hora);
//
//            for (Object[] l : lista) {
//                System.out.println(l[0] + " " + l[1]);
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//    }
 /*   @Test
    @Sql({"classpath:ciudad.sql","classpath:tipo.sql","classpath:usuario.sql","classpath:administrador.sql","classpath:moderador.sql","classpath:horario.sql","classpath:lugar.sql","classpath:comentario.sql"})
    public void obtenerLugaresCreadosPorUsuarioTest(){
        List<Object> listado=lugarRepo.obtenerLugaresCreadosPorUsuario("daniel@gmail.com");
        for(Object l:listado){
            System.out.println(l);
            System.out.println();
        }
    }
    @Test
    @Sql({"classpath:ciudad.sql","classpath:tipo.sql","classpath:usuario.sql","classpath:administrador.sql","classpath:moderador.sql","classpath:horario.sql","classpath:lugar.sql","classpath:comentario.sql"})
    public void  obtenerListaLugaresEinformacionUsuarioCreador(){
        List<LugaresPorUsuarioDTO> listado=lugarRepo. obtenerListaLugaresEinformacionUsuarioCreador();
        for(LugaresPorUsuarioDTO u: listado){
                System.out.println(u);
                System.out.println();

        }
    }
    @Test
    @Sql({"classpath:ciudad.sql","classpath:tipo.sql","classpath:usuario.sql","classpath:administrador.sql","classpath:moderador.sql","classpath:horario.sql","classpath:lugar.sql","classpath:comentario.sql"})
    public void obtenerComentariosPorLugarTest(){
        List<Comentario> listado=lugarRepo.obtenerComentariosPorLugar(1);
        for(Object l:listado){
            System.out.println(l);
        }
    }

    @Test
    @Sql({"classpath:ciudad.sql","classpath:tipo.sql","classpath:usuario.sql","classpath:administrador.sql","classpath:moderador.sql","classpath:horario.sql","classpath:lugar.sql","classpath:comentario.sql"})
    public void obtenerComentarioSinRespuestaPorUsuarioTest(){
        List<Object> listado=lugarRepo.obtenerComentarioSinRespuestaPorUsuario(1);
        for(Object l:listado){
            System.out.println(l);
        }
    }
    @Test
    @Sql({"classpath:ciudad.sql","classpath:tipo.sql","classpath:usuario.sql","classpath:administrador.sql","classpath:moderador.sql","classpath:horario.sql","classpath:lugar.sql","classpath:comentario.sql"})
    public void obtenerCantComentariosPorCalificacionLugarTest(){
        List<Object[]> listado=lugarRepo.obtenerCantComentariosPorCalificacionLugar(1);
        for(Object[] l:listado){

            for(Object c:l){
                System.out.println(c);
            }
        }
    }*/

}

package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.LugarCalificacionDTO;
import co.edu.uniquindio.proyecto.dto.NumeroLugaresPorCategoriaDTO;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Horario;
import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.repositorios.HorarioRepo;
import co.edu.uniquindio.proyecto.repositorios.LugarRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service

public class LugarServicioImpl implements LugarServicio {

    private final LugarRepo lugarRepo;
    private final UsuarioRepo usuarioRepo;
    private final HorarioRepo horarioRepo;
    private final ComentarioRepo comentarioRepo;

    public LugarServicioImpl(LugarRepo lugarRepo, UsuarioRepo usuarioRepo, HorarioRepo horarioRepo, ComentarioRepo comentarioRepo) {

        this.lugarRepo = lugarRepo;
        this.usuarioRepo = usuarioRepo;
        this.horarioRepo = horarioRepo;
        this.comentarioRepo = comentarioRepo;
    }

    /**
     * Metodo que permite crear un lugar en la bd
     *
     * @param l
     * @return lugar guardado
     * @throws Exception
     */

    @Override
    public Lugar crearLugar(Lugar l) throws Exception {

        if (!estaLugar(l.getCodigo())) {
            throw new Exception("El lugar ya existe");
        }
        if (!existeNombreLugar(l.getNombre())) {
            throw new Exception("El nombre del lugar ya está en uso");
        }
        if (l.getDescripcion().length() > 1000) {
            throw new Exception("La descripcion debe ser menor de 1000 caracteres");
        }
        if (l.getNombre().length() > 200) {
            throw new Exception("El nombre debe ser menor de 200 caracteres");
        }

        l.setEstado(false);
        l.setFecha_creacion(new Date());
        //if(l.getImagenes().isEmpty())
        return lugarRepo.save(l);
    }

    /**
     * Metodo que permite eliminar un lugar de la bd
     *
     * @param l
     * @throws Exception
     */

    @Override
    public void eliminarLugar(Lugar l) throws Exception {
        if (estaLugar(l.getCodigo())) {
            throw new Exception("El lugar no existe");
        }
        lugarRepo.delete(l);
    }

    @Override
    public void eliminarLugarPorId(int id) throws Exception {
        Optional<Lugar> lugar = lugarRepo.findByCodigo(id);
        if (lugar.isEmpty()) {
            throw new Exception("El lugar no existe");
        }
//        horarioRepo.eliminarHorariosLugar(id);
//        comentarioRepo.eliminarComentariosLugar(id);
        lugarRepo.delete(lugar.get());
    }

    /**
     * Metodo que permite actualizar la info de un lugar de la bd
     *
     * @param l
     * @return
     * @throws Exception
     */

    @Override
    public Lugar actualizarLugar(Lugar l) throws Exception {

        if (estaLugar(l.getCodigo())) {
            throw new Exception("El lugar no existe");
        }

        if (l.getDescripcion().length() > 1000) {
            throw new Exception("La descripcion debe ser menor de 1000 caracteres");
        }
        if (l.getNombre().length() > 200) {
            throw new Exception("El nombre debe ser menor de 200 caracteres");
        }

        return lugarRepo.save(l);

    }

    /**
     * Metodo que permite obtener un lugar
     *
     * @param codigo
     * @return lugar
     */
    @Override
    public Lugar obtenerLugar(int codigo) throws Exception {
        Optional<Lugar> lugar = obtenerLugarPorId(codigo);
        if (lugar.isEmpty()) {
            throw new Exception("No existe un lugar con el id dado");
        }

        return lugar.get();
    }

    /**
     * Metodo que permite listar los lugares de la bd
     *
     * @return lugares
     * @throws Exception
     */

    @Override
    public List<Lugar> listarLugares() throws Exception {
        return lugarRepo.findAll();
    }

    @Override
    public List<Lugar> buscarLugares(String nombre) throws Exception {
        List<Lugar> lista = lugarRepo.buscarLugares(nombre);
        if (lista.isEmpty()) {
            throw new Exception("No hay Lugares Aun");
        }
        return lista;
    }

    @Override
    public Lugar buscarLugarPorNombre(String nombre) throws Exception {
        Optional<Lugar> lugar = lugarRepo.findByNombre(nombre);
        if (lugar.isEmpty()) {
            throw new Exception("El lugar con el nombre dado no existe");
        }

        return lugar.get();
    }


    @Override
    public List<Comentario> listarComentariosDeUnLugar(int codigoLugar) {
        return lugarRepo.obtenerComentariosPorLugar(codigoLugar);
    }

    @Override
    public List<Horario> listarHorariosDeUnLugar(int codigoLugar) {
        return lugarRepo.obtenerHorariosPorLugar(codigoLugar);
    }

    @Override
    public Integer obtenerCalificacionPromedio(int lugarId) throws Exception {
        if (estaLugar(lugarId)) {
            throw new Exception("El id no existe");
        }
        return lugarRepo.obtenerCalificacionPromedio(lugarId);
    }

    @Override
    public void marcarFavorito(Lugar l, Usuario u) {

        if (u.getLugaresFavoritos().contains(l)) {
            u.getLugaresFavoritos().remove(l);
        } else {
            u.getLugaresFavoritos().add(l);
        }

        usuarioRepo.save(u);

    }

    @Override
    public boolean esFavorito(Lugar l, Usuario u) {

        if (u.getLugaresFavoritos().contains(l)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<NumeroLugaresPorCategoriaDTO> cantLugaresPorCategorias() {


        return lugarRepo.obtenerCantidadLugaresPorCategoria();
    }

    @Override
    public List<LugarCalificacionDTO> calificacionesLugarCiudad(int codigoCiudad) {
        System.out.println("entre");
        List<LugarCalificacionDTO> listaLugarCalificacionDTOS = new ArrayList<>();
        List<Object[]> listObjetos = lugarRepo.obtenerLugaresCalificacionPorCiudad(codigoCiudad);
        System.out.println("sigo adentro");
        for (Object[] o : listObjetos) {
            System.out.println("en el for");
            LugarCalificacionDTO l = new LugarCalificacionDTO((Lugar) o[0], (Double) o[1]);

            listaLugarCalificacionDTOS.add(l);
        }
        System.out.println("sali del for");
        return listaLugarCalificacionDTOS;
    }

    @Override
    public String tipoLugar(int codigo) throws Exception {


        Optional<Lugar> lugar = lugarRepo.findByCodigo(codigo);
        if (lugar.isEmpty()) {
            throw new Exception("El lugar con el codigo dado no existe");
        }

        return lugarRepo.obtenerTipoLugar(codigo);
    }

    @Override
    public List<Lugar> obtenerLugaresUsuario(String email) throws Exception {
        return lugarRepo.obtenerLugaresCreadosPorUsuario(email);
    }

    @Override
    public List<Lugar> obtenerLugaresPendientes() throws Exception {
        return lugarRepo.obtenerLugaresPendientes();
    }

    @Override
    public List<Lugar> obtenerLugaresAprobadorModerador(String email) throws Exception {
        return lugarRepo.obtenerLugaresAprobadosModerador(email);
    }

    @Override
    public List<Lugar> obtenerLugaresDenegadosModerador(String email) throws Exception {
        return lugarRepo.obtenerLugaresDenegadosModerador(email);
    }

    @Override
    public List<Lugar> obtenerLugaresAprobados() throws Exception {
        return lugarRepo.obtenerLugaresAprobados();
    }

    @Override
    public List<Lugar> obtenerLugaresAprobadosPorNombreOTipo(String nombre) throws Exception {
        List<Lugar> lista = lugarRepo.obtenerLugaresAprobadosPorNombreOTipo(nombre);
        if (lista.isEmpty()) {
            throw new Exception("No hay lugares aun");
        }
        return lista;
    }


    /**
     * Metodo que permite validar si un lugar se encuentra en la bd
     *
     * @param id
     * @return true si el lugar no esta
     */
    public boolean estaLugar(int id) {
        Optional<Lugar> lugar = lugarRepo.findByCodigo(id);
        return lugar.isEmpty();
    }

    /**
     * Metodo que permite validar si ya existe un nombre de un lugar en la bd
     *
     * @param nombre
     * @return true si el lugar no esta
     */

    public boolean existeNombreLugar(String nombre) {
        Optional<Lugar> lugar = lugarRepo.findByNombre(nombre);
        return lugar.isEmpty();
    }

    /**
     * Metodo que permite obtener un lugar dado su id
     *
     * @param codigo
     * @return lugar
     */

    public Optional<Lugar> obtenerLugarPorId(int codigo) {
        return lugarRepo.findByCodigo(codigo);
    }


}

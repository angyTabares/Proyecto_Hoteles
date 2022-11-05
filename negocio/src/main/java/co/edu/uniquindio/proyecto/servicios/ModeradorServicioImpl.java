package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Moderador;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ModeradorRepo;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ModeradorServicioImpl implements ModeradorServicio {

    private final ModeradorRepo moderadorRepo;

    public ModeradorServicioImpl(ModeradorRepo moderadorRepo) {
        this.moderadorRepo = moderadorRepo;
    }

    /**
     * Metodo que permite guardar un moderador en la bd
     *
     * @param m
     * @return moderador guardado
     * @throws Exception
     */

    @Override
    public Moderador registrarModerador(Moderador m) throws Exception {

        if (!nicknameEstaDisponible(m.getNickname())) {
            throw new Exception("El nickname no esta disponible");
        }
        if (!emailEstaDisponible(m.getEmail())) {
            throw new Exception("El email no esta disponible");
        }
        if (m.getNickname().length() > 255) {
            throw new Exception("El nickname debe ser menor a 255 caracteres");
        }
        if (m.getEmail().length() > 255) {
            throw new Exception("El email debe ser menor a 255 caracteres");
        }

        if (m.getNombre().length() > 255) {
            throw new Exception("El nombre debe ser menor a 255 caracteres");
        }
        return moderadorRepo.save(m);
    }

    /**
     * Metodo que permite eliminar un moderador de la bd
     *
     * @param m
     * @throws Exception
     */
    @Override
    public void eliminarModerador(Moderador m) throws Exception {
        if (estaModerador(m.getId())) {
            throw new Exception("El moderador no existe");
        }
        moderadorRepo.delete(m);
    }

    /**
     * Metodo que permite actualizar la info de un moderador de la bd
     *
     * @param m
     * @return moderador
     * @throws Exception
     */

    @Override
    public Moderador actualizarModerador(Moderador m) throws Exception {
        if (estaModerador(m.getId())) {
            throw new Exception("El moderador no existe");
        }
        if (m.getNickname().length() > 255) {
            throw new Exception("El nickname debe ser menor a 255 caracteres");
        }
        if (m.getEmail().length() > 255) {
            throw new Exception("El email debe ser menor a 255 caracteres");
        }
        if (m.getNombre().length() > 255) {
            throw new Exception("El nombre debe ser menor a 255 caracteres");
        }
        return moderadorRepo.save(m);
    }

    /**
     * Metodo que permite obtener un moderador
     *
     * @param id
     * @return moderador
     */

    @Override
    public Moderador obtenerModerador(int id) throws Exception {
        Optional<Moderador> moderador = moderadorRepo.findById(id);
        if (moderador.isEmpty()) {
            throw new Exception("No existe un moderador con el id dado");
        }

        return moderador.get();
    }

    /**
     * Metodo que permite listar los moderadoresde la bd
     *
     * @return moderadores
     * @throws Exception
     */

    @Override
    public List<Moderador> listarModeradores() throws Exception {
        List<Moderador> lista = moderadorRepo.findAll();
        for (Moderador m : lista) {
            System.out.println(m.getNombre());
        }
        return lista;
    }

    /**
     * Metodo que permite validar si un nickname esta disponible
     *
     * @param nickname
     * @return true si esta disponible
     */

    public boolean nicknameEstaDisponible(String nickname) {
        Optional<Moderador> moderadorNick = moderadorRepo.findByNickname(nickname);
        return moderadorNick.isEmpty();
    }

    /**
     * Metodo que permite validar si un email esta disponible
     *
     * @param email
     * @return true si esta disponible
     */

    public boolean emailEstaDisponible(String email) {
        Optional<Moderador> moderadorEmail = moderadorRepo.findByEmail(email);
        return moderadorEmail.isEmpty();
    }

    /**
     * Metodo que permite validar si un moderador se encuentra en la bd
     *
     * @param id
     * @return true si el moderador no esta
     */

    public boolean estaModerador(int id) {
        Optional<Moderador> moderador = moderadorRepo.findById(id);
        return moderador.isEmpty();
    }
}

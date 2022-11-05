package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Moderador;
import co.edu.uniquindio.proyecto.repositorios.AdministradorRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdministradorServicioImpl implements AdministradorServicio {

    private final AdministradorRepo administradorRepo;
    private ModeradorServicio moderadorServicio;
    private TipoServicio tipoServicio;

    public AdministradorServicioImpl(AdministradorRepo administradorRepo) {
        this.administradorRepo = administradorRepo;
    }

    /**
     * Metodo que permite guardar un administrador en la bd
     *
     * @param admin
     * @return admin guardado
     * @throws Exception
     */

    @Override
    public Administrador registrarAdmin(Administrador admin) throws Exception {
        if (!nicknameEstaDisponible(admin.getNickname())) {
            throw new Exception("El nickname no esta disponible");
        }
        if (!emailEstaDisponible(admin.getEmail())) {
            throw new Exception("El email no esta disponible");
        }
        if (admin.getNickname().length() > 255) {
            throw new Exception("El nickname debe ser menor a 255 caracteres");
        }
        if (admin.getEmail().length() > 255) {
            throw new Exception("El email debe ser menor a 255 caracteres");
        }
        if (admin.getNombre().length() > 255) {
            throw new Exception("El nombre debe ser menor a 255 caracteres");
        }
        if (admin.getPassword().length() > 255) {
            throw new Exception("La contraseña debe ser menor a 255 caracteres");
        }

        return administradorRepo.save(admin);
    }

    /**
     * Metodo que permite eliminar un admin de la bd
     *
     * @param admin
     * @throws Exception
     */

    @Override
    public void eliminarAdmin(Administrador admin) throws Exception {

        if (estaAdmin(admin.getId())) {
            throw new Exception("El administrador no existe");
        }
        administradorRepo.delete(admin);
    }

    /**
     * Metodo que permite actualizar la info de un administrador de la bd
     *
     * @param admin
     * @return
     * @throws Exception
     */

    @Override
    public Administrador actualizarAdmin(Administrador admin) throws Exception {

        if (estaAdmin(admin.getId())) {
            throw new Exception("el administrador no existe");
        }
        if (admin.getNombre().length() > 255) {
            throw new Exception("El nombre debe ser menor a 255 caracteres");
        }
        if (admin.getPassword().length() > 255) {
            throw new Exception("La contraseña debe ser menor a 255 caracteres");
        }
        return administradorRepo.save(admin);
    }

    /**
     * Metodo que permite obtener un administrador
     *
     * @param id
     * @return admin
     */

    @Override
    public Administrador obtenerAdmin(int id) throws Exception {
        Optional<Administrador> admin = administradorRepo.findById(id);
        if (admin.isEmpty()) {
            throw new Exception("No existe un admin con el id dado");
        }

        return admin.get();
    }

    /**
     * Metodo que permite listar los admininistradores de la bd
     *
     * @return administradores
     * @throws Exception
     */

    @Override
    public List<Administrador> listarAdmins() throws Exception {
        return administradorRepo.findAll();
    }

    @Override
    public boolean existenAdmins() throws Exception {
        if (administradorRepo.findAll().isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * Metodo que permite validar si un admin se encuentra en la bd
     *
     * @param id
     * @return true si el admin no esta
     */

    public boolean estaAdmin(int id) {
        Optional<Administrador> admin = administradorRepo.findById(id);
        return admin.isEmpty();
    }

    /**
     * Metodo que permite validar si un nickname esta disponible
     *
     * @param nickname
     * @return true si esta disponible
     */

    public boolean nicknameEstaDisponible(String nickname) {
        Optional<Administrador> administradorNick = administradorRepo.findByNickname(nickname);
        return administradorNick.isEmpty();
    }

    /**
     * Metodo que permite validar si un email esta disponible
     *
     * @param email
     * @return true si esta disponible
     */

    public boolean emailEstaDisponible(String email) {
        Optional<Administrador> administradorEmail = administradorRepo.findByEmail(email);
        return administradorEmail.isEmpty();
    }


}

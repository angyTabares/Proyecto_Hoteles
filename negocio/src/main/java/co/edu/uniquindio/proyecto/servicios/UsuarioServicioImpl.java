package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service

public class UsuarioServicioImpl implements  UsuarioServicio{

    private final UsuarioRepo usuarioRepo;

    public UsuarioServicioImpl(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    public boolean longitudValida(String c){
        String cadena=c.trim().replace(" ","");

        return (cadena.length()>255 || cadena.length()<=0)?true:false;

    }

    /**
     * Metodo que permite validar si un nickname esta disponible
     * @param nickname
     * @return true si esta disponible
     */
    public boolean nicknameEstaDisponible(String nickname){
        Optional<Usuario> usuarioNick= usuarioRepo.findByNickname(nickname);
        return usuarioNick.isEmpty();
    }

    /**
     * Metodo que permite validar si un usuario se encuentra en la bd
     * @param id
     * @return true si el usuario no esta
     */
    public boolean estaUsuario(int id){
        Optional<Usuario> usuario=usuarioRepo.findById(id);
        return usuario.isEmpty();
    }

    /**
     * Metodo que permite validar si un email esta disponible
     * @param email
     * @return true si esta disponible
     */
    public boolean emailEstaDisponible(String email){
        Optional<Usuario> usuarioEmail= usuarioRepo.findByEmail(email);
        return usuarioEmail.isEmpty();
    }

    public Optional<Usuario> obtenerUsuarioPorId(int id){
        return usuarioRepo.findById(id);
    }

    /**
     * Metodo que permite guardar un usuario en la bd
     * @param u
     * @return usuario guardado
     * @throws Exception
     */
    @Override
    public Usuario registrarUsuario(Usuario u) throws Exception {
       if(!nicknameEstaDisponible(u.getNickname())){
           throw new Exception("El nickname no esta disponible");
       }
       if(!emailEstaDisponible(u.getEmail())){
           throw new Exception("El email no esta disponible" );
       }
      if(longitudValida(u.getNombre())){
          throw new Exception("El nombre debe contener al menos un caracter y debe ser menor a 255 caracteres");
      }
      if(longitudValida(u.getEmail())){
          throw new Exception("El email debe contener al menos un caracter y debe ser menor a 255 caracteres");
      }
      if(longitudValida(u.getNickname())){
          throw new Exception("El nickname debe contener al menos un caracter y debe ser menor a 255 caracteres");
      }

       return  usuarioRepo.save(u);
    }

    /**
     * Metodo que permite eliminar un usuario de la bd
     * @param u
     * @throws Exception
     */
    @Override
    public void eliminarUsuario(Usuario u) throws Exception {
        if(estaUsuario(u.getId())){
           throw new Exception("El usuario no se encuentra") ;
        }
        usuarioRepo.delete(u);
    }


    /**
     * Metodo que permite actualizar la infor de un usuario de la bd
     * @param email
     * @return
     * @throws Exception
     */
    @Override
    public Usuario actualizarUsuario(String email) throws Exception {
        Optional <Usuario> usuario = usuarioRepo.findByEmail(email);

        if(longitudValida(usuario.get().getNombre())){
            throw new Exception("El nombre debe ser menor a 255 caracteres");
        }
        if(longitudValida(usuario.get().getEmail())){
            throw new Exception("El email debe ser menor a 255 caracteres");
        }
        if(longitudValida(usuario.get().getNickname())){
            throw new Exception("El nickname debe ser menor a 255 caracteres");
        }

        return  usuarioRepo.save(usuario.get());
    }

    @Override
    public Usuario actualizarUsuarioCompleto(Usuario usuario) throws Exception {

        if(longitudValida(usuario.getNombre())){
            throw new Exception("El nombre debe ser menor a 255 caracteres");
        }
        if(longitudValida(usuario.getEmail())){
            throw new Exception("El email debe ser menor a 255 caracteres");
        }
        if(longitudValida(usuario.getNickname())){
            throw new Exception("El nickname debe ser menor a 255 caracteres");
        }

        return  usuarioRepo.save(usuario);
    }

    @Override
    public Usuario obtenerUsuario(Integer id) throws Exception {
        Optional<Usuario> usuario =obtenerUsuarioPorId(id);
        if(usuario.isEmpty()){
            throw new Exception("No existe un usuario con el id dado");
        }

        return usuario.get();
    }

    /**
     * Metodo que permite listar los usuarios de la bd
     * @return
     * @throws Exception
     */
    @Override
    public List<Usuario> listarUsuario() throws Exception {
        return usuarioRepo.findAll();
    }

}

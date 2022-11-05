package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.LugarServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class PerfilUsuarioBean implements Serializable {

    @Value(value="#{seguridadBean.persona}")
    private Persona personaLogin;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private CiudadServicio ciudadServicio;

    @Autowired
    private LugarServicio lugarServicio;

    @Getter @Setter
    private String nombre;

    @Getter @Setter
    private String password;

    @Getter @Setter
    private String nickname;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private Integer calificacionPromedio;

    @Getter @Setter
    private List<Lugar> lugares;

    @Getter @Setter
    private List<Comentario> comentarios;

    @PostConstruct
    public void inicializar() {

        if(personaLogin!=null)
        {
            try {

                this.nombre = personaLogin.getNombre();
                this.nickname= personaLogin.getNickname();
                this.password= personaLogin.getPassword();
                this.email= personaLogin.getEmail();
                this.lugares= lugarServicio.obtenerLugaresUsuario(this.email);

             //   this.comentarios= lugarServicio.listarComentariosDeUnLugar();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public boolean tieneLugares()
    {
        if(this.lugares.isEmpty())
        {
            return false;
        }
        return true;
    }

    public void editarUsuario()
    {
        personaLogin.setEmail(this.email);
        personaLogin.setPassword(this.password);
        personaLogin.setNickname(this.nickname);
        personaLogin.setNombre(this.nombre);

        try {
            Usuario usuarioActualizado = usuarioServicio.actualizarUsuarioCompleto((Usuario) personaLogin);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Moderador;
import co.edu.uniquindio.proyecto.entidades.Persona;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.PersonaServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Scope("session")
public class SeguridadBean implements Serializable {

    @Getter
    @Setter
    private Persona persona;

    @Getter
    @Setter
    private boolean autenticado;

    @Getter
    @Setter
    @NotBlank
    private String email, password;

    @Getter
    @Setter
    @NotBlank
    private String emailRecuperacion, nickRecuperacion;

    @Getter
    @Setter
    private Persona personaRecuperacion;


    private final PersonaServicio personaServicio;
    private Email emailSender;

    @Getter
    @Setter
    private String rol;

    public SeguridadBean(PersonaServicio personaServicio) {
        this.personaServicio = personaServicio;
    }


    public String iniciarSesion() {
        if (email != null && password != null) {
            try {
                persona = personaServicio.login(email, password);
                autenticado = true;
                if (persona instanceof Usuario) {
                    rol = "usuario";
                    return "/index?faces-redirect=true";
                } else if (persona instanceof Administrador) {
                    rol = "admin";
                    return "/administrador/administrador?faces-redirect=true";
                } else {
                    rol = "moderador";
                    return "/index?faces-redirect=true";
                }


            } catch (Exception e) {
                FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("login-bean", m);
            }
        }
        FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Ingrese los datos");
        FacesContext.getCurrentInstance().addMessage("login-bean", m);
        return null;
    }

    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }

    public void recuperarPassword() {
        System.out.println("recuperando");
        try {
            personaRecuperacion = personaServicio.recuperarPassword(this.emailRecuperacion, this.nickRecuperacion);
            String usuario = personaRecuperacion.getNombre();
            String subject = "Unilocal : Recuperar Contraseña";
            String to = this.emailRecuperacion;
            String from = "unilocal2021@gmail.com";
            String password = Email.randomPassword();
            PrimeFaces.current().executeScript("PF('recuperar').hide()");
            ExecutorService executor = Executors.newFixedThreadPool(10);
            executor.execute(new Runnable() {
                public void run() {

                    Email.sendEmailPassword(usuario, subject, to, from, password);

                }
            });
            executor.shutdown();

            personaRecuperacion.setPassword(password);
            personaServicio.actualizarDatos(personaRecuperacion);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}

package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.ComentarioServicio;
import co.edu.uniquindio.proyecto.servicios.HorarioServicio;
import co.edu.uniquindio.proyecto.servicios.LugarServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@ViewScoped
public class ComentariosBean implements Serializable {

    @Value("#{param['lugar']}")
    private String idLugar;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

    @Autowired
    private LugarServicio lugarServicio;

    @Autowired
    private ComentarioServicio comentarioServicio;

    @Autowired
    private HorarioServicio horarioServicio;

    @Getter
    @Setter
    private Lugar lugar;

    @Getter
    @Setter
    private List<Horario> horarios;

    @Getter
    @Setter
    private Horario horario;

    private List<String> images;

    @Getter
    @Setter
    private List<Comentario> comentarios;

    @Getter
    @Setter
    private Comentario comentario;

    @Getter
    @Setter
    private String respuesta;

    @Getter
    @Setter
    private LocalTime horaApertura;

    @Getter
    @Setter
    private LocalTime horaCierre;

    @Getter
    @Setter
    private String dia;

    @Getter
    @Setter
    private List<String> telefonos;

    @Getter
    @Setter
    private String telefono;

    @Getter
    @Setter
    private Comentario comentarioEmail;

    @PostConstruct
    public void inicializar() {
        // this.icono = "pi pi-star-o";
        try {
            images = new ArrayList<String>();

            images.add("https://www.eltiempo.com/files/image_640_428/uploads/2019/03/30/5ca02668e04df.jpeg");
            images.add("https://www.revistalabarra.com/wp-content/uploads/2019/02/Potada-nota-enano-1500x800.jpg");
            images.add("https://www.entornoturistico.com/wp-content/uploads/2016/06/clientes-en-un-bar-660x330.jpg");

            int id = Integer.parseInt(idLugar);
            this.lugar = lugarServicio.obtenerLugar(id);
            this.comentarios = lugarServicio.listarComentariosDeUnLugar(id);
            this.horarios = lugarServicio.listarHorariosDeUnLugar(id);


//            LugarDTO markerLugar = new LugarDTO(this.lugar.getCodigo(), this.lugar.getNombre(), this.lugar.getDescripcion(), this.lugar.getLatitud(), this.lugar.getLongitud(), this.lugar.getTipo().getNombre());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void responderComentario(Comentario comentario) {
        try {
            comentario.setRespuesta(this.respuesta);
            this.comentarioEmail = comentario;
            comentarioServicio.actualizarComentario(comentario);
            ExecutorService executor = Executors.newFixedThreadPool(10);
            executor.execute(new Runnable() {
                public void run() {

                    enviarEmailRespuesta();
                }
            });
            executor.shutdown();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void enviarEmailRespuesta() {
        try {
            String usuarioPropietario = personaLogin.getNombre();
            String usuarioRespuesta = this.comentarioEmail.getUsuarioComentario().getNombre();
            String respuesta = this.comentarioEmail.getRespuesta();
            String lugar = this.comentarioEmail.getLugarComentario().getNombre();
            String subject = "Unilocal : Respuesta Comentario";
            String from = "unilocal2021@gmail.com";
            String to = this.comentarioEmail.getUsuarioComentario().getEmail();
            Email.sendEmailRespuesta(usuarioPropietario, subject, to, from, usuarioRespuesta, lugar, respuesta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean hayRespuesta(Comentario comentario) {
        System.out.println(comentario.getRespuesta());
        if (comentario.getRespuesta() == null) {
            return false;
        }
        return true;
    }

    public void editarHorario(int idHorario) {
        try {
            this.horarios.get(idHorario).setDia(this.dia);
            this.horarios.get(idHorario).setHoraApertura(this.horaApertura);
            this.horarios.get(idHorario).setHoraCierre(this.horaCierre);
            horarioServicio.actualizarHorario(this.horarios.get(idHorario));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String editarLugar() {
        try {
            this.lugar.setHorarios(this.horarios);
            lugarServicio.actualizarLugar(this.lugar);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/usuario/comentarios?faces-redirect=true&amp;lugar=" + Integer.parseInt(idLugar);
    }
}

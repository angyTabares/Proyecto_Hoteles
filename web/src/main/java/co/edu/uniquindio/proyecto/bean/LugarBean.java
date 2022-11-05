package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.file.UploadedFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class LugarBean implements Serializable {

    @Getter
    @Setter
    private Lugar lugar;


    @Value("${upload.url}")
    private String urlImagenes;
    private ArrayList<String> imagenes;

    @Getter
    @Setter
    private boolean skip;

    private final LugarServicio lugarServicio;
    private final CiudadServicio ciudadServicio;
    private final UsuarioServicio usuarioServicio;
    private final TipoServicio tipoServicio;
    private final HorarioServicio horarioServicio;

    @Getter
    @Setter
    private List<Ciudad> ciudades;

    @Getter
    @Setter
    private List<Tipo> tipoLugares;

    @Getter
    @Setter
    private List<Horario> horarios;

    @Getter
    @Setter
    private Horario horario;

    @Getter
    @Setter
    private ArrayList<String> dias;

    @Getter
    @Setter
    private ArrayList<String> telefonos;


    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;


    public LugarBean(LugarServicio lugarServicio, CiudadServicio ciudadServicio, UsuarioServicio usuarioServicio, TipoServicio tipoServicio, HorarioServicio horarioServicio) {
        this.lugarServicio = lugarServicio;

        this.ciudadServicio = ciudadServicio;
        this.usuarioServicio = usuarioServicio;
        this.tipoServicio = tipoServicio;
        this.horarioServicio = horarioServicio;
    }

    @PostConstruct
    public void inicializar() {
        this.llenarDias();
        this.horario = new Horario();
        this.horarios = new ArrayList<>();
        this.lugar = new Lugar();
        this.ciudades = ciudadServicio.listarCiudades();
        this.tipoLugares = tipoServicio.listarTiposLugares();
        this.imagenes = new ArrayList<>();
        this.telefonos = new ArrayList<>();
    }

    public void crearLugar() {

        try {
            if (personaLogin != null) {
                System.out.println(lugar.getLatitud() + "," + lugar.getLongitud());
                if (lugar.getLatitud() != null && lugar.getLongitud() != null && !imagenes.isEmpty()) {
                    lugar.setImagenes(imagenes);
                    lugar.setUsuario((Usuario) personaLogin);
                    lugar.setTelefonos(this.telefonos);
                    lugarServicio.crearLugar(lugar);

                    for (Horario h : horarios) {
                        h.setHorarioLugar(this.lugar);
                        horarioServicio.crearHorario(h);
                    }


                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "El lugar fue creado exitosamente");
                    FacesContext.getCurrentInstance().addMessage("mensaje_bean", msg);

                } else {
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Es necesario ubicar el lugar dentro del mapa y subir al menos una imagen");
                    FacesContext.getCurrentInstance().addMessage("mensaje_bean", msg);

                }
            }

        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", msg);

        }

    }

    public void subirImagenes(FileUploadEvent event) {
        UploadedFile imagen = event.getFile();
        String nombreImagen = subirImagen(imagen);
        if (nombreImagen != null) {
            imagenes.add(nombreImagen);
        }

    }

    public String subirImagen(UploadedFile file) {
        try {
            InputStream input = file.getInputStream();
            String filename = FilenameUtils.getName(file.getFileName());
            String basename = FilenameUtils.getBaseName(filename) + "_";
            String extension = "." + FilenameUtils.getExtension(filename);
            File fileDest = File.createTempFile(basename, extension, new File(urlImagenes));
            FileOutputStream output = new FileOutputStream(fileDest);
            IOUtils.copy(input, output);
            return fileDest.getName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;    //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public void llenarDias() {
        this.dias = new ArrayList<>();
        this.dias.add("Lunes");
        this.dias.add("Martes");
        this.dias.add("Miercoles");
        this.dias.add("Jueves");
        this.dias.add("Viernes");
        this.dias.add("Sabado");
        this.dias.add("Domingo");
    }

    public void nuevoHorario() {
        System.out.println("Nuevo Horario");
        this.horario = new Horario();

    }

    public void crearHorario() {
        System.out.println("Creando horario");
        System.out.println(this.horario.getDia());
        System.out.println("Hora apertura" + this.horario.getHoraApertura().toString());
        System.out.println("Hora cierre" + this.horario.getHoraCierre().toString());
        try {

            this.horarios.add(horario);
            nuevoHorario();
            PrimeFaces.current().executeScript("PF('Horarios    ').hide()");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarHorario() {
        this.horarios.remove(this.horario);
        nuevoHorario();
    }

    public String obtenerStringHorasApertura() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        return sdf.format(this.horario.getHoraApertura());
    }


}

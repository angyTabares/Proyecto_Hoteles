package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.entidades.Persona;
import co.edu.uniquindio.proyecto.servicios.LugarServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class LugaresModeradorBean implements Serializable {

    @Value(value="#{seguridadBean.persona}")
    private Persona personaLogin;

    private List<String> images;

    @Getter @Setter
    private List<Lugar> lugaresPendiente;

    @Getter @Setter
    private List<Lugar> lugaresAprobados;

    @Getter @Setter
    private List<Lugar> lugaresDenegados;

    @Autowired
    private LugarServicio lugarServicio;

    @PostConstruct
    public void inicializar() {
        // this.icono = "pi pi-star-o";
        try {
            images = new ArrayList<String>();

            images.add("https://www.eltiempo.com/files/image_640_428/uploads/2019/03/30/5ca02668e04df.jpeg");
            images.add("https://www.revistalabarra.com/wp-content/uploads/2019/02/Potada-nota-enano-1500x800.jpg");
            images.add("https://www.entornoturistico.com/wp-content/uploads/2016/06/clientes-en-un-bar-660x330.jpg");

            this.lugaresPendiente= lugarServicio.obtenerLugaresPendientes();
            this.lugaresAprobados= lugarServicio.obtenerLugaresAprobadorModerador(personaLogin.getEmail());
            this.lugaresDenegados= lugarServicio.obtenerLugaresDenegadosModerador(personaLogin.getEmail());
        //    System.out.println(this.lugares);
//            LugarDTO markerLugar = new LugarDTO(this.lugar.getCodigo(), this.lugar.getNombre(), this.lugar.getDescripcion(), this.lugar.getLatitud(), this.lugar.getLongitud(), this.lugar.getTipo().getNombre());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean hayLugaresPendientes()
    {
        if(this.lugaresPendiente.isEmpty())
        {
            return false;
        }
        return true;
    }

    public boolean hayLugaresAprobados()
    {
        if(this.lugaresAprobados.isEmpty())
        {
            return false;
        }
        return true;
    }

    public boolean hayLugaresDenegados()
    {
        if(this.lugaresDenegados.isEmpty())
        {
            return false;
        }
        return true;
    }
}

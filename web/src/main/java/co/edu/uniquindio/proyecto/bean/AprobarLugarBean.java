package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.entidades.Moderador;
import co.edu.uniquindio.proyecto.entidades.Persona;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.LugarServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class AprobarLugarBean {

    @Value(value="#{seguridadBean.persona}")
    private Persona personaLogin;

    @Autowired
    private LugarServicio lugarServicio;

    @Value("#{param['lugar']}")
    private String idLugar;

    @Getter @Setter
    private Lugar lugar;

    private List<String> images;
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
//            LugarDTO markerLugar = new LugarDTO(this.lugar.getCodigo(), this.lugar.getNombre(), this.lugar.getDescripcion(), this.lugar.getLatitud(), this.lugar.getLongitud(), this.lugar.getTipo().getNombre());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String aprobarLugar()
    {
        try {
            this.lugar.setEstado(true);
            this.lugar.setModerador((Moderador) personaLogin);
            lugarServicio.actualizarLugar(this.lugar);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/moderador/lugaresModerador?faces-redirect=true";
    }

    public String denegarLugar()
    {
        try {
            this.lugar.setEstado(false);
            this.lugar.setModerador((Moderador) personaLogin);
            lugarServicio.actualizarLugar(this.lugar);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/moderador/lugaresModerador?faces-redirect=true";
    }

    public boolean estaAprobado()
    {
        if(this.lugar.isEstado())
        {
            return true;
        }
        return false;
    }
}

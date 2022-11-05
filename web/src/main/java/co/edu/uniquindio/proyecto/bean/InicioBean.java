package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.dto.LugarDTO;
import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.servicios.LugarServicio;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.primefaces.component.collector.Collector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ViewScoped
public class InicioBean implements Serializable {

    @Autowired
    private LugarServicio lugarServicio;

    @Getter
    @Setter
    private List<Lugar> lugares;

    @Getter
    @Setter
    private List<Lugar> lugaresAprobados;

    @PostConstruct
    public void inicializar() {
        try {
            this.lugaresAprobados = lugarServicio.obtenerLugaresAprobados();
            List<LugarDTO> markerLugares = this.lugaresAprobados.stream().map(l -> new LugarDTO(l.getCodigo(), l.getNombre(), l.getDescripcion(), l.getLatitud(), l.getLongitud(), l.getTipo().getNombre())).collect(Collectors.toList());

            PrimeFaces.current().executeScript("crearMapa(" + new Gson().toJson(markerLugares) + ")");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrarLugaresPorDistancia() {
        PrimeFaces.current().executeScript("test()");
    }


    public String irADetalle(int id) {
        return "/detalleLugar?faces-redirect=true&amp;lugar=" + id;

    }

    public String irAComentario(int id) {
        return "/usuario/comentarios?faces-redirect=true&amp;lugar=" + id;

    }

    public String irAAprobacion(int id) {
        return "/moderador/aprobarLugar?faces-redirect=true&amp;lugar=" + id;

    }

    public String irALugaresModerador() {
        System.out.println("entré");
        return "/moderador/lugaresModerador?faces-redirect=true";

    }

    public String irAEDitarLugar(int id) {
        return "/usuario/editarLugar?faces-redirect=true&amp;lugar=" + id;

    }
}

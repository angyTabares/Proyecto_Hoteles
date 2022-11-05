package co.edu.uniquindio.proyecto.converter;

import co.edu.uniquindio.proyecto.entidades.Tipo;
import co.edu.uniquindio.proyecto.servicios.TipoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;

@Component
public class TipoLugarConverter implements Converter<Tipo>, Serializable {

    @Autowired
    private TipoServicio tipoServicio;

    @Override
    public Tipo getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        try {
            if (s != null && !"".equals(s)) {
                int id = Integer.parseInt(s);
                return tipoServicio.obtenerTipoLugar(id);
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Tipo tipo) {

       if(tipo!=null)
       {
           return ""+tipo.getId();
       }
        return "";
    }
}

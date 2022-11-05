package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class LugaresNoAutDTO {
    private String ciudad;
    private long cantidad;

    public LugaresNoAutDTO(String ciudad, long cantidad) {
        this.ciudad = ciudad;
        this.cantidad = cantidad;
    }
}

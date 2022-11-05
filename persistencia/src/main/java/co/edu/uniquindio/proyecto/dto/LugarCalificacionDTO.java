package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.entidades.Lugar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LugarCalificacionDTO {
    private Lugar lugar;
    private Double calificacion;

    public LugarCalificacionDTO(Lugar lugar, double calificacion) {
        this.lugar = lugar;
        this.calificacion = calificacion;
    }
}

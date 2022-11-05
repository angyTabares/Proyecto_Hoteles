package co.edu.uniquindio.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@AllArgsConstructor
public class LugarDTO {
    private int id;
    private String nombre;
    private String descripcion;
    private float lat, lng;
    private String tipo;
}


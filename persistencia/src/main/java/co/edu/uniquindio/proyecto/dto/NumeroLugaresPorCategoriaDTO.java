package co.edu.uniquindio.proyecto.dto;

public class NumeroLugaresPorCategoriaDTO {

    private String nombre;
    private Long cantidadLugares;

    public NumeroLugaresPorCategoriaDTO(String nombre, Long cantidadLugares) {
        this.nombre = nombre;
        this.cantidadLugares = cantidadLugares;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getCantidadLugares() {
        return cantidadLugares;
    }

    public void setCantidadLugares(Long cantidadLugares) {
        this.cantidadLugares = cantidadLugares;
    }

    @Override
    public String toString() {
        return "NumeroLugaresPorCategoriaDTO{" +
                "nombre='" + nombre + '\'' +
                ", cantidadLugares=" + cantidadLugares +
                '}';
    }
}

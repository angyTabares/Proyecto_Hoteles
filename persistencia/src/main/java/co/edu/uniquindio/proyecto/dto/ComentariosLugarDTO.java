package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Lugar;

import java.util.List;

public class ComentariosLugarDTO {

    private Lugar lugar;
    private Comentario comentario;

    public ComentariosLugarDTO(Lugar lugar, Comentario comentario) {
        this.lugar = lugar;
        this.comentario = comentario;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "ComentariosLugarDTO{" +
                "lugar=" + lugar +
                ", comentario=" + comentario +
                '}';
    }
}

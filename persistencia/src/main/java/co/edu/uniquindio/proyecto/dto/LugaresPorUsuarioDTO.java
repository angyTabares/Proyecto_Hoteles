package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.entidades.Usuario;

import java.util.List;

public class LugaresPorUsuarioDTO {
    private Usuario usuario;
    private Lugar listaLugares;

    public LugaresPorUsuarioDTO(Usuario usuario, Lugar listaLugares) {
        this.usuario = usuario;
        this.listaLugares = listaLugares;
    }


    public Usuario getUsuario() {

        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Lugar getListaLugares() {
        return listaLugares;
    }

    public void setListaLugares(Lugar listaLugares) {
        this.listaLugares = listaLugares;
    }

    @Override
    public String toString() {
        return "LugaresPorUsuarioDTO{" + "usuario=" + usuario + ", listaLugares=" + listaLugares + '}';
    }
}

package co.edu.uniquindio.proyecto.servicios;


import co.edu.uniquindio.proyecto.entidades.Evento;
import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.repositorios.EventoRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoServicioimpl implements EventoServicio {
    private final EventoRepo eventoRepo;


    public EventoServicioimpl(EventoRepo eventoRepo) {
        this.eventoRepo = eventoRepo;
    }

    public boolean longitudValida(String c, int longitud){
        String cadena=c.trim().replace(" ","");

        return (cadena.length()>longitud || cadena.length()<=0)?true:false;

    }
    public boolean estaEvento(int id){
        Optional<Evento> evento= eventoRepo.findById(id);
        return evento.isEmpty();
    }
    public Optional<Evento> buscarEventoId(int id){

        return eventoRepo.findById(id);
    }
    public boolean estaVaciaEventos(){
        List<Evento> lista=eventoRepo.findAll();
        return lista.isEmpty();
    }


    @Override
    public Evento obtenerEvento(int codigo) throws Exception {
        Optional<Evento> evento =buscarEventoId(codigo);
        if(evento.isEmpty()){
            throw new Exception("No existe un evento con el codigo dado");
        }

        return evento.get();
    }

    @Override
    public Evento crearEvento(Evento evento) throws Exception {
        if(longitudValida(evento.getDescripcion(),1000)){
            throw new Exception("La descripcion  debe contener al menos un caracter y debe ser menor a 1000 caracteres");
        }
        if(longitudValida(evento.getNombre(),200)){
            throw new Exception("El nombre  debe contener al menos un caracter y debe ser menor a 200 caracteres");
        }

        return eventoRepo.save(evento);
    }

    @Override
    public List<Evento> listarEventos() throws Exception {

        if(estaVaciaEventos()){
            throw new Exception("La lista de eventos esta vacia");
        }
        return eventoRepo.findAll();
    }

    @Override
    public Evento actualizarEvento(Evento evento) throws Exception {



        return crearEvento(evento);
    }

    @Override
    public void eliminarEvento(Evento evento) throws Exception {
        if(estaEvento(evento.getCodigo())){
            throw new Exception("El evento con ese id no existe");
        }
        eventoRepo.delete(evento);
    }
}

package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Tipo;
import co.edu.uniquindio.proyecto.repositorios.TipoRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoServicioImpl implements TipoServicio{

    private final TipoRepo tipoRepo;

    public TipoServicioImpl(TipoRepo tipoRepo) {
        this.tipoRepo = tipoRepo;
    }

    /**
     * Metodo que permite guardar un tipo de lugar en la bd
     * @param t
     * @return tipo guardado
     * @throws Exception
     */

    @Override
    public Tipo registrarTipoLugar(Tipo t) throws Exception {

        if(t.getNombre().length()>200)
        {
            throw new Exception("el nombre  debe ser menor a 200 caracteres");
        }
        return tipoRepo.save(t);
    }

    /**
     * Metodo que permite eliminar un tipo de lugar de la bd
     * @param t
     * @throws Exception
     */

    @Override
    public void eliminarTipoLugar(Tipo t) throws Exception {

        if(estaTipoDeLugar(t.getId()))
        {
            throw new Exception("El Tipo de lugar no existe") ;
        }

         tipoRepo.delete(t);
    }

    /**
     * Metodo que permite actualizar la info de un tipo de lugar de la bd
     * @param t
     * @return
     * @throws Exception
     */

    @Override
    public Tipo actualizarTipoLugar(Tipo t) throws Exception {

        if(estaTipoDeLugar(t.getId()))
        {
            throw new Exception("El Tipo de lugar no existe") ;
        }

        if(t.getNombre().length()>200)
        {
            throw new Exception("el nombre debe ser menor a 200 caracteres");
        }

        return tipoRepo.save(t);
    }

    /**
     * Metodo que permite obtener un tipo de lugar
     * @param id
     * @return tipo
     */

    @Override
    public Tipo obtenerTipoLugar(int id) throws Exception {
        Optional<Tipo> tipo =tipoRepo.findById(id);
        if(tipo.isEmpty()){
            throw new Exception("No existe un lugar con el id dado");
        }

        return tipo.get();
    }

    /**
     * Metodo que permite listar los tipos de lugares de la bd
     * @return tipos
     * @throws Exception
     */

    @Override
    public List<Tipo> listarTiposLugares()  {
       return tipoRepo.findAll();
    }

    /**
     * Metodo que permite validar si un tipo de lugar se encuentra en la bd
     * @param id
     * @return true si el tipo de lugar no esta
     */

    public boolean estaTipoDeLugar(int id){
        Optional<Tipo> tipo=tipoRepo.findById(id);
        return tipo.isEmpty();
    }

}

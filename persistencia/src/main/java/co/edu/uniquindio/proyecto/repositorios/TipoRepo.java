package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.entidades.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Esta interface define el deposito de datos de tipo de lugar
 * @author: Daniel Ceballos, Angy Tabares
 */
@Repository
public interface TipoRepo extends JpaRepository<Tipo,Integer> {

    @Query("select t.nombre,avg (c.calificacion) as total  from Tipo t join t.lugares l left join l.comentarios c where l.ciudadLugar.codigo= :codigoCiudad  group by l.tipo order by total desc")
    List<Object[]> categoriaCalificacionPromedioCiudadEspecifica(Integer codigoCiudad);

    Optional<Tipo> findByNombre(String nombre);
    Optional<Tipo> findById(int id);
}

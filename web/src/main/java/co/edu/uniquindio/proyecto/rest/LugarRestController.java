package co.edu.uniquindio.proyecto.rest;

import co.edu.uniquindio.proyecto.dto.Mensaje;
import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.LugarServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/lugares")
public class LugarRestController {
    private LugarServicio lugarServicio;

    public LugarRestController(LugarServicio lugarServicio) {
        this.lugarServicio = lugarServicio;
    }

    @GetMapping()
    public List<Lugar> listar() {
        try {
            return lugarServicio.listarLugares();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    @PostMapping
    public ResponseEntity<Mensaje> crear(@RequestBody Lugar lugar) {
        try {
            lugarServicio.crearLugar(lugar);
            return ResponseEntity.status(201).body(new Mensaje("El lugar se ha creado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<Mensaje> actualizar(@RequestBody Lugar lugar) {
        try {
            lugarServicio.actualizarLugar(lugar);
            return ResponseEntity.status(200).body(new Mensaje("El lugar se ha actualizado correctamente"));
        } catch (Exception e) {

            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mensaje> eliminar(@PathVariable(name = "id") Integer id) {
        try {
            lugarServicio.eliminarLugarPorId(id);
            return ResponseEntity.status(200).body(new Mensaje("El lugar se ha eliminado correctamente"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerLugar(@PathVariable(name = "id") Integer id) {
        try {

            return ResponseEntity.status(200).body(lugarServicio.obtenerLugar(id));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));

        }

    }

    @GetMapping("/{nombre}")
    public ResponseEntity<?> obtenerLugar(@PathVariable(name = "nombre") String nombre) {
        try {

            return ResponseEntity.status(200).body(lugarServicio.buscarLugarPorNombre(nombre));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));

        }

    }

    @GetMapping("/tipo/{id}")
    public ResponseEntity<?> obtenerTipoLugar(@PathVariable(name = "id") Integer id) {
        try {

            return ResponseEntity.status(200).body(lugarServicio.tipoLugar(id));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));

        }

    }

    @GetMapping("/usuarioslugar/{email}")
    public ResponseEntity<?> obtenerLugaresUsuario(@PathVariable(name = "email") String email) {
        try {
            return ResponseEntity.status(200).body(lugarServicio.obtenerLugaresUsuario(email));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }
    
}

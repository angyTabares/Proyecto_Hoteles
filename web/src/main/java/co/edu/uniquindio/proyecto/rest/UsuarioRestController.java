package co.edu.uniquindio.proyecto.rest;


import co.edu.uniquindio.proyecto.dto.Mensaje;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioRestController {
    private final UsuarioServicio usuarioServicio;

    public UsuarioRestController(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @GetMapping()
    public List<Usuario> listar() {
        try {
            return usuarioServicio.listarUsuario();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @PostMapping
    public ResponseEntity<Mensaje> crear(@RequestBody Usuario usuario) {
        try {
            usuarioServicio.registrarUsuario(usuario);
            return ResponseEntity.status(201).body(new Mensaje("El usuario se ha registrado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<Mensaje> actualizar(@RequestBody Usuario usuario) {
        try {
            usuarioServicio.actualizarUsuarioCompleto(usuario);
            return ResponseEntity.status(200).body(new Mensaje("El usuario se ha actualizado correctamente"));
        } catch (Exception e) {

            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mensaje> eliminar(@PathVariable(name = "id") Integer id) {
        try {
            Usuario usuario = usuarioServicio.obtenerUsuario(id);
            usuarioServicio.eliminarUsuario(usuario);
            return ResponseEntity.status(200).body(new Mensaje("El usuario se ha eliminado correctamente"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable(name = "id") Integer id) {
        try {
            return ResponseEntity.status(200).body(usuarioServicio.obtenerUsuario(id));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));

        }

    }


}

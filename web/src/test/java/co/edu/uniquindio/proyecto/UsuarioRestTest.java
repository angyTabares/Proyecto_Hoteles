package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.entidades.Usuario;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = WebApplication.class)
@AutoConfigureMockMvc

public class UsuarioRestTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    public void registrarTest() throws Exception {
        Usuario usuario = Usuario.builder().nombre("Juanita").nickname("Juana").password("123").email("juana@gmail.com").build();
        mockMvc.perform(post("/api/usuarios").contentType("application/json").
                content(objectMapper.writeValueAsString(usuario))).
                andDo(MockMvcResultHandlers.print()).andExpect(status().isCreated());
    }

    @Test
    @Transactional
    public void actualizarTest() throws Exception {
        Usuario usuario = Usuario.builder().nombre("Juanita").nickname("Juana").password("123").email("juana@gmail.com").build();
        mockMvc.perform(put("/api/usuarios").contentType("application/json").
                content(objectMapper.writeValueAsString(usuario))).
                andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
    }


}

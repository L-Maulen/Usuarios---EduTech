package com.eduDB.EduTech_DB.java.testController;

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.eduDB.EduTech_DB.Controller.TipoUsuarioController;
import com.eduDB.EduTech_DB.Model.TipoUsuario;
import com.eduDB.EduTech_DB.Service.TipoUsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(TipoUsuarioController.class)
public class TestTipoUsuarioController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TipoUsuarioService tipoUsuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    private TipoUsuario tipoUsuario;

    @BeforeEach
    void setUp() {
        tipoUsuario = new TipoUsuario();

        tipoUsuario.setIdTipoUsuario(1);
        tipoUsuario.setDescripcionUsuario("Administrador");
        tipoUsuario.setUsuarios(null);

    }

    // Test unitario para endpoint de GET que lista todos los tipos de usuarios
    @Test
    public void testListarTiposUsuarios() throws Exception {
        when(tipoUsuarioService.listarTiposUsuario()).thenReturn(List.of(tipoUsuario));

        mockMvc.perform(get("/api/v1/TiposUsuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idTipoUsuario").value(tipoUsuario.getIdTipoUsuario()))
                .andExpect(jsonPath("$[0].descripcionUsuario").value(tipoUsuario.getDescripcionUsuario()));

    }

    @Test
    public void testObtenerTipoUsuarioPorId() throws Exception {
        when(tipoUsuarioService.buscarTipoUsuarioId(1)).thenReturn(tipoUsuario);

        mockMvc.perform(get("/api/v1/TiposUsuarios/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idTipoUsuario").value(tipoUsuario.getIdTipoUsuario()))
                .andExpect(jsonPath("$.descripcionUsuario").value(tipoUsuario.getDescripcionUsuario()));
    }

    @Test
    public void testPostTipoUsuario() throws Exception {
        when(tipoUsuarioService.agregartTipoUsuario(tipoUsuario)).thenReturn(tipoUsuario);

        mockMvc.perform(post("/api/v1/TiposUsuarios")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(tipoUsuario)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idTipoUsuario").value(tipoUsuario.getIdTipoUsuario()))
                .andExpect(jsonPath("$.descripcionUsuario").value(tipoUsuario.getDescripcionUsuario()));

    }

    @Test
    public void testUpdateTipoUsuario() throws Exception {
        when(tipoUsuarioService.actualizarTipoUsuario(tipoUsuario)).thenReturn(tipoUsuario);

        mockMvc.perform(put("/api/v1/TiposUsuarios/{id}", tipoUsuario.getIdTipoUsuario())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(tipoUsuario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idTipoUsuario").value(tipoUsuario.getIdTipoUsuario()))
                .andExpect(jsonPath("$.descripcionUsuario").value(tipoUsuario.getDescripcionUsuario()));

    }

    @Test
    public void testDeleteTipoUsuario() throws Exception {
        doNothing().when(tipoUsuarioService).eliminarTiposUsuarioId(tipoUsuario.getIdTipoUsuario());

        mockMvc.perform(delete("/api/v1/TiposUsuarios/{id}", tipoUsuario.getIdTipoUsuario()))
                .andExpect(status().isNoContent());

        verify(tipoUsuarioService, times(1)).eliminarTiposUsuarioId(tipoUsuario.getIdTipoUsuario());
    }

}

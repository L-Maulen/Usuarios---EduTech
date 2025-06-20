package com.eduDB.EduTech_DB.java.testController;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.eduDB.EduTech_DB.Controller.UserController;
import com.eduDB.EduTech_DB.Model.TipoUsuario;
import com.eduDB.EduTech_DB.Model.Usuario;
import com.eduDB.EduTech_DB.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.mockito.Mockito.times;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@WebMvcTest(UserController.class)
public class TestUsuarioController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private Usuario usuario;
    private TipoUsuario tipoUsuario;

    @BeforeEach
    void setUp() {
        tipoUsuario = new TipoUsuario();
        tipoUsuario.setIdTipoUsuario(2);
        tipoUsuario.setDescripcionUsuario("Profesor");
        tipoUsuario.setUsuarios(null);

        usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setPasswrd("Contraseña123");
        usuario.setNombreUsuario("Juan");
        usuario.setApellidoUsuario("Perez");
        usuario.setCorreoUsuario("Jperez@gmail.com");
        usuario.setTipoUsuario(tipoUsuario);

    }

    @Test
    public void testListarUsuarios() throws Exception {
        when(userService.listarUsuarios()).thenReturn(List.of(usuario));

        mockMvc.perform(get("/api/v1/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idUsuario").value(usuario.getIdUsuario()))
                .andExpect(jsonPath("$[0].passwrd").value(usuario.getPasswrd()))
                .andExpect(jsonPath("$[0].nombreUsuario").value(usuario.getNombreUsuario()))
                .andExpect(jsonPath("$[0].apellidoUsuario").value(usuario.getApellidoUsuario()))
                .andExpect(jsonPath("$[0].correoUsuario").value(usuario.getCorreoUsuario()))
                .andExpect(jsonPath("$[0].tipoUsuario.idTipoUsuario").value(tipoUsuario.getIdTipoUsuario()))
                .andExpect(jsonPath("$[0].tipoUsuario.descripcionUsuario").value(tipoUsuario.getDescripcionUsuario()));
    }

    @Test
    public void testObtenerUsuarioPorId() throws Exception {
        when(userService.buscarPorID(1)).thenReturn(usuario);

        mockMvc.perform(get("/api/v1/usuarios/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idUsuario").value(usuario.getIdUsuario()))
                .andExpect(jsonPath("$.passwrd").value(usuario.getPasswrd()))
                .andExpect(jsonPath("$.nombreUsuario").value(usuario.getNombreUsuario()))
                .andExpect(jsonPath("$.apellidoUsuario").value(usuario.getApellidoUsuario()))
                .andExpect(jsonPath("$.correoUsuario").value(usuario.getCorreoUsuario()))
                .andExpect(jsonPath("$.tipoUsuario.idTipoUsuario").value(tipoUsuario.getIdTipoUsuario()))
                .andExpect(jsonPath("$.tipoUsuario.descripcionUsuario").value(tipoUsuario.getDescripcionUsuario()));
    }

    @Test
    public void testPostUsuario() throws Exception {
        when(userService.agregarUsuario(usuario)).thenReturn(usuario);

        mockMvc.perform(post("/api/v1/usuarios")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idUsuario").value(usuario.getIdUsuario()))
                .andExpect(jsonPath("$.passwrd").value(usuario.getPasswrd()))
                .andExpect(jsonPath("$.nombreUsuario").value(usuario.getNombreUsuario()))
                .andExpect(jsonPath("$.apellidoUsuario").value(usuario.getApellidoUsuario()))
                .andExpect(jsonPath("$.correoUsuario").value(usuario.getCorreoUsuario()))
                .andExpect(jsonPath("$.tipoUsuario.idTipoUsuario").value(tipoUsuario.getIdTipoUsuario()))
                .andExpect(jsonPath("$.tipoUsuario.descripcionUsuario").value(tipoUsuario.getDescripcionUsuario()));
    }

    @Test
    public void testUpdateUsuario() throws Exception {
        when(userService.actualizarUsr(usuario)).thenReturn(usuario);

        mockMvc.perform(put("/api/v1/usuarios/{id}", usuario.getIdUsuario())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idUsuario").value(usuario.getIdUsuario()))
                .andExpect(jsonPath("$.passwrd").value(usuario.getPasswrd()))
                .andExpect(jsonPath("$.nombreUsuario").value(usuario.getNombreUsuario()))
                .andExpect(jsonPath("$.apellidoUsuario").value(usuario.getApellidoUsuario()))
                .andExpect(jsonPath("$.correoUsuario").value(usuario.getCorreoUsuario()))
                .andExpect(jsonPath("$.tipoUsuario.idTipoUsuario").value(tipoUsuario.getIdTipoUsuario()))
                .andExpect(jsonPath("$.tipoUsuario.descripcionUsuario").value(tipoUsuario.getDescripcionUsuario()));
    }

    @Test
    public void testDeleteUsuario() throws Exception {
        doNothing().when(userService).eliminiarUsrID(usuario.getIdUsuario());

        mockMvc.perform(delete("/api/v1/usuarios/{id}", usuario.getIdUsuario()))
                .andExpect(status().isNoContent());

        verify(userService, times(1)).eliminiarUsrID(usuario.getIdUsuario());
    }

}

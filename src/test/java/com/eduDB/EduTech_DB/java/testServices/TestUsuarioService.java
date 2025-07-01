package com.eduDB.EduTech_DB.java.testServices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.eduDB.EduTech_DB.Model.Usuario;
import com.eduDB.EduTech_DB.Repository.TipoUsuarioRepository;
import com.eduDB.EduTech_DB.Repository.UserRepository;
import com.eduDB.EduTech_DB.Service.UserService;

import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(properties = {
    "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration"
})
@ActiveProfiles("test")
public class TestUsuarioService {
    @Autowired
    private UserService usuarioService;

    @MockBean
    private UserRepository usuarioRepository;

    @MockBean
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Test
    public void testListarUsuarios() {
        when(usuarioRepository.findAll()).thenReturn(
                List.of(new Usuario(1, "contraseña123", "Esteban", "Gonzalez", "Egonzales@gmail.com", null)));

        List<Usuario> usuarios = usuarioService.listarUsuarios();

        // Verifica que la lista no sea nula y tenga al menos un usuario
        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());

    }

    @Test
    public void testAgregarUsuario() {
        Usuario nuevoUsuario = new Usuario(1, "contraseña123", "Esteban", "Gonzalez", "Egonzales@gmail.com", null);
        when(usuarioRepository.save(nuevoUsuario)).thenReturn(nuevoUsuario);

        Usuario usuarioGuardado = usuarioService.agregarUsuario(nuevoUsuario);

        // Verifica que el usuario guardado no sea nulo y que su nombre coincida con el
        // esperado.
        assertNotNull(usuarioGuardado);
        assertEquals("Esteban", usuarioGuardado.getNombreUsuario());
    }

    @Test
    public void testBuscarPorID() {
        Usuario usuario = new Usuario(1, "contraseña123", "Esteban", "Gonzalez", "Egonzales@gmail.com", null);

        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
        Usuario usuarioEncontrado = usuarioService.buscarPorID(1);

        // Verifica que el usuario no sea null y tenga los datos esperados
        assertNotNull(usuarioEncontrado);
        assertEquals("Esteban", usuarioEncontrado.getNombreUsuario());
    }

    @Test
    public void testEliminarUsuario() {
        Integer idUsuario = 1;
        usuarioService.eliminiarUsrID(idUsuario);

        // Verifica que deleteById fue llamado una vez con el ID correcto
        verify(usuarioRepository).deleteById(idUsuario);
    }

    @Test
    public void testActualizarUsuario() {
        Usuario usuarioExistente = new Usuario(1, "contraseña123", "Esteban", "Gonzalez", "Egonzales@gmail.com", null);

        when(usuarioRepository.existsById(1)).thenReturn(true);
        when(usuarioRepository.save(usuarioExistente)).thenReturn(usuarioExistente);

        Usuario usuarioActualizado = usuarioService.actualizarUsr(usuarioExistente);

        // Verifica que el usuario se actualice correctamente.
        assertNotNull(usuarioActualizado);
        assertEquals("Esteban", usuarioExistente.getNombreUsuario());

    }
}

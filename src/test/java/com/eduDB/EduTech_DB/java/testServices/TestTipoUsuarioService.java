package com.eduDB.EduTech_DB.java.testServices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.eduDB.EduTech_DB.Model.TipoUsuario;
import com.eduDB.EduTech_DB.Repository.TipoUsuarioRepository;
import com.eduDB.EduTech_DB.Service.TipoUsuarioService;


@SpringBootTest
public class TestTipoUsuarioService {
    @Autowired TipoUsuarioService servicioTipoUsuario;

    @MockBean
    private  TipoUsuarioRepository repositorioTipoUsuario;

    @Test
    public void testListarTiposUsuario() {
        when(repositorioTipoUsuario.findAll()).thenReturn(List.of(new TipoUsuario(1, "Profesor", null)));

        List<TipoUsuario> tiposUsuario = servicioTipoUsuario.listarTiposUsuario();

        // Verifica que la lista devuelta no sea nula y contenga exactamente un tipo de usuario.
        assertNotNull(tiposUsuario);
        assertEquals(1, tiposUsuario.size());   

    }

    @Test
    public void testAgregarTipoUsuario() {
        TipoUsuario nuevoTipoUsuario = new TipoUsuario(1, "Profesor", null);
        when(repositorioTipoUsuario.save(nuevoTipoUsuario)).thenReturn(nuevoTipoUsuario);

        TipoUsuario tipoUsuarioGuardado = servicioTipoUsuario.agregartTipoUsuario(nuevoTipoUsuario);

        // Verifica que el tipo de usuario guardado no sea nulo y que su descripción coincida con la esperada.
        assertNotNull(tipoUsuarioGuardado);
        assertEquals("Profesor", tipoUsuarioGuardado.getDescripcionUsuario());
    }

    @Test
    public void testBuscarTipoUsuarioId() {
        TipoUsuario tipoUsuario = new TipoUsuario(1, "Profesor", null);
        
        when(repositorioTipoUsuario.findById(1)).thenReturn(Optional.of(tipoUsuario));

        TipoUsuario tipoUsuarioEncontrado = servicioTipoUsuario.buscarTipoUsuarioId(1);

        // Verifica que el tipo de usuario devuelto no sea nulo y que su código coincida con el código esperado.
        assertNotNull(tipoUsuarioEncontrado);
        assertEquals("Profesor", tipoUsuarioEncontrado.getDescripcionUsuario());
    }


    @Test
    public void testActualizarTipoUsuario() {
        TipoUsuario tipoUsuarioExistente = new TipoUsuario(1, "Profesor", null);
        when(repositorioTipoUsuario.existsById(1)).thenReturn(true);
        when(repositorioTipoUsuario.save(tipoUsuarioExistente)).thenReturn(tipoUsuarioExistente);

        TipoUsuario tipoUsuarioActualizado = servicioTipoUsuario.actualizarTipoUsuario(tipoUsuarioExistente);

        // Verifica que el tipo de usuario actualizado no sea nulo y que su descripción coincida con la esperada.
        assertNotNull(tipoUsuarioActualizado);
        assertEquals("Profesor", tipoUsuarioActualizado.getDescripcionUsuario());
    }

    @Test
    public void testEliminarTiposUsuarioId() {
        int id = 1;
        when(repositorioTipoUsuario.existsById(id)).thenReturn(true);

        // Llama al método de eliminación
        servicioTipoUsuario.eliminarTiposUsuarioId(id);
        
        // Verifica que el método deleteById haya sido llamado con el ID correcto.
        repositorioTipoUsuario.deleteById(id);
    }
}
package com.eduDB.EduTech_DB.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.eduDB.EduTech_DB.Model.Usuario;
import com.eduDB.EduTech_DB.Service.UserService;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con los usuarios")

public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    @Operation(summary = "Obtener todos los usuarios.", description = "Obtiene una lista con todos los usuarios registrados en la base de datos.")
    @ApiResponses( value = {
        @ApiResponse(responseCode = "200", description = "Usuarios listados correctamente."),
        @ApiResponse(responseCode = "204", description = "No se encontraron usuarios en la base de datos."),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor al listar los usuarios.")
    }
    )
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        try {
            if (userService.listarUsuarios().isEmpty()) {
                return new ResponseEntity<>(userService.listarUsuarios(), HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(userService.listarUsuarios(), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    @Operation(summary = "Agregar un nuevo usuario.", description = "Agrega un nuevo usuario a la base de datos con todos los datos solicitados.")
    public ResponseEntity<Usuario> postUsuario(@RequestBody Map<String, Object> payload) {
        try {
            Usuario nuevoUsuario = new Usuario();

            nuevoUsuario.setNombreUsuario((String) payload.get("nombreUsuario"));
            nuevoUsuario.setApellidoUsuario((String) payload.get("apellidoUsuario"));
            nuevoUsuario.setCorreoUsuario((String) payload.get("correoUsuario"));
            nuevoUsuario.setPasswrd((String) payload.get("passwrd"));
            Integer idTipoUsuario = (Integer) payload.get("idTipoUsuario");

            Usuario creado = userService.agregarUsuario(nuevoUsuario, idTipoUsuario);

            return new ResponseEntity<>(creado, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un usuario.", description = "Obtener un usuario de la base de datos segun su ID.")
    public ResponseEntity<Usuario> buscarUsrId(@PathVariable Integer id) {
        try {

            if (userService.buscarPorID(id) == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(userService.buscarPorID(id), HttpStatus.OK);
            }
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un usuario.", description = "Actualizar un usuario de la base de datos segun su ID.")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Integer id,
            @RequestBody Map<String, Object> payload) {
        try {
            Usuario usuarioActualizar = new Usuario();
            usuarioActualizar.setIdUsuario(id);
            usuarioActualizar.setNombreUsuario((String) payload.get("nombreUsuario"));
            usuarioActualizar.setApellidoUsuario((String) payload.get("apellidoUsuario"));
            usuarioActualizar.setCorreoUsuario((String) payload.get("correoUsuario"));
            usuarioActualizar.setPasswrd((String) payload.get("passwrd"));
            Integer idTipoUsuario = (Integer) payload.get("idTipoUsuario");

            Usuario actualizado = userService.actualizarUsr(usuarioActualizar, idTipoUsuario);
            return new ResponseEntity<>(actualizado, HttpStatus.OK);

        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un Usuario.", description = "ELiminar un Usuario de la base de datos segun su ID.")
    public ResponseEntity<Void> eliminiarUsrID(@PathVariable Integer id) {

        try {
            userService.eliminiarUsrID(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

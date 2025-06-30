package com.eduDB.EduTech_DB.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.eduDB.EduTech_DB.Model.Usuario;
import com.eduDB.EduTech_DB.Service.UserService;

import java.util.List;
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
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuarios listados correctamente.", 
                content = @Content(mediaType = "application/json", 
                array = @ArraySchema(schema = @Schema(implementation = Usuario.class)),
                    examples = @ExampleObject(name = "Ejemplo lista de usuarios",
                    value = "[{\"idUsuario\": 1, \"passwrd\": \"Contraseña123\", \"nombreUsuario\": \"Juan\", \"apellidoUsuario\": \"Perez\", \"correoUsuario\": \"juan.perez@gmail.com\", \"tipoUsuario\": {\"idTipoUsuario\": 1, \"descripcionUsuario\": \"Administrador\"}}, {\"idUsuario\": 2, \"passwrd\": \"KAqet53_/#\", \"nombreUsuario\": \"Maria\", \"apellidoUsuario\": \"Lopez\", \"correoUsuario\": \"maria.lopez@gmail.com\", \"tipoUsuario\": {\"idTipoUsuario\": 3, \"descripcionUsuario\": \"Estudiante\"}}]"))),
            @ApiResponse(responseCode = "204", description = "No se encontraron usuarios en la base de datos.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor al listar los usuarios.", content = @Content)
    })
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado correctamente.", 
                content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = Usuario.class),
                    examples = @ExampleObject(name = "Ejemplo crear usuario",
                    value = "{\"passwrd\": \"Contraseña123\", \"nombreUsuario\": \"Juan\", \"apellidoUsuario\": \"Perez\", \"email\": \"juan.perez@gmail.com\", \"tipoUsuario\": {\"idTipoUsuario\": 1}}"))),
            @ApiResponse(responseCode = "400", description = "Error al crear el usuario, datos incorrectos.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Tipo de usuario no encontrado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor al crear el usuario.", content = @Content)

    })
    public ResponseEntity<Usuario> postUsuario(@RequestBody Usuario nuevoUsuario) {
        try {
            userService.agregarUsuario(nuevoUsuario);
            Usuario creadoCompleto = userService.buscarPorID(nuevoUsuario.getIdUsuario());
            return new ResponseEntity<>(creadoCompleto, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un usuario.", description = "Obtener un usuario de la base de datos segun su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado correctamente.", 
                content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = Usuario.class),
                    examples = @ExampleObject(name = "Ejemplo de Usuario",
                    value = "{\"idUsuario\": 2, \"passwrd\": \"Contraseña123\", \"nombreUsuario\": \"Eufrecio\", \"apellidoUsuario\": \"Gomez\", \"email\": \"Egomez@gmail.com\", \"tipoUsuario\": {\"idTipoUsuario\": 3, \"descripcionUsuario\": \"Alumno\"}}"))),
            @ApiResponse(responseCode = "404", description = "El Usuario no existe en la base de datos o el ID es incorrecto.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor al buscar el usuario.", content = @Content)
    })
    public ResponseEntity<Usuario> buscarUsrId(
            @Parameter(description = "ID del usuario a buscar", required = true, example = "1") @PathVariable Integer id) {
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente.", 
                content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = Usuario.class),
                    examples = @ExampleObject(name = "Ejemplo actualizar usuario",
                    value = "{\"idUsuario\": 2, \"passwrd\": \"EGomez999./\", \"nombreUsuario\": \"Eufrecio\", \"apellidoUsuario\": \"Gomez\", \"email\": \"Egomez@gmail.com\", \"tipoUsuario\": {\"idTipoUsuario\": 3, \"descripcionUsuario\": \"Alumno\"}}"))),
            @ApiResponse(responseCode = "400", description = "Error, datos incorrectos para actualizar el usuario.", content = @Content),
            @ApiResponse(responseCode = "404", description = "El usuario no existe en la base de datos o el ID es incorrecto.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor al actualizar el usuario.", content = @Content)
    })
    public ResponseEntity<Usuario> actualizarUsuario(
            @PathVariable Integer id,
            @RequestBody Usuario usuarioActualizar) {
        try {
            usuarioActualizar.setIdUsuario(id);
            Usuario actualizado = userService.actualizarUsr(usuarioActualizar);
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un Usuario.", description = "ELiminar un Usuario de la base de datos segun su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuario eliminado correctamente de la base de datos.", content = @Content),
            @ApiResponse(responseCode = "404", description = "El usuario no existe en la base de datos o el ID es incorrecto.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor al eliminar el usuario.", content = @Content)
    })
    public ResponseEntity<Void> eliminiarUsrID(
            @Parameter(description = "ID del usuario a eliminar", required = true, example = "1") @PathVariable Integer id) {

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

package com.eduDB.EduTech_DB.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.eduDB.EduTech_DB.Model.TipoUsuario;
import com.eduDB.EduTech_DB.Service.TipoUsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1/TiposUsuarios")
@Tag(name = "Tipos de Usuarios", description = "Operaciones relacionadas con los tipos de usuarios")

public class TipoUsuarioController {
    @Autowired
    TipoUsuarioService tipoUsuarioService;

    @GetMapping("")
    @Operation(summary = "Obtener todos los tipos de usuarios.", description = "Obtiene una lista con todos los tipos de usuarios registrados en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipos de usuarios listados correctamente.", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TipoUsuario.class)))),
            @ApiResponse(responseCode = "204", description = "No se encontraron tipos de usuarios en la base de datos.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor al listar los tipos de usuarios.", content = @Content)
    })
    public ResponseEntity<List<TipoUsuario>> listarTiposUsuarios() {
        try {
            if (tipoUsuarioService.listarTiposUsuario().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(tipoUsuarioService.listarTiposUsuario(), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}")
    @Operation(summary = "Obtener un tipo de usuario según su ID.", description = "Obtiene un tipo de usuario específico según su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de usuario encontrado correctamente.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoUsuario.class))),
            @ApiResponse(responseCode = "404", description = "Tipo de usuario no existe o el ID es incorrecto.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor al buscar el tipo de usuario.", content = @Content)
    })
    public ResponseEntity<TipoUsuario> obtenerTipoUsuarioId(
            @Parameter(description = "ID del tipo de usuario a buscar", required = true, example = "1") @PathVariable Integer id) {
        try {
            if (tipoUsuarioService.buscarTipoUsuarioId(id) == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(tipoUsuarioService.buscarTipoUsuarioId(id), HttpStatus.OK);
            }
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    @Operation(summary = "Agregar un nuevo tipo de usuario.", description = "Agrega un nuevo tipo de usuario a la base de datos con la descripción del tipo de usuario.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tipo de usuario creado correctamente.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoUsuario.class))),
            @ApiResponse(responseCode = "400", description = "Error al crear el tipo de usuario, datos incorrectos.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor al crear el tipo de usuario.", content = @Content)
    })
    public ResponseEntity<TipoUsuario> agregTipoUsuario(@RequestBody TipoUsuario tipoUsuario) {
        try {
            if (tipoUsuario.getDescripcionUsuario() == null || tipoUsuario.getDescripcionUsuario().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            TipoUsuario nuevoTipoUsuario = tipoUsuarioService.agregartTipoUsuario(tipoUsuario);
            return new ResponseEntity<>(nuevoTipoUsuario, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un tipo de usuario según su ID.", description = "Actualiza un tipo de usuario específico según su ID en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de usuario actualizado correctamente.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoUsuario.class))),
            @ApiResponse(responseCode = "404", description = "Tipo de usuario no encontrado o el ID es incorrecto.",content = @Content),
            @ApiResponse(responseCode = "400", description = "Error al actualizar el tipo de usuario, datos incorrectos.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor al actualizar el tipo de usuario.", content = @Content)
    })
    public ResponseEntity<TipoUsuario> actualizarTipoUsuario(
            @Parameter(description = "ID del tipo de usuario a actualizar", required = true, example = "1") @PathVariable Integer id,
            @RequestBody TipoUsuario tipoUsuario) {
        try {
            if (tipoUsuario.getDescripcionUsuario().isEmpty() || tipoUsuario.getDescripcionUsuario() == null
                    || id == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            tipoUsuario.setIdTipoUsuario(id);
            TipoUsuario tipoUsuarioActualizado = tipoUsuarioService.actualizarTipoUsuario(tipoUsuario);

            return new ResponseEntity<>(tipoUsuarioActualizado, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un tipo de usuario según su ID.", description = "Elimina un tipo de usuario según su ID de la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tipo de usuario eliminado correctamente.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Tipo de usuario no encontrado o el ID es incorrecto.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor al eliminar el tipo de usuario.", content = @Content)
    })
    public ResponseEntity<Void> eliminarTipoUsuarioId(
            @Parameter(description = "ID del tipo de usuario a eliminar", example = "1") @PathVariable Integer id) {
        try {
            tipoUsuarioService.eliminarTiposUsuarioId(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

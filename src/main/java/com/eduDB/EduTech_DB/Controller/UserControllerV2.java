package com.eduDB.EduTech_DB.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eduDB.EduTech_DB.Model.Usuario;
import com.eduDB.EduTech_DB.Service.UserService;
import com.eduDB.EduTech_DB.assemblers.UsuarioModelAssembler;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/usuarios")
public class UserControllerV2 {

    @Autowired
    private UserService userService;

    @Autowired
    private UsuarioModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Usuario>> obtenerUsuarios() {
        List<EntityModel<Usuario>> usuarios = userService.listarUsuarios().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(usuarios,
                linkTo(methodOn(UserControllerV2.class).obtenerUsuarios()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Usuario> obtenerUsuarioId(@PathVariable Integer id) {
        Usuario usuario = userService.buscarPorID(id);
        return assembler.toModel(usuario);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Usuario>> crearUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = userService.agregarUsuario(usuario);
        return ResponseEntity
                .created(linkTo(methodOn(UserControllerV2.class).obtenerUsuarioId(nuevoUsuario.getIdUsuario())).toUri())
                .body(assembler.toModel(nuevoUsuario));
    }

    @PutMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Usuario>> actualizarUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioActualizado = userService.agregarUsuario(usuario);
        return ResponseEntity
                .ok(assembler.toModel(usuarioActualizado));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> eliminarUsuario(@PathVariable Integer id) {
        userService.eliminiarUsrID(id);
        return ResponseEntity.noContent().build();
    }
}

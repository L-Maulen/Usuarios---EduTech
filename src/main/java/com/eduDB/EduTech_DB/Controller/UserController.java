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

@RestController
@RequestMapping("/api/v1/usuarios")

public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
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

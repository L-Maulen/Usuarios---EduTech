package com.eduDB.EduTech_DB.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.eduDB.EduTech_DB.Model.TipoUsuario;
import com.eduDB.EduTech_DB.Service.TipoUsuarioService;

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

public class TipoUsuarioController {
    @Autowired TipoUsuarioService tipoUsuarioService;

    @GetMapping("")
    public ResponseEntity <List<TipoUsuario>> listarTiposUsuarios(){
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
    public ResponseEntity<TipoUsuario> obtenerTipoUsuarioId(@PathVariable Integer id){
        try {
            if (tipoUsuarioService.buscarTipoUsuarioId(id) == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(tipoUsuarioService.buscarTipoUsuarioId(id), HttpStatus.OK);
            }
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    @PostMapping()
    public ResponseEntity <TipoUsuario> agregTipoUsuario(@RequestBody TipoUsuario tipoUsuario){
        try {
            TipoUsuario nuevoTipoUsuario = tipoUsuarioService.agregartTipoUsuario(tipoUsuario);
            return new ResponseEntity<>(nuevoTipoUsuario, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<TipoUsuario> actualizarTipoUsuario(@PathVariable Integer id, @RequestBody TipoUsuario tipoUsuario){
        try {
            tipoUsuario.setIdTipoUsuario(id);
            TipoUsuario tipoUsuarioActualizado = tipoUsuarioService.actualizarTipoUsuario(tipoUsuario);
            
            return new ResponseEntity<>(tipoUsuarioActualizado, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTipoUsuarioId(@PathVariable Integer id){
        try {
            tipoUsuarioService.eliminarTiposUsuarioId(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

}

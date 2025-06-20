package com.eduDB.EduTech_DB.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduDB.EduTech_DB.Model.TipoUsuario;
import com.eduDB.EduTech_DB.Repository.TipoUsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TipoUsuarioService {

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    public List<TipoUsuario> listarTiposUsuario(){
        return tipoUsuarioRepository.findAll();
    }

    public TipoUsuario agregartTipoUsuario(TipoUsuario newtipoUsuario){
        return tipoUsuarioRepository.save(newtipoUsuario);
    }

    public TipoUsuario buscarTipoUsuarioId(int id){
        try {
            return tipoUsuarioRepository.findById(id).get();
        } catch (Exception e) {
            return null;
        }
    }

    public TipoUsuario actualizarTipoUsuario(TipoUsuario newTipoUsuario){
        if (tipoUsuarioRepository.existsById(newTipoUsuario.getIdTipoUsuario())) {
            return tipoUsuarioRepository.save(newTipoUsuario);
        } else {
            throw new NoSuchElementException("Tipo de usuario no encontrado con ID: " + newTipoUsuario.getIdTipoUsuario());
        }
    }

    public void eliminarTiposUsuarioId (int id){
            tipoUsuarioRepository.deleteById(id);
    }


}

package com.eduDB.EduTech_DB.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduDB.EduTech_DB.Model.Usuario;
import com.eduDB.EduTech_DB.Repository.TipoUsuarioRepository;
import com.eduDB.EduTech_DB.Repository.UserRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class UserService {

    @Autowired
    private UserRepository repositorioUsuario;

    @Autowired
    TipoUsuarioRepository repositorioTipoUsuario;

    public List<Usuario> listarUsuarios() {
        return repositorioUsuario.findAll();
    }

    public Usuario buscarPorID(Integer usrId) {
        try {
            return repositorioUsuario.findById(usrId).get();
        } catch (Exception e) {
            return null;
        }

    }

    public Usuario agregarUsuario(Usuario usr) {
        try {
            return repositorioUsuario.save(usr);
        } catch (Exception e) {
            return null;
        }
    }

    public void eliminiarUsrID(Integer id) {
            repositorioUsuario.deleteById(id);
    }

    public Usuario actualizarUsr(Usuario usr) {
        if (repositorioUsuario.existsById(usr.getIdUsuario())) {
            return repositorioUsuario.save(usr);
        } else {
            return null;
        }
    }

}
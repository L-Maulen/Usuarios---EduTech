package com.eduDB.EduTech_DB.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduDB.EduTech_DB.Model.TipoUsuario;
import com.eduDB.EduTech_DB.Model.Usuario;
import com.eduDB.EduTech_DB.Repository.TipoUsuarioRepository;
import com.eduDB.EduTech_DB.Repository.UserRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class UserService {

    @Autowired
    private UserRepository repositorioUsuario;

    @Autowired TipoUsuarioRepository repositorioTipoUsuario;

    
    public List<Usuario> listarUsuarios(){
        return repositorioUsuario.findAll();
    }

    public Usuario buscarPorID(Integer usrId){
        if (repositorioUsuario.existsById(usrId)) {
            return repositorioUsuario.findById(usrId).get();
        } else {
            return null;
        }
    }

    public Usuario agregarUsuario(Usuario usr, Integer IdtipoUsuario){
        try {
            TipoUsuario tipoUsuario = repositorioTipoUsuario.findById(IdtipoUsuario).get();
            usr.setTipoUsuario(tipoUsuario);
            return repositorioUsuario.save(usr);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean eliminiarUsrID(Integer id){
        if (repositorioUsuario.existsById(id)) {
            try {
                repositorioUsuario.deleteById(id);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
            
        }
    }


    public Usuario actualizarUsr(Usuario usr, int idTipoUsuario){
        if (repositorioUsuario.existsById(usr.getIdUsuario())) {

            TipoUsuario tipoUsuario = repositorioTipoUsuario.findById(idTipoUsuario).get();

            Usuario usrCambiar = repositorioUsuario.findById(usr.getIdUsuario()).get();

            usrCambiar.setNombreUsuario(usr.getNombreUsuario());
            usrCambiar.setApellidoUsuario(usr.getApellidoUsuario());
            usrCambiar.setCorreoUsuario(usr.getCorreoUsuario());
            usrCambiar.setPasswrd(usr.getPasswrd());
            usrCambiar.setTipoUsuario(tipoUsuario);

            return repositorioUsuario.save(usrCambiar);
        } else {
            return null;
        }
    }

}
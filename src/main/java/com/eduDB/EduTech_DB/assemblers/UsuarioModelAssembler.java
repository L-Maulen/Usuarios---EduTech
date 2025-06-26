package com.eduDB.EduTech_DB.assemblers;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.eduDB.EduTech_DB.Controller.UserControllerV2;
import com.eduDB.EduTech_DB.Model.Usuario;



@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>> {

    @Override
    public EntityModel<Usuario> toModel(Usuario usuario) {
        return EntityModel.of(usuario,
                linkTo(methodOn(UserControllerV2.class).obtenerUsuarioId(usuario.getIdUsuario())).withSelfRel(),
                linkTo(methodOn(UserControllerV2.class).obtenerUsuarios()).withRel("usuarios"));
    }
}


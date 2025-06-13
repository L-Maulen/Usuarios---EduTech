package com.eduDB.EduTech_DB;

import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.eduDB.EduTech_DB.Model.TipoUsuario;
import com.eduDB.EduTech_DB.Model.Usuario;
import com.eduDB.EduTech_DB.Repository.TipoUsuarioRepository;
import com.eduDB.EduTech_DB.Repository.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        // Poblar tabla TipoUsuario
        String[] tipos = { "Administrador", "Profesor", "Estudiante" };
        for (String t : tipos) {
            TipoUsuario tipoUsuario = new TipoUsuario();
            tipoUsuario.setDescripcionUsuario(t);
            tipoUsuarioRepository.save(tipoUsuario);
        }

        // Poblar tabla Usuario
        for (int i = 0; i < 100; i++) {
            Usuario usuario = new com.eduDB.EduTech_DB.Model.Usuario();
            usuario.setNombreUsuario(faker.name().firstName());
            usuario.setApellidoUsuario(faker.name().lastName());
            usuario.setCorreoUsuario(faker.internet().emailAddress());
            usuario.setPasswrd(faker.lorem().characters(10, 16, true, true));
            usuario.setTipoUsuario(tipoUsuarioRepository.findById(random.nextInt(3) + 1)
                    .orElseThrow(() -> new RuntimeException("TipoUsuario not found")));
            userRepository.save(usuario);
        }
    }

}
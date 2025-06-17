package com.eduDB.EduTech_DB.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TipoUsuario")

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "Entidad que representa el tipo de usuario en la base de datos.")
public class TipoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del tipo de usuario.", example = "1")
    private int idTipoUsuario;
    
    @Column(unique = true, nullable = false, name="")
    @Schema(description = "Descripción del tipo de usuario.", example = "Profesor")
    private String descripcionUsuario;

    @OneToMany(mappedBy = "tipoUsuario")
    @JsonIgnore
    private List<Usuario> usuarios;
}

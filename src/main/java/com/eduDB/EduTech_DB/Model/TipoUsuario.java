package com.eduDB.EduTech_DB.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class TipoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Configura la generación automática del valor de la clave primaria por la base de datos.
    private int idTipoUsuario;
    
    @Column(unique = true, nullable = false, name="")
    private String descripcionUsuario;

    @OneToMany(mappedBy = "tipoUsuario")
    @JsonIgnore
    private List<Usuario> usuarios;
}

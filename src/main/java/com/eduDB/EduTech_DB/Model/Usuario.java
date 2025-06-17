package com.eduDB.EduTech_DB.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="Usuario")

@Data
@AllArgsConstructor 
@NoArgsConstructor 

@Schema(description = "Entidad que representa a un Usuario en la base de datos.")
public class Usuario {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del Usuario.", example = "1")
    private Integer idUsuario;
    
    @Column(unique = false, length = 40, nullable = false)
    @Schema(description = "Constraseña del Usuario.", example = "contraseña123")
    private String passwrd;

    @Column(unique=false, length= 60, nullable=false)
    @Schema(description = "Nombre real del Usuario.", example = "Esteban")
    private String nombreUsuario;

    @Column(unique=false, length= 60, nullable=false)
    @Schema(description = "Apellido real del Usuario.", example = "Gonzalez")
    private String apellidoUsuario;
    
    @Column(unique = true, length = 150, nullable = false)
    @Schema(description = "Correo electronico del Usuario.", example = "eGonzales@gmail.com")
    private String correoUsuario;
    


    @ManyToOne //Indica que muchos usuarios pueden tener el mismo tipo de usuario
    @JoinColumn(name = "id_tipo_usuario",nullable = false) //Especifica la columna en la tabla Usuario que se utilizará como clave foránea para referenciar la tabla
    private TipoUsuario tipoUsuario;



}

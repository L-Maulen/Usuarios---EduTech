package com.eduDB.EduTech_DB.Model;

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

@Data // Me crea automaticamente los metodos (getters y setter) constructor, toString y otras mas
@AllArgsConstructor //
@NoArgsConstructor //
public class Usuario {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;
    
    @Column(unique = false, length = 40, nullable = false)
    private String passwrd;

    @Column(unique=false, length= 60, nullable=false)
    private String nombreUsuario;

    @Column(unique=false, length= 60, nullable=false)
    private String apellidoUsuario;
    
    @Column(unique = true, length = 150, nullable = false)
    private String correoUsuario;
    


    @ManyToOne //Indica que muchos usuarios pueden tener el mismo tipo de usuario
    @JoinColumn(name = "id_tipo_usuario",nullable = false) //Especifica la columna en la tabla Usuario que se utilizará como clave foránea para referenciar la tabla
    private TipoUsuario tipoUsuario;



}

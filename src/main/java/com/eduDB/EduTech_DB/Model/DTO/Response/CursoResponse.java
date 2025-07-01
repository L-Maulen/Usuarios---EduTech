package com.eduDB.EduTech_DB.Model.DTO.Response;

import com.eduDB.EduTech_DB.Model.DTO.Response.CategoriaResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CursoResponse {
    private long idCurso;
    private String nombreCurso;
    private String descripcion;
    private String nivel; // Usa String si no tienes el enum
    private int precio;
    private int duracionHoras;
    private String idioma;
    private boolean certificadoDisponible;
    private long idInstructor;
    private CategoriaResponse categoria;


}

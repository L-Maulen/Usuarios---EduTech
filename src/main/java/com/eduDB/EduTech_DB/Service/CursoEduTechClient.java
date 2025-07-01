package com.eduDB.EduTech_DB.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value; // <-- Importa esta
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.eduDB.EduTech_DB.Model.DTO.Response.CursoResponse;

@Service
public class CursoEduTechClient {

    @Value("${cursoEdutech.api.url}")
    private String urlApiCurso;

    @Autowired
    private RestTemplate restTemplate;

    public CursoResponse[] obtenerTodosLosCursos() {
        System.out.println("Llamando a: " + urlApiCurso);
        return restTemplate.getForObject(urlApiCurso, CursoResponse[].class);
    }

    public CursoResponse obtenerCursoPorId(Long id) {
        String url = urlApiCurso + "id/" + id;
        return restTemplate.getForObject(url, CursoResponse.class);
    }

    public CursoResponse[] obtenerCursosPorUsuarioId(Long usuarioId) {
        String url = urlApiCurso + "usuario/" + usuarioId;
        return restTemplate.getForObject(url, CursoResponse[].class);
    }

}

package com.eduDB.EduTech_DB.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduDB.EduTech_DB.Model.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Integer>{
}

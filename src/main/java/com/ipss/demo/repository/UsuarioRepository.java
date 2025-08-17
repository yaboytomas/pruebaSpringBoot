package com.ipss.demo.repository;

import org.springframework.stereotype.*;
import com.ipss.demo.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNombre(String nombre);
    Optional<Usuario> findByNombreAndContrasena(String nombre, String contrasena);



}




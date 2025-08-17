package com.ipss.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipss.demo.model.Usuario;
import com.ipss.demo.repository.UsuarioRepository;

import java.util.Optional;
import java.util.List;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void inicializarUsuarios() {

        if (usuarioRepository.findByNombre("admin").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setNombre("admin");
            admin.setContrasena("admin");
            admin.setRol("Administrator");
            usuarioRepository.save(admin);
        }

        if (usuarioRepository.findByNombre("cliente").isEmpty()) {
            Usuario cliente = new Usuario();
            cliente.setNombre("cliente");
            cliente.setContrasena("cliente");
            cliente.setRol("Cliente");
            usuarioRepository.save(cliente);}
        }

    // Create User
    public Optional<Usuario> registrarUsuario(Usuario nuevoUsuario) {
        if (usuarioRepository.findByNombre(nuevoUsuario.getNombre()).isPresent()) {
            return Optional.empty();
        }
        nuevoUsuario.setRol("Cliente");
        return Optional.of(usuarioRepository.save(nuevoUsuario));
    }

    // Get User 
    public Optional<Usuario> buscarUsuarioPorNombreYContrasena(String nombre, String contrasena) {
        return usuarioRepository.findByNombreAndContrasena(nombre, contrasena);
    }

    // Get All Users
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();

    }
}
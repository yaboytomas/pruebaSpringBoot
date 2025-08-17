package com.ipss.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ipss.demo.repository.UsuarioRepository;
import com.ipss.demo.model.Usuario;

@Component
public class DataLoader implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;

    public DataLoader(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        cargarDatosIniciales();
    }

    private void cargarDatosIniciales() {
        Usuario admin = new Usuario();
        admin.setNombre("admin");
        admin.setContrasena("admin");
        admin.setRol("Administrador");
        usuarioRepository.save(admin);

        Usuario cliente = new Usuario();
        cliente.setNombre("cliente");
        cliente.setContrasena("cliente");
        cliente.setRol("Cliente");
        usuarioRepository.save(cliente);
    }
}

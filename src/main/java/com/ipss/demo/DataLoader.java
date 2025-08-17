package com.ipss.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ipss.demo.repository.UsuarioRepository;
import com.ipss.demo.repository.MesaRepository;
import com.ipss.demo.model.Usuario;
import com.ipss.demo.model.Mesa;

@Component
public class DataLoader implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final MesaRepository mesaRepository;

    public DataLoader(UsuarioRepository usuarioRepository, MesaRepository mesaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.mesaRepository = mesaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        cargarDatosIniciales();
    }

    private void cargarDatosIniciales() {
        // Only create admin user if it doesn't exist
        if (usuarioRepository.findByNombre("admin").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setNombre("admin");
            admin.setContrasena("admin");
            admin.setRol("Administrador");
            usuarioRepository.save(admin);
            System.out.println("Usuario administrador creado: admin/admin");
        }

        // Only create client user if it doesn't exist
        if (usuarioRepository.findByNombre("cliente").isEmpty()) {
            Usuario cliente = new Usuario();
            cliente.setNombre("cliente");
            cliente.setContrasena("cliente");
            cliente.setRol("Cliente");
            usuarioRepository.save(cliente);
            System.out.println("Usuario cliente creado: cliente/cliente");
        }
        
        // Create sample tables if none exist
        if (mesaRepository.count() == 0) {
            // Create 8 tables with different capacities
            Mesa mesa1 = new Mesa(1, 2);
            Mesa mesa2 = new Mesa(2, 4);
            Mesa mesa3 = new Mesa(3, 6);
            Mesa mesa4 = new Mesa(4, 2);
            Mesa mesa5 = new Mesa(5, 4);
            Mesa mesa6 = new Mesa(6, 8);
            Mesa mesa7 = new Mesa(7, 4);
            Mesa mesa8 = new Mesa(8, 6);
            
            mesaRepository.save(mesa1);
            mesaRepository.save(mesa2);
            mesaRepository.save(mesa3);
            mesaRepository.save(mesa4);
            mesaRepository.save(mesa5);
            mesaRepository.save(mesa6);
            mesaRepository.save(mesa7);
            mesaRepository.save(mesa8);
            
            System.out.println("8 mesas creadas con capacidades variadas");
        }
    }
}

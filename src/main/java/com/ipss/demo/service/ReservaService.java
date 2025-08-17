package com.ipss.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ipss.demo.model.*;
import com.ipss.demo.repository.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {
    
    @Autowired
    private ReservaRepository reservaRepository;
    
    @Autowired
    private MesaRepository mesaRepository;
    
    // Get all available tables
    public List<Mesa> obtenerMesasDisponibles() {
        return mesaRepository.findByDisponible(true);
    }
    
    // Get all tables
    public List<Mesa> obtenerTodasLasMesas() {
        return mesaRepository.findAll();
    }
    
    // Create reservation
    public Optional<Reserva> crearReserva(Usuario usuario, Long mesaId, LocalDate fecha, LocalTime hora, int numeroPersonas) {
        Optional<Mesa> mesaOpt = mesaRepository.findById(mesaId);
        if (mesaOpt.isPresent()) {
            Mesa mesa = mesaOpt.get();
            
            Reserva reserva = new Reserva();
            reserva.setUsuario(usuario);
            reserva.setMesa(mesa);
            reserva.setFecha(fecha);
            reserva.setHora(hora);
            reserva.setNumeroPersonas(numeroPersonas);
            reserva.setEstado("ACTIVA");
            
            return Optional.of(reservaRepository.save(reserva));
        }
        return Optional.empty();
    }
    
    // Get user reservations
    public List<Reserva> obtenerReservasUsuario(Usuario usuario) {
        return reservaRepository.findByUsuario(usuario);
    }
    
    // Get all reservations
    public List<Reserva> obtenerTodasLasReservas() {
        return reservaRepository.findAll();
    }
    
    // Cancel reservation
    public boolean cancelarReserva(Long reservaId) {
        Optional<Reserva> reservaOpt = reservaRepository.findById(reservaId);
        if (reservaOpt.isPresent()) {
            Reserva reserva = reservaOpt.get();
            reserva.setEstado("CANCELADA");
            reservaRepository.save(reserva);
            return true;
        }
        return false;
    }
}

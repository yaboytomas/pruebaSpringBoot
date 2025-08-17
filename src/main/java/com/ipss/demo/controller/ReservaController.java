package com.ipss.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import com.ipss.demo.model.Usuario;
import com.ipss.demo.model.Reserva;
import com.ipss.demo.service.ReservaService;

import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    // Show reservation form
    @GetMapping("/nueva")
    public String mostrarFormularioReserva(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null || !"Cliente".equals(usuario.getRol())) {
            return "redirect:/login";
        }
        
        model.addAttribute("mesas", reservaService.obtenerMesasDisponibles());
        return "cliente/nueva_reserva";
    }

    // Process new reservation
    @PostMapping("/crear")
    public String crearReserva(@RequestParam Long mesaId,
                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha,
                              @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime hora,
                              @RequestParam int numeroPersonas,
                              HttpSession session,
                              Model model) {
        
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null || !"Cliente".equals(usuario.getRol())) {
            return "redirect:/login";
        }

        Optional<Reserva> reserva = reservaService.crearReserva(usuario, mesaId, fecha, hora, numeroPersonas);
        
        if (reserva.isPresent()) {
            model.addAttribute("mensaje", "Reserva creada exitosamente");
            return "redirect:/reservas/mis-reservas";
        } else {
            model.addAttribute("error", "Error al crear la reserva");
            model.addAttribute("mesas", reservaService.obtenerMesasDisponibles());
            return "cliente/nueva_reserva";
        }
    }

    // Show user reservations
    @GetMapping("/mis-reservas")
    public String mostrarMisReservas(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null || !"Cliente".equals(usuario.getRol())) {
            return "redirect:/login";
        }
        
        model.addAttribute("reservas", reservaService.obtenerReservasUsuario(usuario));
        return "cliente/mis_reservas";
    }

    // Cancel reservation
    @PostMapping("/cancelar/{id}")
    public String cancelarReserva(@PathVariable Long id, HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null || !"Cliente".equals(usuario.getRol())) {
            return "redirect:/login";
        }

        if (reservaService.cancelarReserva(id)) {
            model.addAttribute("mensaje", "Reserva cancelada exitosamente");
        } else {
            model.addAttribute("error", "Error al cancelar la reserva");
        }
        
        return "redirect:/reservas/mis-reservas";
    }
}

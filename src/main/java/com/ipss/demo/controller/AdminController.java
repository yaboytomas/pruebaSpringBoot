package com.ipss.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ipss.demo.model.Usuario;
import com.ipss.demo.service.UsuarioService;
import com.ipss.demo.service.ReservaService;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private ReservaService reservaService;

    @GetMapping("/admin/home")
    public String homeAdmin(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null || !"Administrador".equals(usuario.getRol())) {
            return "redirect:/login";
        }
        model.addAttribute("usuario", usuario);
        return "admin/home";
    }

    @GetMapping("/admin/usuarios")
    public String usuariosAdmin(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null || !"Administrador".equals(usuario.getRol())) {
            return "redirect:/login";
        }
        model.addAttribute("usuarios", usuarioService.obtenerTodosLosUsuarios());
        return "admin/lista_usuarios";
    }
    
    @GetMapping("/admin/reservas")
    public String reservasAdmin(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null || !"Administrador".equals(usuario.getRol())) {
            return "redirect:/login";
        }
        model.addAttribute("reservas", reservaService.obtenerTodasLasReservas());
        return "admin/lista_reservas";
    }
    
    @GetMapping("/admin/mesas")
    public String mesasAdmin(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null || !"Administrador".equals(usuario.getRol())) {
            return "redirect:/login";
        }
        model.addAttribute("mesas", reservaService.obtenerTodasLasMesas());
        return "admin/lista_mesas";
    }

}
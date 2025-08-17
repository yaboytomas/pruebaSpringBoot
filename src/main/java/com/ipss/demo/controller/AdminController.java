package com.ipss.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ipss.demo.model.Usuario;
import com.ipss.demo.service.UsuarioService;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    private UsuarioService usuarioService;

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
        return "admin/listar_usuarios";
    }

}
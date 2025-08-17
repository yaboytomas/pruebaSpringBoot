package com.ipss.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ipss.demo.model.Usuario;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;

@Controller
public class ClienteController {

    @GetMapping("/cliente/home")
    public String homeCliente(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null || !"Cliente".equals(usuario.getRol())) {
            return "redirect:/login";
        }
        return "cliente/home";
    }

}

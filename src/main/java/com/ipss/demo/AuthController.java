package com.ipss.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ipss.demo.model.Usuario;
import com.ipss.demo.service.UsuarioService;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String index(HttpSession session) {  

        if (session.getAttribute("usuario") != null) {
           // si hay usuario en session 
           Usuario usuario = (Usuario) session.getAttribute("usuario");
           if ("Administrador".equals(usuario.getRol())) {
               return "redirect:/admin/home";
           }else{
            return "redirect:/cliente/home";
           }
        }
        // si no hay usuario en session
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login"; //login.html
    }   

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        Optional<Usuario> usuarioOpt = usuarioService.buscarUsuarioPorNombreYContrasena(username, password);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            session.setAttribute("usuario", usuario);
            if ("Administrador".equals(usuario.getRol())) {
                return "redirect:/admin/home";
            } else {
                return "redirect:/cliente/home";
            }
        } else {
            model.addAttribute("error", "Credenciales inválidas");
            return "login"; //login.html
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registrar"; //register.html
    }

    @PostMapping("/register")
    public String procesarRegistro(@ModelAttribute Usuario usuario, Model model, HttpSession session) {
        Optional<Usuario> nuevoUsuario = usuarioService.registrarUsuario(usuario);
        if (nuevoUsuario.isPresent()) {
            session.setAttribute("usuario", nuevoUsuario.get());
            return "redirect:/cliente/home";
        } else {
            model.addAttribute("error", "Error al registrar usuario");
            return "registrar"; //registrar.html
        }
    }
}
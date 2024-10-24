package com.moxos.uab.presentation.controller.authentication;

import com.moxos.uab.domain.dto.request.authentication.LoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @GetMapping("/")
    public String rootPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
            return "redirect:/index";
        }
        return "redirect:/login";
    }

    @RequestMapping("/login")
    public String loginPage(@ModelAttribute("model") LoginRequest login, Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/index";
        }
        if (login.getError() != null) {
            if (login.getError().equals(1)) {
                model.addAttribute("authenticationError", login.getMessage());
                model.addAttribute("login", login.getApodo());
                return "Login/Login";
            }
            if (login.getError().equals(2)) {
                model.addAttribute("authenticationError", "Captcha incorrecto");
                model.addAttribute("login", login.getApodo());
                return "Login/Login";
            }
            if (login.getError().equals(3)) {
                model.addAttribute("authenticationError", "No existe el usuario");
                return "redirect:/signin";
            }
            if (login.getError().equals(4)) {
                model.addAttribute("authenticationError", "Se encueantra bloqueado temporalmente por multiples intentos incorrecto al iniciar sesion");
                model.addAttribute("login", "");
                return "Login/Login";
            }
            if (login.getError().equals(5)) {
                model.addAttribute("authenticationError", "Codigo de verificacion incorrecto");
                model.addAttribute("login", login.getApodo());
                return "Login/Login";
            }
        }
        model.addAttribute("login", login.getApodo());
        return "Login/Login";
    }
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login";
    }
}

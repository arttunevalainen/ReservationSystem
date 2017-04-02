/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webproject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import webproject.Models.User;

/**
 *
 * @author Arttu
 */

@Controller
public class AdminController {
    
    @GetMapping("/adminsettings")
    public String adminSettings(Model model) {
        
        model.addAttribute("title", "Admin settings");
        model.addAttribute("userName", AuthenticationUtils.getUserDetails().getUsername());
        model.addAttribute("role", AuthenticationUtils.getUserRole());
        
        if(AuthenticationUtils.getUserRole().equals("admin")) {
            return "adminsettings";
        }
        else {
            return "redirect:home";
        }
    }
}

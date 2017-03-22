/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webproject;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Arttu
 */
@Controller
public class SettingsController {
    
    
    @GetMapping("/settings")
    public String settings() {
     
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails details = (UserDetails) auth.getPrincipal();
        
        return "settings";
    }
    
    //Palautetaan tietojen tallentamisen jälkeen home.
    @PostMapping("/settings")
    public String saved() {
        
        return "home";
    }
}

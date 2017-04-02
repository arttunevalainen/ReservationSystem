/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webproject;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import webproject.Models.User;
import webproject.dataaccess.UserRepository;

/**
 *
 * @author Arttu
 */
@Controller
public class SettingsController {
    
    private final UserRepository userRepository;
    private final AuthenticationUtils authenticationUtils;
    
    public SettingsController(UserRepository userRepository, AuthenticationUtils authenticationUtils){
        this.userRepository = userRepository;
        this.authenticationUtils = authenticationUtils;
    }
    
    @GetMapping("/settings")
    public String settings(Model model) {
     
        model.addAttribute("userName", authenticationUtils.getUserDetails().getUsername());
        model.addAttribute("title", "Settings");
        model.addAttribute("role", authenticationUtils.getUserRole());
        
        return "settings";
    }
    
    //Palautetaan tietojen tallentamisen jälkeen home.
    @RequestMapping(value = "/settings", method = RequestMethod.POST)
    public String saved(Model error, HttpServletRequest request) {
        
        //Saadaan käyttäjän salasanat
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        
        //Tarkistetaan samanlaisuus ja eivät tyhjiä.
        if(!password1.equals(password2) || password1.equals("")) {
            error.addAttribute("error", "Error changing password");
            return "redirect:settings";
        }
        else {
            
            //TÄSSÄ KÄYTTÄJÄN ID HAKU KÄYTTÄJÄNIMELLÄ.
            
            int userid = authenticationUtils.getUserId();
            
            userRepository.changePassword(userid, password1);
            
            return "redirect:home";
        }
    }
}

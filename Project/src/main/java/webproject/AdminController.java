/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webproject;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import webproject.Models.Reservable;
import webproject.Models.User;
import webproject.dataaccess.ReservableRepository;
import webproject.dataaccess.UserRepository;

/**
 *
 * @author Arttu
 */

@Controller
public class AdminController {
    
    private final AuthenticationUtils authenticationUtils;
    private final UserRepository userRepository;
    
    public AdminController(AuthenticationUtils authenticationUtils, UserRepository userRepository){
        this.authenticationUtils = authenticationUtils;
        this.userRepository = userRepository;
    }
    
    @GetMapping("/adminsettings")
    public String adminSettings(Model model) {
        
        model.addAttribute("title", "Admin settings");
        model.addAttribute("userName", authenticationUtils.getUserDetails().getUsername());
        model.addAttribute("role", authenticationUtils.getUserRole());
        model.addAttribute("userId", authenticationUtils.getUserId());
        
        List<User> users = userRepository.getAll();
        deleteAdminsFromList(users);
        model.addAttribute("users", users);
        
        if(authenticationUtils.getUserRole().equals("admin")) {
            return "adminsettings";
        }
        else {
            return "redirect:home";
        }
    }
    
    
    @GetMapping("/adminsettings/{id}")
    public String deletingAccount(Model model, @PathVariable String id) {
        
        userRepository.deleteUser(Integer.parseInt(id));
        
        model.addAttribute("accountDeletionSuccess", "Account deleted");
        
        model.addAttribute("userName", authenticationUtils.getUserDetails().getUsername());
        model.addAttribute("role", authenticationUtils.getUserRole());
        
        model.addAttribute("title", "Admin settings");
        
        List<User> users = userRepository.getAll();
        deleteAdminsFromList(users);
        model.addAttribute("users", users);
        
        return "adminsettings";
    }
    
    public void deleteAdminsFromList(List<User> l) {
        for(int i = 0; i < l.size(); i++) {
            if(l.get(i).getRole().equals("admin")) {
                l.remove(i);
                i--;
            }
        }
    }
}

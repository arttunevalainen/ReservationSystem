
package webproject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder


@Controller
public class HomeController extends WebMvcConfigurerAdapter {

    //Ohjataan käyttäjä kotisivulle josta kirjautumatta jättäneet menevät loginiin.
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addRedirectViewController("/", "/home");
    }

    /**
     * The main page
     * @param model
     * @return 
     */

    @RequestMapping("/login")
    public String login() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return "login";
    }

    @RequestMapping("/home")
    public String index(Model model) {
        
        model.addAttribute("userName", "Pekka");
        model.addAttribute("title", "Reservations");
        model.addAttribute("role", "admin");
        model.addAttribute("userId", "3");
        return "home";
    }
    
    //Tänne voisi esim laittaa
        //listan omista varauksista
        //linkit hallintopaneeleihin
        //lista varattavista kohteista, niistä linkit niiden varaussivulle
    
}
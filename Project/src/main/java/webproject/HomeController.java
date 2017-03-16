
package webproject;

import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import static webproject.Application.sessionFactory;
import webproject.Models.User;


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
        Session session = sessionFactory.openSession();
        User us = (User) session.get(User.class, 1);
        model.addAttribute("userName", us.getName());
        model.addAttribute("title", "Reservations");
        model.addAttribute("role", "admin");
        model.addAttribute("userId", us.getId());
        

        /*session.beginTransaction();
        session.save(new User());
        //session.save( new User( 100, "Testi user", "passu", "Matti meikäläinen", "owner" ) );
        session.getTransaction().commit();
        session.close();*/

        return "home";
    }
    
}

package webproject;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import webproject.Models.*;
import webproject.dataaccess.ReservationItemRepository;
import webproject.dataaccess.ReservationRepository;
import webproject.dataaccess.UserRepository;

@Controller
public class HomeController extends WebMvcConfigurerAdapter {
    
    private final UserRepository userRepository;
    private final ReservationItemRepository reservationItemRepository;
    private final ReservationRepository reservationRepository;
    
    public HomeController(UserRepository userRepository,
                            ReservationItemRepository reservationItemRepository,
                            ReservationRepository reservationRepository){
        this.userRepository = userRepository;
        this.reservationItemRepository = reservationItemRepository;
        this.reservationRepository = reservationRepository;
    }

    //Ohjataan käyttäjä kotisivulle josta kirjautumatta jättäneet menevät loginiin.
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addRedirectViewController("/", "/home");
    }

    /**
     * The main page
     * @return 
     */
    @RequestMapping("/login")
    public String login() {

        return "login";
    }

    @RequestMapping("/home")
    public String index(Model model, SecurityContextHolderAwareRequestWrapper request) {

        String role = "";
        if(request.isUserInRole("USER")) {
            role = ("User");
        }
        
        model.addAttribute("userName", request.getUserPrincipal().getName());
        model.addAttribute("title", "Home");
        model.addAttribute("role", role);
        model.addAttribute("userId");
        
        ReservationItem item = reservationItemRepository.get(1);
        model.addAttribute("reservationitem", item.getName());
        model.addAttribute("ownername", item.getOwner().getName());
        
        item.getReservations().stream()
                            .forEach(x -> System.out.println("asd"));
                              //.forEach(s -> System.out.println(s.getReserver().getName()));
        
        Reservation res = reservationRepository.get(1);
        
        model.addAttribute("reservation_reservable", res.getReservationItem().getName());
        model.addAttribute("reservation_user", res.getReserver().getName());
        
        userRepository.save(new User("uusi", "kayttaja", "jee", "user"));
        
        return "home";
    }
    
}
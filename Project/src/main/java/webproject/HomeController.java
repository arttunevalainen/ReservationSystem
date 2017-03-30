
package webproject;

import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import webproject.Models.*;
import webproject.dataaccess.ReservableRepository;
import webproject.dataaccess.ReservationRepository;
import webproject.dataaccess.UserRepository;

@Controller
public class HomeController extends WebMvcConfigurerAdapter {
    
    private final UserRepository userRepository;
    private final ReservableRepository reservationItemRepository;
    private final ReservationRepository reservationRepository;
    
    public HomeController(UserRepository userRepository,
                            ReservableRepository reservationItemRepository,
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
    public String index(Model model) {
        
        //Saadaan kaivettua kirjautunut. Tietokannasta pitäisi saada id sun muut vielä josta tehdä User olio??
        //Tällä .getUsername() ja .getAuthorities() joka Collection oikeuksista.
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails details = (UserDetails) auth.getPrincipal();
        
        //TÄSSÄ KÄYTTÄJÄN ID HAKU KÄYTTÄJÄNIMELLÄ.
        int userid = userRepository.getUserIDByUserName(details.getUsername());
        
        
        User a = userRepository.get(userid);
        a.getReservations().stream()
                           .forEach(s -> System.out.println(s.getReserver().getName()));
        
        
        model.addAttribute("title", "Home");
        model.addAttribute("role", details.getAuthorities());
        model.addAttribute("userId", userid);
        
        Reservable item = reservationItemRepository.get(1);
        model.addAttribute("reservationitem", item.getName());
        model.addAttribute("ownername", item.getOwner().getName());
     
        item.getReservations().stream()
                              .forEach(s -> System.out.println(s.getReserver().getName()));
        
        Reservation res = reservationRepository.get(userid);
        
        model.addAttribute("reservation_reservable", res.getReservationItem().getName());
        model.addAttribute("reservation_user", res.getReserver().getName());
        
        //userRepository.save(new User("uusi", "kayttaja", "jee", "user"));
        
        return "home";
    }
}
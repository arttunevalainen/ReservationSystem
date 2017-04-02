
package webproject;

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
    private final AuthenticationUtils authenticationUtils;
    
    public HomeController(UserRepository userRepository,
                            ReservableRepository reservationItemRepository,
                            ReservationRepository reservationRepository,
                            AuthenticationUtils authenticationUtils){
        this.userRepository = userRepository;
        this.reservationItemRepository = reservationItemRepository;
        this.reservationRepository = reservationRepository;
        this.authenticationUtils = authenticationUtils;
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
        
        //TÄSSÄ KÄYTTÄJÄN ID HAKU KÄYTTÄJÄNIMELLÄ.
        int userid = authenticationUtils.getUserId();
        
        User a = userRepository.get(userid);
        
        model.addAttribute("userName", authenticationUtils.getUserDetails().getUsername());
        model.addAttribute("title", "Home");
        model.addAttribute("userId", userid);
        model.addAttribute("role", authenticationUtils.getUserRole());

        return "home";
    }
}
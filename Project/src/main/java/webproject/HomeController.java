
package webproject;

import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import static webproject.Application.sessionFactory;
import webproject.Models.*;


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

        return "login";
    }

    @RequestMapping("/home")
    public String index(Model model, SecurityContextHolderAwareRequestWrapper request) {
        Session session = sessionFactory.openSession();

        String role = "";
        if(request.isUserInRole("USER")) {
            role = ("User");
        }
        
        model.addAttribute("userName", request.getUserPrincipal().getName());
        model.addAttribute("title", "Reservations");
        model.addAttribute("role", role);
        model.addAttribute("userId");
        ReservationItem item = session.get(ReservationItem.class, 1);
        model.addAttribute("reservationitem", item.getName());
        model.addAttribute("ownername", item.getOwner().getName());
        
        Reservation res = session.get(Reservation.class, 1);
        System.out.println(res);
        
        model.addAttribute("reservation_reservable", res.getReservationItem().getName());
        model.addAttribute("reservation_user", res.getReserver().getName());
        
        /*session.beginTransaction();
        session.save(new User());
        //session.save( new User( 100, "Testi user", "passu", "Matti meikäläinen", "owner" ) );
        session.getTransaction().commit();
        session.close();*/

        return "home";
    }
    
}
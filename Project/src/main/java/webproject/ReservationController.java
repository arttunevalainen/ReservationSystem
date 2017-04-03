
package webproject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import webproject.Models.Reservation;
import webproject.dataaccess.ReservationRepository;

@Controller
public class ReservationController {
    
    private final ReservationRepository reservationRepository;
    private final AuthenticationUtils authenticationUtils;
    
    public ReservationController(ReservationRepository reservationRepository, AuthenticationUtils authenticationUtils){
        this.reservationRepository = reservationRepository;
        this.authenticationUtils = authenticationUtils;
    }
    
    @GetMapping("/reservation/{id}")
    public String reservation(Model model, @PathVariable String id) {
        
        
        
        model.addAttribute("userName", authenticationUtils.getUserDetails().getUsername());
        model.addAttribute("role", authenticationUtils.getUserRole());
        model.addAttribute("title", "Your reservation");
        model.addAttribute("resid", id);
        return "reservation";
    }
    
    @PostMapping("/reservation/{id}")
    public String deleteReservation(Model model, @PathVariable String id) {
        System.out.println(id);
        Reservation res = reservationRepository.get(Integer.parseInt(id));
        
        int userId = authenticationUtils.getUserId();
        if(userId != res.getReserver().getId()){
            return "home";
        }
        
        reservationRepository.delete(res);
        
        model.addAttribute("userName", authenticationUtils.getUserDetails().getUsername());
        model.addAttribute("title", "Home");
        model.addAttribute("userId", userId);
        model.addAttribute("role", authenticationUtils.getUserRole());
        
        return "home";
    }
    
    
}


/*


POISTETAAN TÄÄ JOS TÄNNE EI TUU MITÄÄN TOIMINTAA



*/






package webproject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webproject.dataaccess.ReservationRepository;

@Controller
@RequestMapping("reservation")
public class ReservationController {
    
    private final ReservationRepository reservationRepository;
    private final AuthenticationUtils authenticationUtils;
    
    public ReservationController(ReservationRepository reservationRepository, AuthenticationUtils authenticationUtils){
        this.reservationRepository = reservationRepository;
        this.authenticationUtils = authenticationUtils;
    }
    
    @GetMapping("/reservation")
    public String Reservation(Model model) {
        
        model.addAttribute("userName", authenticationUtils.getUserDetails().getUsername());
        model.addAttribute("role", authenticationUtils.getUserRole());
        
        return "reservation";
    }
    
    @PostMapping("/reservation")
    public String PostReservation() {
        //DELETE
        return "home";
    }
    
    
}
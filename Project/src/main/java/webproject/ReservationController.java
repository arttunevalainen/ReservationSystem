

/*


POISTETAAN TÄÄ JOS TÄNNE EI TUU MITÄÄN TOIMINTAA



*/






package webproject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import webproject.dataaccess.ReservationRepository;

@Controller
@RequestMapping("reservation")
public class ReservationController {
    
    private final ReservationRepository reservationRepository;
    
    public ReservationController(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }
    
    
    
    
}
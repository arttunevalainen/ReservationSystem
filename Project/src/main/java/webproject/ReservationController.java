

/*


POISTETAAN TÄÄ JOS TÄNNE EI TUU MITÄÄN TOIMINTAA



*/






package webproject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import webproject.Models.Reservation;
import webproject.dataaccess.ReservationRepository;

@Controller
@RequestMapping("reservation")
public class ReservationController {
    
    private final ReservationRepository reservationRepository;
    
    public ReservationController(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }
    
    
    
    
}
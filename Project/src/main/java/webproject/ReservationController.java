
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

    //TOIMINNOT
        //UUSI reservation
        //peruminen
        //lisää tietoja varauksen sivulle
    
    private final ReservationRepository reservationRepository;
    
    public ReservationController(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }
    
    /**
     * Reservation page
     * @param model
     * @param id
     * @return 
     */
    @RequestMapping("/{id}")
    public String index(Model model, @PathVariable String id) {
        Reservation res = reservationRepository.get(Integer.parseInt(id));
        model.addAttribute("title", "Reservation");
        model.addAttribute("reservation_title", res.getReservationItem().getName() + " at " + res.getStartTime().toString());
        return "reservation";
    }
}
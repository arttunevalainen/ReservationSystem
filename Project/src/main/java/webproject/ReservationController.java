
package webproject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReservationController {

    /**
     * Reservation page
     * @param model
     * @return 
     */
    @RequestMapping("/reservation")
    public String index(Model model) {
        //TODO: http parametri, id kohteelle
        //Tänne esim varauksen yleiset tiedot, joku kuva siitä ehkä, 
           // ja aikatauluun kaikki jo varatut ajankohdat
        
        //model.addAttribute("attr", "value");

        return "reservation";
    }
    
}
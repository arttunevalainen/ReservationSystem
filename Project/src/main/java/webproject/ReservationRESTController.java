
package webproject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ilmari Mäntysaari
 */
@RestController
@RequestMapping("/reservationREST")
public class ReservationRESTController {
    
    @RequestMapping("/get_reservation")
    public String getReservations(){
        //hae userin varaukset
        return "not implemented";
    }
    
    @RequestMapping("/get_reservable")
    public String getReservables(){
        //hae userille näkyvät kohteet
        return "not implemented";
    }
}

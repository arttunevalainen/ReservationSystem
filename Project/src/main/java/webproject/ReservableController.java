
package webproject;

import java.util.List;
import org.postgresql.util.PSQLException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webproject.Models.Reservable;
import webproject.Models.ReservablePostModel;
import webproject.Models.Reservation;
import webproject.dataaccess.ReservableRepository;
import webproject.dataaccess.ReservationRepository;
import webproject.dataaccess.UserRepository;


@Controller
@RequestMapping("reservable")
public class ReservableController{

    private final ReservableRepository reservableRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    
    public ReservableController(ReservableRepository reservableRepository, UserRepository userRepository,
                                ReservationRepository reservationRepository){
        this.reservableRepository = reservableRepository;
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
    }
    
    @RequestMapping("/list")
    public String list(Model model) {
        
        List<Reservable> res = reservableRepository.getAll();
        model.addAttribute("reservables", res);
        model.addAttribute("title", "Reservations");
        return "reservable/list";
    }
    
    @RequestMapping("/info/{id}")
    public String id(Model model, @PathVariable String id) {
        //tsekkaa että userilla oikeudet nähdä tämä
        Reservable res = reservableRepository.get(Integer.parseInt(id));
        model.addAttribute("reservable", res);
        model.addAttribute("reservations", res.getReservations());
        model.addAttribute("title", "Reservations");
        
        model.addAttribute("reservableId", id);
        return "reservable/info";
    }
    
    @RequestMapping("/new")
    public String newReservable(Model model){
        //tsekkaa että oikeus lisätä reservable
        model.addAttribute("title", "Reservations");
        model.addAttribute("newReservable", new ReservablePostModel());
        return "reservable/new";
    }
    
    @PostMapping("/new")
    public String newReservablePost(@ModelAttribute ReservablePostModel newRes, Model model) {
        
        Reservable reservable = new Reservable();
        reservable.setName(newRes.getName());
        reservable.setInfo(newRes.getInfo());
        model.addAttribute("result", "New reservable was added succesfully");
        
        try{
            //TODO: change this to current logged user
            reservable.setOwner(userRepository.get(1));
            
            reservableRepository.save(reservable);
        }
        catch(PSQLException ex){
            model.addAttribute("result", "Error while processing request");
        }
        
        return "reservable/postback";
    }
    
    
    /**
     * Reservation page
     * @param model
     * @param id
     * @return 
     */
    @RequestMapping("/reservation/{id}")
    public String reservation(Model model, @PathVariable String id) {
        Reservation res = reservationRepository.get(Integer.parseInt(id));
        model.addAttribute("title", "Reservations");
        model.addAttribute("reservation_title", res.getReservationItem().getName() + " at " + res.getStartTime().toString());
        
        return "reservation";
    }
    
    
}

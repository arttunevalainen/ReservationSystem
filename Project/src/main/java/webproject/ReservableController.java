
package webproject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webproject.Models.Reservable;
import webproject.Models.ReservablePostModel;
import webproject.Models.Reservation;
import webproject.Models.ReservationPostModel;
import webproject.dataaccess.ReservableRepository;
import webproject.dataaccess.ReservationRepository;
import webproject.dataaccess.UserRepository;

/*
TÄN VOI EHKÄ REFAKTOROIDA USEEMPAAN LUOKKAAN
*/

@Controller
@RequestMapping("reservable")
public class ReservableController{

    private final ReservableRepository reservableRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final AuthenticationUtils authenticationUtils;
    
    public ReservableController(ReservableRepository reservableRepository, UserRepository userRepository,
                                ReservationRepository reservationRepository, AuthenticationUtils authenticationUtils){
        this.reservableRepository = reservableRepository;
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
        this.authenticationUtils = authenticationUtils;
    }
    
    @RequestMapping("/list")
    public String list(Model model) {
        
        List<Reservable> res = reservableRepository.getAll();
        model.addAttribute("reservables", res);
        model.addAttribute("title", "Reservations");
        
        model.addAttribute("userName", authenticationUtils.getUserDetails().getUsername());
        model.addAttribute("role", authenticationUtils.getUserRole());
        
        return "reservable/list";
    }
    
    @RequestMapping("/info/{id}")
    public String id(Model model, @PathVariable String id) {
        Reservable res = reservableRepository.get(Integer.parseInt(id));
        model.addAttribute("reservable", res);
        model.addAttribute("reservations", res.getReservations());
        model.addAttribute("title", "Reservations");
        
        model.addAttribute("userName", authenticationUtils.getUserDetails().getUsername());
        model.addAttribute("role", authenticationUtils.getUserRole());
        
        model.addAttribute("newReservation", new ReservationPostModel());
        
        model.addAttribute("reservableId", id);
        return "reservable/info";
    }
    
    @RequestMapping("/new")
    public String newReservable(Model model){
        //tsekkaa että oikeus lisätä reservable
        model.addAttribute("title", "Reservations");
        model.addAttribute("newReservable", new ReservablePostModel());
        
        model.addAttribute("userName", authenticationUtils.getUserDetails().getUsername());
        model.addAttribute("role", authenticationUtils.getUserRole());
        
        return "reservable/new";
    }
    
    @PostMapping("/new")
    public String newReservablePost(@ModelAttribute ReservablePostModel newRes, Model model) {
        
        Reservable reservable = new Reservable();
        reservable.setName(newRes.getName());
        reservable.setInfo(newRes.getInfo());
        model.addAttribute("result", "New reservable was added succesfully");
        
        try{
            reservable.setOwner(userRepository.get(authenticationUtils.getUserId()));
            
            reservableRepository.save(reservable);
        }
        catch(Exception ex){
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
    //TODO: this can probably be removed
    @RequestMapping("/reservation/{id}")
    public String reservation(Model model, @PathVariable String id) {
        Reservation res = reservationRepository.get(Integer.parseInt(id));
        model.addAttribute("title", "Reservations");
        model.addAttribute("reservation_title", res.getReservationItem().getName() + " at " + res.getStartTime().toString());
        
        return "reservation";
    }
    
    /**
     * Adds new reservation
     * @param newRes
     * @param model
     * @return 
     */
    @PostMapping("/newreservation")
    public String newReservation(@ModelAttribute ReservationPostModel newRes, Model model) {
        
        Reservation reservation = new Reservation();
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        model.addAttribute("result", "New reservation was added succesfully");
        try{
            reservation.setStartTime(format.parse(newRes.getStartDate()));
            reservation.setEndTime(format.parse(newRes.getEndDate()));
            reservation.setReservationItem(reservableRepository.get(newRes.getReservableId()));
            reservation.setReserver(userRepository.get(authenticationUtils.getUserId()));
            reservationRepository.save(reservation);
        }
        catch(Exception ex){
            ex.printStackTrace();
            model.addAttribute("result", "Error while processing request");
        }
        return "reservable/postback";
    }
    
    
}

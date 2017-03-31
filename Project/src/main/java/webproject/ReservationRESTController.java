
package webproject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webproject.Models.Reservation;
import webproject.Models.Reservable;
import webproject.dataaccess.ReservableRepository;
import webproject.dataaccess.UserRepository;

/**
 *
 * @author Ilmari Mäntysaari
 */
@RestController
@RequestMapping("/reservationREST")
public class ReservationRESTController {
    
    private final UserRepository userRepository;
    private final ReservableRepository reservableRepository;
    
    
    public ReservationRESTController(UserRepository userRepository, ReservableRepository resItemRepository){
        this.userRepository = userRepository;
        this.reservableRepository = resItemRepository;
    }
    
    @RequestMapping("/users_reservations/{userId}")
    public List<SerializableReservation> getUserReservations(@PathVariable String userId){
        
        Set<Reservation> reservations = userRepository.get(Integer.parseInt(userId)).getReservations();
        List<SerializableReservation> list = new ArrayList<>();
        
        for(Reservation res : reservations){
            list.add(new SerializableReservation(res));
        }
        
        return list;
    }
    
    @RequestMapping("/reservables_reservations/{reservableId}")
    public List<ReservationEvent> reservablesReservations(@PathVariable String reservableId){
        System.out.println("PErksles");
        Set<Reservation> reservations = reservableRepository.get(Integer.parseInt(reservableId)).getReservations();
        List<ReservationEvent> list = new ArrayList<>();
        
        for(Reservation res : reservations){
            list.add(new ReservationEvent(res));
        }
        
        return list;
    }
    
    private class SerializableReservation{
        public int id;
        public String user;
        public int userId;
        public String reservable;
        public int reservableId;
        public Date startTime;
        public Date endTime;
        
        public SerializableReservation(Reservation res){
            this.id = res.getId();
            this.user = res.getReserver().getName();
            this.reservable = res.getReservationItem().getName();
            this.startTime = res.getStartTime();
            this.endTime = res.getEndTime();
            this.userId = res.getReserver().getId();
            this.reservableId = res.getReservationItem().getId();
        }
    }
    
    private class ReservationEvent{
        public String start;
        public String end;
        public String title;
        
        public ReservationEvent(Reservation res){
            this.title = res.getReserver().getName();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            this.start = df.format(res.getStartTime());
            this.end = df.format(res.getEndTime());
        }
    }
}

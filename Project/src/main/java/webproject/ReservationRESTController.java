
package webproject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webproject.Models.Reservation;
import webproject.Models.ReservationItem;
import webproject.dataaccess.ReservationItemRepository;
import webproject.dataaccess.UserRepository;

/**
 *
 * @author Ilmari Mäntysaari
 */
@RestController
@RequestMapping("/reservationREST")
public class ReservationRESTController {
    
    private final UserRepository userRepository;
    private final ReservationItemRepository reservationItemRepository;
    
    public ReservationRESTController(UserRepository userRepository, ReservationItemRepository resItemRepository){
        this.userRepository = userRepository;
        this.reservationItemRepository = resItemRepository;
    }
    
    @RequestMapping("/users_reservations/{userId}")
    public List<SerializableReservation> getUserReservations(@PathVariable String userId){
        
        Set<Reservation> reservations = userRepository.get(Integer.parseInt(userId)).getReservations();
        List<SerializableReservation> list = new ArrayList<>();
        
        for(Reservation res : reservations){
            list.add(new SerializableReservation(res.getId(), res.getReserver().getName(),
                                                res.getReservationItem().getName(), res.getStartTime(),
                                                res.getEndTime()));
        }
        
        return list;
    }
    
    @RequestMapping("/users_reservables/{userId}")
    public List<SerializableReservationItem> getReservables(@PathVariable String userId){
        //TODO: if there's time, make is possible to set reservables to be visible to selected user only
        //at the moment, all reservables are visible to everyone
        List<ReservationItem> reservables = reservationItemRepository.getAll();
        List<SerializableReservationItem> list = new ArrayList<>();
        
        for(ReservationItem item : reservables){
            list.add(new SerializableReservationItem());
        }
        
        return list;
    }
    
    private class SerializableReservation{
        public int id;
        public String user;
        public String reservable;
        public Date startTime;
        public Date endTime;
        
        public SerializableReservation(int id, String user, String reservable, Date startTime, Date endTime){
            this.id = id;
            this.user = user;
            this.reservable = reservable;
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }
    private class SerializableReservationItem{
        
    }
}

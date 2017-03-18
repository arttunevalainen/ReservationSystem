
package webproject.dataaccess;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import webproject.Application;
import webproject.Models.Reservation;

/**
 *
 * @author Ilmari Mäntysaari
 */
@Component
public class ReservationRepository {

    public void save(Reservation reservation){
        
    }
    
    public Reservation get(int id){
        Session session = Application.sessionFactory.openSession();
        Reservation item = session.get(Reservation.class, 1);
        session.close();
        return item;
    }
}

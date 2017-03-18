
package webproject.dataaccess;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import webproject.Application;
import webproject.Models.ReservationItem;

/**
 *
 * @author Ilmari Mäntysaari
 */
@Component
public class ReservationItemRepository {
    
    public void save(){
        //foreign keyt?
    }
    
    public ReservationItem get(int id){
        Session session = Application.sessionFactory.openSession();
        ReservationItem item = session.get(ReservationItem.class, 1);
        Hibernate.initialize(item.getReservations());
        session.close();
        return item;
    }
    
}

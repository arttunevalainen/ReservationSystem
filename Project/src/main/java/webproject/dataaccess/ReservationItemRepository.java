
package webproject.dataaccess;

import java.util.List;
import java.util.Set;
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
        //foreign keyt? hibernate valittaa ownerId sarakkeesta
    }
    
    public ReservationItem get(int id){
        Session session = Application.sessionFactory.openSession();
        ReservationItem item = session.get(ReservationItem.class, 1);
        Hibernate.initialize(item.getReservations());
        session.close();
        return item;
    }
    
    public List<ReservationItem> getAll(){
        Session session = Application.sessionFactory.openSession();
        List<ReservationItem> items = session.createCriteria(ReservationItem.class).list();
        
        items.stream().forEach(x -> Hibernate.initialize(x.getReservations()));
        
        session.close();
        return items;
    }
    
}

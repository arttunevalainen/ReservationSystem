
package webproject.dataaccess;

import java.util.List;
import java.util.Set;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import webproject.Application;
import webproject.Models.Reservable;

/**
 *
 * @author Ilmari Mäntysaari
 */
@Component
public class ReservableRepository {
    
    public void save(){
        //foreign keyt? hibernate valittaa ownerId sarakkeesta
    }
    
    public Reservable get(int id){
        Session session = Application.sessionFactory.openSession();
        Reservable item = session.get(Reservable.class, 1);
        Hibernate.initialize(item.getReservations());
        session.close();
        return item;
    }
    
    public List<Reservable> getAll(){
        Session session = Application.sessionFactory.openSession();
        List<Reservable> items = session.createCriteria(Reservable.class).list();
        
        items.stream().forEach(x -> Hibernate.initialize(x.getReservations()));
        
        session.close();
        return items;
    }
    
}

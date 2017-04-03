
package webproject.dataaccess;

import org.hibernate.Hibernate;
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
    
    public Reservation get(int id){
        Session session = Application.sessionFactory.openSession();
        Reservation item = session.get(Reservation.class, id);
        Hibernate.initialize(item.getReserver());
        Hibernate.initialize(item.getReservationItem());
        session.close();
        return item;
    }
    
    
    public void save(Reservation res){
        Session session = Application.sessionFactory.openSession();
        Hibernate.initialize(res);
        session.beginTransaction();
        session.save(res);
        session.getTransaction().commit();
        session.close();
    }
    
    public void delete(Reservation res) {
        
        Session session = Application.sessionFactory.openSession();
        Hibernate.initialize(res);
        session.beginTransaction();
        session.delete(res);
        session.getTransaction().commit();
        session.close();
    }
}

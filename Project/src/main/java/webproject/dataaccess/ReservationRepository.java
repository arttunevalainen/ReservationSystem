
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
        Reservation item = session.get(Reservation.class, 1);
        session.close();
        return item;
    }
    
    
    public void save(Reservation res){
        Session session = Application.sessionFactory.openSession();
//        Hibernate.initialize(res.getReservationItem());
//        Hibernate.initialize(res.getReserver());
        Hibernate.initialize(res);
        session.beginTransaction();
        session.save(res);
        session.getTransaction().commit();
        session.close();
    }
}

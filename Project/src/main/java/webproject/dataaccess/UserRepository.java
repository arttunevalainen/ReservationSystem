
package webproject.dataaccess;

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import webproject.Application;
import webproject.Models.Reservable;
import webproject.Models.Reservation;
import webproject.Models.User;

/**
 *
 * @author Ilmari Mäntysaari
 */
@Component
public class UserRepository {
    
    private final ReservableRepository reservableRepository;
    
    public UserRepository(ReservableRepository reservableRepository){
        this.reservableRepository = reservableRepository;
    }

    public void save(User user){
        Session session = Application.sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }
    
    public User get(int id){
        Session session = Application.sessionFactory.openSession();
        User item = session.get(User.class, id);
        Hibernate.initialize(item.getReservations());
        for(Reservation res : item.getReservations()){
            Hibernate.initialize(res.getReserver());
            Hibernate.initialize(res.getReservationItem());
        }
        session.close();
        return item;
    }
    
    //User id by username
    public int getUserIDByUserName(String username) {
        
        Session session = Application.sessionFactory.openSession();
        String hql = "SELECT u.id FROM User u WHERE username=:name";
        Query query = session.createQuery(hql);
        query.setParameter("name", username);
        List results = query.list();
        
        session.close();
        return Integer.parseInt(results.get(0).toString());
    }
    
    @Transactional
    public void changePassword(int id, String password) {
        
        Session session = Application.sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        Query query = session.createQuery("UPDATE User u SET u.password = :psswrd WHERE u.id=:id");
        query.setParameter("psswrd", password);
        query.setParameter("id", id);
        query.executeUpdate();
       
        session.close();
    }
    
    //Is username available?
    public boolean usernameAvailable(String name) {
        Session session = Application.sessionFactory.openSession();
        String hql = "SELECT u.id FROM User u WHERE username=:name";
        Query query = session.createQuery(hql);
        query.setParameter("name", name);
        List results = query.list();
        session.close();
        
        if(results.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public List<User> getAll() {
        
        Session session = Application.sessionFactory.openSession();
        List<User> items = session.createCriteria(User.class).list();
         
        return items;
    }
    
    public void deleteUser(int id) {
        Session session = Application.sessionFactory.openSession();
        
        
        User user = get(id);
        //delete reservations
        session.beginTransaction();
        for(Reservation r:user.getReservations()){
            session.delete(r);
        }
        session.getTransaction().commit();
        //delete reservables
        session.beginTransaction();
        for(Reservable res:reservableRepository.getAll()){
            if(res.getOwner().getId() == user.getId()){
                session.delete(res);
            }
        }
        session.getTransaction().commit();
        //delete user
        session.beginTransaction();
        user.setReservations(null);
        session.delete(user);
        session.getTransaction().commit();
        /*
        Query query = session.createQuery("delete from User u where u.id= :id");
        query.setParameter("id", id);
        query.executeUpdate();*/
        
        session.close();
    }
}

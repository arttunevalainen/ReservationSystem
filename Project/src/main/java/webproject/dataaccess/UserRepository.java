
package webproject.dataaccess;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import webproject.Application;
import webproject.Models.User;

/**
 *
 * @author Ilmari Mäntysaari
 */
@Component
public class UserRepository {

    public void save(User user){
        Session session = Application.sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }
    
    public User get(int id){
        Session session = Application.sessionFactory.openSession();
        User item = session.get(User.class, 1);
        session.close();
        return item;
    }
}

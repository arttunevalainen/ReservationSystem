
package webproject;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Arttu
 */
public class AuthenticationUtils {
    //Käyttäjän rooli saadaan täältä.
    public static String getUserRole() {
        
        UserDetails details = getUserDetails();
        
        Object b[] = details.getAuthorities().toArray();
        String role = b[0].toString();
        
        return role;
    }
    
    public static UserDetails getUserDetails() {
        
        //Saadaan kaivettua kirjautunut
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails details = (UserDetails) auth.getPrincipal();
        
        return details;
    }
}

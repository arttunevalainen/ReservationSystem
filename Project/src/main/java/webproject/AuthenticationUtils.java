
package webproject;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import webproject.dataaccess.UserRepository;

/**
 * Methods for getting informations about current logged in user
 * @author Arttu
 */
@Component
public class AuthenticationUtils {
    
    private final UserRepository userRepository;
    
    public AuthenticationUtils(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    
    //Käyttäjän rooli saadaan täältä.
    public String getUserRole() {
        
        UserDetails details = getUserDetails();
        
        Object b[] = details.getAuthorities().toArray();
        String role = b[0].toString();
        
        return role;
    }
    
    public int getUserId(){
        return userRepository.getUserIDByUserName(getUserDetails().getUsername());
    }
    
    public UserDetails getUserDetails() {
        
        //Saadaan kaivettua kirjautunut
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails details = (UserDetails) auth.getPrincipal();
        
        return details;
    }
}

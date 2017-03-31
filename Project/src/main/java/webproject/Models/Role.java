package webproject.Models;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


public class Role {
    
    public static String getRole() {
        
        String role = "";
        
        UserDetails details = Role.getUserDetails();
        
        Object b[] = details.getAuthorities().toArray();
        role = b[0].toString();
        
        return role;
    }
    
    public static UserDetails getUserDetails() {
        
        //Saadaan kaivettua kirjautunut
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails details = (UserDetails) auth.getPrincipal();
        
        return details;
    }
}
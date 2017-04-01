package webproject.Models;

import java.util.Set;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


public class User{
    private int id;
    private String name;
    private String role;
    private String password;
    private String username;
    private Set<Reservation> reservations;
    
    public User(){
        
    }
    
    public User(String username, String password, String name, String role){
        this.name = name;
        this.password = password;
        this.role = role;
        this.username = username;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the reservations
     */
    public Set<Reservation> getReservations() {
        return reservations;
    }

    /**
     * @param reservations the reservations to set
     */
    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }
    
    //Käyttäjän rooli saadaan täältä.
    public static String getUserRole() {
        
        UserDetails details = User.getUserDetails();
        
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
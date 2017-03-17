package webproject.Models;

import java.util.Set;


public class ReservationItem{
    private int id;
    private String name;
    private String info;
    private User owner;
    //TODO keksi hyvä tietorakenne ja poista vanhat setterit
    private Set<Reservation> reservations;
    
    
    public ReservationItem(){
        
    }
    
    public ReservationItem(String name, String info, User user){
        
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
     * @return the info
     */
    public String getInfo() {
        return info;
    }

    /**
     * @param info the info to set
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * @return the owner
     */
    public User getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(User owner) {
        this.owner = owner;
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
}
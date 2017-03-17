package webproject.Models;

import java.util.Date;

public class Reservation{
    private int id;
    private ReservationItem reservationItem;
    private User reserver;
    private Date startTime;
    private Date endTime;
    
    public Reservation(){
        
    }
    
    public Reservation(User reserver, ReservationItem reservationItem, Date startTime, Date endTime){
        this.reserver = reserver;
        this.reservationItem = reservationItem;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * @return the reservationItem
     */
    public ReservationItem getReservationItem() {
        return reservationItem;
    }

    /**
     * @param reservationItem the reservationItem to set
     */
    public void setReservationItem(ReservationItem reservationItem) {
        this.reservationItem = reservationItem;
    }

    /**
     * @return the reserver
     */
    public User getReserver() {
        return reserver;
    }

    /**
     * @param user the reserver to set
     */
    public void setReserver(User user) {
        this.reserver = user;
    }

    /**
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
    
}
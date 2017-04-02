
package webproject.Models;

/**
 *
 * @author Ilmari Mäntysaari
 */
public class ReservationPostModel {
    private int reservableId;
    private String date;

    /**
     * @return the reservableId
     */
    public int getReservableId() {
        return reservableId;
    }

    /**
     * @param reservableId the reservableId to set
     */
    public void setReservableId(int reservableId) {
        this.reservableId = reservableId;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

}


package webproject.Models;

/**
 *
 * @author Ilmari Mäntysaari
 */
public class ReservationPostModel {
    private int reservableId;
    private String startDate;
    private String endDate;

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
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

}

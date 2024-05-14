package ViewReservations;

public class Reservation {
    private final String reservation;
    private final String date;
    private final String startTime;
    private final String endTime;
    private final int ID;
    private final String gender;

    public Reservation(int ID,String reservation, String date, String startTime, String endTime,  String gender) {
        this.reservation = reservation;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.ID = ID;
        this.gender = gender;
    }

    public String getReservation() {
        return reservation;
    }

    public String getDate() {
        return date;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public int getID() {
        return ID;
    }

    public String getGender() {
        return gender;
    }
}

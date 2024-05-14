package ShowEvents;

public class Event {
    private final String facility;
    private final String date;
    private final String startTime;
    private final String endTime;
    private final int ID;
    private final String participants;

    public Event(int ID, String facility, String participants,String date, String startTime, String endTime) {
        this.ID = ID;
        this.facility = facility;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.participants = participants;
    }

    public String getFacility() {
        return facility;
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

    public String getParticipants() {
        return participants;
    }
}

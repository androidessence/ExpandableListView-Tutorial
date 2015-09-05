package androidessence.expandablelistviewsample;

import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by adammcneilly on 9/4/15.
 */
public class Event {
    private String description;
    private LocalDateTime time;

    public Event(String description, LocalDateTime time){
        setDescription(description);
        setTime(time);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getTimeString(){
        DateTimeFormatter dtf = DateTimeFormat.forPattern("hh:mm a");
        return dtf.print(time);
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}

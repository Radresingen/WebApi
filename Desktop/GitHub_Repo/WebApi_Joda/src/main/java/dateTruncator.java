import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.Date;

public class dateTruncator {
    DateTime startDate = null;
    DateTime endDate = null;
    DateTime systemDate = null;

    public dateTruncator(Date startDate,Date endDate){
        this.startDate = new DateTime(startDate);
        this.startDate = DateTime.now(DateTimeZone.UTC);
        this.endDate = new DateTime(endDate);
        this.endDate = DateTime.now(DateTimeZone.UTC);

        systemDate = new DateTime();
        systemDate = DateTime.now(DateTimeZone.UTC);
    }

    public void truncateMinutes(){
        truncateSeconds();
        startDate = startDate.minuteOfDay().roundFloorCopy();
        endDate = endDate.minuteOfDay().roundCeilingCopy();
    }
    public void truncateSeconds(){
        startDate = startDate.secondOfDay().roundFloorCopy();
        endDate = startDate.secondOfDay().roundFloorCopy();
    }

}

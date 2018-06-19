import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

public class dateTruncator {
    protected List<LocalDate> interval;
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;
    protected LocalDateTime systemDate;
    protected int hotZone;

    public dateTruncator(LocalDateTime startDate, LocalDateTime endDate,int hotZone){
        interval = startDate.toLocalDate().datesUntil(endDate.toLocalDate()).collect(Collectors.toList());
        this.startDate = startDate;
        this.endDate = endDate;
        systemDate = LocalDateTime.now(ZoneId.of("UTC"));

        if(hotZone != 0){
            this.hotZone = hotZone;
        }
        else{
            this.hotZone = 3;
        }
    }
    public List<LocalDate> getInterval(){
        return interval;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
    public LocalDateTime getStartDate() {
        return startDate;
    }
    public LocalDateTime getSystemDate() {
        return systemDate;
    }
    public void secondTruncator(){

    }
    public void minuteTruncator(){

    }
}

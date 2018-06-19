import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class dateTruncator {
    String fullDate = null;
    String startDate = null;
    String startTime = null;
    String endDate =null;
    String endTime = null;
    SimpleDateFormat sdf = null;
    Date today = null;
    String todayFullDate = null;
    String todayDate = null;
    String systemHour = null;

    public dateTruncator(String startDate,String endDate){
        this.fullDate = fullDate;
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        today = new Date();
        todayFullDate = sdf.format(today);

        fullDateParser("start");
        fullDateParser("end");
        todayFullDateParser();
    }
    public void fullDateParser(String op){
        String[] parser = fullDate.split("T");
        if(op.compareTo("start") == 0){
            startDate = parser[0];
            startTime = parser[1].substring(0,parser[1].length()-1);
        }
        else if(op.compareTo("end") == 0){
            endDate = parser[0];
            endTime = parser[1];
        }
    }
    public void todayFullDateParser(){
        String[] parser = todayFullDate.split(" ");

        todayDate = parser[0];
        systemHour = parser[1];
    }
}

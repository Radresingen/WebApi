import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.DurationFieldType;
import java.util.ArrayList;
import java.util.Date;

public class indexNameGenerator extends dateTruncator{
    ArrayList<String> indexNames = new ArrayList<String>();
    int hotZone;
    public indexNameGenerator(Date startDate, Date endDate,int hotzone){
        super(startDate,endDate);
        this.hotZone = hotzone;
    }

    public void nameGenerator(){
        //first we need to look at end time if it is in hotZone then system - hotzone to endtime will be considered
        //as type 60

        //checking if they are same date
        if(endDate.getYear() == systemDate.getYear() && endDate.getMonthOfYear() == systemDate.getMonthOfYear()
                && endDate.getDayOfMonth() == systemDate.getDayOfMonth()){
            //this area is type60
            if(systemDate.getHourOfDay() - endDate.getHourOfDay() < hotZone){
                type60();
                //after hotZone is taken now we can consider type3600();
                type3600();
            }
            else{
                type3600();
            }
        }
    }
    public void type60(){
        for(int i=systemDate.getHourOfDay() - hotZone;i <= endDate.getHourOfDay();i++){
            String s="Type60-"+endDate.getYear()+"-"+endDate.getMonthOfYear()+"-"+endDate.getDayOfMonth()+"-"+i;
            indexNames.add(s);
        }

        //update endDate hour
        endDate = endDate.withHourOfDay(systemDate.getHourOfDay() - hotZone);
    }

    public void type3600(){
        int days = Days.daysBetween(startDate,endDate).getDays();

        for(int i=0;i < days;i++){
            DateTime d = startDate.withFieldAdded(DurationFieldType.days(),i);
            String s=d.getYear()+"-"+d.getMonthOfYear()+"-"+d.getDayOfMonth()+"-"+d.getHourOfDay();
            indexNames.add(s);
        }
    }
}

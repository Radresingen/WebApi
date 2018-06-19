import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ListIterator;

public class mainClass {
    static int startYear;
    static int startMonth;
    static int startDay;
    static int startHour;
    static int startMin;
    static int startSecond;

    static int endYear;
    static int endMonth;
    static int endDay;
    static int endHour;
    static int endMin;
    static int endSecond;
    public static void main(String [] args){
        timeParser(args);


        LocalDateTime startDate = LocalDateTime.of(startYear,startMonth,startDay,startHour,startMin,startSecond);
        LocalDateTime endDate = LocalDateTime.of(endYear,endMonth,endDay,endHour,endMin,endSecond);


        /*LocalDateTime startDate = LocalDateTime.now(ZoneId.of("UTC"));
        startDate = startDate.minusHours(10);
        LocalDateTime endDate = LocalDateTime.now(ZoneId.of("UTC"));


        endDate = endDate.minusHours(5);*/
        System.out.println("startDate:  " + startDate);
        System.out.println("endDate:    "+endDate);

        indexGenerator indexNames = new indexGenerator(startDate,endDate,3);

        System.out.println("systemDate: "+indexNames.getSystemDate());

        ListIterator<String> iterator = indexNames.getIndexNames().listIterator();

        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
    public static void timeParser(String [] arguments){
        boolean start = true;
        for(String arg : arguments){
            String []tokens = arg.split("T");
            String []date_tokens =  tokens[0].split("-");
            String []time_tokens = tokens[1].split(":");


            if(start){
                //System.out.println("startYear: "+date_tokens[0]);
                startYear = Integer.parseInt(date_tokens[0]);
                //System.out.println("startMonth: "+date_tokens[1]);
                startMonth = Integer.parseInt(date_tokens[1]);
                //System.out.println("startDay: "+date_tokens[1]);
                startDay= Integer.parseInt(date_tokens[2]);

                //System.out.println("startHour: "+time_tokens[0]);
                startHour = Integer.parseInt(time_tokens[0]);
                //System.out.println("startMin: "+time_tokens[1]);
                startMin = Integer.parseInt(time_tokens[1]);
                //System.out.println("startSec: "+time_tokens[2].substring(0,time_tokens[2].length()-1));
                startSecond = Integer.parseInt(time_tokens[2].substring(0,time_tokens[2].length()-1));
                start = false;
            }
            //end date parsing
            else{
                //System.out.println("endYear: "+date_tokens[0]);
                endYear = Integer.parseInt(date_tokens[0]);
                //System.out.println("endMonth: "+date_tokens[1]);
                endMonth = Integer.parseInt(date_tokens[1]);
                //System.out.println("endDay: "+date_tokens[1]);
                endDay= Integer.parseInt(date_tokens[2]);

                //System.out.println("endHour: "+time_tokens[0]);
                endHour = Integer.parseInt(time_tokens[0]);
                //System.out.println("endMin: "+time_tokens[1]);
                endMin = Integer.parseInt(time_tokens[1]);
                //System.out.println("endSec: "+time_tokens[2].substring(0,time_tokens[2].length()-1));
                endSecond = Integer.parseInt(time_tokens[2].substring(0,time_tokens[2].length()-1));

            }
        }
    }
}

import java.util.ArrayList;

public class indexNameCreator extends dateTruncator{
    ArrayList<String> indexNames;
    String type;
    int endYear;
    int endMonth;
    int endDay;

    int startYear;
    int startMonth;
    int startDay;

    int systemYear;
    int systemMonth;
    int systemDay;

    int startHour;
    int startMinute;
    int startSeconds;

    int endHour;
    int endMinute;
    int endSeconds;
    int hotZone = 3;

    int systemHours;
    int systemMinute;
    int systemSecond;

    public indexNameCreator(String startDate,String endDate){
        super(startDate,endDate);
    }


    //First i need to figure the type is 60(mins) or 3600(hours)
    // if given date is in hot zone compare to system date then the zone i will be taken as type 60
    // else take the date as type 3600
    public void typeFinder(){
        // Date Parser
        // index 0 is year
        // index 1 is month
        // index 2 is date

        // Time Parser
        // index 0 is hour
        // index 1 is month
        // index 2 is seconds

        String [] givenStartDateParser = startDate.split("-");
        String [] givenStartTimeParser = startTime.split(":");
        String [] givenEndDateParser = endDate.split("-");
        String [] givenEndTimeParser = endTime.split(":");
        String [] systemDateParser = todayDate.split("-");
        String [] systemTimeParser = systemHour.split(":");

        endYear = Integer.parseInt(givenEndDateParser[0]);
        endMonth = Integer.parseInt(givenEndDateParser[1]);
        endDay = Integer.parseInt(givenEndDateParser[2]);

        startYear = Integer.parseInt(givenStartDateParser[0]);
        startMonth = Integer.parseInt(givenStartDateParser[1]);
        startDay = Integer.parseInt(givenStartDateParser[2]);

        systemYear = Integer.parseInt(systemDateParser[0]);
        systemMonth = Integer.parseInt(systemDateParser[1]);
        systemDay = Integer.parseInt(systemDateParser[2]);

        endHour = Integer.parseInt(givenEndTimeParser[0]);
        endMinute = Integer.parseInt(givenEndTimeParser[1]);
        endSeconds = Integer.parseInt(givenEndDateParser[2]);

        startHour = Integer.parseInt(givenStartTimeParser[0]);
        startMinute = Integer.parseInt(givenStartTimeParser[1]);
        startSeconds = Integer.parseInt(givenStartTimeParser[2]);

        systemHours = Integer.parseInt(systemTimeParser[0]);
        systemMinute = Integer.parseInt(systemTimeParser[1]);
        systemSecond = Integer.parseInt(systemTimeParser[2]);

        //if endTime (in hours is in hot zone then take it as a type 60 and take rest of the time as a type 3600

        //type 60
        if(systemHours - endHour < hotZone){
            type60();
        }
        //type 3600
        else{
            type3600();
        }

    }
    public void type60(){
        for(int i=systemHours - hotZone;i<=endHour;i++){
            String s = "Type-60-"+endYear+"-"+endMonth+"-"+endDay+"-"+Integer.toString(i);
            indexNames.add(s);
        }
        //update endHour for the rest of it.
        endHour = systemHours - hotZone;

    }
    public void type3600(){
        for(int i=startYear;i<=endYear;i++){
            int j;
            if(i==startYear){
                for(j=startMonth;j<=12;j++){
                    if(j==startMonth){
                        for(int k=startDay;k<31;k++) {
                            String s = "Type 3600-" + i + "-" + j + "-" + k;
                            indexNames.add(s);
                        }
                    }
                    else{
                        for(int k=0;k<31;k++) {
                            String s = "Type 3600-" + i + "-" + j + "-" + k;
                            indexNames.add(s);
                        }
                    }

                }
            }
            else if(i == endYear){
                for(j=1;j<=endMonth;j++){
                    if(j ==endMonth){
                        for(int k=1;k<endDay;k++) {
                            String s = "Type 3600-" + i + "-" + j + "-" + k;
                            indexNames.add(s);
                        }
                    }
                    else{
                        for(int k=1;k<=31;k++) {
                            String s = "Type 3600-" + i + "-" + j + "-" + k;
                            indexNames.add(s);
                        }
                    }

                }
            }
            else{
                for(j=1;j<=12;j++){
                    for(int k=1;k<=31;k++){
                        String s = "Type 3600-" + i + "-" + j + "-" + k;
                        indexNames.add(s);
                    }
                }
            }
        }
    }
}

import javax.print.DocFlavor;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class indexGenerator extends dateTruncator{
    private ArrayList<String> indexNames;
    public indexGenerator(LocalDateTime startTime,LocalDateTime endTime,int hotZone){
        super(startTime,endTime,hotZone);
        indexNames = new ArrayList<String>();
        parseDatesToNames();
    }

    public void parseDatesToNames(){
        String temp;
        Duration duration = Duration.between(endDate,systemDate);
        long difference = Math.abs(duration.toHours());


        /*BITIS TARIHI ILE SISTEM TARIHI ARASINDAKI ZAMAN FARKI HOTZONE DAN AZ ISE

          BU ZAMAN ARALIĞI TYPE60 OLARAK ALINIR VE BITIS TARIHI HOTZONE A GORE YENIDEN DUZENLENIP

          GERİ KALAN ZAMAN ARALIĞI (STARTDATETIME DAN ENDDATETIME A KADAR) TYPE 3600 OLARAK ALINIR.

         */
        if(difference < hotZone){
            duration = Duration.between(startDate,endDate);
            while(systemDate.getHour() - endDate.getHour() != hotZone && Math.abs(duration.toHours()) != 0){
                temp = "TYPE-60-"+endDate.getYear()+"-"+endDate.getMonth()+"-"+ endDate.getDayOfMonth()+"-"+endDate.getHour();
                endDate = endDate.minusHours(1);
                duration = Duration.between(startDate,endDate);
                indexNames.add(temp);
            }

            //EĞER HOTZONE ÇIKTIKTAN SONRA ENDDATE İLE STARTDATE ARASINDA FARK VARSA KESİŞİM DURUMUNA GİRER
            if(Math.abs(duration.toHours()) != 0){
                type3600();
            }

        }

        /* GUN GUN INDEX SEARCH AT */
        //VERİLEN TARİH ARALIĞI TAMAMEN HOTZONE DIŞINDA
        else{
            type3600();
        }
    }
    public void type3600(){
        String temp;
        ListIterator<LocalDate> iterator = interval.listIterator();

        //startDate and endDate are same date
        if(!iterator.hasNext()){
            temp = "TYPE-3600-"+endDate.getYear()+"-"+endDate.getMonth()+"-"
                    +endDate.getDayOfMonth();
            indexNames.add(temp);
        }
        while(iterator.hasNext()){
            LocalDate tempDate = iterator.next();
            temp = "TYPE-3600-"+tempDate.getYear()+"-"+tempDate.getMonth()+"-"
                    +tempDate.getDayOfMonth();
            indexNames.add(temp);
        }
    }

    public List<String> getIndexNames(){
        return indexNames;
    }
}

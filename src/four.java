import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;
import static java.time.temporal.ChronoUnit.DAYS;
public class UserInput {

    Scanner scanner = new Scanner(System.in);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMMyyyy");

    public static void main(String[] args) {
        System.out.println("WELCOME TO HOTEL RESERVATION PROGRAM");
        UserInput userInput = new UserInput();
        userInput.uc1();
        Date[] dateArray=new Date[2];
        dateArray = userInput.input();
        userInput.uc1(dateArray[0],dateArray[1]);
    }

    //UC1 basic rates
    public void uc1(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMMyyyy");
    public Date[] input(){

        Date start,end;
        LocalDate startF,endF;
        while(true) {
            System.out.println("Enter the start date in the format ddMMMYYYY");
            try {
                start = simpleDateFormat.parse(scanner.nextLine());
                startF = convertToLocalDateViaSqlDate(start);
            start = returnStartDate(scanner.nextLine());
            if(start!=null)
                break;
            } catch (ParseException e) {
                System.out.println("Enter proper date");;
            }
        }
        while (true){
                System.out.println("Enter the end date in the format ddMMMYYYY");
            try {
                end = simpleDateFormat.parse(scanner.nextLine());
                endF = convertToLocalDateViaSqlDate(end);
                System.out.println(endF);
                endF=endF.plusDays(1);
                System.out.println(endF);
                end=convertToDateViaSqlDate(endF);
            System.out.println("Enter the end date in the format ddMMMYYYY");
            end = returnEndDate(scanner.nextLine());
            if(end!=null)
                break;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Date[] dateArray=new Date[2];
        dateArray[0]=start;
        dateArray[1]=end;
        return dateArray;
    }

    //UC1 basic rates
    public int uc1(Date start, Date end){
        long difference = end.getTime()-start.getTime();
        int numOfDays = (int) Math.floor(difference/(3600*24*1000));
        int bridgewood_rates=bridgewoodRates(numOfDays);
        int lakewood_rates=lakewoodRates(numOfDays);
        int ridgewood_rates=ridgewoodRates(numOfDays);
        if(lakewood_rates<bridgewood_rates&&lakewood_rates<ridgewood_rates){
            System.out.println("LakeWood , Total Rates: " + lakewood_rates);
            return lakewood_rates;
        }
        else if(bridgewood_rates<lakewood_rates&&bridgewood_rates<ridgewood_rates)
            System.out.println("BridgeWood , Total Rates: " + bridgewood_rates);
            return bridgewood_rates;
        else
            System.out.println("RidgeWood , Total Rates: " + ridgewood_rates);
            return ridgewood_rates;
    }

    public int lakewoodRates(int numOfDays){
        Lakewood lakewood=new Lakewood();
        return numOfDays*lakewood.weekdayReg;
    }
    public int bridgewoodRates(int numOfDays){
        Bridgewood bridgewood = new Bridgewood();
        return numOfDays*bridgewood.weekdayReg;
    }
    public int ridgewoodRates(int numOfDays){
        Ridgewood ridgewood = new Ridgewood();
        return numOfDays*ridgewood.weekdayReg;
    }
    public LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }
    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    public Date returnStartDate(String start){
        try {
            return simpleDateFormat.parse(start);
        } catch (ParseException e) {
            return null;
        }
    }

    public Date returnEndDate(String end){
        try {
            Date date = simpleDateFormat.parse(end);
            LocalDate localDate = convertToLocalDateViaSqlDate(date);
            localDate=localDate.plusDays(1);
            date=convertToDateViaSqlDate(localDate);
            return date;
        } catch (ParseException e) {
            return null;
        }
    }
}
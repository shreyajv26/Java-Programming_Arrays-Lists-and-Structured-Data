
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer LA = new LogAnalyzer();
        LA.readFile("weblog2_log");
        //LA.printAll();
        System.out.println("Greater than 400 are: ");
        LA.printAllHigherThanNum(400);
        ArrayList<String> days = new ArrayList<String>();
        days = LA.uniqueIPVisitsOnDay("Sep 24");
        System.out.println("Size of LA.uniqueIPVisitsOnDay is: " + days.size());
        for(int i = 0; i < days.size(); i++){
            System.out.println(days.get(i));
        }
        int count = LA.countUniqueIPsInRange(200,299);
        System.out.println("Count is: " +count);
    }
    
    public void testUniqueIP(){
        LogAnalyzer LA = new LogAnalyzer();
        LA.readFile("weblog2_log");
        int uniqueIPs = LA.countUniqueIPs();
        System.out.println("There are: " + uniqueIPs + " unique IPs");
    }
    
    public void testCounts(){
        LogAnalyzer LA = new LogAnalyzer();
        LA.readFile("weblog2_log");
        HashMap<String, Integer> counts = LA.countVisitsPerIP();
        System.out.println(counts);
        int count = LA.mostNumberVisitsByIP(counts);
        System.out.println("Most number visits by IP are: "+count);
        ArrayList<String> IPs = LA.iPsMostVisits(counts);
        System.out.println("Most visits IPs are: ");
        for(int i = 0; i < IPs.size(); i++){
            System.out.println(IPs.get(i));
        }
        HashMap<String, ArrayList<String>> ips_days = LA.iPsForDays();
        System.out.println("iPsForDays >>");
        System.out.println(ips_days);
        String day = LA.dayWithMostIPVisits(ips_days);
        System.out.println("Day with most IP visits is: "+day);
    }
    
    public void testIPsWithMostVisitsOnDay () {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> ips_days = la.iPsForDays();
        System.out.println("iPsForDays >>");
        ArrayList<String>  d = la.iPsWithMostVisitsOnDay(ips_days, "Sep 29");
        System.out.println(d);
     }
}

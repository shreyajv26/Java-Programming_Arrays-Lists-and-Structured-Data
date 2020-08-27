
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for(String line : fr.lines()){
             LogEntry entry = WebLogParser.parseEntry(line);
             records.add(entry);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs(){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for(LogEntry le : records){
             String ipAddress = le.getIpAddress();
             if(!uniqueIPs.contains(ipAddress)){
                 uniqueIPs.add(ipAddress);
             }
         }
         return uniqueIPs.size();
     }
     
     public void printAllHigherThanNum(int num){
         for(LogEntry le : records){
             int statusCode = le.getStatusCode();
             if(statusCode > num){
                 System.out.println("The statusCode greater than " + num + " are " + statusCode);
             }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         ArrayList<String> days = new ArrayList<String>();
         for(LogEntry le : records){
             Date AccessDate = le.getAccessTime();
             String DateAccessed = AccessDate.toString();
             String DateComp = DateAccessed.substring(4,10);
             //System.out.println(DateComp);
             if(DateComp.equals(someday)){
                 String ipAddress = le.getIpAddress();
                 if(!days.contains(ipAddress)){
                     days.add(ipAddress);
                 }
             }
         }
     return days;
    }
    
    public int countUniqueIPsInRange(int low, int high){
        ArrayList<String> IPs = new ArrayList<String>();
        for(LogEntry le : records){
             int statusCode = le.getStatusCode();
             //System.out.println(statusCode);
             String ipAddress = le.getIpAddress();
             if(statusCode >= low && statusCode <= high){
                 //System.out.println(statusCode);
                 if(!IPs.contains(ipAddress)){
                     //System.out.println("The statusCode in the range are " + statusCode);
                     IPs.add(ipAddress);
                 }
             }
         }
         return IPs.size();
    }
    
    public HashMap<String, Integer> countVisitsPerIP(){
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for(LogEntry le : records){
            String ip = le.getIpAddress();
            if(!counts.containsKey(ip)){
                counts.put(ip,1);
            }
            else{
                counts.put(ip,counts.get(ip)+1);
            }
        }
        return counts;
    }
    
    public int mostNumberVisitsByIP(HashMap<String, Integer> counts){
        int count = 0;
        for(String s : counts.keySet()){
            int value = counts.get(s);
            if (value > count){
                count = value;
            }
        }
        return count;
    }
    
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts){
        ArrayList<String> IPs = new ArrayList<String>();
        int count = 0;
        for(String s : counts.keySet()){
            int value = counts.get(s);
            String ip = s;
            if (value >= count){
                count = value;
                IPs.add(s);
            }
        }
        return IPs;
    }
    
    public HashMap<String, ArrayList<String>> iPsForDays(){
        HashMap<String, ArrayList<String>> ips_days = new HashMap<String, ArrayList<String>>();
        for(LogEntry le : records){
            Date AccessDate = le.getAccessTime();
            String DateAccessed = AccessDate.toString();
            String DateComp = DateAccessed.substring(4,10);
            String ip = le.getIpAddress();
            ArrayList<String> ips = new ArrayList<String>();
            if(!ips_days.containsKey(DateComp)){
                ips.add(ip);
                ips_days.put(DateComp,ips);
            }
            else{
                ips = ips_days.get(DateComp);
                ips.add(ip);
                ips_days.put(DateComp,ips);
            }
        }
        return ips_days;
    }
    
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> ips_days){
        String day = "";
        int count = 0;
        for(String s : ips_days.keySet()){
             ArrayList<String> ips = new ArrayList<String>();
             ips = ips_days.get(s);
             int num = ips.size();
             if(num > count){
                count = num;
                day = s;
             }
        }
        return day;
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> ips_days, String day){
         HashMap<String, Integer> ipCount = new HashMap<String, Integer>();
         //System.out.println(ips_days);
         ArrayList<String> iPsOnDay = ips_days.get(day);
         //System.out.println(iPsOnDay);
         for (String s : iPsOnDay) {
             if(!ipCount.containsKey(s)){
                 ipCount.put(s, 1);
             }
             else{
                 ipCount.put(s, ipCount.get(s) + 1);
             }
         }
         return iPsMostVisits(ipCount);
    }
}

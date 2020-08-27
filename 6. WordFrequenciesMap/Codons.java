
/**
 * Write a description of Codons here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class Codons {
    private HashMap <String,Integer> myMap = new HashMap <String,Integer>();
    
    public Codons(){
        myMap = new HashMap <String, Integer>();
    }
    
    public void buildCodonMap(int start, String dna){
        myMap.clear();
        dna = dna.toUpperCase();
        for(int i = start; i < dna.length()-3; i+=3){
            //"ABCABCABCABCDEFDEFDEFQWEQWE"
            String dna_sub = dna.substring(i, i+3);
            //System.out.println(dna_sub);
            //System.out.println(i);
            if (!myMap.containsKey(dna_sub)){
                myMap.put(dna_sub,1);
            }
            else{
                myMap.put(dna_sub,myMap.get(dna_sub)+1);
            }
            //start = start + 3;
        }
        for(String dna_sub : myMap.keySet()){
            int value = myMap.get(dna_sub);		
            System.out.println(value+"\t"+dna_sub);
        }
    }
    
    public String getMostCommonCodon(){
    int max = 0;
    String mostCommonCodon = "";
    for(String dna_sub : myMap.keySet()){
        int value = myMap.get(dna_sub);
        if (value > max){
            max = value;
            mostCommonCodon = dna_sub;
        }
    }
    System.out.println("The most common codon is: "+mostCommonCodon + " and count is: " +max);
    return mostCommonCodon;
    }
    
    public void printCodonCounts(int start, int end){
        for(String w : myMap.keySet()){
            int value = myMap.get(w);
            if (value >= start && value <= end){
                System.out.println(value+"\t"+w);
            }
            //total += value;
        }
        //System.out.println("total count: "+total+" different = "+map.keySet().size());
    }
    public void tester(){
        FileResource fr = new FileResource();
        String dna = fr.asString();
        dna = dna.trim();
        buildCodonMap(0,dna);
        String mostcommoncodon = getMostCommonCodon();
        System.out.println("The total number of unique codons in the reading frame is: " + myMap.size());
        System.out.println("The most common codon is: "+ mostcommoncodon + "count: " + myMap.get(mostcommoncodon));
        System.out.println("The codon counts inclusive are: ");
        printCodonCounts(4,4);
    }
}
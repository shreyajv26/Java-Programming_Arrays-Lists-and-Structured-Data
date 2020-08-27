
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;
import java.io.*;

public class WordsInFiles {
    private HashMap <String,ArrayList<String>> myMap = new HashMap <String,ArrayList<String>>();
    
    public WordsInFiles(){
        myMap = new HashMap <String, ArrayList<String>>();
        //files = new ArrayList<File>();
    }
    
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        String filename = f.getName();
	for(String w : fr.words()){
	   ArrayList<String> files = new ArrayList<String>();
	   //w = w.toLowerCase();
	   if(!myMap.containsKey(w)){
	       //files.clear();
	       files.add(filename);
	       myMap.put(w,files);
	   }
	   else{
	       ArrayList<String> currArr = myMap.get(w);
	       if(!currArr.contains(filename)){
	           currArr.add(filename);
                   files = currArr;
                   myMap.put(w,files);
	       }    
	   }
	} 
    }
    
    public void buildWordFileMap(){
        myMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber(){
        buildWordFileMap();
        int count = 0;
        for(String s : myMap.keySet()){
            ArrayList<String> currArr = myMap.get(s);
            int Size = currArr.size();
            if(Size > count){
                count = Size;
            }
        }
        System.out.println(count);
        return count;
    }
    
    public ArrayList<String> wordsInNumFiles(int number){
        buildWordFileMap();
        int count = 0;
        int total = 0;
        ArrayList<String> finalArr = new ArrayList<String>();
        
        for(String s : myMap.keySet()){
            ArrayList<String> currArr = myMap.get(s);
            count = myMap.get(s).size();
            if(count == number){
                finalArr.add(s);
                //total = total + count;
            }
        }
        //System.out.println(total);
        return finalArr;
    }
    
    public void printFilesIn(String word){
        for (String st : myMap.keySet()){
                if(st.equals(word)){
                    System.out.println(st + "\t" + myMap.get(st));
                }
        }
    }
    
    public void tester(){
            int count = 0;
            ArrayList<String> wordList = new ArrayList<String>();
            wordList = wordsInNumFiles(4);
            for (String st : wordList){
                System.out.println("The words that appear in 5 files are: " +st);
            }
            System.out.println(wordList.size());
            printFilesIn("sea");
    }
}

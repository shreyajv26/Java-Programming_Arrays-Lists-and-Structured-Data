import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap <String,ArrayList<String>> myMap = new HashMap <String,ArrayList<String>>();
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data/";
    private ArrayList<String> seenList = new ArrayList<String>();;
    private ArrayList<String> used_categories = new ArrayList<String>();
    private int count = 0;
    
    public GladLibMap(){
        //initializeFromSource(dataSourceDirectory);
        myMap = new HashMap <String, ArrayList<String>>();
        myRandom = new Random();
    }
    
    private void initializeFromSource() {
        seenList = new ArrayList<String>();
        String [] Arr_cat = {"adjective","noun","color","country","name","animal","timeframe","verb","fruit"};
        for(int i = 0; i < Arr_cat.length; i++){
            String category = Arr_cat[i];
            ArrayList<String> list = new ArrayList<String>();
            list = readIt(dataSourceDirectory+category+".txt");
            myMap.put(category,list);
        }
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    private String getSubstitute(String label) {
        for(String s : myMap.keySet()){
            if (s.equals(label)) {
                //System.out.println(randomFrom(myMap.get(s)));
                return randomFrom(myMap.get(s));
            }
            else if(label.equals("number")){
                return ""+myRandom.nextInt(50)+5;
            }
        }
        return "**UNKNOWN**";
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    public int totalWordsInMap(){
        int count = 0;
        int total = 0;
        for(String s : myMap.keySet()){
            ArrayList<String> currArr = myMap.get(s);
            count = myMap.get(s).size();
            total = total + count;
        }
        System.out.println("Total words are: "+total);
        return total;
    }
    
    public int totalWordsConsidered(){
        int total = 0;
        int count = 0;
        for(String st : used_categories){
            if(st.equals("number")){
                //System.out.println("number");
                count++;
            }
            else{
                //System.out.println(st);
                count += myMap.get(st).size();
            }
        }
        System.out.println("Total words considered are: "+count);
        return count;
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String word = w.substring(first+1,last);
        String sub = getSubstitute(w.substring(first+1,last));
        //System.out.println(w.substring(first+1,last));
        //System.out.println("Word is: "+word);
        if(!used_categories.contains(word)){
            used_categories.add(word);
        }
        int index = seenList.indexOf(sub);
        if(index == -1) {
            seenList.add(sub);
            count ++;
            return prefix+sub+suffix;
        }
        return processWord(w);
    }
    
    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 200);
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("Count is: " +count);
    }
    
    public void tester(){
        initializeFromSource();
        for(String s : myMap.keySet()){
            System.out.println(s + "\t" + myMap.get(s));
        }
        getSubstitute("timeframe");
        totalWordsInMap();
        makeStory();
        totalWordsConsidered();
    }
}
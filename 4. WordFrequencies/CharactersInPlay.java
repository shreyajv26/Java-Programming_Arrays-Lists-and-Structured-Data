
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> myNames;
    private ArrayList<Integer> myCounts;
    
    public CharactersInPlay(){
        myNames = new ArrayList<String>();
        myCounts = new ArrayList<Integer>();
    }
    
    public void update(String person){
        //myNames.clear();
        //myCounts.clear();
        person = person.trim();
        //System.out.println(person);
        int index = myNames.indexOf(person);
        //System.out.println(index);
        if(index == -1){
            myNames.add(person);
            myCounts.add(1);
        }
        else{
            int count_index = myCounts.get(index);
            myCounts.set(index,count_index+1);
        }
    }
    
    public void findAllCharacters(){
        myNames.clear();
        myCounts.clear();
        FileResource resource = new FileResource();
        for(String s : resource.lines()){
            s = s.toLowerCase();
            int index = s.indexOf('.');
            if(index != -1){
                String word = s.substring(0,index);
                update(word);
                //System.out.println(word);
            }
        }
        //System.out.println(myNames.size());
        for(int i = 0; i < myNames.size(); i++){
                System.out.println(myNames.get(i) + " " + myCounts.get(i));
        }
    }
    
    public int findMax(){
        int max = myCounts.get(0);
        int maxIndex = 0;
        for(int k=0; k < myCounts.size(); k++){
            if (myCounts.get(k) > max){
                max = myCounts.get(k);
                maxIndex = k;
            }
        }
        return maxIndex;
    }
    
    public void charactersWithNumParts(int num1, int num2){
        System.out.println("Names in the range are: ");
        for(int i = 0; i < myCounts.size(); i++){
            if(num1 <= myCounts.get(i) && myCounts.get(i) <= num2){
                System.out.println(myNames.get(i) + " " + myCounts.get(i));
            }
        }
    }
    
    public void tester(){
        findAllCharacters();
        System.out.println("# Main Characters: "+myNames.size());
        int index = findMax();
        System.out.println("max Name/Count:"+myNames.get(index)+" "+myCounts.get(index));
        charactersWithNumParts(2,100);
    }
}
